package application.model;

import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import application.model.produktion.Destillat;
import application.model.produktion.FadIndhold;
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

    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100.0);
        hylde = lager.createHylde();
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        fadIndhold = new FadIndhold(fad);
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
        assertEquals(20, fad.resterendePladsILiter());
    }

}