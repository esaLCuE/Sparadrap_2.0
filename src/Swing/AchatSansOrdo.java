package Swing;

import classes.Achat;
import classes.Medicament;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static classes.Achat.ajoutAchat;
import static classes.Client.clients;
import static classes.FonctionsCommunes.*;
import static classes.Medicament.medicaments;

public class AchatSansOrdo extends JFrame {

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

    private List<Medicament> tsMedics = new ArrayList<>();
    private List<Integer> ttesQtt = new ArrayList<>();

    DefaultListModel<String> modelMedicamentsList = new DefaultListModel<>();
    private JList medicamentsList;
    private JComboBox quantiteBox;

    boolean ordo = false;

    public AchatSansOrdo() {

        medicamentsList.setModel(modelMedicamentsList);

        this.setTitle("Achat sans ordonnance");

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

        for(int i=0;i<clients.size();i++){
            clientOrdoBox.addItem(clients.get(i).getNom()+" - "+clients.get(i).getSecuSociale());
        }
        clientOrdoBox.setSelectedIndex(0);

        for(int i=0;i<medicaments.size();i++){
            medicamentsBox.addItem(medicaments.get(i).getNom()+" - "+medicaments.get(i).getCategorie());
        }
        medicamentsBox.setSelectedIndex(0);
        for(int i=1;i<=10;i++){
            quantiteBox.addItem(i);
        }
        quantiteBox.setSelectedIndex(0);

        ajouterMedicamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tsMedics.isEmpty()){
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
                if (tsMedics.isEmpty()){
                    validerButton.setEnabled(false);
                    retirerMedicamentButton.setEnabled(false);
                }
            }
        });

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enregistrerAchat();
                setVisible(false);
                precedent();
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
        try {
            Achat ach = new Achat(clients.get(clientOrdoBox.getSelectedIndex()),
                    LocalDate.of(Integer.parseInt(anneeBox.getSelectedItem().toString()),
                                Integer.parseInt(moisBox.getSelectedItem().toString()),
                                Integer.parseInt(jourBox.getSelectedItem().toString())));

            // TODO : insert into Achat ach
            // TODO : boucle for pour insert into Comporte

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
