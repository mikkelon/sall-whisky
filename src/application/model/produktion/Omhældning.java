package application.model.produktion;

import application.model.produktion.FadIndhold;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Modellerer en omhældning fra et fad til et andet.
 */
public class Omhældning implements Serializable {
    private String omhældtAf;
    private double mængdeILiter;
    private LocalDate omhældningsDato;
    private FadIndhold fraFadIndhold;
    private FadIndhold tilFadIndhold;

    /**
     * Opretter en omhældning.
     * @param omhældtAf navnet på den person der har omhældt
     * @param mængdeILiter mængden der er omhældt
     * @param omhældningsDato hvornår omhældningen er sket
     * @param fraFadIndhold hvilket fad indhold der er omhældt fra
     * @param tilFadIndhold hvilket fad indhold der er omhældt til
     */
    public Omhældning(String omhældtAf, double mængdeILiter, LocalDate omhældningsDato, FadIndhold fraFadIndhold, FadIndhold tilFadIndhold) {
        this.omhældtAf = omhældtAf;
        this.mængdeILiter = mængdeILiter;
        this.omhældningsDato = omhældningsDato;
        this.fraFadIndhold = fraFadIndhold;
        fraFadIndhold.addOmhældningFra(this);
        this.tilFadIndhold = tilFadIndhold;
        tilFadIndhold.addOmhældningTil(this);

        if (tilFadIndhold.getModningStartDato() == null
                || fraFadIndhold.getModningStartDato().isAfter(tilFadIndhold.getModningStartDato())) {
            tilFadIndhold.setModningStartDato(fraFadIndhold.getModningStartDato());
        }
    }

    /**
     * Returnerer navnet på den person der har omhældt.
     * @return navnet på den person der har omhældt
     */
    public String getOmhældtAf() {
        return omhældtAf;
    }

    /**
     * Returnerer mængden der er omhældt.
     * @return mængden der er omhældt
     */
    public double getMængdeILiter() {
        return mængdeILiter;
    }

    /**
     * Returnerer hvornår omhældningen er sket.
     * @return hvornår omhældningen er sket
     */
    public LocalDate getOmhældningsDato() {
        return omhældningsDato;
    }

    /**
     * Returnerer hvilket fad indhold der er omhældt fra.
     * @return hvilket fad indhold der er omhældt fra
     */
    public FadIndhold getFraFadIndhold() {
        return fraFadIndhold;
    }

    /**
     * Returnerer hvilket fad indhold der er omhældt til.
     * @return hvilket fad indhold der er omhældt til
     */
    public FadIndhold getTilFadIndhold() {
        return tilFadIndhold;
    }

    /**
     * Returnerer en tekststreng med historik for omhældningen.
     * @return en tekststreng med historik for omhældningen
     */
    public String hentHistorik() {
        return "(" + mængdeILiter + "L) omhældt fra: \n" + fraFadIndhold.hentHistorik();
    }
}
