package classes;

import java.util.InputMismatchException;

public class Denomination {

    private String nom;
    private String ndf;
    private String prenom;


    public void setNdf(String ndf) throws NullPointerException, InputMismatchException {
        if (ndf == null || ndf.isEmpty() || ndf.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un nom");
        }
        if (!ndf.matches("^[a-zA-Zà-üÀ-Ü\\s-]*$")) {
            throw new InputMismatchException("Merci de saisir un nom valide.");
        }
        this.ndf = ndf;
    }
    public String getNdf() {
        return this.ndf;
    }

    public void setPrenom(String prenom) throws NullPointerException, InputMismatchException {
        if (prenom == null || prenom.isEmpty() || prenom.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un prénom");
        }
        if (!prenom.matches("^[a-zA-Zà-üÀ-Ü\\s-]*$")){
            throw new InputMismatchException("Merci de saisir un prénom valide.");
        }
        this.prenom = prenom;
    }
    public String getPrenom() {
        return this.prenom;
    }

    public void setNom(String ndf, String prenom) throws NullPointerException, IllegalArgumentException, InputMismatchException {
        setNdf(ndf);
        setPrenom(prenom);
        this.nom = prenom+" "+ndf;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNomSoc(String nom) throws NullPointerException{
        if (nom == null || nom.isEmpty() || nom.matches("\\s+")){
            throw new NullPointerException("Merci de saisir un nom");
        }
        this.nom=nom;
    }
    public String getNomSoc(){
        return this.nom;
    }

    public Denomination(String ndf, String prenom) throws NullPointerException, IllegalArgumentException, InputMismatchException {
        setPrenom(prenom);
        setNdf(ndf);
        setNom(ndf, prenom);
    }

    public Denomination(String nom) throws NullPointerException, IllegalArgumentException, InputMismatchException {
        setNomSoc(nom);
    }

}
