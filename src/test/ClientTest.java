package test;

import classes.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    static Mutuelle MuT = new Mutuelle("Mutuelle Test", "0 rue du test", "99999", "TestVille",
            "0999999990","mutuelle@test.com", 5);

    static Mutuelle MuT2 = new Mutuelle("Deuxième Mutuelle Test", "2 rue du test", "88888", "DeuxTestVille",
            "0888888880", "mutuelle@deuxtest.com", 10);

    static List<Medicament> medicamentsTest = new ArrayList<>();
    static List<Medicament> medicamentsTest2 = new ArrayList<>();

    static Specialiste ST = new Specialiste("Test","Spécialiste","1 adresse Test","10000",
            "villeTest", "0100000000","lespecialiste@test.fr","Cardiologie");
    static Specialiste ST2 = new Specialiste("TestDeux","SpécialisteDeux","2 adresse Test","20000",
            "villeTestDeux", "0200000000","lespecialiste@testdeux.fr","Cardiologie");

    static List<Specialiste> specTest = new ArrayList<>(Arrays.asList(ST));
    static List<Specialiste> specTest2 = new ArrayList<>(Arrays.asList(ST, ST2));

    static MedecinTraitant MeT1 = new MedecinTraitant("Traitest", "Médecin", "3 adresse Test", "01000",
            "villeTest", "07.77/77 77-77", "medecin@test.net", "06171812548");

    static MedecinTraitant MeT2 = new MedecinTraitant("TraitestDeux", "MédecinDeux", "3 adresse Test Deux", "02000",
            "villeTestDeux", "07.77/77 77-78", "medecin2@testdeux.net", "06171812549");

    static Client clientTest1 = new Client("ndfTest", "preTest", "1 adresse test", "00001","villeTest",
            "0600000001", "email@test.com","101012A11122233", LocalDate.of(2000,1,1),
            MuT, MeT1, specTest);

    static Medicament medTest1 = new Medicament("MédiTest","Antibiotique", 10, LocalDate.of(1990,1,1));
    static Medicament medTest2 = new Medicament("MédeuxTest","Antibiotique", 10, LocalDate.of(1990,1, 1));

    @BeforeAll
    static void setUp() {
        medicamentsTest.add(medTest1);
        medicamentsTest2.add(medTest1);
        medicamentsTest2.add(medTest2);

    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    void setSecuSociale() {
        clientTest1.setSecuSociale("299032A44477711");
        assertEquals(clientTest1.getSecuSociale(), "299032A44477711", "Sécurité sociale");
    }

    @Test
    void setDateNaissance() {
        clientTest1.setDateNaissance(LocalDate.of(1900,1,1));
        assertEquals(clientTest1.getDateNaissanceOri(), LocalDate.of(1900,1,1), "Date de naissance");
    }

    @Test
    void setMutuelle() {
        clientTest1.setMutuelle(MuT2);
        assertEquals(clientTest1.getMutuelle(), MuT2, "Mutuelle");
    }

    @Test
    void setMedecinTraitant() {
        clientTest1.setMedecinTraitant(MeT2);
        assertEquals(clientTest1.getMedecinTraitant(), MeT2, "Médecin");
    }

    @Test
    void setSpecialistesClient() {
        clientTest1.setSpecialistesClient(specTest2);
        assertEquals(clientTest1.getSpecialistesClient(), specTest2, "Specialiste Client");
    }
}