package application.model;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNr;
    private static int antalHylder = 0;
    private Lager lager;
    private final ArrayList<Fad> fade = new ArrayList<>();

    public Hylde(Lager lager) {
        antalHylder++;
        this.hyldeNr = antalHylder;
        this.lager = lager;
    }

    public int getHyldeNr() {
        return hyldeNr;
    }

    public Lager getLager() {
        return lager;
    }

    public void setLager(Lager lager) {
        if (lager == null) {
            throw new RuntimeException("En hylde skal være tilknyttet et lager.");
        }

        if (this.lager != lager) {
            Lager oldLager = this.lager;
            oldLager.removeHylde(this);
            this.lager = lager;
            lager.addHylde(this);
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
