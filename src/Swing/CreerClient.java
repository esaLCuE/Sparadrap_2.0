package Swing;

import classes.Client;
import classes.Specialiste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static classes.Client.clients;
import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Mutuelle.mutuelles;
import static classes.Specialiste.specialistes;

public class CreerClient extends JFrame{
    private JPanel contentPane;
    private JButton retourButton;
    private JButton quitterButton;
    private JButton validerButton;
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

    private int jour=1;
    private int mois=1;
    private int annee=2024;

    DefaultListModel<String> modelSpec = new DefaultListModel<>();
    private JList<String> listeSpecClient = new JList<>(modelSpec);

    private JButton retirerSpecialisteButton;

    List <Specialiste> specialistesCli;

    public CreerClient() {
        this.setTitle("Création du client");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameCreCli = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        List <Integer> indexSpec = new ArrayList<>();
        specialistesCli = new ArrayList<>();

        for (int i =0; i<specialistes.size();i++){
            specBox.addItem(specialistes.get(i).getNom()+" - "+specialistes.get(i).getDomaine());
        }

        for (int i =1; i<=31;i++){
            jourBox.addItem(i);
        }
        for (int i =1; i<=12;i++){
            moisBox.addItem(i);
        }
        for (int i =2024; i>=1900;i--){
            anneeBox.addItem(i);
        }

        for (classes.Mutuelle mutuelle : mutuelles) {
            mutuelleBox.addItem(mutuelle.getNom());
        }

        for (classes.MedecinTraitant medecinsTraitant : medecinsTraitants) {
            medTraitBox.addItem(medecinsTraitant.getNom());
        }

        anneeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annee = Integer.parseInt(anneeBox.getSelectedItem().toString());
            }
        });
        moisBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mois = Integer.parseInt(moisBox.getSelectedItem().toString());
            }
        });
        jourBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jour = Integer.parseInt(jourBox.getSelectedItem().toString());
            }
        });

        ajouterSpecialisteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String specChoisie = specBox.getSelectedItem().toString();
                if (!modelSpec.contains(specChoisie)) {
                    modelSpec.addElement(specBox.getSelectedItem().toString());
                    specialistesCli.add(specialistes.get(specBox.getSelectedIndex()));
                    indexSpec.add(specBox.getSelectedIndex());
                }
            }
        });
        retirerSpecialisteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelSpec.removeElement(listeSpecClient.getSelectedIndex());
                indexSpec.remove(specBox.getSelectedIndex());
                specialistesCli.remove(specialistes.get(specBox.getSelectedIndex()));
            }
        });

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enregistrerClient();
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

    private void enregistrerClient() {
        try {
            Client cli = new Client(ndfField.getText(), prenomField.getText(), adresseField.getText(), cpField.getText(),
                    villeField.getText(), telephoneField.getText(), emailField.getText(), secuSocField.getText(),
                    LocalDate.of(annee, mois, jour), mutuelles.get(mutuelleBox.getSelectedIndex()),
                    medecinsTraitants.get(medTraitBox.getSelectedIndex()), specialistesCli);

            clients.add(cli);

            setVisible(false);
            suppHistorique();

            ConsulterClient cli2 = new ConsulterClient();
        } catch (DateTimeException dte){
            afficherErreur("Date invalide");
        } catch (Exception e) {
            afficherErreur(e.getMessage());
        }
    }
}
