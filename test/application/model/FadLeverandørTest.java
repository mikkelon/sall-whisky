package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FadLeverandørTest {

    FadLeverandør fadLeverandør;

    @BeforeEach
    void setUp() {
        fadLeverandør = new FadLeverandør("Garrison Brothers", "USA");
    }

    /**
     * TC1 - Constructor test.
     */
    @Test
    void fadLeverandør(){

    }

    /**
     * TC2 - get navn test.
     */
    @Test
    void getNavn() {
        assertEquals("Garrison Brothers", fadLeverandør.getNavn());
    }

    /**
     * TC3 - get land test.
     */
    @Test
    void getLand() {
        assertEquals("USA", fadLeverandør.getLand());
    }
}