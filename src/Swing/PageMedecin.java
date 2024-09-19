package Swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static classes.Achat.getAchats;
import static classes.Client.clients;
import static classes.FonctionsCommunes.*;
import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Specialiste.specialistes;

public class PageMedecin extends JFrame {
    private JLabel infosMedecin;
    private JButton precedentButton;
    private JButton quitterButton;
    private JPanel contentPane;
    private JLabel patientsTitre;
    private JLabel ordonnanceTitre;
    private JButton consulterOrdoButton;

    DefaultListModel modelClient = new DefaultListModel();
    private JList patientsList;
    DefaultListModel modelOrdonnance = new DefaultListModel();
    private JList ordonnancesList;
    private JButton consulterPatientButton;
    private JButton supprimerMedecinButton;
    private JButton modifierMedecinButton;


    public PageMedecin(String pos, int id) {

        String nom;

        if(Objects.equals(pos, "med")){
            nom = medecinsTraitants.get(id).getNom();
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

        ajoutHistorique(this);

        patientsList.setModel(modelClient);
        ordonnancesList.setModel(modelOrdonnance);

        if (Objects.equals(pos, "med")){

            String texte = (nom + "\n" + medecinsTraitants.get(id).getAdresse() + " " + medecinsTraitants.get(id).getCodePostal() +
                    " " + medecinsTraitants.get(id).getVille() +"\n"+ "Contacts : " + medecinsTraitants.get(id).getEmail()+
                    " - " + medecinsTraitants.get(id).getTelephone() +"\n"+ "Numéro d'agrément : " + medecinsTraitants.get(id).getAgrement());
            infosMedecin.setText("<html>" + texte.replaceAll("\n", "<br/>") + "</html>");
        }
        else {
            String text = (nom + " spécialiste en " + specialistes.get(id).getDomaine() + "\n" +specialistes.get(id).getAdresse() +
                    " " + specialistes.get(id).getCodePostal() + " " + specialistes.get(id).getVille() + "\n" +
                    "Contacts : " + specialistes.get(id).getEmail() + " - "+specialistes.get(id).getTelephone() + ". ");
            infosMedecin.setText("<html>" + text.replaceAll("\n", "<br/>") + "</html>");
        }

        List<Integer> idClients = new ArrayList<>();
        if (Objects.equals(pos, "med")){
        for (int i=0; i<clients.size(); i++) {
            if (clients.get(i).getMedecinTraitant().equals(medecinsTraitants.get(id))) {
                modelClient.addElement(clients.get(i).getNom()+" - "+clients.get(i).getSecuSociale());
                idClients.add(i);
                }
            }
        } else {
            for (int i=0; i<clients.size(); i++) {
                if (clients.get(i).getSpecialistesClient().contains(specialistes.get(id))) {
                    modelClient.addElement(clients.get(i).getNom()+" - "+clients.get(i).getSecuSociale());
                    idClients.add(i);
                }
            }
        }
        if (modelClient.size()==0){
            consulterPatientButton.setEnabled(false);
        } else {
            patientsList.setSelectedIndex(0);
        }

        List<Integer> idOrdo = new ArrayList<>();

        for (int i = 0; i < getAchats().size(); i++) {
            if (Objects.equals(pos, "med")) {
                if (getAchats().get(i).getMedecinTraitant()!=null && getAchats().get(i).getMedecinTraitant().equals(medecinsTraitants.get(id))){
                    modelOrdonnance.addElement(getAchats().get(i).getDateAchatForm() + " " + getAchats().get(i).getClientAchat().getNom());
                    idOrdo.add(i);
                }
            } else {
                if (getAchats().get(i).getSpecialisteAchat()!=null && getAchats().get(i).getSpecialisteAchat().equals(specialistes.get(id))) {
                    modelOrdonnance.addElement(getAchats().get(i).getDateAchatForm() + " " + getAchats().get(i).getClientAchat().getNom());
                    idOrdo.add(i);
                }
            }
        }
        if (modelOrdonnance.size()==0){
            consulterOrdoButton.setEnabled(false);
        } else {
            ordonnancesList.setSelectedIndex(0);
        }

        consulterPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PageClient cli = new PageClient((idClients.get(patientsList.getSelectedIndex())));
                cli.setVisible(true);
            }
        });

        consulterOrdoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PageAchat ach = new PageAchat((idOrdo.get(ordonnancesList.getSelectedIndex())));
            }
        });

        modifierMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if (Objects.equals(pos, "med")) {
                    ModifMedecinTraitant modTra = new ModifMedecinTraitant(id);
                } else {
                    ModifMedecinSpecialiste modSpe = new ModifMedecinSpecialiste(id);
                }
            }
        });

        supprimerMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws IllegalArgumentException {
                try {
                    int input = JOptionPane.showConfirmDialog(null, "Supprimer le médecin ?", "Suppression médecin", JOptionPane.YES_NO_OPTION);
                    if (input == JOptionPane.YES_OPTION) {
                        if (pos == "med") {
                            for (int i = 0; i < clients.size(); i++) {
                                if (clients.get(i).getMedecinTraitant().equals(medecinsTraitants.get(id))) {
                                    throw new IllegalArgumentException("Vous ne pouvez pas supprimer de médecin traitant ayant encore des patients.");
                                }
                            }
                            medecinsTraitants.remove(medecinsTraitants.get(id));
                            setVisible(false);
                            suppHistorique();
                            suppHistorique();
                            ConsulterMedecins consMed = new ConsulterMedecins();
                            consMed.setVisible(true);
                        } else {
                            for (int i = 0; i < clients.size(); i++) {
                                clients.get(i).getSpecialistesClient().remove(specialistes.get(id));
                            }
                            specialistes.remove(specialistes.get(id));
                            setVisible(false);
                            suppHistorique();
                            suppHistorique();
                            ConsulterMedecins consMed = new ConsulterMedecins();
                            consMed.setVisible(true);
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    afficherErreur(ex.getMessage());
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
