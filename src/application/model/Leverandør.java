package application.model;

import java.util.ArrayList;

public class Leverandør {
    private String navn;
    private String land;


    /**
     * Initialiserer en ny leverandørs navn og land.
     * @param navn leverandørens navn
     * @param land leverandørens land
     */
    public Leverandør(String navn, String land) {
        this.navn = navn;
        this.land = land;
    }

    /**
     * Returnerer leverandørens navn
     * @return leverandørens navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * Returnerer leverandørens land
     * @return leverandørens land
     */
    public String getLand() {
        return land;
    }

}

