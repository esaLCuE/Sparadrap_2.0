package Swing;

import classes.Achat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static classes.Client.clients;
import static classes.FonctionsCommunes.*;

public class HistoriqueAchats extends JFrame {
    private JButton quitterButton;
    private JButton retourButton;
    private JButton consulterButton;

    DefaultListModel<String> modelListeAchats = new DefaultListModel<>();
    private JList listeAchats;
    private JPanel contentPane;

    public HistoriqueAchats(LocalDate date) {

        this.setTitle("Historique des achats");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameHistAch = new JFrame();
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

        ajoutHistorique(this);

        listeAchats.setModel(modelListeAchats);

        List<Integer> listeIndex = new ArrayList<>();

        for (int i = 0; i < Achat.getAchats().size(); i++) {
            Achat achI = Achat.getAchats().get(i);
            if(date.equals(achI.getDateAchat())) {
                if (achI.getOrdo()) {
                    modelListeAchats.addElement(achI.getDateAchatForm() + "  -  " + achI.getClientAchat().getNom()+" avec ordonnance");
                    listeIndex.add(i);
                } else {
                    modelListeAchats.addElement(achI.getDateAchatForm() + "  -  " + achI.getClientAchat().getNom()+" sans ordonnance");
                    listeIndex.add(i);
                }
            }
        }
        if (modelListeAchats.getSize()>0) {
            consulterButton.setEnabled(true);
            listeAchats.setSelectedIndex(0);
        }


        consulterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int achatChoisi = listeIndex.get(listeAchats.getSelectedIndex());
                setVisible(false);
                PageAchat pagAch = new PageAchat(achatChoisi);
                pagAch.setVisible(true);
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
