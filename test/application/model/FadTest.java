package application.model;
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

    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100.0);
        hylde = lager.createHylde();
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        startDato = LocalDate.of(2023, 1, 1);
        slutDato = LocalDate.of(2023, 1, 2);
        destillat = new Destillat("77p", "Jens", 61, 2, startDato, slutDato,
                100, "Kommentar", null);
        påfyldningsDato = LocalDate.of(2023, 1, 2);
    }

    @Test
    void constructorTC1() {
        assertEquals(FadType.BOURBON, fad.getFadType());
        assertEquals(80, fad.getStørrelseILiter());
        assertEquals(0, fad.indeholdtVæskeILiter());
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

    // TC4 er udgået, da getIndeholdtVæskeILiter() er erstattet med indeholdtVæskeILiter()

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

    @Test
    void addPåfyldningTC9() {
        Påfyldning påfyldning = new Påfyldning(destillat, fad, "Mikkel", 25, påfyldningsDato);
        fad.addPåfyldning(påfyldning);
        assertTrue(fad.getPåfyldninger().contains(påfyldning));
    }

    @Test
    void indeholdtVæskeILiterTC10() {
        Påfyldning påfyldning1 = new Påfyldning(destillat, fad, "Mikkel", 25, påfyldningsDato);
        Påfyldning påfyldning2 = new Påfyldning(destillat, fad, "Mikkel", 50, påfyldningsDato);
        fad.addPåfyldning(påfyldning1);
        fad.addPåfyldning(påfyldning2);

        assertEquals(75, fad.indeholdtVæskeILiter());
    }

    @Test
    void resterendePladsILiterTC11() {
        Påfyldning påfyldning1 = new Påfyldning(destillat, fad, "Mikkel", 25, påfyldningsDato);
        Påfyldning påfyldning2 = new Påfyldning(destillat, fad, "Mikkel", 50, påfyldningsDato);
        fad.addPåfyldning(påfyldning1);
        fad.addPåfyldning(påfyldning2);

        assertEquals(5, fad.resterendePladsILiter());
    }

    @Test
    void forventetFærdigproduceretTC12() {
        LocalDate nyPåfyldningsDato = LocalDate.of(2023, 1, 3);
        Påfyldning påfyldning1 = new Påfyldning(destillat, fad, "Mikkel", 25, påfyldningsDato);
        Påfyldning påfyldning2 = new Påfyldning(destillat, fad, "Mikkel", 50, nyPåfyldningsDato);
        fad.addPåfyldning(påfyldning1);
        fad.addPåfyldning(påfyldning2);

        LocalDate forventetSlutDato = LocalDate.of(2026, 1, 3);
        assertEquals(forventetSlutDato, fad.forventetFærdigproduceret());
    }

    @Test
    void getAlkoholProcentTC13() {
        assertEquals(0, fad.getAlkoholProcent());
    }

    @Test
    void getAlkoholProcentTC14() {
        Destillat destillat1 = new Destillat("77p", "Jens", 50, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning1 = new Påfyldning(destillat1, fad, "Mikkel", 1, påfyldningsDato);
        fad.addPåfyldning(påfyldning1);

        assertEquals(50, fad.getAlkoholProcent());
    }

    @Test
    void getAlkoholProcentTC15() {
        Destillat destillat1 = new Destillat("77p", "Jens", 40, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning1 = new Påfyldning(destillat1, fad, "Mikkel", 2, påfyldningsDato);
        fad.addPåfyldning(påfyldning1);

        Destillat destillat2 = new Destillat("77p", "Jens", 60, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning2 = new Påfyldning(destillat2, fad, "Mikkel", 1, påfyldningsDato);
        fad.addPåfyldning(påfyldning2);

        assertEquals(46.6667, fad.getAlkoholProcent(), 0.0001);
    }

    @Test
    void getAlkoholProcentTC16() {
        Destillat destillat1 = new Destillat("77p", "Jens", 0, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning1 = new Påfyldning(destillat1, fad, "Mikkel", 1, påfyldningsDato);
        fad.addPåfyldning(påfyldning1);

        Destillat destillat2 = new Destillat("77p", "Jens", 100, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning2 = new Påfyldning(destillat2, fad, "Mikkel", 1, påfyldningsDato);
        fad.addPåfyldning(påfyldning2);

        assertEquals(50, fad.getAlkoholProcent(), 0.0001);
    }

    @Test
    void getAlkoholProcentTC17() {
        Destillat destillat1 = new Destillat("77p", "Jens", 20, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning1 = new Påfyldning(destillat1, fad, "Mikkel", 1, påfyldningsDato);
        fad.addPåfyldning(påfyldning1);

        Destillat destillat2 = new Destillat("77p", "Jens", 40, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning2 = new Påfyldning(destillat2, fad, "Mikkel", 2, påfyldningsDato);
        fad.addPåfyldning(påfyldning2);

        Destillat destillat3 = new Destillat("77p", "Jens", 60, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning3 = new Påfyldning(destillat3, fad, "Mikkel", 1, påfyldningsDato);
        fad.addPåfyldning(påfyldning3);

        assertEquals(40, fad.getAlkoholProcent(), 0.0001);
    }
}