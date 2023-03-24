package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {
    private Lager lager;

    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100.0);
    }

    @Test
    void constructorTC1() {
        assertEquals("Baldersgade 39", lager.getAdresse());
        assertEquals("Sall Whisky Lager", lager.getNavn());
        assertEquals(100.0, lager.getKvm());
    }

    @Test
    void getSetAdresseTC2() {
        lager.setAdresse("Otte Ruds Gade 24");
        assertEquals("Otte Ruds Gade 24", lager.getAdresse());
    }

    @Test
    void getSetNavnTC3() {
        lager.setNavn("Sall Whisky Fjernlager");
        assertEquals("Sall Whisky Fjernlager", lager.getNavn());
    }

    @Test
    void getSetKvmTC4() {
        lager.setKvm(200);
        assertEquals(200, lager.getKvm());
    }

    @Test
    void getHylderTC5() {
        lager.createHylde();
        assertEquals(1, lager.getHylder().size());
    }

    @Test
    void createHyldeTC6() {
        Hylde hylde = lager.createHylde();
        assertTrue(lager.getHylder().contains(hylde));
    }

    @Test
    void removeHyldeTC7() {
        Hylde hylde = lager.createHylde();
        lager.removeHylde(hylde);
        assertFalse(lager.getHylder().contains(hylde));
    }

    @Test
    void removeHyldeTC8(){
        Hylde hylde = lager.createHylde();
        FadLeverandør fadLeverandør = new FadLeverandør("Anders","Danmark");
        Fad fad = new Fad(FadType.BOURBON,100,fadLeverandør,hylde);
        assertThrows(RuntimeException.class,()-> lager.removeHylde(hylde));

    }
}