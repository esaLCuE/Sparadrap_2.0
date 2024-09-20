package classes;

import Swing.ConsulterClient;
import Swing.HistoriqueAchats;

import javax.swing.*;

import java.time.LocalDate;
import java.util.List;

import static Swing.Accueil.frames;
import static classes.Achat.getAchats;
import static classes.Client.clients;
import static classes.Ordonnance.ordonnances;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

public class FonctionsCommunes {
    public static int afficherMessage(String message, String titre) {
        return (JOptionPane.showConfirmDialog(null, message, titre, YES_NO_OPTION));
    }

    public static void afficherErreur(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void supprimer(JFrame framePageCli, String nom, int id, List liste, int nbSupHist, String suite, LocalDate date) {
        int input = afficherMessage("Supprimer " + nom + " ?", "Supprimer");
        if (input == JOptionPane.YES_OPTION) {
            if (suite=="client") {
                nettoyageOrdo(clients.get(id));
            }
            liste.remove(id);
            framePageCli.setVisible(false);
            for (int i = 0; i < nbSupHist; i++) {
                suppHistorique();
            }
            if (suite=="client") {
                ConsulterClient cli2 = new ConsulterClient();
            } else if (suite=="achat") {
                HistoriqueAchats hisAch2 = new HistoriqueAchats(date);
            }
        }
    }

    public static void nettoyageOrdo(Client cli){
        for (int i=0;i<getAchats().size();i++){
            if (getAchats().get(i).getClientAchat()==cli){
                getAchats().remove(i);
            }
        }
    }

    public static void quitterProgramme(){
        int choix = showConfirmDialog(null,"Quitter l'application ?", "Quitter", YES_NO_OPTION);
        if (choix == 0) {
            System.exit(0);
        }
    }

    public static void precedent(){
        if (frames.size()>1) {
            frames.get(frames.size() - 2).setVisible(true);
            frames.removeLast();
        }
    }

    public static void suppHistorique(){
        frames.removeLast();
    }

    public static void ajoutHistorique(JFrame frame){
        frames.add(frames.size(), frame);
    }
}