package application.model;

import java.util.ArrayList;

public class Leverandør {
    private String navn;
    private String land;

    private ArrayList<Fad> fade = new ArrayList<>();

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

    public ArrayList<Fad> getFade() {
        return fade;
    }


    public void addFad(Fad fad) {
        if (!this.fade.contains(fad)) {
            fade.add(fad);
        }
    }

    public void removeFad(Fad fad) {
        if (this.fade.contains(fad)) {
            fade.remove(fad);
        }
    }
}

