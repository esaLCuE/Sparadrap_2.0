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


    public static ArrayList<Mutuelle> mutuelles = new ArrayList<>(Arrays.asList());

    public Mutuelle(String nom, String adresse, String codePostal, String ville, String telephone, String email,
                    float priseEnCharge) throws IllegalArgumentException, InputMismatchException, NullPointerException {
        super(nom, adresse, codePostal, ville, telephone, email);
        setDepartement(codePostal);
        setpriseEnCharge(priseEnCharge);
    }
}
