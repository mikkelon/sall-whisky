package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaltbatchTest {

    private Maltbatch maltbatch;


    @BeforeEach
    void setUp() {
        maltbatch = new Maltbatch("Byg", "Larses mark", "Larses gård", "Lars", true);
    }

    @Test
    void constructerTC1() {
        // Her tester vi om vores constructor bliver lavet
        //Vi tester også get metoderne.
        assertEquals("Byg", maltbatch.getKornsort());
        assertEquals("Larses mark", maltbatch.getMark());
        assertEquals("Larses gård", maltbatch.getGård());
        assertEquals("Lars", maltbatch.getDyrketAf());
        assertTrue(maltbatch.isØkologisk());
    }
}