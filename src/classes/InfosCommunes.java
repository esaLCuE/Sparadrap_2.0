package classes;

import java.util.InputMismatchException;

public class InfosCommunes extends Denomination {

    private final String regexAdresse = "^([0-9]*?\\s?)+[a-zA-Zà-üÀ-Ü\\s-',]*$";
    private final String regexCodePostal = "^[0-9]{2}( ?)[0-9]{3}$";
    private final String regexVille = "^[a-zA-Zà-üÀ-Ü\\s-]*$";
    private final String regexTel = "^[0-9\\s-./]{10}$";
    private final String regexEmail = ("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;

    public void setAdresse(String adresse) throws NullPointerException, InputMismatchException {
        if (adresse == null || adresse.isEmpty() || adresse.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir une adresse");
        }
        if (!adresse.matches(regexAdresse)) {
            throw new InputMismatchException("Merci de saisir une adresse valide");
        }
        this.adresse = adresse;
    }
    public String getAdresse(){
        return this.adresse;
    }

    public void setCodePostal(String codePostal) throws NullPointerException, InputMismatchException {
        if (codePostal == null || codePostal.isEmpty()) {
            throw new NullPointerException("Merci de saisir un code postal");
        }
        if (!codePostal.matches(regexCodePostal)) {
            throw new InputMismatchException("Merci de saisir un code postal valide");
        }
        this.codePostal = codePostal.replaceAll(" ","");
    }
    public String getCodePostal(){
        return this.codePostal;
    }

    public void setVille(String ville) throws NullPointerException, InputMismatchException {
        if (ville == null || ville.isEmpty() || ville.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un ville");
        }
        if (!ville.matches(regexVille)) {
            throw new InputMismatchException("Merci de saisir un ville valide");
        }
        this.ville = ville;
    }
    public String getVille(){
        return this.ville;
    }

    public void setTelephone(String telephone) throws NullPointerException, InputMismatchException {
        telephone=String.valueOf(telephone.replaceAll("[\\s-./]+", ""));
        if (telephone == null || telephone.isEmpty() || telephone.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un numéro de telephone");
        }
        if (!telephone.matches(regexTel)) {
            throw new InputMismatchException("Merci de saisir un numéro de telephone valide");
        }
        this.telephone = telephone;
    }
    public String getTelephone(){
        return this.telephone.replaceAll("(.{2})","$0 ").trim();
    }

    public void setEmail(String email) throws NullPointerException, InputMismatchException {
        if (email == null || email.isEmpty()) {
            throw new NullPointerException("Merci de saisir un email");
        }
        if (!email.matches(regexEmail)) {
            throw new InputMismatchException("Merci de saisir un email valide");
        }
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public InfosCommunes(String nom, String prenom, String adresse, String codePostal, String ville, String telephone, String email)
            throws NullPointerException, InputMismatchException{
        super(nom, prenom);
        setAdresse(adresse);
        setCodePostal(codePostal);
        setVille(ville);
        setTelephone(telephone);
        setEmail(email);
    }
    public InfosCommunes(String nom, String adresse, String codePostal, String ville, String telephone, String email)
            throws NullPointerException, InputMismatchException, IllegalArgumentException{
        super(nom);
        setAdresse(adresse);
        setCodePostal(codePostal);
        setVille(ville);
        setTelephone(telephone);
        setEmail(email);
    }
}
