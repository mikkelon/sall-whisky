package application.model;

import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import application.model.produktion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private Lager lager;
    private Hylde hylde;
    private FadLeverandør fadLeverandør;
    private LocalDate startDato, slutDato;
    private Destillat destillat;
    private LocalDate påfyldningsDato;

    private FadIndhold fadIndhold;
    private Påfyldning påfyldning;

    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100.0);
        hylde = lager.createHylde();
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        startDato = LocalDate.of(2023, 1, 1);
        slutDato = LocalDate.of(2023, 1, 2);
        destillat = new Destillat("77p", "Jens", 62, 2, startDato, slutDato,
                100, "Kommentar", null);
        påfyldningsDato = LocalDate.of(2023, 1, 2);
        påfyldning = fad.påfyld(destillat,
                10, "Mads", LocalDate.of(2023, 4, 13));
        fadIndhold = fad.getFadIndhold();
    }

    @Test
    void constructorTC1() {
        assertEquals(FadType.BOURBON, fad.getFadType());
        assertEquals(80, fad.getStørrelseILiter());
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
    void getHyldeTC4() {
        assertEquals(hylde, fad.getHylde());
    }

    @Test
    void getFadLeverandørTC5() {
        assertEquals(fadLeverandør, fad.getFadLeverandør());
    }

    @Test
    void setHyldeNormalTC6() {
        Hylde h2 = lager.createHylde();
        fad.setHylde(h2);
        assertEquals(h2, fad.getHylde());
        assertTrue(h2.getFade().contains(fad));
        assertFalse(hylde.getFade().contains(fad));
    }

    @Test
    void setHyldeFejlTC7() {
        assertThrows(RuntimeException.class, () -> fad.setHylde(null));
    }


    @Test
    void resterendePladsILiterTC8() {
        fad.påfyld(destillat,
                60, "Mads", LocalDate.of(2023, 2, 2));
        assertEquals(10, fad.resterendePladsILiter());
    }

    @Test
    void setFadIndholdTC9() {
        FadIndhold nytFadIndhold = new FadIndhold(fad);

        fad.setFadIndhold(nytFadIndhold);
        assertEquals(nytFadIndhold, fad.getFadIndhold());
    }

    @Test
    void getFadIndholdsHistorikTC10() {
        fad.aftap("Mads", 70, LocalDate.of(2023, 2, 3));
        assertEquals(1, fad.getFadIndholdsHistorik().size());
    }

    @Test
    void removeFromFadIndholdHistorikTC11() {
        fad.påfyld(destillat,
                60, "Mads", LocalDate.of(2023, 2, 2));
        fad.aftap("Mads", 70, LocalDate.of(2023, 2, 3));
        assertTrue(fad.getFadIndholdsHistorik().contains(fadIndhold));
        fad.removeFromFadIndholdHistorik(fadIndhold);
        assertEquals(0, fad.getFadIndholdsHistorik().size());
    }

    @Test
    void isEmptyTC12() {
        fad.setFadIndhold(null);
        assertTrue(fad.isEmpty());
    }

    @Test
    void isEmptyTC13() {
        fad.påfyld(destillat,
                60, "Mads", LocalDate.of(2023, 2, 2));
        assertFalse(fad.isEmpty());
    }

    @Test
    void isFullTC14() {
        assertFalse(fad.isFull());
    }

    @Test
    void isFullTC15() {
        fad.påfyld(destillat,
                fad.getStørrelseILiter() - fad.resterendePladsILiter() - 10, "Mads", LocalDate.of(2023, 2, 2));
        assertFalse(fad.isFull());
    }

    @Test
    void isFullTC16() {
        fad.påfyld(destillat,
                fad.resterendePladsILiter(), "Mads", LocalDate.of(2023, 2, 2));
        assertTrue(fad.isFull());
    }

    @Test
    void påfyldTC17() {
        fad.setFadIndhold(null);
        påfyldning = fad.påfyld(destillat,
                10, "Mads", LocalDate.of(2023, 4, 13));

        assertNotNull(fad.getFadIndhold());
        assertTrue(fad.getFadIndhold().getPåfyldninger().contains(påfyldning));
        assertEquals(fad, påfyldning.getFadIndhold().getFad());
    }

    @Test
    void påfyldTC18() {
        assertTrue(fad.getFadIndhold().getPåfyldninger().contains(påfyldning));
        assertEquals(fad, påfyldning.getFadIndhold().getFad());
    }

    @Test
    void aftapTC19() {
        Aftapning aftapning = fad.aftap("Anders", 7.2, LocalDate.of(2023, 4, 13));

        assertTrue(fad.getFadIndhold().getAftapninger().contains(aftapning));
        assertEquals(fadIndhold, aftapning.getFadIndhold());
    }

    @Test
    void aftapTC20() {
        Aftapning aftapning = fad.aftap("Anders", fad.resterendePladsILiter(), LocalDate.of(2023, 4, 13));

        assertTrue(fadIndhold.getAftapninger().contains(aftapning));
        assertEquals(fadIndhold, aftapning.getFadIndhold());

        assertTrue(fad.getFadIndholdsHistorik().contains(fadIndhold));
        assertNull(fad.getFadIndhold());
    }

    @Test
    void omhældTC21() {
        Fad nytFad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        Omhældning omhældning = fad.omhæld("Mads", 10, LocalDate.of(2023, 4, 13), nytFad);

        assertNotNull(nytFad.getFadIndhold());
        assertEquals(nytFad.getFadIndhold(), omhældning.getTilFadIndhold());
        assertTrue(nytFad.getFadIndhold().getTilføjedeOmhældninger().contains(omhældning));

        assertEquals(fadIndhold, omhældning.getFraFadIndhold());
        assertTrue(fadIndhold.getFjernedeOmhældninger().contains(omhældning));
    }

    @Test
    void omhældTC22() {
        Fad nytFad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        nytFad.påfyld(destillat,
                10, "Mads", LocalDate.of(2023, 2, 2));

        Omhældning omhældning = fad.omhæld("Mads", 10, LocalDate.of(2023, 4, 13), nytFad);

        assertEquals(nytFad.getFadIndhold(), omhældning.getTilFadIndhold());
        assertTrue(nytFad.getFadIndhold().getTilføjedeOmhældninger().contains(omhældning));

        assertEquals(fadIndhold, omhældning.getFraFadIndhold());
        assertTrue(fadIndhold.getFjernedeOmhældninger().contains(omhældning));
    }

    @Test
    void resterendePladsILiterTC23() {
        fad.setFadIndhold(null);
        assertEquals(fad.getStørrelseILiter(), fad.resterendePladsILiter());
    }

    @Test
    void resterendePLadsILiterTC24() {
        assertEquals(80 - 10, fad.resterendePladsILiter());
    }

    @Test
    void toStringTC25() {
        fad.getFadIndhold().setAlkoholProcentEfterModning(60);

        assertEquals("Fad: " + fad.getFadNr() + ", type: Bourbon (10.0/80.0L) (62.0% / 60.0%)", fad.toString());
    }

    @Test
    void toStringTC26() {
        fad.getFadIndhold().setAlkoholProcentEfterModning(-1);

        assertEquals("Fad: " + fad.getFadNr() + ", type: Bourbon (10.0/80.0L) (62.0%)", fad.toString());
    }

    @Test
    void toStringTC27() {
        fad.setFadIndhold(null);

        assertEquals("Fad: " + fad.getFadNr() + ", type: Bourbon (0.0/80.0L)", fad.toString());
    }

    @Test
    void hentHistorikTC28() {
        assertTrue(fad.hentHistorik().contains("Fadtype: Bourbon\nFadnr: " + fad.getFadNr() + "\nStørrelse: 80.0\nFadet er på lager: "));
    }
}