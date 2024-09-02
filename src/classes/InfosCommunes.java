package classes;

public class InfosCommunes extends Denomination {
    // NOM PRENOM ADRESSE CODE POSTAL VILLE TELEPHONE EMAIL
    // CLIENT MEDECIN SPECIALISTE MUTUELLE
    // FAIRE AUTRE CLASSE POUR NOM SEUL POUR MEDICAMENT ET MUTUELLE

    private String regexAdresse = "^([0-9]*\\s)+[a-zA-Zà-üÀ-Ü\\s-]*$";
    private String regexCodePostal = "^[0-9]{2}( ?)[0-9]{3}$";
    private String regexVille = "^[a-zA-Zà-üÀ-Ü\\s-]*$";
    private String regexTel = " ^[0-9 -]{10}$";
    private String regexEmail = ("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;

    public void setAdresse(String adresse) throws NullPointerException, IllegalArgumentException {
        if (adresse == null || adresse.isEmpty() || adresse.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir une adresse");
        }
        if (!adresse.matches(regexAdresse)) {
            throw new IllegalArgumentException("Merci de saisir une adresse valide");
        }
        this.adresse = adresse;
    }
    public String getAdresse(){
        return this.adresse;
    }

    public void setCodePostal(String codePostal) throws NullPointerException, IllegalArgumentException {
        if (codePostal == null || codePostal.isEmpty()) {
            throw new NullPointerException("Merci de saisir un code postal");
        }
        if (!codePostal.matches(regexCodePostal)) {
            throw new IllegalArgumentException("Merci de saisir un code postal valide");
        }
        this.codePostal = codePostal;
    }

    public String getCodePostal(){
        return this.codePostal;
    }

    public void setVille(String ville) throws NullPointerException, IllegalArgumentException {
        if (ville == null || ville.isEmpty() || ville.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un ville");
        }
        if (!ville.matches(regexVille)) {
            throw new IllegalArgumentException("Merci de saisir un ville valide");
        }
        this.ville = ville;
    }

    public String getVille(){
        return this.ville;
    }

    public void setTelephone(String telephone) throws NullPointerException, IllegalArgumentException {
        if (telephone == null || telephone.isEmpty() || telephone.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un numéro de telephone");
        }
        if (!telephone.matches(regexTel)) {
            throw new IllegalArgumentException("Merci de saisir un numéro de telephone valide");
        }
        this.telephone = telephone.replaceAll("[\\s-]+", "");
    }

    public String getTelephone(){
        return this.telephone;
    }

    public void setEmail(String email) throws NullPointerException, IllegalArgumentException {
        if (email == null || email.isEmpty()) {
            throw new NullPointerException("Merci de saisir un email");
        }
        if (!email.matches(regexEmail)) {
            throw new IllegalArgumentException("Merci de saisir un email valide");
        }
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public InfosCommunes(String nom, String prenom, String adresse, String codePostal, String ville, String telephone, String email) {
        super(nom, prenom);
        setAdresse(adresse);
        setCodePostal(codePostal);
        setVille(ville);
        setTelephone(telephone);
        setEmail(email);
    }
    public InfosCommunes(String nom, String adresse, String codePostal, String ville, String telephone, String email) {
        super(nom);
        setAdresse(adresse);
        setCodePostal(codePostal);
        setVille(ville);
        setTelephone(telephone);
        setEmail(email);
    }
}
