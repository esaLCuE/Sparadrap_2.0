package classes;

// AJOUTER SECURITE SOCIALE, DATE DE NAISSANCE, MUTUELLE, MEDECIN TRAITANT ET SPECIALISTE(S)

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Client extends InfosCommunes {
    private String regexSecu = "^[12][0-9]{2}(0[1-9]|1[0-2])(2[AB]|[0-9]{2})[0-9]{3}[0-9]{3}([0-9]{2})$";

    private String secuSociale;
    private LocalDate dateNaissance;
    private Mutuelle mutuelle;
    private MedecinTraitant medecinTraitant;
    private List<Specialiste> specialistesClient;

    public void setSecuSociale(String secuSociale) throws NullPointerException, InputMismatchException {
        if (secuSociale == null || secuSociale.isEmpty() || secuSociale.matches("^\\s$")) {
            throw new NullPointerException("Merci de saisir un numéro de sécurité sociale");
        }
        if (!secuSociale.matches(regexSecu)){
            throw new InputMismatchException("Merci de saisir un numéro de sécurité sociale valide");
        }
        this.secuSociale = secuSociale;
    }

    public String getSecuSociale() {
        return this.secuSociale;
    }

    // SAISIE DATES POSSIBLE AVEC COMBOBOX
    public void setDateNaissance(LocalDate dateNaissance) /*throws IllegalArgumentException, InputMismatchException*/ {
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dateNaissance = LocalDate.parse(dateNaissance.format(formatPattern));
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    // SAISIE DANS UNE COMBOBOX
    public void setMutuelle(Mutuelle mutuelle) throws NullPointerException {
        if (mutuelle == null ){
            throw new NullPointerException("Merci de saisir une mutuelle");
        }
        this.mutuelle = mutuelle;
    }

    public Mutuelle getMutuelle() {
        return this.mutuelle;
    }

    public void setMedecinTraitant(MedecinTraitant medecinTraitant) throws NullPointerException {
        if (medecinTraitant == null) {
            throw new NullPointerException("Merci de saisir un médecin traitant");
        }
        this.medecinTraitant = medecinTraitant;
    }

    public MedecinTraitant getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public void setSpecialistesClient(List<Specialiste> specialistesClient) throws NullPointerException {
        for (int i = 0; i < specialistesClient.size(); i++) {
            if (specialistesClient.get(i) == null) {
                throw new NullPointerException("Merci de saisir un spécialiste valide");
            }
        }
        this.specialistesClient = specialistesClient;
    }

    public List<Specialiste> getSpecialistesClient() {
        return this.specialistesClient;
    }



    public static List<Client> clients = new ArrayList<>();

    public Client(String ndf, String prenom, String adresse, String codePostal, String ville, String telephone,
                  String email, String secuSociale, LocalDate dateNaissance, Mutuelle mutuelle,
                  MedecinTraitant medecinTraitant, List<Specialiste> specialistesClient)
                    throws IllegalArgumentException, InputMismatchException, NullPointerException {
        super(ndf, prenom, adresse, codePostal, ville, telephone, email);
        setSecuSociale(secuSociale);
        setDateNaissance(dateNaissance);
        setMutuelle(mutuelle);
        setMedecinTraitant(medecinTraitant);
        setSpecialistesClient(specialistesClient);
    }
}
