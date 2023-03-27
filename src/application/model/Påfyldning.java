package application.model;

import java.time.LocalDate;

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
        fad.addPåfyldning(this);
        this.påfyldtAf = påfyldtAf;
        this.mængdeILiter = mængdeILiter;
        this.påfyldningsDato = påfyldningsDato;
    }

    public String getPåfyldtAf() {
        return påfyldtAf;
    }

    public double getMængdeILiter() {
        return mængdeILiter;
    }

    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    public Fad getFad() {
        return fad;
    }

    public Destillat getDestillat() {
        return destillat;
    }

    public String toString() {
        return destillat.getNewMakeNr() + ", " + mængdeILiter + "L, " + påfyldtAf + ", " + påfyldningsDato;
    }
}
