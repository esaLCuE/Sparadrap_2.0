package Swing;

import classes.Client;
import classes.Specialiste;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static classes.Client.clients;
import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Mutuelle.mutuelles;
import static classes.Specialiste.specialistes;

public class ModifClient extends JFrame {
    private JPanel contentPane;
    private JTextField prenomField;
    private JTextField ndfField;
    private JTextField adresseField;
    private JTextField cpField;
    private JTextField villeField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField secuSocField;
    private JComboBox mutuelleBox;
    private JComboBox medTraitBox;
    private JComboBox jourBox;
    private JComboBox moisBox;
    private JComboBox anneeBox;
    private JComboBox specBox;
    private JButton ajouterSpecialisteButton;
    private JButton retirerSpecialisteButton;
    private JButton quitterButton;
    private JButton retourButton;
    private JButton validerModifsButton;

    List<Specialiste> specialistesCliModif;

    DefaultListModel<String> modelSpecModif = new DefaultListModel<>();
    private JList listeSpecClient;

    public ModifClient(int id) {
        Client cli = clients.get(id);
        specialistesCliModif = new ArrayList<>();
        List <Integer> indexSpecModif = new ArrayList<>();
        listeSpecClient.setModel(modelSpecModif);

        this.setTitle("Modification de "+clients.get(id).getNom());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameModifCli = new JFrame();
        setLocationRelativeTo(null);
        setResizable(true);

        ajoutHistorique(this);

        prenomField.setText(cli.getPrenom());
        ndfField.setText(cli.getNdf());
        adresseField.setText(cli.getAdresse());
        cpField.setText(cli.getCodePostal());
        villeField.setText(cli.getVille());
        telephoneField.setText(cli.getTelephone());
        emailField.setText(cli.getEmail());
        secuSocField.setText(cli.getSecuSociale());

        for (int i =1; i<=31;i++){
            jourBox.addItem(i);
        }
        for (int i =1; i<=12;i++){
            moisBox.addItem(i);
        }
        for (int i =2024; i>=1900;i--){
            anneeBox.addItem(i);
        }

        jourBox.setSelectedItem(cli.getDateNaissanceOri().getDayOfMonth());
        moisBox.setSelectedItem(cli.getDateNaissanceOri().getMonthValue());
        anneeBox.setSelectedItem(cli.getDateNaissanceOri().getYear());

        /*
        for (classes.Mutuelle mutuelle : mutuelles) {
            mutuelleBox.addItem(mutuelle.getNom());
        }
        // TODO : remplacer par un appel de la table Mutuelle
         */

        /*
        for (classes.MedecinTraitant medecinsTraitant : medecinsTraitants) {
            medTraitBox.addItem(medecinsTraitant.getNom());
        }
        // TODO : remplacer par un appel de la table MedecinTraitant
         */

        mutuelleBox.setSelectedItem(cli.getMutuelle().getNom());
        medTraitBox.setSelectedItem(cli.getMedecinTraitant());

        /*
        for (int i =0; i<specialistes.size();i++){
            specBox.addItem(specialistes.get(i).getNom()+" - "+specialistes.get(i).getDomaine());
        }
        for (int i=0;i<cli.getSpecialistesClient().size();i++){
            modelSpecModif.addElement(cli.getSpecialistesClient().get(i).getNom()+" - "+cli.getSpecialistesClient().get(i).getDomaine());
            specialistesCliModif.add(i,cli.getSpecialistesClient().get(i));
        }
        // TODO : remplacer par appel de la table Specialiste
         */

        ajouterSpecialisteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String specChoisie = specBox.getSelectedItem().toString();
                if (!modelSpecModif.contains(specChoisie)) {
                    modelSpecModif.addElement(specBox.getSelectedItem().toString());
                    /*
                    specialistesCliModif.add(specialistes.get(specBox.getSelectedIndex()));
                    TODO :  mettre à la fin et remplacer par un ajout dans la table Suit selon ensemble modifs
                     */
                }
            }
        });
        retirerSpecialisteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listeSpecClient.getSelectedIndex()!=-1) {
                    specialistesCliModif.remove(listeSpecClient.getSelectedIndex());
                    /*
                    modelSpecModif.removeElementAt(listeSpecClient.getSelectedIndex());
                    TODO : mettre à la fin et remplacer par suppression dans la table Suit selon ensemble modifs
                     */
                }
            }
        });


        validerModifsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    enregistrerModifsClient(cli);

                    setVisible(false);
                    suppHistorique();
                    suppHistorique();
                    suppHistorique();

                    ConsulterClient cli2 = new ConsulterClient();
                } catch(Exception exc) {
                    afficherErreur(exc.getMessage());
                }
            }
        });

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                precedent();

            }
        });

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitterProgramme();
            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                precedent();
            }
        });

    }
    public void enregistrerModifsClient(Client cli){
        /*
        cli.setNdf(ndfField.getText());
        cli.setPrenom(prenomField.getText());
        cli.setNom(ndfField.getText(), prenomField.getText());
        cli.setAdresse(adresseField.getText());
        cli.setCodePostal(cpField.getText());
        cli.setVille(villeField.getText());
        cli.setTelephone(telephoneField.getText());
        cli.setEmail(emailField.getText());
        cli.setSecuSociale(secuSocField.getText());
        cli.setDateNaissance(LocalDate.of(Integer.parseInt(anneeBox.getSelectedItem().toString()),
                Integer.parseInt(moisBox.getSelectedItem().toString()), Integer.parseInt(jourBox.getSelectedItem().toString())));
        cli.setMutuelle(mutuelles.get(mutuelleBox.getSelectedIndex()));
        cli.setMedecinTraitant(medecinsTraitants.get(medTraitBox.getSelectedIndex()));
        TODO : update Client set [...] where [...] ; modifier table Suit
         */
    }
}
