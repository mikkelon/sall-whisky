package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadIndholdTest {
    private FadIndhold fadIndhold;
    private Fad fad;

    private Hylde hylde;
    private Lager lager;
    private FadLeverandør fadLeverandør;
    private Destillat destillat;

    private LocalDate påfyldningsDato;

    private Whisky whisky;

    private LocalDate startDato, slutDato;


    @BeforeEach
    void setUp() {
        whisky = new Whisky(40, "Lars' vand", "God whisky");
        destillat = new Destillat("77p", "Jens", 80, 2, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2), 100, "Kommentar", RygeMateriale.TØRV);
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        hylde = new Hylde(lager);
        fad = new Fad(FadType.BOURBON, 90.0, fadLeverandør, hylde);
        fadIndhold = new FadIndhold(40.0, fad);
        påfyldningsDato = LocalDate.of(2023, 1, 2);
        startDato = LocalDate.of(2023, 1, 1);
        slutDato = LocalDate.of(2023, 1, 2);
    }


    @Test
    void constructerTC1() {
        assertEquals(40.0, fadIndhold.getAlkoholProcentEfterModning());
        assertNull(fadIndhold.getSenestPåfyldt());
        assertEquals(fad, fadIndhold.getFad());
    }


    @Test
    void forventetFærdigProduceretTC2() {
        Påfyldning påfyldning1 = new Påfyldning(destillat, fadIndhold, "Mikkel", 80, påfyldningsDato);
        Påfyldning påfyldning2 = new Påfyldning(destillat, fadIndhold, "Mikkel", 90, påfyldningsDato);
        fadIndhold.addPåfyldning(påfyldning1);
        fadIndhold.addPåfyldning(påfyldning2);

        LocalDate forventetSlutDato = LocalDate.of(2026, 1, 2);
        assertEquals(forventetSlutDato, fadIndhold.forventetFærdigProduceret());

    }


    @Test
    void getPåfyldningerOgAdd() {
        Påfyldning påfyldning1 = new Påfyldning(destillat, fadIndhold, "Mikkel", 80, påfyldningsDato);
        fadIndhold.addPåfyldning(påfyldning1);
        assertTrue(fadIndhold.getPåfyldninger().contains(påfyldning1));

    }

    @Test
    void getAftapningerOgAdd() {
        Aftapning aftapning = new Aftapning("Mikkel", 60, LocalDate.of(2023, 1, 3), fadIndhold);
        fadIndhold.addAftapning(aftapning);
        assertTrue(fadIndhold.getAftapninger().contains(aftapning));
    }


    @Test
    void getAlkoholProcent2() {
        Destillat destillat1 = new Destillat("77p", "Jens", 50, 2, startDato, slutDato, 100, "Kommentar", RygeMateriale.TØRV);
        Påfyldning påfyldning1 = new Påfyldning(destillat1, fadIndhold, "Mikkel", 80, påfyldningsDato);
        fadIndhold.addPåfyldning(påfyldning1);

        assertEquals(50.0, fadIndhold.getAlkoholProcent());
    }

    @Test
    void getAlkoholProcent() {
        Destillat destillat1 = new Destillat("77p", "Jens", 40, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning = new Påfyldning(destillat1, fadIndhold, "Mikkel", 80, påfyldningsDato);
        fadIndhold.addPåfyldning(påfyldning);

        Destillat destillat2 = new Destillat("77p", "Jens", 60, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning2 = new Påfyldning(destillat2, fadIndhold, "Mikkel", 80, påfyldningsDato);
        fadIndhold.addPåfyldning(påfyldning2);

        assertEquals(50, fadIndhold.getAlkoholProcent(), 0.0001);
    }


    @Test
    void getAlkoholProcentTC287378264() {
        Destillat destillat1 = new Destillat("77p", "Jens", 20, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning = new Påfyldning(destillat1, fadIndhold, "Mikkel", 80, påfyldningsDato);
        Destillat destillat2 = new Destillat("77p", "Jens", 40, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning2 = new Påfyldning(destillat2, fadIndhold, "Mikkel", 80, påfyldningsDato);
        Destillat destillat3 = new Destillat("77p", "Jens", 60, 2,
                startDato, slutDato, 100, "Kommentar", null);
        Påfyldning påfyldning3 = new Påfyldning(destillat3, fadIndhold, "Mikkel", 80, påfyldningsDato);

        assertEquals(40, fadIndhold.getAlkoholProcent(), 0.0001);

    }


    @Test
    void getMængde() {
        Påfyldning påfyldning1 = new Påfyldning(destillat, fadIndhold, "Mikkel", 25, påfyldningsDato);
        Påfyldning påfyldning2 = new Påfyldning(destillat, fadIndhold, "Mikkel", 50, påfyldningsDato);
        fadIndhold.addPåfyldning(påfyldning1);
        fadIndhold.addPåfyldning(påfyldning2);

        assertEquals(75, fadIndhold.getMængde());

    }
}