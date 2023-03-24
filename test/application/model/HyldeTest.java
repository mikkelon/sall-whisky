package application.model;

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
    void constructerTC1() {
        assertEquals(hylde.getLager(), lager);
        assertTrue(hylde.getHyldeNr() > 0);
    }

    @Test
    void getHyldeNrTC2() {
        assertTrue(hylde.getHyldeNr() > 0);
    }

    @Test
    void getLagerTC3() {
        assertTrue(hylde.getLager().equals(lager));
    }


    @Test
    void getFadeTC4() {
        assertTrue(hylde.getFade().contains(fad));
    }

    @Test
    void addFadTC5() {
        Hylde hylde2 = lager.createHylde();
        hylde2.addFad(fad);
        assertTrue(hylde2.getFade().contains(fad));
        assertFalse(hylde.getFade().contains(fad));
    }

    @Test
    void removeFadTC6() {
        Fad nytFad = new Fad(FadType.SHERRY, 50, fadLeverandør, hylde);
        hylde.addFad(nytFad);
        hylde.removeFad(nytFad);
        assertFalse(hylde.getFade().contains(nytFad));
    }
}