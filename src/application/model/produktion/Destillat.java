package application.model.produktion;

import application.model.RygeMateriale;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Modellerer et destillat.
 */
public class Destillat implements Serializable {
    private String newMakeNr;
    private String medarbejder;
    private double alkoholProcent;
    private int antalDestilleringer;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double mængdeILiter;
    private String kommentar;
    private RygeMateriale rygeMateriale;
    private Set<Påfyldning> påfyldninger = new HashSet<>();
    private Set<Maltbatch> maltbatches = new TreeSet<>();

    /**
     * Opretter et nyt destillat med angivet parametre.
     * @param newMakeNr           destillates newMakeNr
     * @param medarbejder         hvilket medarbejder der har destilleret dette destillat
     * @param alkoholProcent      destillatets alkoholProcent
     * @param antalDestilleringer hvor mange gange destilattet er destilleret
     * @param startDato           startdatoen for destillatet
     * @param slutDato            slutdatoen for destillatet
     * @param mængdeILiter        hvor mange liter der er af dette destillat
     * @param kommentar           kommentar til destillatet
     * @param rygeMateriale       om der er brugt rygemaateriale til destillatet
     * @Pre: 0 <= alkoholprocent <= 100<br />antalDestilleringer > 0<br />startDato < slutDato<br />mængdeILiter > 0<br />rygeMateriale != null
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
     * @return destillatets newMakeNr
     */
    public String getNewMakeNr() {
        return newMakeNr;
    }

    /**
     * Returnerer hvilket medarbejder der har destilleret dette destillat.
     * @return hvilket medarbejder der har destilleret dette destillat
     */
    public String getMedarbejder() {
        return medarbejder;
    }

    /**
     * Returnerer destillatets alkoholProcent.
     * @return destillatets alkoholProcent
     */
    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    /**
     * Returnerer hvor mange gange destilattet er destilleret.
     * @return hvor mange gange destilattet er destilleret
     */
    public int getAntalDestilleringer() {
        return antalDestilleringer;
    }

    /**
     * Returnerer startdatoen for destillatet.
     * @return startdatoen for destillatet
     */
    public LocalDate getStartDato() {
        return startDato;
    }

    /**
     * Returnerer slutdatoen for destillatet.
     * @return slutdatoen for destillatet
     */
    public LocalDate getSlutDato() {
        return slutDato;
    }

    /**
     * Returnerer hvor mange liter der er af dette destillat.
     * @return hvor mange liter der er af dette destillat
     */
    public double getMængdeILiter() {
        return mængdeILiter;
    }

    /**
     * Returnerer kommentar til destillatet.
     * @return kommentar til destillatet
     */
    public String getKommentar() {
        return kommentar;
    }

    /**
     * Returnerer om der er brugt rygemaateriale til destillatet.
     * @return om der er brugt rygemaateriale til destillatet
     */
    public RygeMateriale getRygeMateriale() {
        return rygeMateriale;
    }

    /**
     * Returnerer en liste over påfyldninger.
     * @return en liste over påfyldninger
     */
    public Set<Påfyldning> getPåfyldninger() {
        return new HashSet<>(påfyldninger);
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

    /**
     * Fjerner en påfyldning fra destillatet.
     * @param påfyldning påfyldningen der skal fjernes
     */
    public void removePåfyldning(Påfyldning påfyldning) {
        if (påfyldninger.contains(påfyldning)){
            påfyldninger.remove(påfyldning);
        }
    }

    /**
     * Returnerer resterende mængde i liter.
     * @return resterende mængde i liter
     */
    public double resterendeMængdeILiter() {
        double resterendeMængde = mængdeILiter;
        for (Påfyldning påfyldning : påfyldninger) {
            resterendeMængde -= påfyldning.getMængdeILiter();
        }
        return resterendeMængde;
    }

    /**
     * Returnerer en liste over maltbatches.
     * @return en liste over maltbatches
     */
    public Set<Maltbatch> getMaltbatches(){
        return new TreeSet<>(maltbatches);
    }

    /**
     * Tilføjer et maltbatch til destillatet.
     * @param maltbatch tilføjes til destillatet
     */
    public void addMaltbatch(Maltbatch maltbatch){
        if(!maltbatches.contains(maltbatch)){
            maltbatches.add(maltbatch);
            maltbatch.tilføjDestillat();
        }
    }

    /**
     * Fjerner et maltbatch fra destillatet.
     * @param maltbatch fjernes fra destillatet
     */
    public void removeMaltbatch(Maltbatch maltbatch){
        if(maltbatches.contains(maltbatch)){
            maltbatches.remove(maltbatch);
            maltbatch.fjernDestillat();
        }
    }

    @Override
    public String toString() {
        return newMakeNr + " (" + roundOfDecimals(resterendeMængdeILiter()) + "L)";
    }

    /**
     * Returnerer en tekststreng med historik for destillatet.
     * @return en tekststreng med historik for destillatet
     */
    public String hentHistorik() {
        StringBuilder sb = new StringBuilder();
        sb.append("New make: " + newMakeNr + "\n" + "Medarbejder: " + medarbejder
                + "\n" + "Alkoholprocent: " + alkoholProcent + "\n" + "Antal destilleringer: "
                + antalDestilleringer + "\n" + "Startdato: " + startDato + "\n" + "Slutdato: "
                + slutDato + "\n" + "Mængde i liter: " + mængdeILiter + "\n" + "Kommentar: "
                + kommentar + "\n" + "Rygemateriale: " + rygeMateriale + "\n");

        for (Maltbatch maltbatch : maltbatches) {
            sb.append(maltbatch.hentHistorik() + "\n");
        }
        return sb.toString();
    }

    private double roundOfDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}
