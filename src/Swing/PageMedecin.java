package Swing;

import classes.MedecinTraitant;
import classes.Specialiste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import static classes.Client.clients;
import static classes.Ordonnance.ordonnances;

public class PageMedecin extends JFrame {
    private JLabel infosMedecin;
    private JButton precedentButton;
    private JButton quitterButton;
    private JPanel contentPane;
    private JLabel patientsTitre;
    private JLabel ordonnanceTitre;
    private JButton consulterButton;
    DefaultListModel modelClient = new DefaultListModel();
    private JList patientsList = new JList(modelClient);
    DefaultListModel modelOrdonnance = new DefaultListModel();
    private JList ordonnancesList = new JList(modelOrdonnance);


    public PageMedecin(List<MedecinTraitant> medecins, List<Specialiste> specialistes, String pos, int id) {

        String nom;

        if(Objects.equals(pos, "med")){
            nom = medecins.get(id).getNom();
        } else {
            nom = specialistes.get(id).getNom();
        }
        this.setTitle("Page de "+nom);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        this.pack();

        JFrame framePageMed = new JFrame();
        setLocationRelativeTo(null);
        setResizable(true);

        Accueil.ajoutHistorique(this);

        if (Objects.equals(pos, "med")){

            String texte = (nom + "\n" + medecins.get(id).getAdresse() + " " + medecins.get(id).getCodePostal() +
                    " " + medecins.get(id).getVille() +"\n"+ "Contacts : " + medecins.get(id).getEmail()+
                    " - " + medecins.get(id).getTelephone() +"\n"+ "Numéro d'agrément : " + medecins.get(id).getAgrement());
            infosMedecin.setText("<html>" + texte.replaceAll("\n", "<br/>") + "</html>");
        }
        else {
            String text = (nom + " spécialiste en " + specialistes.get(id).getDomaine() + "\n" +specialistes.get(id).getAdresse() +
                    " " + specialistes.get(id).getCodePostal() + " " + specialistes.get(id).getVille() + "\n" +
                    "Contacts : " + specialistes.get(id).getEmail() + " - "+specialistes.get(id).getTelephone() + ". ");
            infosMedecin.setText("<html>" + text.replaceAll("\n", "<br/>") + "</html>");
        }


        for (int i=0; i<clients.size(); i++) {
            if (clients.get(i).getMedecinTraitant().equals(medecins.get(id))) {
                modelClient.add(i, clients.get(i).getNom());
            }
        }

        for (int i=0; i<ordonnances.size(); i++) {
            if (ordonnances.get(i).getMedecin().equals(medecins.get(id))) {
                modelOrdonnance.add(i, ordonnances.get(i).getEmission()+" "+ordonnances.get(i).getNomClient());
            }
        }

        precedentButton.addActionListener(new ActionListener() {
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
