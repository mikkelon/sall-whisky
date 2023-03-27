package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PåfyldningTest {
    private Påfyldning påfyldning;
    private Destillat destillat;
    private Fad fad;

    @BeforeEach
    void setUp() {
        Lager lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        Hylde hylde = lager.createHylde();
        FadLeverandør fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 90.0, fadLeverandør, hylde);
        destillat = new Destillat("77p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky",  RygeMateriale.INTET);
        påfyldning = new Påfyldning(destillat, fad, "Mikkel", 80.0,
                LocalDate.of(2023, 4, 1));
    }

    @Test
    void ConstructorTC1() {
        assertEquals(destillat, påfyldning.getDestillat());
        assertEquals(fad, påfyldning.getFad());
        assertEquals("Mikkel", påfyldning.getPåfyldtAf());
        assertEquals(80.0, påfyldning.getMængdeILiter());
        assertEquals(LocalDate.of(2023, 4, 1), påfyldning.getPåfyldningsDato());
    }

    @Test
    void getPåfyldtAfTC2() {
        assertEquals("Mikkel", påfyldning.getPåfyldtAf());
    }

    @Test
    void getMængdeILiterTC3() {
        assertEquals(80.0, påfyldning.getMængdeILiter());
    }

    @Test
    void getPåfyldningsDatoTC4() {
        assertEquals(LocalDate.of(2023, 4, 1), påfyldning.getPåfyldningsDato());
    }

    @Test
    void getFadTC5() {
        assertEquals(fad, påfyldning.getFad());
    }

    @Test
    void getDestillatTC6() {
        assertEquals(destillat, påfyldning.getDestillat());
    }
}