package application.model.produktion;

import application.model.produktion.Destillat;
import application.model.produktion.FadIndhold;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Modellerer en påfyldning af et fad med et bestemt destillat.
 */
public class Påfyldning implements Serializable {
    private String påfyldtAf;
    private double mængdeILiter;
    private LocalDate påfyldningsDato;
    private FadIndhold fadIndhold;
    private Destillat destillat;

    /** Opretter en påfyldning af et fad med et bestemt destillat.
     * <pre>
     * Pre: fad != null, destillat != null, mængdeILiter > 0, påfyldtAf != null, påfyldningsDato != null
     * </pre>
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

        if (fadIndhold.getModningStartDato() == null || påfyldningsDato.isAfter(fadIndhold.getModningStartDato())) {
            fadIndhold.setModningStartDato(påfyldningsDato);
        }
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

    /**
     * Returnerer en tekststreng med historikken for påfyldningen.
     * @return en tekststreng med historikken for påfyldningen
     */
    public String hentHistorik() {
        return "(" + mængdeILiter + "L) påfyldt fra: \n" + destillat.hentHistorik();
    }
}


