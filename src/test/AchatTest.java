package test;

import classes.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static classes.Mutuelle.mutuelles;
import static classes.MedecinTraitant.medecinsTraitants;
import static org.junit.jupiter.api.Assertions.*;

class AchatTest {

    static Mutuelle MuT = new Mutuelle("Mutuelle Test", "0 rue du test", "99999", "TestVille",
            "0999999990","mutuelle@test.com", 5);

    static Mutuelle MuT2 = new Mutuelle("Deuxième Mutuelle Test", "2 rue du test", "88888", "DeuxTestVille",
            "0888888880", "mutuelle@deuxtest.com", 10);



    static List<Medicament> medicamentsTest = new ArrayList<>();
    static List<Medicament> medicamentsTest2 = new ArrayList<>();

    static List<Integer> quantitesTest = new ArrayList<>(Arrays.asList(2));
    static List<Integer> quantitesTest2 = new ArrayList<>(Arrays.asList(2,4));

    static List<Achat> achatsTest = new ArrayList<>();

    static Specialiste ST = new Specialiste("Test","Spécialiste","1 adresse Test","10000",
            "villeTest", "0100000000","lespecialiste@test.fr","Cardiologie");
    static Specialiste ST2 = new Specialiste("TestDeux","SpécialisteDeux","2 adresse Test","20000",
            "villeTestDeux", "0200000000","lespecialiste@testdeux.fr","Cardiologie");

    static List<Specialiste> specTest = new ArrayList<>(Arrays.asList(ST, ST2));
    static List<Specialiste> specTest2 = new ArrayList<>(Arrays.asList(ST, ST2));

    static MedecinTraitant MeT1 = new MedecinTraitant("Traitest", "Médecin", "3 adresse Test", "01000",
            "villeTest", "07.77/77 77-77", "medecin@test.net", "06171812548");

    static MedecinTraitant MeT2 = new MedecinTraitant("TraitestDeux", "MédecinDeux", "3 adresse Test Deux", "02000",
            "villeTestDeux", "07.77/77 77-78", "medecin2@testdeux.net", "06171812549");

    static Client clientTest1 = new Client("ndfTest", "preTest", "1 adresse test", "00001","villeTest",
            "0600000001", "email@test.com","101012A11122233", LocalDate.of(2000,01,01),
            MuT, MeT1/*, specTest*/);

    static Client clientTest2 = new Client("Deuxième", "Client", "2 adresse test","00002","Deuxième ville test",
            "0600000002", "deuxieme@email.test", "201012A11122233", LocalDate.of(2002, 02,02),
            MuT2, MeT2/*, specTest2*/);

    static Medicament medTest1 = new Medicament("MédiTest", 10, LocalDate.of(1990,01,01),"Antibiotique");
    static Medicament medTest2 = new Medicament("MédeuxTest", 10, LocalDate.of(1990,01,01),"Antibiotique");

    static Achat achTest = new Achat(clientTest1, LocalDate.of(2010,01,01));

    @BeforeAll
    static void setUp() {
        mutuelles.add(MuT);

        medecinsTraitants.add(MeT1);
        medecinsTraitants.add(MeT2);

        medicamentsTest.add(medTest1);
        medicamentsTest2.add(medTest1);
        medicamentsTest2.add(medTest2);

        achatsTest.add(achTest);

    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    void testSetClientAchat() {
        achTest.setClientAchat(clientTest2);
        assertEquals(clientTest2, achTest.getClientAchat(), "Client");
    }

    @Test
    void testSetMedecinTraitant() {
        achTest.setMedecinTraitant(MeT2);
        assertEquals(MeT2, achTest.getMedecinTraitant(), "Médecin traitant");
    }

    @Test
    void testSetSpecialisteAchat() {
        achTest.setSpecialisteAchat(ST2);
        assertEquals(ST2, achTest.getSpecialisteAchat(), "Specialiste");
    }
/*
    @Test
    void testSetListeMedicaments() {
        achTest.setListeMedicaments(medicamentsTest2);
        assertEquals(medicamentsTest2, achTest.getListeMedicaments(), "Médicaments");
    }

    @Test
    void testSetListeQuantites() {
        achTest.setListeQuantites(quantitesTest2);
        assertEquals(quantitesTest2, achTest.getListeQuantites(), "Quantités");
    }

    @Test
    void testSetDateAchat() {
        LocalDate dateTest = LocalDate.of(2012,02,02);
        achTest.setDateAchat(dateTest);
        assertEquals(dateTest, achTest.getDateAchat(), "Date");
        assertTrue(dateTest.isAfter(achTest.getClientAchat().getDateNaissanceOri()));
        for(int i=0;i<achTest.getListeMedicaments().size();i++) {
            assertTrue(dateTest.isAfter(achTest.getListeMedicaments().get(i).getMiseEnService()));
        }
    }
*/
    @Test
    void testAjoutAchat() {
        Achat achTest2 = new Achat(clientTest2, LocalDate.of(2010,01,01));
        achatsTest.add(achTest2);
        assertTrue(achatsTest.contains(achTest2), "Ajout d'achat");
    }

    @Test
    void testRetraitAchat() {
        achatsTest.remove(achTest);
        assertFalse(achatsTest.contains(achTest), "Retrait d'achat");
    }
/*
    @Test
    void testSetOrdo() {
        achTest.setOrdo(false);
        assertTrue(!achTest.getOrdo(), "Booléen ordonnance");
    }
*/
}