package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DestillatTest {
    private Destillat destillat;
    private RygeMateriale rygeMateriale;

    @BeforeEach
    void setUp() {
        rygeMateriale = RygeMateriale.TØRV;
        destillat = new Destillat("77p", "Mikkel",53.0,
                2, LocalDate.of(2023,3,27),LocalDate.of(2023,3,30),
                80.0,"Destillat for bourbon whisky",rygeMateriale);
    }

    @Test
    void constructorTC1(){
        assertEquals("77p",destillat.getNewMakeNr());
        assertEquals("Mikkel",destillat.getMedarbejder());
        assertEquals(53.0,destillat.getAlkoholProcent());
        assertEquals(2,destillat.getAntalDestilleringer());
        assertEquals(LocalDate.of(2023,3,27),destillat.getStartDato());
        assertEquals(LocalDate.of(2023,3,30),destillat.getSlutDato());
        assertEquals(80.0,destillat.getMængdeILiter());
        assertEquals("Destillat for bourbon whisky",destillat.getKommentar());
        assertEquals(rygeMateriale,destillat.getRygeMateriale());
    }


    @Test
    void addPåfyldningerTC2() {
        Lager lager = new Lager("Baldersgade 39","Sall Whisky Lager",100);
        Hylde hylde = lager.createHylde();
        FadLeverandør fadLeverandør = new FadLeverandør("Garrison Brothers","USA");
        Fad fad = new Fad(FadType.BOURBON,90.0,fadLeverandør,hylde);
        Påfyldning påfyldning = new Påfyldning(destillat,fad,"Mikkel",80,LocalDate.of(2023,4,1));
        destillat.addPåfyldning(påfyldning);
        assertTrue(destillat.getPåfyldninger().contains(påfyldning));
    }


    @Test
    void removePåfyldningTC3() {
        Lager lager = new Lager("Baldersgade 39","Sall Whisky Lager",100);
        Hylde hylde = lager.createHylde();
        FadLeverandør fadLeverandør = new FadLeverandør("Garrison Brothers","USA");
        Fad fad = new Fad(FadType.BOURBON,90.0,fadLeverandør,hylde);
        Påfyldning påfyldning = new Påfyldning(destillat,fad,"Mikkel",80,LocalDate.of(2023,4,1));
        destillat.addPåfyldning(påfyldning);
        destillat.removePåfyldning(påfyldning);
        assertFalse(destillat.getPåfyldninger().contains(påfyldning));
    }
}