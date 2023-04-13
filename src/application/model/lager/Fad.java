package application.model.lager;

import application.model.*;
import application.model.produktion.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Modellerer et fad, som bruges til at modne destillat til whisky.
 */
public class Fad implements Comparable<Fad>, Serializable {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private static int antalFade;
    private Hylde hylde;
    private FadLeverandør fadLeverandør;
    private FadIndhold fadIndhold;
    private Set<FadIndhold> fadIndholdHistorik = new HashSet<>();

    /**
     * Opretter et fad med en given størrelse, fadtype, fadleverandør og hylde.
     * fadNr sættes til et unikt nummer.
     * @param fadType         fadets tidligere indhold, f.eks. "bourbon", "shery", osv.
     * @param størrelseILiter størrelsen på fadet i liter
     * @param fadLeverandør   fadleverandøren, der har leveret fadet
     * @param hylde           hylden hvor fadet er opbevaret
     * @Pre fadType != null && størrelseILiter > 0 && fadLeverandør != null && hylde != null
     */
    public Fad(FadType fadType, double størrelseILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        antalFade++;
        this.fadNr = antalFade;
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.fadLeverandør = fadLeverandør;
        this.hylde = hylde;
        hylde.addFad(this);
    }

    /**
     * Returnerer typen på fadet.
     *
     * @return typen på fadet
     */
    public FadType getFadType() {
        return fadType;
    }

    /**
     * Returnerer størrelsen på fadet i liter.
     *
     * @return størrelsen på fadet i liter
     */
    public double getStørrelseILiter() {
        return størrelseILiter;
    }

    /**
     * Returnerer det unikke nummer for fadet.
     *
     * @return unikt nummer for fadet
     */
    public int getFadNr() {
        return fadNr;
    }

    /**
     * Registrerer typen på fadet.
     *
     * @param fadType er fadets nye type.
     */
    public void setFadType(FadType fadType) {
        this.fadType = fadType;
    }

    /**
     * Returnerer hylden hvor fadet er opbevaret.
     *
     * @return hylden hvor fadet er opbevaret
     */
    public Hylde getHylde() {
        return hylde;
    }

    /**
     * Returnerer leverandøren for fadet.
     *
     * @return leverandøren for fadet
     */
    public FadLeverandør getFadLeverandør() {
        return fadLeverandør;
    }


    /**
     * Registrerer hylden hvor fadet er opbevaret.
     *
     * @param hylde er fadets nye hylde.
     */
    public void setHylde(Hylde hylde) {
        if (hylde == null) {
            throw new RuntimeException("Fadet skal være tilknyttet en hylde.");
        }
        if (this.hylde != hylde) {
            Hylde oldhylde = this.hylde;
            oldhylde.removeFad(this);
            this.hylde = hylde;
            hylde.addFad(this);
        }
    }

    /**
     * Registrerer fadets indhold.
     * @param fadIndhold
     */
    public void setFadIndhold(FadIndhold fadIndhold) {
        this.fadIndhold = fadIndhold;
        fadIndholdHistorik.add(fadIndhold);
    }

    /**
     * Returnerer fadets indhold.
     * @return fadets indhold
     */
    public FadIndhold getFadIndhold() {
        return fadIndhold;
    }

    /**
     * Returnerer fadets historik over indhold.
     * @return fadets historik over indhold
     */
    public Set<FadIndhold> getFadIndholdsHistorik() {
        return new HashSet<>(fadIndholdHistorik);
    }

    /**
     * Fjerner et fadindhold fra fadets historik.
     * @param fadIndhold er det fadindhold der skal fjernes
     */
    public void removeFromFadIndholdHistorik(FadIndhold fadIndhold) {
        fadIndholdHistorik.remove(fadIndhold);
    }

    /**
     * Returnerer true hvis fadet er tomt.
     * @return true hvis fadet er tomt
     */
    public boolean isEmpty() {
        return fadIndhold == null;
    }

    public boolean isFull() {
        return fadIndhold != null && fadIndhold.getMængde() == størrelseILiter;
    }

    /**
     * Påfylder et fad med et destillat.
     * @param destillat er destillatet der påfyldes
     * @param mængde er mængden der påfyldes
     * @param påfyldtAf er navnet på den person, der har påfyldt fadet
     * @param påfyldningsDato er datoen for påfyldningen
     * @return den påfyldning der er oprettet
     * Pre: destillat != null, mængde > 0, påfyldningsDato != null, mængde <= fadIndhold.resterendePladsILiter(), mængde <= destillat.getResterendeMængdeILiter()
     */
    public Påfyldning påfyld(Destillat destillat, double mængde, String påfyldtAf, LocalDate påfyldningsDato) {
        Påfyldning påfyldning;
        if (fadIndhold == null) {
            fadIndhold = new FadIndhold(this);
            påfyldning = new Påfyldning(destillat, fadIndhold, påfyldtAf, mængde, påfyldningsDato);
        } else {
            påfyldning = new Påfyldning(destillat, fadIndhold, påfyldtAf, mængde, påfyldningsDato);
        }
        return påfyldning;
    }

    /**
     * Aftapper et fad.
     * @param aftappetAf er navnet på den person, der har aftappet fadet
     * @param mængdeILiter er mængden i liter, der skal aftappes
     * @param aftapningsDato er datoen for aftapningen
     * @return aftapningen
     * @Pre aftappet != null, mængdeILiter > 0, aftapningsDato != null, mængdeILiter <= fadIndhold.indeholdtVæskeILiter(), 0 < fadIndhold.getAlkoholProcent() <= 100
     */
    public Aftapning aftap(String aftappetAf, double mængdeILiter, LocalDate aftapningsDato) {
        Aftapning aftapning = new Aftapning(aftappetAf, mængdeILiter, aftapningsDato, fadIndhold);
        if (fadIndhold.getMængde() <= 0) {
            fadIndholdHistorik.add(fadIndhold);
            fadIndhold = null;
        }
        return aftapning;
    }

    /**
     * Omhælder en mængde af fadets indhold til et andet fad.
     * @param omhældtAf er navnet på den person, der har omhældt fadet
     * @param mængdeILiter er mængden i liter, der skal omhældes
     * @param omhældningsDato er datoen for omhældningen
     * @param til er fadet, der skal omhældes til
     * @return omhældningen
     * @Pre: omhældtAf != null<br/> mængdeILiter > 0<br/> omhældningsDato != null<br/> mængdeILiter <= til.resterendeMængdeILiter()<br/>mængdeILiter <= fadIndhold.getMængde() <br/> til != null
     */
    public Omhældning omhæld(String omhældtAf, double mængdeILiter, LocalDate omhældningsDato, Fad til) {
        Omhældning omhældning;
        if (til.isEmpty()) {
            FadIndhold nytFadIndhold = new FadIndhold(til);
            til.setFadIndhold(nytFadIndhold);
            omhældning = new Omhældning(omhældtAf, mængdeILiter, omhældningsDato, fadIndhold, nytFadIndhold);
        } else {
            omhældning = new Omhældning(omhældtAf, mængdeILiter, omhældningsDato, fadIndhold, til.getFadIndhold());
        }

        if (fadIndhold.getMængde() <= 0) {
            fadIndholdHistorik.add(fadIndhold);
            fadIndhold = null;
        }
        return omhældning;
    }

    /**
     * Udregner resterende plads i fadet i liter.
     * @return resterende plads i fadet i liter
     */
    public double resterendePladsILiter() {
        if (fadIndhold == null) {
            return størrelseILiter;
        }
    	return størrelseILiter - fadIndhold.getMængde();
    }

    @Override
    public String toString() {
        String informationOmFadIndhold = "";
        if (fadIndhold != null && fadIndhold.getAlkoholProcentEfterModning() != -1) {
            informationOmFadIndhold = "(" + roundOfDecimals(fadIndhold.getAlkoholProcent())
                    + "% / " + roundOfDecimals(fadIndhold.getAlkoholProcentEfterModning()) + "%)";
        } else if (fadIndhold != null) {
            informationOmFadIndhold = "(" + roundOfDecimals(fadIndhold.getAlkoholProcent()) + "%)";
        }

        String mængdeInformation = " (0.0/" + størrelseILiter + "L)";
        if (fadIndhold != null) {
            mængdeInformation = " (" + fadIndhold.getMængde() + "/" + størrelseILiter + "L) ";
        }
        return "Fad: " + fadNr + ", type: " + fadType + mængdeInformation + informationOmFadIndhold;
    }

    /**
     * Returnerer en tekststreng med historik for fadet.
     * @return en tekststreng med historik for fadet
     */
    public String hentHistorik() {
        String historik = "Fadtype: " + fadType + "\n" + "Fadnr: " + fadNr + "\n"
                + "Størrelse: " + størrelseILiter + "\n" + "Fadet er på lager: "
                + hylde.getLager() + ", hylde nr: " + hylde.getHyldeNr() + "\n" + "Fadet er leveret af: " + fadLeverandør + "\n";

        return historik;
    }

    /**
     * Runder et tal af til to decimaler.
     * @param number er det tal der skal afrundes
     * @return det afrundede tal
     */
    private double roundOfDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    @Override
    public int compareTo(Fad o) {
        return fadNr - o.getFadNr();
    }
}
