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
       hylde = lager.createHylde();
       fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
       fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
    }

    @Test
    void constructorTC1() {
        assertEquals(FadType.BOURBON, fad.getFadType());
        assertEquals(80, fad.getStørrelseILiter());
        assertEquals(0, fad.getIndeholdtVæskeILiter());
        assertEquals(hylde, fad.getHylde());
        assertEquals(fadLeverandør, fad.getFadLeverandør());
        assertTrue(hylde.getFade().contains(fad));

        // Test at antalFade tæller 1 op for hver gang der oprettes et nyt fad
        Fad fad2 = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        assertEquals(fad.getFadNr() + 1, fad2.getFadNr());
    }

    @Test
    void getSetFadTypeTC2() {
        fad.setFadType(FadType.SHERRY);
        assertEquals(FadType.SHERRY, fad.getFadType());
    }

    @Test
    void getStørrelseILiterTC3() {
        assertEquals(80, fad.getStørrelseILiter());
    }

    @Test
    void getSetIndeholdtVæskeILiterTC4() {
        fad.setIndeholdtVæskeILiter(50);
        assertEquals(50, fad.getIndeholdtVæskeILiter());
    }

    @Test
    void getHyldeTC5() {
        assertEquals(hylde, fad.getHylde());
    }

    @Test
    void getFadLeverandørTC6() {
        assertEquals(fadLeverandør, fad.getFadLeverandør());
    }

    @Test
    void setHyldeNormalTC7() {
        Hylde h2 = lager.createHylde();
        fad.setHylde(h2);
        assertEquals(h2, fad.getHylde());
        assertTrue(h2.getFade().contains(fad));
        assertFalse(hylde.getFade().contains(fad));
    }

    @Test
    void setHyldeFejlTC8() {
        assertThrows(RuntimeException.class, () -> fad.setHylde(null));
    }
}