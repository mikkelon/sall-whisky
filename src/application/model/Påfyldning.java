package application.model;

import java.time.LocalDate;

/**
 * Modellerer en påfyldning af et fad med et bestemt destillat.
 */
public class Påfyldning {
    private String påfyldtAf;
    private double mængdeILiter;
    private LocalDate påfyldningsDato;
    private FadIndhold fadIndhold;
    private Destillat destillat;

    /** Opretter en påfyldning til et fad indhold med et bestemt destillat.
     * Pre: fadIndhold != null, destillat != null, mængdeILiter > 0, påfyldtAf != null, påfyldningsDato != null
     * @param destillat destillatet der påfyldes
     * @param fadIndhold fad indholdet der påfyldes
     * @param påfyldtAf navnet på den person der påfylder
     * @param mængdeILiter mængden der påfyldes i liter
     * @param påfyldningsDato dato for påfyldningen
     */
    public Påfyldning(Destillat destillat, FadIndhold fadIndhold, String påfyldtAf, double mængdeILiter, LocalDate påfyldningsDato) {
        this.destillat = destillat;
        this.fadIndhold = fadIndhold;
        this.påfyldtAf = påfyldtAf;
        this.mængdeILiter = mængdeILiter;
        this.påfyldningsDato = påfyldningsDato;
        fadIndhold.addPåfyldning(this);
        destillat.addPåfyldning(this);
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
     * Returnerer fad indholdet der er fyldt på.
     * @return fad indholdet der er fyldt på
     */
    public FadIndhold getFadIndhold() {
        return fadIndhold;
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
        return destillat.getNewMakeNr() + ", " + mængdeILiter + " liter";
    }
}


