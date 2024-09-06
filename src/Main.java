// COMME ARGUMENT POUR LES SPECIALISTES PAR EX, UTILISER UNE LISTE


import Swing.Accueil;
import classes.MedecinTraitant;
import classes.Mutuelle;

import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Mutuelle.*;
import static classes.Mutuelle.mutuelles;

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

        Mutuelle HM = new Mutuelle("Harmonie Mutuelle", "143 rue Blomet", "75015", "Paris",
                "0980980880","convhms@harmonie-mutuelles.fr", 5);
        Mutuelle MAAF = new Mutuelle("MAAF", "Rue de la Treille", "79180", "Chauray", "0969328328",
                "facteur.maaf_sante@maaf.fr", 5);
        Mutuelle JSP = new Mutuelle("JSP", "10 Rue de l'Avenue", "71370", "Ipender",
                "0713705420", "emailpro@mutuelle.fr", 5);

        mutuelles.add(HM);
        mutuelles.add(MAAF);
        mutuelles.add(JSP);

        Accueil menu = new Accueil();
    }
}