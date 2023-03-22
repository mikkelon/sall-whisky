package application.model;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNr;
    private final ArrayList<Fad> fade = new ArrayList<>();

    public Hylde(int hyldeNr) {
        this.hyldeNr = hyldeNr;
    }

    public int getHyldeNr() {
        return hyldeNr;
    }

    public ArrayList<Fad> getFade(){
        return new ArrayList<>(fade);
    }

    public void setHyldeNr(int hyldeNr) {
        this.hyldeNr = hyldeNr;
    }

    public void addFad(Fad fad){
        if(!fade.contains(fad)){
            fade.add(fad);
        }
    }

    public void removeFad(Fad fad){
        if(fade.contains(fad)){
            fade.remove(fad);
        }
    }
}
