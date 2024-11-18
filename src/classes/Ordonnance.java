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
    private Client nomClientOrdo;
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
    public void setClientOrdo(Client clientOrdo) throws InputMismatchException, IllegalArgumentException, NullPointerException {
        this.nomClientOrdo = clientOrdo;
    }
    public Client getNomClientOrdo() {
        return this.nomClientOrdo;
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
    public Ordonnance(LocalDate emission,/* int id_achat, */ Specialiste spec, Client client)
            throws InputMismatchException, IllegalArgumentException, NullPointerException, DateTimeException {
        setEmission(emission);
        setClientOrdo(client);
        setNomSpec(spec);

        // TODO : ajouter id achat, changer client en id_client
    }
}
