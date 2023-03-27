package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Modellerer et destillat.
 */
public class Destillat {
    private String newMakeNr;
    private String medarbejder;
    private double alkoholProcent;
    private int antalDestilleringer;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double mængdeILiter;
    private String kommentar;
    private RygeMateriale rygeMateriale;

    private HashSet <Påfyldning> påfyldninger = new HashSet<>();

    /**
     * Opretter et nyt destillat med angivet parametre.
     * pre: alkoholProcent > 0, antalDestilleringer > 0, startDato < slutDato, mængdeILiter > 0, rygeMateriale != null
     * @param newMakeNr           destillates newMakeNr
     * @param medarbejder         hvilket medarbejder der har destilleret dette destillat
     * @param alkoholProcent      destillatets alkoholProcent
     * @param antalDestilleringer hvor mange gange destilattet er destilleret
     * @param startDato           startdatoen for destillatet
     * @param slutDato            slutdatoen for destillatet
     * @param mængdeILiter        hvor mange liter der er af dette destillat
     * @param kommentar           kommentar til destillatet
     * @param rygeMateriale       om der er brugt rygemaateriale til destillatet
     */
    public Destillat(String newMakeNr, String medarbejder, double alkoholProcent,
                     int antalDestilleringer, LocalDate startDato, LocalDate slutDato,
                     double mængdeILiter, String kommentar, RygeMateriale rygeMateriale) {
        this.newMakeNr = newMakeNr;
        this.medarbejder = medarbejder;
        this.alkoholProcent = alkoholProcent;
        this.antalDestilleringer = antalDestilleringer;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.mængdeILiter = mængdeILiter;
        this.kommentar = kommentar;
        this.rygeMateriale = rygeMateriale;
    }

    /**
     * Returnerer destillatets newMakeNr.
     *
     * @return destillatets newMakeNr
     */
    public String getNewMakeNr() {
        return newMakeNr;
    }

    /**
     * Returnerer hvilket medarbejder der har destilleret dette destillat.
     *
     * @return hvilket medarbejder der har destilleret dette destillat
     */
    public String getMedarbejder() {
        return medarbejder;
    }

    /**
     * Returnerer destillatets alkoholProcent.
     *
     * @return destillatets alkoholProcent
     */
    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    /**
     * Returnerer hvor mange gange destilattet er destilleret.
     *
     * @return hvor mange gange destilattet er destilleret
     */
    public int getAntalDestilleringer() {
        return antalDestilleringer;
    }

    /**
     * Returnerer startdatoen for destillatet.
     *
     * @return startdatoen for destillatet
     */
    public LocalDate getStartDato() {
        return startDato;
    }

    /**
     * Returnerer slutdatoen for destillatet.
     *
     * @return slutdatoen for destillatet
     */
    public LocalDate getSlutDato() {
        return slutDato;
    }

    /**
     * Returnerer hvor mange liter der er af dette destillat.
     *
     * @return hvor mange liter der er af dette destillat
     */
    public double getMængdeILiter() {
        return mængdeILiter;
    }

    /**
     * Returnerer kommentar til destillatet.
     *
     * @return kommentar til destillatet
     */
    public String getKommentar() {
        return kommentar;
    }

    /**
     * Returnerer om der er brugt rygemaateriale til destillatet.
     *
     * @return om der er brugt rygemaateriale til destillatet
     */
    public RygeMateriale getRygeMateriale() {
        return rygeMateriale;
    }

    /**
     * Returnerer en liste over påfyldninger.
     *
     * @return en liste over påfyldninger
     */
    public ArrayList<Påfyldning> getPåfyldninger() {
        return new ArrayList<>(påfyldninger);
    }

    /**
     * Tilføjer en påfyldning til destillatet.
     * @param påfyldning påfyldningen der skal tilføjes
     */
    public void addPåfyldning(Påfyldning påfyldning) {
        if (!påfyldninger.contains(påfyldning)){
            påfyldninger.add(påfyldning);
        }
    }

    public void removePåfyldning(Påfyldning påfyldning) {
        if (påfyldninger.contains(påfyldning)){
            påfyldninger.remove(påfyldning);
        }
    }

    /**
     * Returnerer resterende mængde i liter.
     *
     * @return resterende mængde i liter
     */
    public double resterendeMængdeILiter() {
        double resterendeMængde = mængdeILiter;
        for (Påfyldning påfyldning : påfyldninger) {
            resterendeMængde -= påfyldning.getMængdeILiter();
        }
        return resterendeMængde;
    }

    @Override
    public String toString() {
        String s = "New Make: " + newMakeNr + ", Ansat: " + medarbejder
                + "\nAlkohol: " + alkoholProcent + "%, Destilleringer: " + antalDestilleringer
                + "\nStart: " + startDato + ", Slut: " + slutDato
                + "\nMængde: " + mængdeILiter + "L"
                + "\nKommentar: " + kommentar;
        if (rygeMateriale != RygeMateriale.INTET) {
            s += "\nRyge materiale: " + rygeMateriale;
        }
        return s;
    }
}
