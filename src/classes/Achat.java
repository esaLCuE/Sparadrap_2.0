package classes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Achat {

    private Client client;
    private MedecinTraitant medecinTraitant;
    private Specialiste specialiste;
    private List<Medicament> listeMedicaments;
    private List<Integer> listeQuantites;
    private LocalDate dateAchat;
    private String dateAchatForm;
    private boolean ordo;
    private static List<Achat> achats = new ArrayList<>();

    public void setClientAchat(Client client) {
        this.client = client;
    }
    public Client getClientAchat() {
        return this.client;
    }

    public void setMedecinTraitant(MedecinTraitant medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }
    public MedecinTraitant getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public void setSpecialisteAchat(Specialiste specialiste) throws IllegalArgumentException {
        if(!client.getSpecialistesClient().contains(specialiste) && !(specialiste ==null)){
            throw new IllegalArgumentException("Ce spécialiste ne suit pas ce patient");
        }
        this.specialiste = specialiste;
    }
    public Specialiste getSpecialisteAchat() {
        return this.specialiste;
    }

    public void setListeMedicaments(List<Medicament> listeMedicaments) {
        this.listeMedicaments = listeMedicaments;
    }
    public List<Medicament> getListeMedicaments() {
        return this.listeMedicaments;
    }

    public void setListeQuantites(List<Integer> listeQuantites) {
        this.listeQuantites = listeQuantites;
    }
    public List<Integer> getListeQuantites() {
        return this.listeQuantites;
    }

    public void setDateAchat(LocalDate dateAchat) throws DateTimeException {
        if(dateAchat.isBefore(client.getDateNaissanceOri())){
            throw new DateTimeException("L'achat ne peut pas être antérieur à la naisance du client.");
        }
        for (int i=0; i<listeMedicaments.size(); i++){
            if(dateAchat.isBefore(listeMedicaments.get(i).getMiseEnService())){
                throw new DateTimeException("L'achat ne peut pas être antérieur à la mise en service du médicament.");
            }
        }
        this.dateAchat = dateAchat;
        DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dateAchatForm = dateAchat.format(formatPattern);
    }
    public LocalDate getDateAchat() {
        return this.dateAchat;
    }
    public String getDateAchatForm() {
        return this.dateAchatForm;
    }

    public static void ajoutAchat(Achat achat) {
        achats.add(achat);
    }
    public static void retraitAchat(Achat achat) {
        achats.remove(achat);
    }

    public static List<Achat> getAchats() {
        return achats;
    }

    public void setOrdo(boolean ordo){
        this.ordo = ordo;
    }

    public boolean getOrdo(){
        return this.ordo;
    }

    public Achat(Client client, List<Medicament> medicaments, List<Integer> quantites, LocalDate dateAchat, boolean ordo) {
        setClientAchat(client);
        setListeMedicaments(medicaments);
        setListeQuantites(quantites);
        setDateAchat(dateAchat);
        setOrdo(ordo);
    }

    public Achat(Client client, Specialiste specialiste, List<Medicament> medicaments, List<Integer> quantites,
                 LocalDate dateAchat, boolean ordo) {
        setClientAchat(client);
        setSpecialisteAchat(specialiste);
        setListeMedicaments(medicaments);
        setListeQuantites(quantites);
        setDateAchat(dateAchat);
        setOrdo(ordo);
    }
}
