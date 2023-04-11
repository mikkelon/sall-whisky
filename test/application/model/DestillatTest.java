package application.model;

import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import application.model.produktion.Destillat;
import application.model.produktion.FadIndhold;
import application.model.produktion.Påfyldning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DestillatTest {
    private Destillat destillat;
    private RygeMateriale rygeMateriale;
    private FadIndhold fadIndhold;
    private Fad fad;
    private Lager lager;
    private Hylde hylde;


    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        hylde = lager.createHylde();
        fad = new Fad(FadType.BOURBON, 90.0, new FadLeverandør("Garrison Brothers", "USA"), hylde);
        fadIndhold = new FadIndhold(fad);
        rygeMateriale = RygeMateriale.TØRV;
        destillat = new Destillat("77p", "Mikkel", 53.0,
                2, LocalDate.of(2023, 3, 27), LocalDate.of(2023, 3, 30),
                80.0, "Destillat for bourbon whisky", rygeMateriale);
    }

    @Test
    void constructorTC1() {
        assertEquals("77p", destillat.getNewMakeNr());
        assertEquals("Mikkel", destillat.getMedarbejder());
        assertEquals(53.0, destillat.getAlkoholProcent());
        assertEquals(2, destillat.getAntalDestilleringer());
        assertEquals(LocalDate.of(2023, 3, 27), destillat.getStartDato());
        assertEquals(LocalDate.of(2023, 3, 30), destillat.getSlutDato());
        assertEquals(80.0, destillat.getMængdeILiter());
        assertEquals("Destillat for bourbon whisky", destillat.getKommentar());
        assertEquals(rygeMateriale, destillat.getRygeMateriale());
    }


    @Test
    void addPåfyldningerTC2() {
        Påfyldning påfyldning = new Påfyldning(destillat, fadIndhold, "Mikkel", 80.0, LocalDate.of(2023, 4, 1));
        destillat.addPåfyldning(påfyldning);
        assertTrue(destillat.getPåfyldninger().contains(påfyldning));
    }


    @Test
    void removePåfyldningTC3() {
        Påfyldning påfyldning = new Påfyldning(destillat, fadIndhold, "Mikkel", 80, LocalDate.of(2023, 4, 1));
        destillat.addPåfyldning(påfyldning);
        destillat.removePåfyldning(påfyldning);
        assertFalse(destillat.getPåfyldninger().contains(påfyldning));
    }
}