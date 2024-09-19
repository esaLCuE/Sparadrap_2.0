package Swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static classes.FonctionsCommunes.*;
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

    public static List<JFrame> frames = new ArrayList<>();

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

        achatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                int input = JOptionPane.showConfirmDialog(null, "Y a-t-il une ordonnance pour cet achat ?",
                        "Ordonnance", JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == JOptionPane.YES_OPTION) {
                    AchatAvecOrdo achat = new AchatAvecOrdo();
                } else if (input == JOptionPane.NO_OPTION) {
                    AchatSansOrdo achat = new AchatSansOrdo();
                } else {
                    setVisible(true);
                }
            }
        });

        historiqueAchatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                SelectionDateAchat selDatAch = new SelectionDateAchat();
                selDatAch.setVisible(true);
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
        ConsulterMedecins consMed = new ConsulterMedecins();
        consMed.setVisible(true);
    }
    public List<JFrame> getFrame() {
        return frames;
    }

}