package classes;

public class MedecinTraitant extends InfosCommunes {

    private String regexAgrement = "^[0-9]{11}$";
    private String agrement;

    public void setAgrement(String agrement) throws IllegalArgumentException {
        if (!agrement.matches(regexAgrement)) {
            throw new IllegalArgumentException("Merci de saisir un RPPS valide");
        }
        this.agrement= agrement;
    }

    public String getAgrement() {
        return this.agrement;
    }

    public MedecinTraitant(String nom, String prenom, String adresse, String codePostal, String ville, String telephone,
                           String email, String agrement) {
        super(nom, prenom, adresse, codePostal, ville, telephone, email);
        setAgrement(agrement);
    }
}
