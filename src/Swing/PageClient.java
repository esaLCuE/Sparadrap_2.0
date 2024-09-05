package Swing;

import classes.MedecinTraitant;
import classes.Specialiste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static classes.Client.clients;

public class PageClient extends JFrame{
    private JList list1;
    private JList list2;
    private JButton retourButton;
    private JButton quitterButton;
    private JButton consulterButton;
    private JLabel infosClient;
    private JLabel specialistesTitre;
    private JLabel ordonnancesTitre;
    private JPanel contentPane;

    public PageClient(int id) {
        this.setTitle("Page de "+clients.get(id).getNom());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame framePageCli = new JFrame();
        setLocationRelativeTo(null);
        setResizable(true);

        Accueil.ajoutHistorique(this);


        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Accueil.precedent();

            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Accueil.precedent();
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Accueil.quitterProgramme();
            }
        });
    }
}
