package application.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Modellerer et fad, som bruges til at modne destillat til whisky.
 */
public class Fad {
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

    public void removeFromFadIndholdHistorik(FadIndhold fadIndhold) {
        fadIndholdHistorik.remove(fadIndhold);
    }

    public boolean isEmpty() {
        return fadIndhold == null;
    }

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
     * <pre>
     *     Pre: aftappet != null, mængdeILiter > 0, aftapningsDato != null, mængdeILiter <= fadIndhold.indeholdtVæskeILiter(), 0 < fadIndhold.getAlkoholProcent() <= 100
     * </pre>
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
        return "Fad: " + fadNr + ", type: " + fadType + " (" + størrelseILiter + "L)";
    }

    public String hentHistorik() {
        String historik = "Fadtype: " + fadType + "\n" + "Fadnr: " + fadNr + "\n"
                + "Størrelse: " + størrelseILiter + "\n" + "Fadet er på lager: "
                + hylde.getLager() + ", hylde nr: " + hylde.getHyldeNr() + "\n" + "Fadet er leveret af: " + fadLeverandør + "\n";
        return historik;
    }
}
