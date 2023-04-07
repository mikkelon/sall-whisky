package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerForProduktionTest {
    private ControllerForProduktion controllerForProduktion;
    private ControllerForLager controllerForLager;
    private Destillat destillat;
    private Påfyldning påfyldning;
    private Fad fad;
    private FadLeverandør fadLeverandør;
    private Hylde hylde;
    private Lager lager;


    @BeforeEach
    void setUp() {
        controllerForProduktion = ControllerForProduktion.getTestController();
        controllerForLager = ControllerForLager.getTestController();
        controllerForProduktion.clearStorage();
        fadLeverandør = controllerForLager.createFadLeverandør("Garrison Brothers", "USA");
        lager = controllerForLager.createLagerWithAntalHylder("Baldersgade 39", "Sall Whisky Lager", 100, 4);
        hylde = controllerForLager.createHylde(lager);

        fad = controllerForLager.createFad(FadType.BOURBON, 80, fadLeverandør, hylde);
        destillat = controllerForProduktion.createDestillat("77p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky", RygeMateriale.INTET);
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
    void getDestillaterTC2() {
        assertTrue(controllerForProduktion.getDestillater().contains(destillat));
    }

    @Test
    void createDestillatTC3() {
        assertTrue(controllerForProduktion.getDestillater().contains(destillat));
    }

    @Test
    void createDestillatTC4() {
        assertThrows(RuntimeException.class, () -> controllerForProduktion.createDestillat("77p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky", RygeMateriale.INTET));
    }

    @Test
    void removeDestillatTC5() {
        Destillat destillat1 = controllerForProduktion.createDestillat("78p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky", RygeMateriale.INTET);

        controllerForProduktion.removeDestillat(destillat1);
        assertFalse(controllerForProduktion.getDestillater().contains(destillat1));
    }

    @Test
    void removeDestillatTC6() {
        assertThrows(RuntimeException.class, () -> controllerForProduktion.removeDestillat(destillat));
    }

    @Test
    void createPåfyldningTC7() {
        assertTrue(destillat.getPåfyldninger().contains(påfyldning));
    }

    @Test
    void createPåfyldningTC8() {
        Destillat destillat1 = controllerForProduktion.createDestillat("78p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27),
                LocalDate.of(2023, 3, 30), 80.0,
                "Destillat for bourbon whisky", RygeMateriale.INTET);

        assertThrows(RuntimeException.class, () -> controllerForProduktion.createPåfyldning(destillat1, fad, "Mikkel", 80.0,
                LocalDate.of(2023, 4, 1)));
    }

    @Test
    void createPåfyldningTC9() {
        assertThrows(RuntimeException.class, () -> controllerForProduktion.createPåfyldning(destillat, fad, "Mikkel", 90.0,
                LocalDate.of(2023, 4, 1)));
    }
}