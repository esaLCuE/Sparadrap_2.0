package Swing;

import classes.MedecinTraitant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Specialiste.specialistes;

public class Accueil extends JFrame {
    private JButton achatButton;
    private JButton historiqueAchatsButton;
    private JButton consulterMedecinButton;
    private JButton consulterClientsButton;
    private JButton quitterButton;
    private JButton precedentButton;
    private JPanel contentPane;

    private static List<JFrame> frames = new ArrayList<>();

    public Accueil(){

        this.setTitle("Menu principal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        setVisible(true);

        JFrame frameMenu = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        ajoutHistorique(this);

        /*precedentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        */

        consulterClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                voirClients();
            }
        });

        consulterMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                voirMedecins();
            }
        });

        precedentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public void voirClients(){
        ConsulterClient consCli = new ConsulterClient();
        consCli.setVisible(true);
    }

    public void voirMedecins() {
        try {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConsulterMedecins consMed = new ConsulterMedecins(medecinsTraitants, specialistes);
        consMed.setVisible(true);
    }


    public static void quitterProgramme(){
        System.exit(0);
    }

    public static void precedent(){
        if (frames.size()>1) {
            frames.get(frames.size() - 2).setVisible(true);
            frames.removeLast();
        }
    }

    public static void ajoutHistorique(JFrame frame){
        frames.add(frames.size(), frame);
    }
}
