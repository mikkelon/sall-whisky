package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private Lager lager;
    private FadLeverandør fadLeverandør;
    private Hylde hylde;
    private Fad fad;

    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
        lager = controller.createLagerWithAntalHylder("Baldersgade 39", "Sall Whisky Lager", 100, 4);
        fadLeverandør = controller.createFadLeverandør("Garrison Brothers", "USA");
        hylde = controller.createHylde(lager);
        fad = controller.createFad(FadType.BOURBON, 80, fadLeverandør, hylde);
    }
    @Test
    void SingletonTC1() {
        Controller c1 = Controller.getController();
        Controller c2 = Controller.getController();
        assertSame(c1, c2);
    }

    @Test
    void createLagerWithAntalHylderTC2() {
        assertTrue(controller.getLagre().contains(lager));
        assertEquals(5, lager.getHylder().size());
    }

    @Test
    void removeLagerNormalTC3() {
        for (Hylde h : lager.getHylder()) {
            for (Fad f : h.getFade()) {
                controller.removeFad(f);
            }
            controller.removeHylde(h);
        }
        controller.removeLager(lager);
        assertFalse(controller.getLagre().contains(lager));
    }

    @Test
    void removeLagerFejlTC4() {
        assertThrows(RuntimeException.class, () -> controller.removeLager(lager));
    }

    @Test
    void getLagreTC5() {
        assertTrue(controller.getLagre().contains(lager));
    }

    @Test
    void createFadLeverandørTC6() {
        assertTrue(controller.getFadLeverandører().contains(fadLeverandør));
    }

    @Test
    void removeFadLeverandørNormalTC7() {
        controller.removeFad(fad);
        controller.removeFadLeverandør(fadLeverandør);
        assertFalse(controller.getFadLeverandører().contains(fadLeverandør));
    }

    @Test
    void removeFadLeverandørFejlTC8() {
        assertThrows(RuntimeException.class, () -> controller.removeFadLeverandør(fadLeverandør));
    }

    @Test
    void getFadLeverandørTC9() {
        assertTrue(controller.getFadLeverandører().contains(fadLeverandør));
    }

    @Test
    void createHyldeTC10() {
        assertTrue(controller.getHylder().contains(hylde));
    }

    @Test
    void removeHyldeNormalTC11() {
        controller.removeFad(fad);
        controller.removeHylde(hylde);
        assertFalse(controller.getHylder().contains(hylde));
    }

    @Test
    void removeHyldeFejlTC12() {
        assertThrows(RuntimeException.class, () -> controller.removeHylde(hylde));
    }

    @Test
    void getHylderTC13() {
        assertTrue(controller.getHylder().contains(hylde));
    }

    @Test
    void getHylderByLagerTC14() {
        assertTrue(controller.getHylder(lager).contains(hylde));
    }

    @Test
    void createFadTC15() {
        assertTrue(hylde.getFade().contains(fad));
    }

    @Test
    void removeFadTC16() {
        controller.removeFad(fad);
        assertFalse(hylde.getFade().contains(fad));
    }
}