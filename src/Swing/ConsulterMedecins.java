package Swing;

import classes.MedecinTraitant;
import classes.Specialiste;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static classes.FonctionsCommunes.*;

public class ConsulterMedecins extends JFrame {

    private JComboBox<String> medecinsBox;
    private JPanel contentPane;
    private JButton precedentButton;
    private JButton quitterButton;
    private JButton visualiserButton;
    private JButton ajouterButton;
    private JButton supprimerButton;


    public ConsulterMedecins(List<MedecinTraitant> medecins, List<Specialiste> specialistes) {

        this.setTitle("Choix du médecin");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame frameMedTrait = new JFrame();
        setLocationRelativeTo(null);
        setResizable(false);

        ajoutHistorique(this);

        for (MedecinTraitant medecin : medecins) {
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
                int i;
                for(i = 0; i < medecins.size(); i++){
                    if(medecins.get(i).getAgrement().equals(agrSpecTemp)){
                        pos = "med";
                        int medecinIndex = i;
                        break;
                    }
                }
                if (!pos.equals("med")){
                    for(i = 0; i < specialistes.size(); i++){
                        if(specialistes.get(i).getDomaine().equals(agrSpecTemp)){
                            pos = "spec";
                            int medecinIndex = i;
                            break;
                        }
                    }
                }
                setVisible(false);
                PageMedecin pageMed = new PageMedecin(pos, i);
                pageMed.setVisible(true);



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
