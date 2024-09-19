package Swing;

import classes.Specialiste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.FonctionsCommunes.*;
import static classes.Specialiste.domaines;
import static classes.Specialiste.specialistes;

public class CreerMedecinSpecialiste extends JFrame {
    private JPanel contentPane;
    private JTextField prenomField;
    private JTextField ndfField;
    private JTextField adresseField;
    private JTextField cpField;
    private JTextField villeField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JComboBox<String> domaineBox;
    private JButton retourButton;
    private JButton quitterButton;
    private JButton validerButton;

    public CreerMedecinSpecialiste() {


        this.setTitle("Création de médecin spécialiste");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameCreSpe = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);
        ajoutHistorique(this);

        setVisible(true);

        for (int i=0;i<domaines.size();i++){
            domaineBox.addItem(domaines.get(i).toString());
        }
        domaineBox.setSelectedIndex(0);

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enregistrerMedecinSpecialiste();
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
                System.exit(0);
            }
        });
    }

    public void enregistrerMedecinSpecialiste() {
        try {
            Specialiste medSpe = new Specialiste(ndfField.getText(), prenomField.getText(), adresseField.getText(),
                    cpField.getText(), villeField.getText(), telephoneField.getText(), emailField.getText(), domaineBox.getSelectedItem().toString());

            for (int i=0;i<specialistes.size();i++){
                if ((specialistes.get(i).getNom()+specialistes.get(i).getDomaine()).equalsIgnoreCase(medSpe.getNom()+medSpe.getDomaine())){
                    throw new IllegalArgumentException("Un médecin de ce nom et de cette spécialité existe déjà.");
                }
            }

            specialistes.add(medSpe);
            setVisible(false);
            suppHistorique();
            suppHistorique();

            ConsulterMedecins consMed = new ConsulterMedecins();
            consMed.setVisible(true);
        } catch (Exception e) {
            afficherErreur(e.getMessage());
        }
    }
}