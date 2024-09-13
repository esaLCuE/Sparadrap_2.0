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

        MedecinTraitant NP = new MedecinTraitant("Ndf", "Prenom", "20 Rue de jsp", "54390",
                "Nancy", "0779429475", "unprenom@unnom.net", "06171812548");
        MedecinTraitant AiF = new MedecinTraitant("Fujimi", "Aika", "39 Rue des poissons", "87006",
                "Saumure", "0648972464", "lespoissons@eau.com", "94156438281");
        MedecinTraitant IZ = new MedecinTraitant("Zaward", "Ivo", "17 Rue du Liechtenstein", "01960",
                "Péronnas", "0741217111", "couteau@taface.fr", "64214983056");
        medecinsTraitants.add(NP);
        medecinsTraitants.add(AiF);
        medecinsTraitants.add(IZ);

        Mutuelle HM = new Mutuelle("Harmonie Mutuelle", "143 rue Blomet", "75015", "Paris",
                "0980980880","convhms@harmonie-mutuelles.fr", 5);
        Mutuelle MAAF = new Mutuelle("MAAF", "Rue de la Treille", "79180", "Chauray", "0969328328",
                "facteur.maaf_sante@maaf.fr", 5);
        Mutuelle JSP = new Mutuelle("JSP", "10 Rue de l'Avenue", "71370", "Ipender",
                "0713705420", "emailpro@mutuelle.fr", 5);

        mutuelles.add(HM);
        mutuelles.add(MAAF);
        mutuelles.add(JSP);

        Specialiste HH = new Specialiste("Hebimori","Hikari","Forêt aux serpents","55555",
                "Le Serpent", "0555555555","lesserpents@laforet.fr","Cardiologie");
        Specialiste BL = new Specialiste("Lanteille","Baptiste","7 Rue du Clerc","67386",
                "Rauwillier","0771523689","lindifference@leregard.fr","Neurologie");
        Specialiste TP = new Specialiste("Primo", "Théo", "1 rue du Primeur","01001",
                "Ainville","0100000001","lepremier@tout.fr","Pédiatrie");
        specialistes.add(HH);
        specialistes.add(BL);
        specialistes.add(TP);

        List<Specialiste> specialistesAP=new ArrayList<>();
        specialistesAP.add(HH);
        specialistesAP.add(BL);

        List<Specialiste> specialistesAkF=new ArrayList<>();
        specialistesAkF.add(TP);
        specialistesAkF.add(BL);

        List<Specialiste> specialistesAZ=new ArrayList<>();
        specialistesAZ.add(HH);
        specialistesAZ.add(TP);

        Client AP = new Client("Paga", "Al", "20 rue du Pérou", "60430",
                "Lima", "0708090405", "leslamas@lamontagne.pe", "160052A55577799",
                LocalDate.of(2000,10,15), HM, NP, specialistesAP);

        Client AkF = new Client("Fujimi", "Akihide", "1 pas de rue", "10000",
                "Tourcoing", "0615325698", "tonargent@mespoches.com", "160052A44466688",
                LocalDate.of(1995,1,1), JSP, IZ, specialistesAkF);

        Client AZ = new Client("Zaward", "Alice", "17 Rue du Liechtenstein", "01960",
                "Péronnas", "0782461937", "lesarbres@laforet.com", "201052A44466688",
                LocalDate.of(2000,9,6), MAAF, AiF, specialistesAZ);
        clients.add(AP);
        clients.add(AkF);
        clients.add(AZ);

        Medicament GES = new Medicament("Analgesor","Analgésique",10,LocalDate.of(2020,6,15));
        Medicament BIO = new Medicament("Biocidator","Antibiotique",15,LocalDate.of(2021,7,16));
        Medicament VIR = new Medicament("Viraflax","Antiviral",20,LocalDate.of(2022,8,17));
        medicaments.add(GES);
        medicaments.add(BIO);
        medicaments.add(VIR);

        Accueil menu = new Accueil();
    }
}