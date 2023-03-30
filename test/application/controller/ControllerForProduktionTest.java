package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerForProduktionTest {
    private ControllerForProduktion controllerForProduktion;
    private ControllerForLager controllerForLager;
    private Lager lager;
    private FadLeverandør fadLeverandør;
    private Hylde hylde;
    private Fad fad;
    private Destillat destillat;
    private Påfyldning påfyldning;

    @BeforeEach
    void setUp() {
        controllerForProduktion = ControllerForProduktion.getTestController();
        controllerForProduktion.clearStorage();
        lager = controllerForLager.createLagerWithAntalHylder("Baldersgade 39", "Sall Whisky Lager", 100, 4);
        fadLeverandør = controllerForLager.createFadLeverandør("Garrison Brothers", "USA");
        hylde = controllerForLager.createHylde(lager);
        fad = controllerForLager.createFad(FadType.BOURBON, 80, fadLeverandør, hylde);
        destillat = controllerForProduktion.createDestillat("77p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky",  RygeMateriale.INTET);
        påfyldning = controllerForProduktion.createPåfyldning(destillat, fad, "Mikkel", 80.0,
                LocalDate.of(2023, 4, 1));
    }
    @Test
    void SingletonTC1() {
        ControllerForProduktion c1 = ControllerForProduktion.getController();
        ControllerForProduktion c2 = ControllerForProduktion.getController();
        assertSame(c1, c2);
    }

    @Test
    void createLagerWithAntalHylderTC2() {
        assertTrue(controllerForLager.getLagre().contains(lager));
        assertEquals(5, lager.getHylder().size());
    }

    @Test
    void removeLagerNormalTC3() {
        for (Hylde h : lager.getHylder()) {
            for (Fad f : h.getFade()) {
                controllerForLager.removeFad(f);
            }
            controllerForLager.removeHylde(h);
        }
        controllerForLager.removeLager(lager);
        assertFalse(controllerForLager.getLagre().contains(lager));
    }

    @Test
    void removeLagerFejlTC4() {
        assertThrows(RuntimeException.class, () -> controllerForLager.removeLager(lager));
    }

    @Test
    void getLagreTC5() {
        assertTrue(controllerForLager.getLagre().contains(lager));
    }

    @Test
    void createFadLeverandørTC6() {
        assertTrue(controllerForLager.getFadLeverandører().contains(fadLeverandør));
    }

    @Test
    void removeFadLeverandørNormalTC7() {
        controllerForLager.removeFad(fad);
        controllerForLager.removeFadLeverandør(fadLeverandør);
        assertFalse(controllerForLager.getFadLeverandører().contains(fadLeverandør));
    }

    @Test
    void removeFadLeverandørFejlTC8() {
        assertThrows(RuntimeException.class, () -> controllerForLager.removeFadLeverandør(fadLeverandør));
    }

    @Test
    void getFadLeverandørTC9() {
        assertTrue(controllerForLager.getFadLeverandører().contains(fadLeverandør));
    }

    @Test
    void createHyldeTC10() {
        assertTrue(controllerForLager.getHylder().contains(hylde));
    }

    @Test
    void removeHyldeNormalTC11() {
        controllerForLager.removeFad(fad);
        controllerForLager.removeHylde(hylde);
        assertFalse(controllerForLager.getHylder().contains(hylde));
    }

    @Test
    void removeHyldeFejlTC12() {
        assertThrows(RuntimeException.class, () -> controllerForLager.removeHylde(hylde));
    }

    @Test
    void getHylderTC13() {
        assertTrue(controllerForLager.getHylder().contains(hylde));
    }

    @Test
    void getHylderByLagerTC14() {
        assertTrue(controllerForLager.getHylder(lager).contains(hylde));
    }

    @Test
    void createFadTC15() {
        assertTrue(hylde.getFade().contains(fad));
    }

    @Test
    void removeFadTC16() {
        int fadLeverandørAntalFade = fad.getFadLeverandør().getAntalFade();
        controllerForLager.removeFad(fad);
        assertFalse(hylde.getFade().contains(fad));
       assertEquals(fadLeverandørAntalFade - 1, fad.getFadLeverandør().getAntalFade());
    }

    @Test
    void getDestillaterTC17() {
        assertTrue(controllerForProduktion.getDestillater().contains(destillat));
    }

    @Test
    void createDestillatTC18() {
        assertTrue(controllerForProduktion.getDestillater().contains(destillat));
    }

    @Test
    void createDestillatTC19() {
        assertThrows(RuntimeException.class, () -> controllerForProduktion.createDestillat("77p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky",  RygeMateriale.INTET));
    }

    @Test
    void removeDestillatTC20() {
        Destillat destillat1 = controllerForProduktion.createDestillat("78p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky",  RygeMateriale.INTET);

        controllerForProduktion.removeDestillat(destillat1);
        assertFalse(controllerForProduktion.getDestillater().contains(destillat1));
    }

    @Test
    void removeDestillatTC21() {
        assertThrows(RuntimeException.class, () -> controllerForProduktion.removeDestillat(destillat));
    }

    @Test
    void createPåfyldningTC22() {
        assertTrue(fad.getPåfyldninger().contains(påfyldning));
        assertTrue(destillat.getPåfyldninger().contains(påfyldning));
    }

    @Test
    void createPåfyldningTC23() {
        Destillat destillat1 = controllerForProduktion.createDestillat("78p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky",  RygeMateriale.INTET);

        assertThrows(RuntimeException.class, () -> controllerForProduktion.createPåfyldning(destillat1, fad, "Mikkel", 80.0,
                LocalDate.of(2023, 4, 1)));
    }

    @Test
    void createPåfyldningTC24() {
        assertThrows(RuntimeException.class, () -> controllerForProduktion.createPåfyldning(destillat, fad, "Mikkel", 90.0,
                LocalDate.of(2023, 4, 1)));
    }
}