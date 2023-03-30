package application.model;

import java.time.LocalDate;

/**
 * Modellerer en påfyldning af et fad med et bestemt destillat.
 */
public class Påfyldning {
    private String påfyldtAf;
    private double mængdeILiter;
    private LocalDate påfyldningsDato;
    private Fad fad;
    private Destillat destillat;

    /** Opretter en påfyldning af et fad med et bestemt destillat.
     * Pre: fad != null, destillat != null, mængdeILiter > 0, påfyldtAf != null, påfyldningsDato != null
     * @param destillat destillatet der påfyldes
     * @param fad fadet der påfyldes
     * @param påfyldtAf navnet på den person der påfylder
     * @param mængdeILiter mængden der påfyldes i liter
     * @param påfyldningsDato dato for påfyldningen
     */
    public Påfyldning(Destillat destillat, Fad fad, String påfyldtAf, double mængdeILiter, LocalDate påfyldningsDato) {
        this.destillat = destillat;
        this.fad = fad;
        this.påfyldtAf = påfyldtAf;
        this.mængdeILiter = mængdeILiter;
        this.påfyldningsDato = påfyldningsDato;
    }

    /**
     * Returnerer navnet på den person der har påfyldt fadet.
     * @return navnet på den person der har påfyldt fadet
     */
    public String getPåfyldtAf() {
        return påfyldtAf;
    }

    /**
     * Returnerer mængden der er påfyldt i liter.
     * @return mængden der er påfyldt i liter
     */
    public double getMængdeILiter() {
        return mængdeILiter;
    }

    /**
     * Returnerer datoen for påfyldningen.
     * @return datoen for påfyldningen
     */
    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    /**
     * Returnerer fadet der er påfyldt.
     * @return fadet der er påfyldt
     */
    public Fad getFad() {
        return fad;
    }

    /**
     * Returnerer destillatet der er fyldt på fadet.
     * @return destillatet der er fyldt på fadet
     */
    public Destillat getDestillat() {
        return destillat;
    }

    @Override
    public String toString() {
        return "Påfyldning for fad " + fad.getFadNr() + " med destillat " + destillat.getNewMakeNr();
    }
}


