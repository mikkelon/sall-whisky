package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WhiskyTest {

    private Whisky whisky;

    @BeforeEach
    void setUp() {
        whisky = new Whisky(43.0, Betegnelse.SINGLECASK, 0.7, "Kilde vand", "En god whisky");

    }

    @Test
    void constructorTC1() {
        //Tester om hylde nummeret tæller op korrekt
        //Her får vi også testet alle get() metoderne undtagen for list.
        assertEquals(43.0, whisky.getAlkoholProcent());
        assertEquals(Betegnelse.SINGLECASK, whisky.getBetegnelse());
        assertEquals(0.7, whisky.getMængdeVandILiter());
        assertEquals("Kilde vand", whisky.getVandAfstamning());
        assertEquals("En god whisky", whisky.getTekstBeskrivelse());
        assertEquals(1, whisky.getWhiskyNr());
    }

    @Test
    void getWhiskyNr() {
        //Her tester vi igen om at den tæller op på whisky nummeret når vi laver et nyt objekt
        Whisky whisky1 = new Whisky(40.0, Betegnelse.SINGLECASK, 0.7, "Kilde vand", "En god whisky");
        assertEquals(2, whisky1.getWhiskyNr());
    }


    @Test
    void addAftapning() {
        //Her tester vi om at vi kan tilføje en aftapning til en whisky
        Lager lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        Hylde hylde = new Hylde(lager);
        FadLeverandør fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        Fad fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        Whisky whisky2 = new Whisky(40.0, Betegnelse.SINGLECASK, 0.7, "Kilde vand", "En god whisky");
        Aftapning aftapning = new Aftapning("Frederikke",40,LocalDate.of(2023,2,2),fad,whisky2);

        whisky2.addAftapning(aftapning);
        assertTrue(whisky2.getAftapninger().contains(aftapning));
    }
}