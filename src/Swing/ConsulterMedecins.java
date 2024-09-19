package Swing;

import classes.MedecinTraitant;
import classes.Specialiste;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Specialiste.specialistes;

public class ConsulterMedecins extends JFrame {

    private JComboBox<String> medecinsBox;
    private JPanel contentPane;
    private JButton precedentButton;
    private JButton quitterButton;
    private JButton visualiserButton;
    private JButton ajouterMedecinButton;


    public ConsulterMedecins() {

        this.setTitle("Choix du médecin");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameMedTrait = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        ajoutHistorique(this);

        for (MedecinTraitant medecin : medecinsTraitants) {
            medecinsBox.addItem(medecin.getNom()+" - "+medecin.getAgrement());
        }
        for (Specialiste specialiste : specialistes) {
            medecinsBox.addItem(specialiste.getNom()+" - "+specialiste.getDomaine());
        }

        visualiserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String medecinChoisi = (String) medecinsBox.getSelectedItem();
                String agrSpecTemp = medecinChoisi.replaceAll("^.*(- )","");
                String pos="";
                int medecinIndex=0;
                int i;
                for(i = 0; i < medecinsTraitants.size(); i++){
                    if(medecinsTraitants.get(i).getAgrement().equals(agrSpecTemp)){
                        pos = "med";
                        medecinIndex = i;
                        break;
                    }
                }
                if (!pos.equals("med")){
                    medecinIndex = medecinsBox.getSelectedIndex()-medecinsTraitants.size();
                }
                setVisible(false);
                PageMedecin pageMed = new PageMedecin(pos, medecinIndex);
                pageMed.setVisible(true);

            }
        });

        ajouterMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Object[] options1 = {"Spécialiste", "Traitant","Annuler"};
                JPanel panel = new JPanel();
                panel.add(new JLabel("Quel type de médecin ajouter ?"));

                int choix = JOptionPane.showOptionDialog(null, panel, "Choix médecin", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, options1, null);
                if (choix == JOptionPane.YES_OPTION) {
                    CreerMedecinSpecialiste creSpe = new CreerMedecinSpecialiste();
                } else if (choix == JOptionPane.NO_OPTION) {
                    CreerMedecinTraitant creTra = new CreerMedecinTraitant();
                    creTra.setVisible(true);
                } else {
                    setVisible(true);
                }
            }
        });

        precedentButton.addActionListener(new ActionListener() {
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
}
