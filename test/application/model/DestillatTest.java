package application.model;

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
    void contruscterTC1(){
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
    void getNewMakeNr() {
        assertEquals("77p",destillat.getNewMakeNr());
    }

    @Test
    void getMedarbejder() {
        assertEquals("Mikkel",destillat.getMedarbejder());
    }

    @Test
    void getAlkoholProcent() {
        assertEquals(53.0,destillat.getAlkoholProcent());
    }

    @Test
    void getAntalDestilleringer() {
        assertEquals(2,destillat.getAntalDestilleringer());
    }

    @Test
    void getStartDato() {
        assertEquals(LocalDate.of(2023,3,27),destillat.getStartDato());
    }

    @Test
    void getSlutDato() {
        assertEquals(LocalDate.of(2023,3,30),destillat.getSlutDato());
    }

    @Test
    void getMængdeILiter() {
        assertEquals(80.0,destillat.getMængdeILiter());
    }

    @Test
    void getKommentar() {
        assertEquals("Destillat for bourbon whisky",destillat.getKommentar());
    }

    @Test
    void getRygeMateriale() {
        assertEquals(rygeMateriale,destillat.getRygeMateriale());
    }

    @Test
    void getOgAddPåfyldninger() {

    }

    @Test
    void addPåfyldning() {
    }

    @Test
    void removePåfyldning() {
    }
}