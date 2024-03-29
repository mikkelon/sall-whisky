package application.model;

import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import application.model.produktion.Aftapning;
import application.model.produktion.FadIndhold;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AftapningTest {

    private Aftapning aftapning;
    private Fad fad;
    private Lager lager;
    private FadLeverandør fadLeverandør;
    private Hylde hylde;
    private FadIndhold fadIndhold;


    @BeforeEach
    void setUp() {
        fadIndhold = new FadIndhold(fad);
        lager = new Lager("Baldersgade 39", "Sall Whisky Lager", 100);
        hylde = lager.createHylde();
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
        fad = new Fad(FadType.BOURBON, 80, fadLeverandør, hylde);
        aftapning = new Aftapning("Frederikke", 40, LocalDate.of(2023, 2, 2), fadIndhold);
    }

    @Test
    void constructerTC1() {
        //Her tester vi om at vi får lavet et nyt objekt af Aftapning.
        //Her får vi også testet alle get() metoderne.
        assertEquals("Frederikke", aftapning.getAftappetAf());
        assertEquals(40, aftapning.getMængdeILiter());
        assertEquals(LocalDate.of(2023, 2, 2), aftapning.getAftapningsDato());
        assertEquals(fadIndhold, aftapning.getFadIndhold());
    }
}