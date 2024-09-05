package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Mutuelle extends InfosCommunes {

    private String departement;
    private float priseEnCharge;

    public void setDepartement(String codePostal) {
        this.departement = codePostal.replaceAll("...$","");
    }

    public String getDepartement() {
        return this.departement;
    }

    public void setpriseEnCharge(float priseEnCharge) throws IllegalArgumentException, InputMismatchException {
        this.priseEnCharge = priseEnCharge;
    }

    public float getpriseEnCharge() {
        return this.priseEnCharge;
    }

    static Mutuelle HM = new Mutuelle("Harmonie Mutuelle", "143 rue Blomet", "75015", "Paris",
            "0980980880","convhms@harmonie-mutuelles.fr", 5);
    static Mutuelle MAAF = new Mutuelle("MAAF", "Rue de la Treille", "79180", "Chauray", "0969328328",
            "facteur.maaf_sante@maaf.fr", 5);
    static Mutuelle JSP = new Mutuelle("JSP", "10 Rue de l'Avenue", "71370", "Ipender",
            "0713705420", "emailpro@mutuelle.fr", 5);

    public static ArrayList<Mutuelle> mutuelles = new ArrayList<>(Arrays.asList(HM, MAAF, JSP));

    public Mutuelle(String nom, String adresse, String codePostal, String ville, String telephone, String email,
                    float priseEnCharge) throws IllegalArgumentException, InputMismatchException, NullPointerException {
        super(nom, adresse, codePostal, ville, telephone, email);
        setDepartement(codePostal);
        setpriseEnCharge(priseEnCharge);
    }
}
