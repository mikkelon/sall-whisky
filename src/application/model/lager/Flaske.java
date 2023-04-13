package application.model.lager;

import application.model.produktion.Whisky;

/**
 * Modellerer en flaske som indeholder whisky.
 */
public class Flaske implements Comparable<Flaske> {
    private int flaskeNr;
    private static int antalFlasker = 0;
    private double størrelseILiter;
    private Whisky whisky;

    /**
     * Opretter en flaske med en given størrelse og whisky.
     * @param størrelseILiter størrelsen på flasken i liter
     * @param whisky whiskyen i flasken
     * @Pre størrelseILiter > 0 && whisky != null
     */
    public Flaske(double størrelseILiter, Whisky whisky) {
        antalFlasker++;
        this.flaskeNr = antalFlasker;
        this.størrelseILiter = størrelseILiter;
        this.whisky = whisky;
        whisky.addFlaske(this);
    }

    /**
     * Returnerer flaske nummeret.
     * @return flaske nummeret
     */

    public int getFlaskeNr() {
        return flaskeNr;
    }

    /**
     * Returnerer størrelsen på flasken i liter.
     * @return størrelsen på flasken i liter
     */
    public double getStørrelseILiter() {
        return størrelseILiter;
    }
    /**
     * Returnerer whiskyen i flasken.
     * @return whiskyen i flasken
     */

    public Whisky getWhisky() {
        return whisky;
    }

    /**
     * Returnerer whiskybeskrivelsen
     * @return whiskybeskrivelsen
     */

    public String getBeskrivelse() {
        return whisky.getTekstBeskrivelse();
    }

    /**
     * Returnerer historikken for whiskyen
     * @return whiskyhistorikken
     */
    public String hentHistorik() {
        return whisky.hentHistorik();
    }

    @Override
    public int compareTo(Flaske flaske) {
        return this.flaskeNr - flaske.flaskeNr;
    }

    @Override
    public String toString() {
        String string = "Flaske #"+ flaskeNr + ", NO. " + whisky.getWhiskyNr();
        if (størrelseILiter >= 1) {
            string += String.format(", %.0f L", størrelseILiter);
        } else {
            string += String.format(", %.0f cl", størrelseILiter * 100);
        }
        return string;
    }
}
