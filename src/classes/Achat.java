package classes;

import java.util.ArrayList;
import java.util.List;

public class Achat {

    boolean ord;

    public List<Achat> achats = new ArrayList<>();

    public Achat(Ordonnance ordonnance) {}

    public Achat(Client client, Specialiste specialiste, List<Medicament> medicaments) {}
}
