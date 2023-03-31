package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WhiskyTest {

    private Whisky whisky;
    private FadIndhold fadIndhold;
    private Fad fad;
    private FadLeverandør fadLeverandør;

    private Lager lager;
    private Hylde hylde;

    @BeforeEach
    void setUp() {
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        hylde = lager.createHylde();
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        whisky = new Whisky(43.0, "God vand","Meget god whisky");
        fadIndhold = new FadIndhold(fad);
    }

    @Test
    void constructorTC1() {
        //Her tester vi om at vi kan lave et nyt objekt af Whisky
        //Her får vi også testet alle get() metoderne undtagen for list.
        assertEquals(43.0, whisky.getMængdeVandILiter() );
        assertEquals("God vand", whisky.getVandAfstamning());
        assertEquals("Meget god whisky", whisky.getTekstBeskrivelse());

        //Test at whiskynr tæller 1 op for hver gang der oprettes en ny whisky
        Whisky whisky1 = new Whisky(43.0, "God vand","Meget god whisky");
        assertEquals(whisky.getWhiskyNr() + 1, whisky1.getWhiskyNr());
    }


    @Test
    void addAftapningTC2() {
        //Her tester vi om at vi kan tilføje en aftapning til en whisky
        Lager lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        Hylde hylde = new Hylde(lager);
        FadLeverandør fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        Fad fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        Whisky whisky2 = new Whisky(40.0, "Kilde vand", "En god whisky");
        Aftapning aftapning = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fadIndhold);

        whisky2.addAftapning(aftapning);
        assertTrue(whisky2.getAftapninger().contains(aftapning));
    }
}