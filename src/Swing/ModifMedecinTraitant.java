package Swing;

import classes.MedecinTraitant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;

public class ModifMedecinTraitant extends JFrame {

    private JPanel contentPane;
    private JTextField adresseField;
    private JTextField cpField;
    private JTextField villeField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField agrementField;
    private JButton validerButton;
    private JButton quitterButton;
    private JButton retourButton;
    private JTextField ndfField;
    private JTextField prenomField;

    public ModifMedecinTraitant(int id) {

        MedecinTraitant med = medecinsTraitants.get(id);
        this.setTitle("Modification de médecin traitant");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameModTra = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        ajoutHistorique(this);
        this.setVisible(true);

        ndfField.setText(med.getNdf());
        prenomField.setText(med.getPrenom());
        adresseField.setText(med.getAdresse());
        emailField.setText(med.getEmail());
        cpField.setText(med.getCodePostal());
        villeField.setText(med.getVille());
        telephoneField.setText(med.getTelephone());
        agrementField.setText(med.getAgrement());

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                modifierMedecinTraitant(med);

                setVisible(false);
                suppHistorique();
                suppHistorique();
                suppHistorique();

                ConsulterMedecins consMed = new ConsulterMedecins();
                consMed.setVisible(true);

            } catch (Exception ex) {
                afficherErreur(ex.getMessage());
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
                System.exit(0);
            }
        });
    }

    private void modifierMedecinTraitant(MedecinTraitant med) {
            med.setNdf(ndfField.getText());
            med.setPrenom(prenomField.getText());
            med.setNom(ndfField.getText(), prenomField.getText());
            med.setEmail(emailField.getText());
            med.setAdresse(adresseField.getText());
            med.setCodePostal(cpField.getText());
            med.setVille(villeField.getText());
            med.setTelephone(telephoneField.getText());
            med.setAgrement(agrementField.getText());
    }
}
