package Swing;

import classes.Achat;
import classes.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import classes.FonctionsCommunes;

import static classes.Client.clients;
import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Specialiste.specialistes;
import static javax.swing.UIManager.get;

public class PageClient extends JFrame{
    private JButton retourButton;
    private JButton quitterButton;
    private JButton consulterAchatButton;
    private JLabel infosClient;
    private JLabel specialistesTitre;
    private JLabel achatsTitre;
    private JPanel contentPane;
    private JButton suppClientButton;
    private JButton modifierClientButton;

    DefaultListModel<String> modelSpecClient = new DefaultListModel<>();
    private JList specList;

    DefaultListModel<String> modelOrd = new DefaultListModel<>();
    private JList achatsList;
    private JButton consulterMedecinOuSpecialisteButton;

    public PageClient(int id) {

        Client cli = clients.get(id);

        try {
            specList.setModel(modelSpecClient);
            achatsList.setModel(modelOrd);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        this.setTitle("Page de "+clients.get(id).getNom());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame framePageCli = this;
        setLocationRelativeTo(null);
        setResizable(true);

        ajoutHistorique(this);

        String texte = (cli.getNom() + " - " + cli.getSecuSociale() + "\n" + "Né le " + cli.getDateNaissance()
                + "\n" + cli.getAdresse() + " " + cli.getCodePostal() + " " + cli.getVille() + "\n" + "Médecin traitant : " +
                cli.getMedecinTraitant().getNom() + "\n" + cli.getMutuelle() + "\n" + "Contacts : " + cli.getEmail() + " - " + cli.getTelephone());
        infosClient.setText("<html>" + texte.replaceAll("\n", "<br/>") + "</html>");

        List<Integer> indSpec = new ArrayList<>();
        indSpec.add(-1);
        modelSpecClient.addElement(cli.getMedecinTraitant().getNom()+" - "+cli.getMedecinTraitant().getAgrement());
        for (int i=0;i<cli.getSpecialistesClient().size();i++) {
            String str = cli.getSpecialistesClient().get(i).getNom()+" - "+cli.getSpecialistesClient().get(i).getDomaine();
            indSpec.add(specialistes.indexOf(cli.getSpecialistesClient().get(i)));
            modelSpecClient.addElement(str);
        }
        specList.setSelectedIndex(0);

        List<Integer> indAch = new ArrayList<>();
        for (int i=0;i< Achat.getAchats().size();i++) {
            if(Achat.getAchats().get(i).getClientAchat().getNom().equals(cli.getNom())){
                if(Achat.getAchats().get(i).getMedecinTraitant()!=null){
                    modelOrd.addElement(Achat.getAchats().get(i).getDateAchatForm()+" avec ordonnance");
                } else {
                    modelOrd.addElement(Achat.getAchats().get(i).getDateAchatForm()+" sans ordonnance");
                }
                indAch.add(i);
            }
        }
        if(modelOrd.size()==0){
            consulterAchatButton.setEnabled(false);
        } else {
            achatsList.setSelectedIndex(0);
        }

        consulterAchatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PageAchat ach = new PageAchat(indAch.get(achatsList.getSelectedIndex()));
            }
        });

        consulterMedecinOuSpecialisteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if (specList.getSelectedIndex()==0) {
                    PageMedecin pagMed = new PageMedecin("med",medecinsTraitants.indexOf(cli.getMedecinTraitant()));
                    pagMed.setVisible(true);
                } else {
                    PageMedecin pagMed = new PageMedecin("spe", indSpec.get(specList.getSelectedIndex()));
                    pagMed.setVisible(true);
                }
            }
        });

        modifierClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                ModifClient modCli = new ModifClient(id);
                modCli.setVisible(true);
            }
        });

        suppClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimer(framePageCli, cli.getNom() ,id, clients,2, "client", null);
                nettoyageOrdo(cli);
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
}
