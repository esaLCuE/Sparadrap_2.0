package test;

import classes.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static classes.MedecinTraitant.medecinsTraitants;
import static classes.Mutuelle.mutuelles;
import static org.junit.Assert.*;

class DenominationTest {

    static Mutuelle MuT = new Mutuelle("Mutuelle Test", "0 rue du test", "99999", "TestVille",
            "0999999990","mutuelle@test.com", 5);

    static Specialiste ST = new Specialiste("Test","Spécialiste","1 adresse Test","10000",
            "villeTest", "0100000000","lespecialiste@test.fr","Cardiologie");

    static List<Specialiste> specTest = new ArrayList<>(Arrays.asList(ST));

    static MedecinTraitant MeT1 = new MedecinTraitant("Traitest", "Médecin", "3 adresse Test", "01000",
            "villeTest", "07.77/77 77-77", "medecin@test.net", "06171812548");

    static Client clientTest = new Client("ndfTest", "preTest", "1 adresse test", "00001","villeTest",
            "0600000001", "email@test.com","101012A11122233", LocalDate.of(2000,01,01),
            MuT, MeT1/*, specTest*/);

    @BeforeAll
    static void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setNdf() {
        clientTest.setNdf("Ndf");
        assertEquals("Nom de famille", "Ndf", clientTest.getNdf());

        try {
            clientTest.setNdf("");
        } catch (NullPointerException e) {}
        assertFalse(clientTest.getNdf().equals(""));

        try {
            clientTest.setNdf(null);
        } catch (NullPointerException e) {}
        assertFalse(clientTest.getNdf() == null);

        try {
            clientTest.setNdf("   ");
        } catch (NullPointerException e) {}
        assertFalse(Objects.equals(clientTest.getNdf(), "   "));

        try {
            clientTest.setNdf("sdz8694634eaz'('-é");
        } catch (InputMismatchException e) {}
        assertFalse(Objects.equals(clientTest.getNdf(), "sdz8694634eaz'('-é"));
    }

    @Test
    void setPrenom() {
        clientTest.setPrenom("Prénom");
        assertEquals("Prénom", "Prénom", clientTest.getPrenom());

        try {
            clientTest.setPrenom("");
        } catch (NullPointerException e) {}
        assertFalse("Prénom", clientTest.getPrenom().equals(""));

        try {
            clientTest.setPrenom(null);
        } catch (NullPointerException e) {}
        assertFalse("Prénom", clientTest.getPrenom() == null);

        try {
            clientTest.setPrenom("   ");
        } catch (NullPointerException e) {}
        assertFalse(Objects.equals(clientTest.getPrenom(), "   "));

        try {
            clientTest.setPrenom("sdz8694634eaz'('-é");
        } catch (InputMismatchException e) {}
        assertFalse(Objects.equals(clientTest.getPrenom(), "sdz8694634eaz'('-é"));
    }

    @Test
    void setNom() {
        clientTest.setNom("Ndf", "Prénom");
        assertEquals("Nom", "Prénom Ndf", clientTest.getNom());

        try {
            clientTest.setNom("", "Prénom");
        } catch (NullPointerException e) {}
        assertFalse("Nom", clientTest.getNom().equals("Prénom "));

        try {
            clientTest.setNom("Ndf", "");
        } catch (NullPointerException e) {}
        assertFalse("Nom", clientTest.getNom().equals(" Ndf"));

        try {
            clientTest.setNom(null, "Prénom");
        } catch (NullPointerException e) {}
        assertFalse("Nom", Objects.equals(clientTest.getNom(), "Prénom"));

        try {
            clientTest.setNom("Ndf", null);
        } catch (NullPointerException e) {}
        assertFalse("Nom", Objects.equals(clientTest.getNom(), "Ndf"));

        try {
            clientTest.setNom("   ", "Prénom");
        } catch (NullPointerException e) {}
        assertFalse("Nom", Objects.equals(clientTest.getNom(), "Prénom    "));

        try {
            clientTest.setNom("Ndf", "   ");
        } catch (NullPointerException e) {}
        assertFalse("Nom", Objects.equals(clientTest.getNom(), "    Ndf"));

        try {
            clientTest.setNom("fde897g4a64rf86(-)", "Prénom");
        } catch (InputMismatchException e) {}
        assertFalse("Nom", Objects.equals(clientTest.getNom(), "Prénom fde897g4a64rf86(-)"));

        try {
            clientTest.setNom("Ndf", "fde897g4a64rf86(-)");
        } catch (InputMismatchException e) {}
        assertFalse("Nom", Objects.equals(clientTest.getNom(), "fde897g4a64rf86(-) Ndf"));
    }

    @Test
    void setNomSoc() {
        try {
            MuT.setNomSoc("");
        } catch (NullPointerException e) {}
        assertFalse("NomSoc", clientTest.getNomSoc().equals(""));

        try {
            MuT.setNomSoc("   ");
        } catch (NullPointerException e) {}
        assertFalse("NomSoc", clientTest.getNomSoc().equals("   "));

        try {
            MuT.setNomSoc(null);
        } catch (NullPointerException e) {}
        assertFalse("NomSoc", clientTest.getNomSoc() == null);
    }
}