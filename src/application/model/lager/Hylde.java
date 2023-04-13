package application.model.lager;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Modellerer en hylde på et lager.
 */
public class Hylde implements Comparable<Hylde>{
    private int hyldeNr;
    private Lager lager;
    private final Set<Fad> fade = new TreeSet<>();

    /**
     * Opretter en hylde på et specifikt lager.
     * Hylden får et unikt hyldenummer for det specifikke lager.
     * @param lager lageret hvor hylden skal oprettes
     */
    Hylde(Lager lager) {
        this.hyldeNr = lager.getAntalHylder();
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
     * Returnerer en liste af fade der opbevares på hylden.
     * @return en liste af fade der opbevares på hylden
     */
    public Set<Fad> getFade(){
        return new TreeSet<>(fade);
    }

    /**
     * Tilføjer et fad til hylden.
     * @param fad er fadet der skal tilføjes til hylden
     */
    public void addFad(Fad fad){
        if(!fade.contains(fad)){
            fade.add(fad);
            fad.setHylde(this);
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

    @Override
    public String toString() {
        return "Hylde #" + hyldeNr;
    }

    @Override
    public int compareTo(Hylde o) {
        return hyldeNr - o.getHyldeNr();
    }
}
