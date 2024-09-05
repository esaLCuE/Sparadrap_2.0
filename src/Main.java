// COMME ARGUMENT POUR LES SPECIALISTES PAR EX, UTILISER UNE LISTE


import Swing.Accueil;
import classes.MedecinTraitant;

import static classes.MedecinTraitant.medecinsTraitants;

public class Main {
    public static void main(String[] args) {

        MedecinTraitant AZ = new MedecinTraitant("Ndf", "Prenom", "20 Rue de jsp", "54390",
                "Nancy", "0779429475", "unprenom@unnom.net", "06171812548");
        MedecinTraitant QS = new MedecinTraitant("Fujimi", "Aika", "39 Rue des poissons", "87006",
                "Saumure", "0648972464", "lespoissons@eau.com", "94156438281");
        MedecinTraitant WX = new MedecinTraitant("Zaward", "Ivo", "17 Rue du Liechtenstein", "01960",
                "Péronnas", "0741217111", "couteau@taface.fr", "64214983056");
        medecinsTraitants.add(AZ);
        medecinsTraitants.add(QS);
        medecinsTraitants.add(WX);

        Accueil menu = new Accueil();
    }
}