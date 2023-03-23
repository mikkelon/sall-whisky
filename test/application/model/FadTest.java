package application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private Lager l1;
    private Hylde h1;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
       l1 = new Lager("Baldersgade 39", "Sall Whisky Lager", 100.0);
       h1 = new Hylde(l1);
       FadLeverandør fl1 = new FadLeverandør("Garrison Brothers", "USA");
       fad = new Fad(FadType.BOURBON, 80, fl1, h1);
    }

    @Test
    void ConstructorTC1() {
        FadLeverandør fl1 = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 80, fl1, h1);

        assertEquals(FadType.BOURBON, fad.getFadType());
        assertEquals(80, fad.getStørrelseILiter());
        assertTrue(fad.getFadNr() > 0);
        assertEquals(0, fad.getIndeholdtVæskeILiter());
        assertEquals(h1, fad.getHylde());
        assertEquals(fl1, fad.getFadLeverandør());
        assertTrue(h1.getFade().contains(fad));
    }

    @org.junit.jupiter.api.Test
    void getSetFadTypeTC2() {
        fad.setFadType(FadType.SHERRY);
        assertEquals(FadType.SHERRY, fad.getFadType());
    }

    @org.junit.jupiter.api.Test
    void getStørrelseILiter() {
    }

    @org.junit.jupiter.api.Test
    void getFadNr() {
    }

    @org.junit.jupiter.api.Test
    void getIndeholdtVæskeILiter() {
    }

    @org.junit.jupiter.api.Test
    void setFadType() {
    }

    @org.junit.jupiter.api.Test
    void getHylde() {
    }

    @org.junit.jupiter.api.Test
    void setIndeholdtVæskeILiter() {
    }

    @org.junit.jupiter.api.Test
    void getFadLeverandør() {
    }

    @org.junit.jupiter.api.Test
    void setHylde() {
    }
}