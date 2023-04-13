package application.controller;

import application.model.*;
import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerForLagerTest {
    private ControllerForLager controllerForLager;
    private Hylde hylde;
    private Fad fad;
    private Lager lager;
    private FadLeverandør fadLeverandør;


    @BeforeEach
    void setUp() {
        controllerForLager = ControllerForLager.getTestController();
        controllerForLager.clearStorage();
        lager = controllerForLager.createLagerWithAntalHylder("Baldersgade 39", "Sall Whisky Lager", 100, 4);
        fadLeverandør = controllerForLager.createFadLeverandør("Garrison Brothers", "USA");
        hylde = controllerForLager.createHylde(lager);
        fad = controllerForLager.createFad(FadType.BOURBON, 80, fadLeverandør, hylde);
    }

    @Test
    void SingletonTC1() {
        ControllerForLager c1 = ControllerForLager.getController();
        ControllerForLager c2 = ControllerForLager.getController();
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
                controllerForLager.fjernFadFraHylde(f);
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
        controllerForLager.fjernFadFraHylde(fad);
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
    void fjernFadFraHyldeTC16() {
        controllerForLager.fjernFadFraHylde(fad);
        assertFalse(hylde.getFade().contains(fad));
    }
}