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

class FadIndholdTest {
    private Fad fad1, fad2;
    private Hylde hylde;
    private Lager lager;
    private FadLeverandør fadLeverandør;
    private Destillat destillat;
    private LocalDate påfyldningsDato;
    private LocalDate startDato, slutDato;


    @BeforeEach
    void setUp() {
        destillat = new Destillat("77p", "Jens", 80, 2, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2), 500, "Kommentar", RygeMateriale.TØRV);
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        hylde = lager.createHylde();
        fad1 = new Fad(FadType.BOURBON, 80.0, fadLeverandør, hylde);
        fad2 = new Fad(FadType.SHERRY, 100, fadLeverandør, hylde);
        påfyldningsDato = LocalDate.of(2023, 1, 2);
        startDato = LocalDate.of(2023, 1, 1);
        slutDato = LocalDate.of(2023, 1, 2);
    }


    @Test
    void constructorTC1() {
        FadIndhold fadIndhold = new FadIndhold(fad1);
        assertEquals(fad1, fadIndhold.getFad());
    }


    @Test
    void forventetFærdigProduceretTC2() {
        fad1.påfyld(destillat, 10, "Mikkel", påfyldningsDato);
        fad1.påfyld(destillat, 10, "Mikkel", påfyldningsDato);
        LocalDate forventetSlutDato = LocalDate.of(2026, 1, 2);
        assertEquals(forventetSlutDato, fad1.getFadIndhold().forventetFærdigProduceret());

    }


    @Test
    void getPåfyldningerOgAddTC3() {
        Påfyldning påfyldning = fad1.påfyld(destillat, 80, "Mikkel", påfyldningsDato);
        assertTrue(fad1.getFadIndhold().getPåfyldninger().contains(påfyldning));
    }

    @Test
    void getAftapningerOgAddTC4() {
        fad1.påfyld(destillat, 80, "Mikkel", påfyldningsDato);
        Aftapning aftapning = fad1.aftap("Mikkel", 60, LocalDate.of(2023, 1, 3));
        assertTrue(fad1.getFadIndhold().getAftapninger().contains(aftapning));
    }

    @Test
    void getAlkoholProcentTC5() {
        Destillat destillat1 = new Destillat("77p", "Jens", 40, 2,
                startDato, slutDato, 100, "Kommentar", null);
        fad1.påfyld(destillat1, 20, "Mikkel", påfyldningsDato);

        Destillat destillat2 = new Destillat("77p", "Jens", 60, 2,
                startDato, slutDato, 100, "Kommentar", null);
        fad1.påfyld(destillat2, 20, "Mikkel", påfyldningsDato);

        assertEquals(50, fad1.getFadIndhold().getAlkoholProcent(), 0.0001);
    }


    @Test
    void getAlkoholProcentTC6() {
        Destillat destillat1 = new Destillat("77p", "Jens", 20, 2,
                startDato, slutDato, 100, "Kommentar", null);
        fad1.påfyld(destillat1, 20, "Mikkel", påfyldningsDato);

        Destillat destillat2 = new Destillat("77p", "Jens", 40, 2,
                startDato, slutDato, 100, "Kommentar", null);
        fad1.påfyld(destillat2, 20, "Mikkel", påfyldningsDato);
        Destillat destillat3 = new Destillat("77p", "Jens", 60, 2,
                startDato, slutDato, 100, "Kommentar", null);
        fad1.påfyld(destillat3, 20, "Mikkel", påfyldningsDato);

        assertEquals(40, fad1.getFadIndhold().getAlkoholProcent(), 0.0001);
    }

    // #--- TESTS FOR getMængde() ---#

    @Test
    void getMængdeTC1() {
        fad1.påfyld(destillat, 2, "Mikkel", påfyldningsDato);

        assertEquals(2, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC2() {
        fad1.påfyld(destillat, 3, "Mikkel", påfyldningsDato);
        fad1.påfyld(destillat, 5, "Mikkel", påfyldningsDato);

        assertEquals(8, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC3() {
        fad1.påfyld(destillat, 10, "Mikkel", påfyldningsDato);
        fad1.aftap("Mikkel", 5, LocalDate.of(2023, 1, 3));

        assertEquals(5, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC4() {
        fad1.påfyld(destillat, 10, "Mikkel", påfyldningsDato);
        fad1.aftap("Mikkel", 5, LocalDate.of(2023, 1, 3));
        fad1.aftap("Mikkel", 2, LocalDate.of(2023, 1, 3));

        assertEquals(3, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC5() {
        fad1.påfyld(destillat, 5, "Mikkel", påfyldningsDato);
        fad1.omhæld("Mikkel", 3, LocalDate.of(2023, 1, 3), fad2);

        assertEquals(2, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC6() {
        fad2.påfyld(destillat, 100, "Mikkel", påfyldningsDato);
        fad2.omhæld("Mikkel", 5, LocalDate.of(2023, 1, 3), fad1);
        fad2.omhæld("Mikkel", 2, LocalDate.of(2023, 1, 3), fad1);

        fad1.påfyld(destillat, 10, "Mikkel", påfyldningsDato);

        assertEquals(17, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC7() {
        fad2.påfyld(destillat, 100, "Mikkel", påfyldningsDato);
        fad2.omhæld("Mikkel", 5, LocalDate.of(2023, 1, 3), fad1);
        fad2.omhæld("Mikkel", 10, LocalDate.of(2023, 1, 3), fad1);

        fad1.omhæld("Mikkel", 3, LocalDate.of(2023, 1, 3), fad2);

        assertEquals(12, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC8() {
        fad2.påfyld(destillat, 100, "Mikkel", påfyldningsDato);
        fad2.omhæld("Mikkel", 5, LocalDate.of(2023, 1, 3), fad1);
        fad2.omhæld("Mikkel", 10, LocalDate.of(2023, 1, 3), fad1);

        fad1.påfyld(destillat, 5, "Mikkel", påfyldningsDato);
        fad1.omhæld("Mikkel", 3, LocalDate.of(2023, 1, 3), fad2);

        assertEquals(17, fad1.getFadIndhold().getMængde());
    }

    @Test
    void getMængdeTC9() {
        fad2.påfyld(destillat, 100, "Mikkel", påfyldningsDato);
        fad2.omhæld("Mikkel", 5, LocalDate.of(2023, 1, 3), fad1);
        fad2.omhæld("Mikkel", 10, LocalDate.of(2023, 1, 3), fad1);

        fad1.påfyld(destillat, 5, "Mikkel", påfyldningsDato);
        fad1.aftap("Mikkel", 5, LocalDate.of(2023, 1, 3));
        fad1.aftap("Mikkel", 10, LocalDate.of(2023, 1, 3));
        fad1.omhæld("Mikkel", 3, LocalDate.of(2023, 1, 3), fad2);

        assertEquals(2, fad1.getFadIndhold().getMængde());
    }
}