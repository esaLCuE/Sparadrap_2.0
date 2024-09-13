package classes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.InputMismatchException;
import java.util.List;

public class Ordonnance {

    private LocalDate emission;
    private String nomMedecinOrdo;
    private String nomClientOrdo;
    private List<Medicament> listMeds;
    private List<Integer> listQtt;
    private String nomSpec;

    public void setEmission(LocalDate emission) throws FormatFlagsConversionMismatchException, DateTimeException {
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.emission = LocalDate.parse(emission.format(formatPattern));
    }
    public LocalDate getEmission() {
        return this.emission;
    }

    //SELECTION AVEC COMBOBOX
    public void setNomMedecin(MedecinTraitant medecinOrdo) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.nomMedecinOrdo = medecinOrdo.getNom();
    }
    public String getMedecin() {
        return this.nomMedecinOrdo;
    }

    //SELECTION AVEC COMBOBOX
    public void setNomClientOrdo(Client clientOrdo) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.nomClientOrdo = clientOrdo.getNom();
    }
    public String getNomClientOrdo() {
        return this.nomClientOrdo;
    }

    //SELECTION AVEC COMBOBOX, AJOUT DYNAMIQUE DE LIGNES
    public void setMedicaments(List<Medicament> medicaments, List<Integer> quantites) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.listMeds = medicaments;
        this.listQtt = quantites;
    }
    public List<Medicament> getMedicaments() {
        return this.listMeds;
    }
    public List<Integer> getQuantites() {
        return this.listQtt;
    }

    //SELECTION AVEC COMBOBOX
    public void setNomSpec(Specialiste spec) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.nomSpec = spec.getNom();
    }
    public String getNomSpec() {
        return this.nomSpec;
    }

    public static List<Ordonnance> ordonnances = new ArrayList<>();

    //PENSER A PARLER DU TAUX DE REMBOURSEMENT DANS LES INFORMATIONS PERTINENTES
    public Ordonnance(LocalDate emission, MedecinTraitant medecin, Specialiste spec, Client client, List<Medicament> listMeds, List<Integer> listQtt)
            throws InputMismatchException, IllegalArgumentException, NullPointerException, DateTimeException {
        setEmission(emission);
        setNomMedecin(medecin);
        setNomClientOrdo(client);
        setMedicaments(listMeds, listQtt);
        setNomSpec(spec);

    }
    public Ordonnance(LocalDate emission, Client client, List<Medicament> listMeds, List<Integer> listQtt)
            throws InputMismatchException, IllegalArgumentException, NullPointerException, DateTimeException {
        setEmission(emission);
        setNomClientOrdo(client);
        setMedicaments(listMeds, listQtt);

    }
}
