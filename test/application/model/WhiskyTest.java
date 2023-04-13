package application.model;

import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import application.model.produktion.Aftapning;
import application.model.produktion.Whisky;
import application.model.produktion.Destillat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WhiskyTest {
    private Fad fad1, fad2;
    private FadLeverandør fadLeverandør;
    private Lager lager;
    private Hylde hylde;
    private Destillat destillat;

    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        hylde = lager.createHylde();
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        destillat = new Destillat("77p", "Anders", 61, 2,
                LocalDate.of(2019,1,1), LocalDate.of(2019,1,2),
                500, "Et velproduceret destillat", RygeMateriale.INTET);

        fad1 = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        fad1.påfyld(destillat, 80, "Mikkel", LocalDate.of(2019, 1, 1));

        fad2 = new Fad(FadType.SHERRY, 100, fadLeverandør, hylde);
        fad2.påfyld(destillat, 100, "Mikkel", LocalDate.of(2019, 1, 1));
    }

    @Test
    void constructorTC1() {
        Whisky whisky = new Whisky(43.0, "God vand","Meget god whisky");
        //Her tester vi om at vi kan lave et nyt objekt af Whisky
        //Her får vi også testet alle get() metoderne undtagen for list.
        assertEquals(43.0, whisky.getMængdeVandILiter() );
        assertEquals("God vand", whisky.getVandAfstamning());
        assertEquals("Meget god whisky", whisky.getTekstBeskrivelse());
    }


    @Test
    void addAftapningTC2() {
        //Her tester vi om at vi kan tilføje en aftapning til en whisky
        Whisky whisky = new Whisky(40.0, "Kilde vand", "En god whisky");
        Aftapning aftapning = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());

        whisky.addAftapning(aftapning);
        assertTrue(whisky.getAftapninger().contains(aftapning));
    }

    // #--- TESTS FOR getBetegnelse (TC1-5) ---#

    @Test
    void getBetegnelseTC1() {
        Whisky whisky = new Whisky(40.0, "Kilde vand", "En god whisky");
        assertEquals(null, whisky.getBetegnelse());
    }

    @Test void getBetegnelseTC2() {
        Aftapning aftapning1 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Aftapning aftapning2 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Whisky whisky = new Whisky(40.0, "Kilde vand", "En god whisky");
        whisky.addAftapning(aftapning1);
        whisky.addAftapning(aftapning2);

        assertEquals(Betegnelse.SINGLE_CASK, whisky.getBetegnelse());
    }

    @Test void getBetegnelseTC3() {
        Aftapning aftapning1 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Aftapning aftapning2 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Whisky whisky = new Whisky(0, "Kilde vand", "En god whisky");
        whisky.addAftapning(aftapning1);
        whisky.addAftapning(aftapning2);

        assertEquals(Betegnelse.CASK_STRENGTH, whisky.getBetegnelse());
    }

    @Test void getBetegnelseTC4() {
        Aftapning aftapning1 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Aftapning aftapning2 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad2.getFadIndhold());
        Whisky whisky = new Whisky(0, "Kilde vand", "En god whisky");
        whisky.addAftapning(aftapning1);
        whisky.addAftapning(aftapning2);

        assertEquals(Betegnelse.SINGLE_MALT, whisky.getBetegnelse());
    }

    @Test void getBetegnelseTC5() {
        Aftapning aftapning1 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Aftapning aftapning2 = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fad2.getFadIndhold());
        Whisky whisky = new Whisky(10, "Kilde vand", "En god whisky");
        whisky.addAftapning(aftapning1);
        whisky.addAftapning(aftapning2);

        assertEquals(Betegnelse.SINGLE_MALT, whisky.getBetegnelse());
    }

    // #--- TESTS FOR getAlkoholProcent (TC1-3) ---#

    @Test void getAlkoholProcentTC1() {
        Whisky whisky = new Whisky(10, "Kilde vand", "En god whisky");

        assertEquals(0.0, whisky.getAlkoholProcent());
    }

    @Test void getAlkoholProcentTC2() {
        fad1.getFadIndhold().setAlkoholProcentEfterModning(60);
        fad2.getFadIndhold().setAlkoholProcentEfterModning(59);
        Aftapning aftapning1 = new Aftapning("Frederikke", 10, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Aftapning aftapning2 = new Aftapning("Frederikke", 10, LocalDate.of(2023, 2, 2), fad2.getFadIndhold());
        Whisky whisky = new Whisky(10, "Kilde vand", "En god whisky");
        whisky.addAftapning(aftapning1);
        whisky.addAftapning(aftapning2);

        assertEquals(39.66, whisky.getAlkoholProcent(), 0.01);
    }

    @Test void getAlkoholProcentTC3() {
        fad1.getFadIndhold().setAlkoholProcentEfterModning(60);
        fad2.getFadIndhold().setAlkoholProcentEfterModning(59);
        Aftapning aftapning1 = new Aftapning("Frederikke", 10, LocalDate.of(2023, 2, 2), fad1.getFadIndhold());
        Aftapning aftapning2 = new Aftapning("Frederikke", 10, LocalDate.of(2023, 2, 2), fad2.getFadIndhold());
        Whisky whisky = new Whisky(0, "Kilde vand", "En god whisky");
        whisky.addAftapning(aftapning1);
        whisky.addAftapning(aftapning2);

        assertEquals(59.5, whisky.getAlkoholProcent(), 0.01);
    }
}