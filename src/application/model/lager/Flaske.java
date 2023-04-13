package application.model.lager;

import application.model.FadType;
import application.model.produktion.*;

import java.util.HashSet;

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

    public String hentLabel() {
        HashSet<String> korn = new HashSet<>();
        HashSet<FadType> fadTyper = new HashSet<>();
        HashSet<String> destilleretAf = new HashSet<>();
        HashSet<String> aftappetAf = new HashSet<>();
        for (Aftapning aftapning : whisky.getAftapninger()) {
            for (Påfyldning påfyldning : aftapning.getFadIndhold().getPåfyldninger()) {
                // Henter alle kornsorter
                for (Maltbatch maltbatch : påfyldning.getDestillat().getMaltbatches()) {
                    korn.add(maltbatch.getKornsort());
                }

                // Henter alle medarbejdere, der har været med til destilleringen
                destilleretAf.add(påfyldning.getDestillat().getMedarbejder());
            }

            // Henter alle fadTyper som whiskyen har ligget på igennem tiden
            for (Omhældning omhældningTil : aftapning.getFadIndhold().getTilføjedeOmhældninger()) {
                fadTyper.add(omhældningTil.getFraFadIndhold().getFad().getFadType());
            }
            fadTyper.add(aftapning.getFadIndhold().getFad().getFadType());

            // Henter alle medarbejdere, der har været med til aftapningen
            aftappetAf.add(aftapning.getAftappetAf());
        }

        String kornString = "";
        for (String kornSort : korn) {
            kornString += kornSort + ", ";
        }
        kornString = kornString.substring(0, kornString.length() - 2); // fjern det sidste ", "

        String fadTypeString = "";
        for (FadType fadType : fadTyper) {
            fadTypeString += fadType + ", ";
        }
        fadTypeString = fadTypeString.substring(0, fadTypeString.length() - 2); // fjern det sidste ", "

        String destilleretAfString = "";
        for (String medarbejder : destilleretAf) {
            destilleretAfString += medarbejder + ", ";
        }
        destilleretAfString = destilleretAfString.substring(0, destilleretAfString.length() - 2); // fjern det sidste ", "

        String aftappetAfString = "";
        for (String medarbejder : aftappetAf) {
            aftappetAfString += medarbejder + ", ";
        }
        aftappetAfString = aftappetAfString.substring(0, aftappetAfString.length() - 2); // fjern det sidste ", "

        String label = "Type: " + whisky.getBetegnelse()
                + "\nKorn: " + kornString
                + "\nFad: " + fadTypeString
                + "\nDyrket af: Sall Whisky"
                + "\nAlder: " + whisky.getAlderIÅr() + " år"
                + "\nAntal flasker: " + whisky.getFlasker().size()
                + "\nAlkohol: " + String.format("%.1f", whisky.getAlkoholProcent()) + "%"
                + "\nVolume: ";

        if (størrelseILiter >= 1) {
            label += String.format("%.0f L", størrelseILiter);
        } else {
            label += String.format("%.0f cl", størrelseILiter * 100);
        }

        label += "\n\nDestilleret af: " + destilleretAfString
                + "\nAftappet af: " + aftappetAfString;

        return label;
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
