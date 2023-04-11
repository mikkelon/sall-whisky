package application.model;

import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HyldeTest {

    private Hylde hylde;

    private Lager lager;

    private FadLeverandør fadLeverandør;

    private Fad fad;

    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        hylde = lager.createHylde();
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
    }


    @Test
    void constructorTC1() {
        assertEquals(hylde.getLager(), lager); // Tester om lageret er tilføjet til hylden
        assertTrue(lager.getHylder().contains(hylde)); // Tester om hylde er tilføjet til lageret

        // Tester om hylde nummeret tæller op korrekt
        // Her får vi også testet getHyldeNr()
        Lager lager2 = new Lager("Testvej 2", "Test Lager 2", 200);
        Hylde hylde1 = lager2.createHylde();
        assertEquals(1, hylde1.getHyldeNr());
        Hylde hylde2 = lager2.createHylde();
        assertEquals(2, hylde2.getHyldeNr());
        Hylde hylde3 = lager2.createHylde();
        assertEquals(3, hylde3.getHyldeNr());
    }

    @Test
    void getLagerTC2() {
        assertTrue(hylde.getLager().equals(lager));
    }

    @Test
    void getFadeTC3() {
        assertTrue(hylde.getFade().contains(fad));
    }

    @Test
    void addFadTC4() {
        Hylde nyHylde = lager.createHylde();
        Fad nytFad = new Fad(FadType.SHERRY, 50, fadLeverandør, hylde);
        assertFalse(nyHylde.getFade().contains(nytFad)); // Tester at den nye hylde ikke indeholder fadet
        nyHylde.addFad(nytFad);
        assertTrue(nyHylde.getFade().contains(nytFad)); // Tester at den nye hylde indeholder fadet
    }

    @Test
    void removeFadTC5() {
        Fad nytFad = new Fad(FadType.SHERRY, 50, fadLeverandør, hylde);
        hylde.removeFad(nytFad);
        assertFalse(hylde.getFade().contains(nytFad));
    }
}