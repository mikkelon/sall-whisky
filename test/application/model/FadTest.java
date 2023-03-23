package application.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private Lager l1;
    private Hylde h1;
    private FadLeverandør fl1;

    @BeforeEach
    void setUp() {
       l1 = new Lager("Baldersgade 39", "Sall Whisky Lager", 100.0);
       h1 = new Hylde(l1);
       fl1 = new FadLeverandør("Garrison Brothers", "USA");
       fad = new Fad(FadType.BOURBON, 80, fl1, h1);
    }

    @Test
    void constructorTC1() {
        fad = new Fad(FadType.BOURBON, 80, fl1, h1);

        assertEquals(FadType.BOURBON, fad.getFadType());
        assertEquals(80, fad.getStørrelseILiter());
        assertTrue(fad.getFadNr() > 0);
        assertEquals(0, fad.getIndeholdtVæskeILiter());
        assertEquals(h1, fad.getHylde());
        assertEquals(fl1, fad.getFadLeverandør());
        assertTrue(h1.getFade().contains(fad));
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
        assertEquals(h1, fad.getHylde());
    }

    @org.junit.jupiter.api.Test
    void getFadLeverandørTC7() {
        assertEquals(fl1, fad.getFadLeverandør());
    }

    @org.junit.jupiter.api.Test
    void setHyldeNormalTC8() {
        Hylde h2 = new Hylde(l1);
        fad.setHylde(h2);
        assertEquals(h2, fad.getHylde());
    }

    @org.junit.jupiter.api.Test
    void setHyldeFejlTC9() {
        assertThrows(RuntimeException.class, () -> fad.setHylde(null));
    }
}