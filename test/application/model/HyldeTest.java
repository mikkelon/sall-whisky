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
        hylde = new Hylde(lager);
        FadLeverandør fadleverandør = new FadLeverandør("Garrison Brothers", "USA");
        Fad fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);


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
    void getOgSetLagerTC3() {
        hylde.setLager(lager);
        assertTrue(hylde.getLager().equals(lager));

    }


    @Test
    void getFadeTC4() {
        hylde.addFad(fad);
        assertTrue(hylde.getFade().contains(fad));
    }

    @Test
    void addFadTC5() {

        hylde.addFad(fad);
        assertTrue(hylde.getFade().contains(fad));
    }

    @Test
    void removeFadTC6() {
        hylde.addFad(fad);
        hylde.removeFad(fad);
        assertTrue(hylde.getFade().isEmpty());
    }
}