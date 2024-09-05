package classes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.InputMismatchException;
import java.util.List;

public class Ordonnance {

    private LocalDate emission;
    private String nomMedecin;
    private String nomClient;
    private List<Medicament> listMeds;
    private String nomSpec;

    public void setEmission(LocalDate emission) throws FormatFlagsConversionMismatchException, DateTimeException {
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.emission = LocalDate.parse(emission.format(formatPattern));
    }

    public LocalDate getEmission() {
        return this.emission;
    }

    //SELECTION AVEC COMBOBOX
    public void setNomMedecin(MedecinTraitant medecin) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.nomMedecin = medecin.getNom();
    }

    public String getMedecin() {
        return this.nomMedecin;
    }

    //SELECTION AVEC COMBOBOX
    public void setNomClient(Client client) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.nomClient = client.getNom();
    }
    public String getNomClient() {
        return this.nomClient;
    }

    //SELECTION AVEC COMBOBOX, AJOUT DYNAMIQUE DE LIGNES
    public void setMedicaments(List<Medicament> medicaments) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.listMeds = medicaments;
    }
    public List<Medicament> getMedicaments() {
        return this.listMeds;
    }

    //SELECTION AVEC COMBOBOX
    public void setNomSpec(Specialiste spec) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.nomSpec = spec.getNom();
    }
    public String getNomSpec() {
        return this.nomSpec;
    }

    public static List<Ordonnance> ordonnances = new ArrayList<>();

    public Ordonnance(LocalDate emission, MedecinTraitant medecin, Client client, List<Medicament> listMeds, Specialiste spec)
            throws InputMismatchException, IllegalArgumentException, NullPointerException, DateTimeException {
        setEmission(emission);
        setNomMedecin(medecin);
        setNomClient(client);
        setMedicaments(listMeds);
        setNomSpec(spec);

    }
}
