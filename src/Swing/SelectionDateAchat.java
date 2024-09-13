package Swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static classes.FonctionsCommunes.*;

public class SelectionDateAchat extends JFrame {
    private JButton quitterButton;
    private JButton retourButton;
    private JButton validerButton;
    private JComboBox jourBox;
    private JComboBox anneeBox;
    private JComboBox moisBox;
    private JPanel contentPane;

    public SelectionDateAchat() {

        this.setTitle("Choix de la date");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameChoixDate = new JFrame();
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

        ajoutHistorique(this);

        for (int i =1; i<=31;i++){
            jourBox.addItem(i);
        }
        for (int i =1; i<=12;i++){
            moisBox.addItem(i);
        }
        for (int i =2024; i>=1900;i--){
            anneeBox.addItem(i);
        }

        jourBox.setSelectedIndex(0);
        moisBox.setSelectedIndex(0);
        anneeBox.setSelectedIndex(0);

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateForm = jourBox.getSelectedItem().toString()+"-"+moisBox.getSelectedItem().toString()+"-"+anneeBox.getSelectedItem().toString();
                LocalDate date = LocalDate.of((Integer) anneeBox.getSelectedItem(), (Integer)moisBox.getSelectedItem(),(Integer)jourBox.getSelectedItem());
                setVisible(false);
                HistoriqueAchats histAch = new HistoriqueAchats(date);
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
}
