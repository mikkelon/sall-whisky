package application.model;

import java.util.ArrayList;

public class Leverandør {
    private String navn;
    private String land;


    public Leverandør(String navn, String land) {
        this.navn = navn;
        this.land = land;
    }

    public String getNavn() {
        return navn;
    }

    public String getLand() {
        return land;
    }

}

