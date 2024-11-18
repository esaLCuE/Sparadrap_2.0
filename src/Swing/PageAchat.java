package Swing;

import classes.Achat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.Achat.getAchats;
import static classes.FonctionsCommunes.*;

public class PageAchat extends JFrame {
    private JButton quitterButton;
    private JButton retourButton;
    private JButton modifierButton;
    private JPanel contentPane;
    private JLabel detailAchatLabel;
    private JButton supprimerAchatButton;

    public PageAchat(int id){

        this.setTitle("Détails de l'achat");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);

        JFrame framePageAch = this;
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        this.pack();

        ajoutHistorique(this);

        Achat achId = Achat.getAchats().get(id);
        String texte;
        float total=0;
        float prix;
        if(achId.getOrdo()) {
            if (achId.getSpecialisteAchat() != null) {
            texte = (achId.getDateAchatForm() + "\n" + achId.getClientAchat().getNom() + "\n" + "Prescrit par : " +
                    achId.getClientAchat().getMedecinTraitant().getNom() + " et " + achId.getSpecialisteAchat().getNom() + "\n");

            } else {
                texte = (achId.getDateAchatForm() + "\n" + achId.getClientAchat().getNom() + "\n" + "Prescrit par : " +
                        achId.getClientAchat().getMedecinTraitant().getNom() +"\n");
            }
        } else {
            texte = (achId.getDateAchatForm() + "\n" + achId.getClientAchat().getNom()+ "\n");
        }

        /*
        for(int i=0;i<achId.getListeMedicaments().size();i++){
            prix = achId.getListeMedicaments().get(i).getPrix()*achId.getListeQuantites().get(i);
            texte=texte+achId.getListeMedicaments().get(i).getNom()+ " x" +achId.getListeQuantites().get(i) +
                    " : " + prix +"€" + "\n";
            total+=prix;
        }
        */
        // TODO : remplacer le getListe par récup de la table Contient

        texte+="Total : "+total+"€";
        detailAchatLabel.setText("<html>" + texte.replaceAll("\n", "<br/>") + "</html>");



        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ModifAchatAvecOrdo modAch = new ModifAchatAvecOrdo(achId);
                modAch.setVisible(true);
            }
        });

        supprimerAchatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimer(framePageAch,"cet achat",id,getAchats(),2, "achat", achId.getDateAchat());
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
