package application.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private Lager lager;
    private Hylde hylde;
    private FadLeverandør fadLeverandør;

    @BeforeEach
    void setUp() {
       lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100.0);
       hylde = new Hylde(lager);
       fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
       fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
    }

    @Test
    void constructorTC1() {
        assertEquals(FadType.BOURBON, fad.getFadType());
        assertEquals(80, fad.getStørrelseILiter());
        assertTrue(fad.getFadNr() > 0);
        assertEquals(0, fad.getIndeholdtVæskeILiter());
        assertEquals(hylde, fad.getHylde());
        assertEquals(fadLeverandør, fad.getFadLeverandør());
        assertTrue(hylde.getFade().contains(fad));
    }


    @Test
    void getSetFadTypeTC2() {
        fad.setFadType(FadType.SHERRY);
        assertEquals(FadType.SHERRY, fad.getFadType());
    }

    @org.junit.jupiter.api.Test
    void getStørrelseILiterTC3() {
        assertEquals(80, fad.getStørrelseILiter());
    }

    @org.junit.jupiter.api.Test
    void getFadNrTC4() {
        assertTrue(fad.getFadNr() > 0);
    }

    @org.junit.jupiter.api.Test
    void getSetIndeholdtVæskeILiterTC5() {
        fad.setIndeholdtVæskeILiter(50);
        assertEquals(50, fad.getIndeholdtVæskeILiter());
    }

    @org.junit.jupiter.api.Test
    void getHyldeTC6() {
        assertEquals(hylde, fad.getHylde());
    }

    @org.junit.jupiter.api.Test
    void getFadLeverandørTC7() {
        assertEquals(fadLeverandør, fad.getFadLeverandør());
    }

    @org.junit.jupiter.api.Test
    void setHyldeNormalTC8() {
        Hylde h2 = new Hylde(lager);
        fad.setHylde(h2);
        assertEquals(h2, fad.getHylde());
    }

    @org.junit.jupiter.api.Test
    void setHyldeFejlTC9() {
        assertThrows(RuntimeException.class, () -> fad.setHylde(null));
    }
}