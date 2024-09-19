package Swing;

import classes.Specialiste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.FonctionsCommunes.*;
import static classes.Specialiste.domaines;
import static classes.Specialiste.specialistes;

public class ModifMedecinSpecialiste extends JFrame {
    private JPanel contentPane;
    private JTextField prenomField;
    private JTextField ndfField;
    private JTextField adresseField;
    private JTextField cpField;
    private JTextField villeField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JComboBox domaineBox;
    private JButton retourButton;
    private JButton quitterButton;
    private JButton validerButton;

    public ModifMedecinSpecialiste(int id) {

        Specialiste spe = specialistes.get(id);

        this.setTitle("Modification de médecin spécialiste");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameModSpe = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);
        ajoutHistorique(this);

        setVisible(true);

        for (int i=0;i<domaines.size();i++){
            domaineBox.addItem(domaines.get(i).toString());
        }
        domaineBox.setSelectedItem(spe.getDomaine());

        prenomField.setText(spe.getPrenom());
        ndfField.setText(spe.getNdf());
        adresseField.setText(spe.getAdresse());
        cpField.setText(spe.getCodePostal());
        villeField.setText(spe.getVille());
        telephoneField.setText(spe.getTelephone());
        emailField.setText(spe.getEmail());


        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                modifierMedecinSpecialiste(spe);

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
                quitterProgramme();
            }
        });
    }

    private void modifierMedecinSpecialiste(Specialiste spe) {
            spe.setNdf(ndfField.getText());
            spe.setPrenom(prenomField.getText());
            spe.setNom(ndfField.getText(), prenomField.getText());
            spe.setAdresse(adresseField.getText());
            spe.setCodePostal(cpField.getText());
            spe.setVille(villeField.getText());
            spe.setTelephone(telephoneField.getText());
            spe.setEmail(emailField.getText());
            spe.setDomaine(domaineBox.getSelectedItem().toString());
    }

}

