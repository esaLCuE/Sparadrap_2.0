package classes;

public class Denomination {

    private String nom;
    private String prenom;

    public void setNom(String nom) throws NullPointerException, IllegalArgumentException {
        if (nom == null || nom.isEmpty() || nom.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un nom");
        }
        if (!nom.matches("^[a-zA-Zà-üÀ-Ü\\s-]*$")) {
            throw new IllegalArgumentException("Merci de saisir un nom valide.");
        }
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setPrenom(String prenom) throws NullPointerException, IllegalArgumentException {
        if (prenom == null || prenom.isEmpty() || prenom.matches("\\s+")) {
            throw new NullPointerException("Merci de saisir un prénom");
        }
        if (!prenom.matches("^[a-zA-Zà-üÀ-Ü\\s-]*$")){
            throw new IllegalArgumentException("Merci de saisir un prénom valide.");
        }
        this.prenom = prenom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Denomination(String nom, String prenom) throws NullPointerException, IllegalArgumentException {
        setNom(nom);
        setPrenom(prenom);
    }

    public Denomination(String nom) throws NullPointerException, IllegalArgumentException {
        setNom(nom);
    }

}
