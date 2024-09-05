package classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MedecinTraitant extends InfosCommunes {

    private String regexAgrement = "^[0-9]{11}$";
    private String agrement;

    public void setAgrement(String agrement) throws InputMismatchException {
        agrement = String.valueOf(agrement);
        if (!agrement.matches(regexAgrement)) {
            throw new InputMismatchException("Merci de saisir un RPPS valide");
        }
        this.agrement= agrement;
    }

    public String getAgrement() {
        return this.agrement;
    }


    public static List<MedecinTraitant> medecinsTraitants = new ArrayList<>();

    public MedecinTraitant(String nom, String prenom, String adresse, String codePostal, String ville, String telephone,
                           String email, String agrement) throws NullPointerException, IllegalArgumentException, InputMismatchException {
        super(nom, prenom, adresse, codePostal, ville, telephone, email);
        setAgrement(agrement);
    }
}
