package Swing;

import classes.MedecinTraitant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;

public class CreerMedecinTraitant extends JFrame {
    private JPanel contentPane;
    private JTextField ndfField;
    private JTextField prenomField;
    private JTextField adresseField;
    private JTextField cpField;
    private JTextField villeField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField agrementField;
    private JButton validerButton;
    private JButton quitterButton;
    private JButton retourButton;

    public CreerMedecinTraitant() {
        this.setTitle("Création de médecin traitant");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameCreTra = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        ajoutHistorique(this);


        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enregistrerMedecinTraitant();
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

    private void enregistrerMedecinTraitant(){
        try{
            MedecinTraitant medTra = new MedecinTraitant(ndfField.getText(), prenomField.getText(), adresseField.getText(),
                    cpField.getText(), villeField.getText(), telephoneField.getText(), emailField.getText(), agrementField.getText());
            for (int i=0;i<medecinsTraitants.size();i++){
                if (medTra.getAgrement().equals(medecinsTraitants.get(i).getAgrement())){
                    throw new IllegalArgumentException("Un médecin avec ce numéro d'agrément existe déjà.");
                }
            }

            // TODO : insert into MedecinTraitant medTra
            setVisible(false);
            suppHistorique();

            ConsulterMedecins consMed = new ConsulterMedecins();
            consMed.setVisible(true);
        } catch (Exception e) {
            afficherErreur(e.getMessage());

        }
    }
}
