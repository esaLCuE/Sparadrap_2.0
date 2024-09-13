package Swing;

import classes.Achat;
import classes.Medicament;
import classes.Specialiste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static classes.Client.clients;
import static classes.FonctionsCommunes.*;
import static classes.Medicament.medicaments;
import static classes.Specialiste.specialistes;

public class ModifAchatAvecOrdo extends JFrame {
    private JButton quitterButton;
    private JButton retourButton;
    private JButton validerButton;
    private JComboBox jourBox;
    private JComboBox moisBox;
    private JComboBox anneeBox;
    private JComboBox quantiteBox;
    private JComboBox specOrdoBox;
    private JComboBox clientOrdoBox;
    private JComboBox medicamentsBox;
    private JButton ajouterMedicamentButton;
    private JButton retirerMedicamentButton;
    private JPanel contentPane;

    Specialiste specOrdo;

    private List<Medicament> tsMedics = new ArrayList<>();
    private List<Integer> ttesQtt = new ArrayList<>();

    DefaultListModel<String> modelMedicamentsList = new DefaultListModel<>();
    private JList medicamentsList;
    private JButton sansOrdonnanceButton;


    public ModifAchatAvecOrdo(Achat achId) {

        medicamentsList.setModel(modelMedicamentsList);

        this.setTitle("Modification de l'achat");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameAchAOrd = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        for(int i=1;i<=31;i++){
            jourBox.addItem(i);
        }
        for(int i=1;i<=12;i++){
            moisBox.addItem(i);
        }
        for(int i=2024;i>=0;i--){
            anneeBox.addItem(i);
        }
        jourBox.setSelectedItem(achId.getDateAchat().getDayOfMonth());
        moisBox.setSelectedItem(achId.getDateAchat().getMonthValue());
        anneeBox.setSelectedItem(achId.getDateAchat().getYear());

        for(int i=0;i<clients.size();i++){
            clientOrdoBox.addItem(clients.get(i).getNom()+" - "+clients.get(i).getSecuSociale());
        }
        clientOrdoBox.setSelectedItem(achId.getClientAchat().getNom()+" - "+achId.getClientAchat().getSecuSociale());

        specOrdoBox.addItem("");
        for(int i=0;i<specialistes.size();i++){
            specOrdoBox.addItem(specialistes.get(i).getNom()+" - "+specialistes.get(i).getDomaine());
        }
        if(achId.getSpecialisteAchat()!=null) {
            specOrdoBox.setSelectedItem(achId.getSpecialisteAchat().getNom() + " - " + achId.getSpecialisteAchat().getDomaine());
        }

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

        for (int i=0;i<achId.getListeMedicaments().size();i++){
            String medNom = achId.getListeMedicaments().get(i).getNom()+" - "+achId.getListeMedicaments().get(i).getCategorie();
            int medQtt = achId.getListeQuantites().get(i);
            modelMedicamentsList.addElement(medNom+" x"+medQtt);
            tsMedics.add(medicaments.get(medicamentsBox.getSelectedIndex()));
            ttesQtt.add(Integer.parseInt(quantiteBox.getSelectedItem().toString()));
        }
        validerButton.setEnabled(true);
        retirerMedicamentButton.setEnabled(true);

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
                modifierAchatAvecOrdo(achId);
                setVisible(false);
                suppHistorique();
                suppHistorique();

                precedent();
            }
        });

        sansOrdonnanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ModifAchatSansOrdo modAchSans = new ModifAchatSansOrdo(achId);
                modAchSans.setVisible(true);
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
    private void modifierAchatAvecOrdo(Achat achId) {
        try {
            achId.setClientAchat(clients.get(clientOrdoBox.getSelectedIndex()));
            achId.setDateAchat(LocalDate.of((Integer) anneeBox.getSelectedItem(), (Integer) moisBox.getSelectedItem(), (Integer) jourBox.getSelectedItem()));
            achId.setSpecialisteAchat(specOrdo);
            achId.setMedecinTraitant(clients.get(clientOrdoBox.getSelectedIndex()).getMedecinTraitant());
            achId.setListeMedicaments(tsMedics);
            achId.setListeQuantites(ttesQtt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
