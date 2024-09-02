package classes;

public class Specialiste extends InfosCommunes {

    private String regexSpecialite = "^[a-zA-Zà-üÀ-Ü\\s-]*$";

    private String specialite;

    public void setSpecialite(String specialite) throws IllegalArgumentException {
        if (!specialite.matches(regexSpecialite)) {
            throw new IllegalArgumentException("Saisie invalide");
        }
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public Specialiste(String nom, String prenom, String adresse, String codePostal, String ville, String telephone,
                       String email, String specialite) {
        super(nom, prenom, adresse, codePostal, ville, telephone, email);
        setSpecialite(specialite);
    }
}
