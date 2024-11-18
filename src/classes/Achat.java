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

    public void setDateAchat(LocalDate dateAchat) throws DateTimeException {
        if(dateAchat.isBefore(client.getDateNaissanceOri())){
            throw new DateTimeException("L'achat ne peut pas être antérieur à la naisance du client.");
        }
        /*
        for (int i=0; i<listeMedicaments.size(); i++){
            if(dateAchat.isBefore(listeMedicaments.get(i).getMiseEnService())){
                throw new DateTimeException("L'achat ne peut pas être antérieur à la mise en service du médicament.");
            }
        }
         */
        // TODO : remplacer listeMeds par récup de la table Contient
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

    public boolean getOrdo(){
        return this.ordo;
    }

    public Achat(Client client, LocalDate dateAchat) {
        setClientAchat(client);
        setDateAchat(dateAchat);
    }
}
