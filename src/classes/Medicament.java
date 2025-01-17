package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Medicament extends Denomination {

    private String categorie;
    private float prix;
    private LocalDate miseEnService;
    private String miseEnServiceForm;
    private int quantite;

    public void setCategorie(String categorie) throws NullPointerException {
        if (categorie == null || categorie.isEmpty()) {
            throw new NullPointerException("Merci de saisir une catégorie de médicament.");
        }
        this.categorie = categorie;
    }

    public String getCategorie() {
        return this.categorie;
    }

    // METTRE CATEGORIES DANS COMBOBOX

    public void setPrix(float prix) throws InputMismatchException {
        this.prix = prix;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setMiseEnService(LocalDate miseEnService) throws IllegalArgumentException, InputMismatchException {
        this.miseEnService = miseEnService;
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.miseEnServiceForm = miseEnService.format(formatPattern);
        //DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //this.miseEnService = LocalDate.parse(miseEnService.format(formatPattern));
    }

    public LocalDate getMiseEnService() {
        return this.miseEnService;
    }

    public void setQuantite(int quantite) throws InputMismatchException {
        this.quantite = quantite;
    }

    public int getQuantite() {
        return this.quantite;
    }


    public static List<String> categories = new ArrayList<>(Arrays.asList("Analgésique", "Antibiotique", "Antiviral"));

    public static List<Medicament> medicaments = new ArrayList<Medicament>();

    public Medicament(String nom, int prix, LocalDate miseEnService, String categorie)
            throws NullPointerException, IllegalArgumentException, InputMismatchException {
        super(nom);
        setCategorie(categorie);
        setPrix(prix);
        setMiseEnService(miseEnService);
    } // TODO : changer les string en id

}
