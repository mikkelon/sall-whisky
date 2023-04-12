package application.model;

import application.model.lager.FadLeverandør;
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
    void contructorTC1(){
        assertEquals("Garrison Brothers",fadLeverandør.getNavn());
        assertEquals("USA",fadLeverandør.getLand());
    }
}