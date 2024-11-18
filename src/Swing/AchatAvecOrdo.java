package Swing;

import classes.Achat;
import classes.Client;
import classes.Medicament;
import classes.Specialiste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static classes.Achat.ajoutAchat;
import static classes.Client.clients;
import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Medicament.medicaments;
import static classes.Specialiste.specialistes;

public class AchatAvecOrdo extends JFrame {

    private JComboBox specOrdoBox;
    private JComboBox medicamentsBox;
    private JComboBox clientOrdoBox;
    private JComboBox jourBox;
    private JComboBox moisBox;
    private JComboBox anneeBox;
    private JButton retourButton;
    private JButton quitterButton;
    private JButton validerButton;
    private JButton retirerMedicamentButton;
    private JButton ajouterMedicamentButton;
    private JPanel contentPane;
    private JComboBox quantiteBox;
    Specialiste specOrdo;

    private List<Medicament> tsMedics = new ArrayList<>();
    private List<Integer> ttesQtt = new ArrayList<>();

    DefaultListModel<String> modelMedicamentsList = new DefaultListModel<>();
    private JList medicamentsList;

    boolean ordo =true;
    List<Client> clientsPossibles = new ArrayList<>();

    public AchatAvecOrdo() {

        medicamentsList.setModel(modelMedicamentsList);

        this.setTitle("Achat avec ordonnance");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameAchAOrd = new JFrame();
        setLocationRelativeTo(null);
        setResizable(true);

        ajoutHistorique(this);
        setVisible(true);


        for(int i=1;i<=31;i++){
            jourBox.addItem(i);
        }
        for(int i=1;i<=12;i++){
            moisBox.addItem(i);
        }
        for(int i=2024;i>=0;i--){
            anneeBox.addItem(i);
        }
        jourBox.setSelectedIndex(0);
        moisBox.setSelectedIndex(0);
        anneeBox.setSelectedIndex(0);

        for(int i=0;i<clients.size();i++) {
            if (clients.get(i).getMedecinTraitant() != null) {
                clientOrdoBox.addItem(clients.get(i).getNom() + " - " + clients.get(i).getSecuSociale());
                clientsPossibles.add(clients.get(i));
            }
        }
        clientOrdoBox.setSelectedIndex(0);

        specOrdoBox.addItem("");
        for(int i=0;i<specialistes.size();i++){
            specOrdoBox.addItem(specialistes.get(i).getNom()+" - "+specialistes.get(i).getDomaine());
        }
        specOrdoBox.setSelectedIndex(0);

        for(int i=0;i<medicaments.size();i++){
            medicamentsBox.addItem(medicaments.get(i).getNom()+" - "+medicaments.get(i).getCategorie());
        }
        medicamentsBox.setSelectedIndex(0);
        for(int i=1;i<=10;i++){
            quantiteBox.addItem(i);
        }
        quantiteBox.setSelectedIndex(0);

        specOrdoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(specOrdoBox.getSelectedIndex()!=0) {
                    specOrdo = specialistes.get(specOrdoBox.getSelectedIndex()-1);
                } else {
                    specOrdo = null;
                }
            }
        });

        ajouterMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tsMedics.size()==0){
                    validerButton.setEnabled(true);
                    retirerMedicamentButton.setEnabled(true);
                }
                String medNom = medicamentsBox.getSelectedItem().toString();
                int medQtt = Integer.parseInt(quantiteBox.getSelectedItem().toString());
                modelMedicamentsList.addElement(medNom+" x"+medQtt);
                tsMedics.add(medicaments.get(medicamentsBox.getSelectedIndex()));
                ttesQtt.add(Integer.parseInt(quantiteBox.getSelectedItem().toString()));
            }
        });
        retirerMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(medicamentsList.getSelectedIndex()==-1){
                    medicamentsList.setSelectedIndex(tsMedics.size()-1);
                }
                tsMedics.remove(medicamentsList.getSelectedIndex());
                ttesQtt.remove(medicamentsList.getSelectedIndex());
                modelMedicamentsList.removeElementAt(medicamentsList.getSelectedIndex());
                if (tsMedics.size()==0){
                    validerButton.setEnabled(false);
                    retirerMedicamentButton.setEnabled(false);
                }
            }
        });

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    enregistrerAchat();
                    setVisible(false);
                    precedent();
                } catch (Exception exc){
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

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                precedent();
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitterProgramme();
            }
        });
    }

    private void enregistrerAchat(){
        Achat ach = new Achat(clientsPossibles.get(clientOrdoBox.getSelectedIndex()),
                    LocalDate.of(Integer.parseInt(anneeBox.getSelectedItem().toString()),
                            Integer.parseInt(moisBox.getSelectedItem().toString()),
                            Integer.parseInt(jourBox.getSelectedItem().toString())));

        // TODO : insert into Achat ach
        // TODO : boucle for pour insert into Comporte
    }
}
