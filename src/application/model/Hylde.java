package application.model;

import java.util.ArrayList;

/**
 * Modellerer en hylde på et lager.
 */
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

    /**
     * Returnerer hyldens unikke nummer.
     * @return unikt nummer for hylden
     */
    public int getHyldeNr() {
        return hyldeNr;
    }

    /**
     * Returnerer lageret hvor hylden er placeret.
     * @return lageret hvor hylden er placeret
     */
    public Lager getLager() {
        return lager;
    }

    /**
     * Registrerer lageret hvor hylden er placeret.
     * @param lager er hyldens nye lager
     */
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
    }

    /**
     * Returnerer en liste af fade der opbevares på hylden.
     * @return en liste af fade der opbevares på hylden
     */
    public ArrayList<Fad> getFade(){
        return new ArrayList<>(fade);
    }

    /**
     * Tilføjer et fad til hylden.
     * @param fad er fadet der skal tilføjes til hylden
     */
    public void addFad(Fad fad){
        if(!fade.contains(fad)){
            fade.add(fad);
        }
    }

    /**
     * Fjerner et fad fra hylden.
     * @param fad er fadet der skal fjernes fra hylden
     */
    public void removeFad(Fad fad){
        if(fade.contains(fad)){
            fade.remove(fad);
        }
    }
}
