package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Specialiste extends InfosCommunes {

    private String regexDomaine = "^[a-zA-Zà-üÀ-Ü\\s-]*$";

    private String domaine;

    public void setDomaine(String domaine) throws InputMismatchException {
        if (!domaine.matches(regexDomaine)) {
            throw new InputMismatchException("Domaine invalide");
        }
        this.domaine = domaine;
    }

    public String getDomaine() {
        return this.domaine;
    }

    public String getTotale() {
        return(getNom()+getAdresse()+getCodePostal()+getVille()+getTelephone()+getEmail()+getDomaine());
    }

    public static List<String> domaines = new ArrayList<>(Arrays.asList("Urologie", "Cardiologie", "Gynécologie",
            "Pédiatrie", "Neurologie"));

    public static List<Specialiste> specialistes = new ArrayList<>();

    public Specialiste(String nom, String prenom, String adresse, String codePostal, String ville, String telephone,
                       String email, String domaine) throws NullPointerException, IllegalArgumentException, InputMismatchException {
        super(nom, prenom, adresse, codePostal, ville, telephone, email);
        setDomaine(domaine);
    }
    // TODO : Le domaine ne doit plus être un string, mais est un id dans la BDD. Changer le get selected item to string en get selected index dans les classes concernées
}
