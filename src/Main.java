// COMME ARGUMENT POUR LES SPECIALISTES PAR EX, UTILISER UNE LISTE


import Swing.Accueil;
import classes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static classes.Client.clients;
import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Medicament.medicaments;
import static classes.Mutuelle.*;
import static classes.Mutuelle.mutuelles;
import static classes.Specialiste.specialistes;

public class Main {
    public static void main(String[] args) {
        jesaispasmerde.selectFromPersonne();
        Accueil menu = new Accueil();
    }
}