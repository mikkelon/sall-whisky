package application.model;

import java.time.LocalDate;

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

    public String getNewMakeNr() {
        return newMakeNr;
    }

    public String getMedarbejder() {
        return medarbejder;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public int getAntalDestilleringer() {
        return antalDestilleringer;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getMængdeILiter() {
        return mængdeILiter;
    }

    public String getKommentar() {
        return kommentar;
    }

    public RygeMateriale getRygeMateriale() {
        return rygeMateriale;
    }

    @Override
    public String toString() {
        return "NewMakeNr: " + newMakeNr + "Alkohol procent: " + alkoholProcent
                + "Antal destilleringer: " + antalDestilleringer + "Start dato: " + startDato
                + "Slut dato: " + slutDato + "Mængde i liter: " + mængdeILiter
                + "Kommentar: " + kommentar + "Ryge materiale: " + rygeMateriale
                + "Medarbejder: " + medarbejder;
    }
}
