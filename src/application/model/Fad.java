package application.model;

/**
 * Modellerer et fad, som bruges til at modne destillat til whisky.
 */
public class Fad {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private static int antalFade;
    private double indeholdtVæskeILiter;
    private Hylde hylde;
    private FadLeverandør fadLeverandør;

    /**
     * Opretter et fad med en given størrelse, fadtype, fadleverandør og hylde.
     * indeholdtVæskeILiter sættes til 0.
     * fadNr sættes til et unikt nummer.
     * @param fadType fadets tidligere indhold, f.eks. "bourbon", "shery", osv.
     * @param størrelseILiter størrelsen på fadet i liter
     * @param fadLeverandør fadleverandøren, der har leveret fadet
     * @param hylde hylden hvor fadet er opbevaret
     */
    public Fad(FadType fadType, double størrelseILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        antalFade++;
        this.fadNr = antalFade;
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.indeholdtVæskeILiter = 0;
        this.fadLeverandør = fadLeverandør;
        this.hylde = hylde;
    }

    /**
     *Returnerer typen på fadet.
     * @return typen på fadet
     */
    public FadType getFadType() {
        return fadType;
    }

    /**
     * Returnerer størrelsen på fadet i liter.
     * @return størrelsen på fadet i liter
     */
    public double getStørrelseILiter() {
        return størrelseILiter;
    }

    /**
     * Returnerer det unikke nummer for fadet.
     * @return unikt nummer for fadet
     */
    public int getFadNr() {
        return fadNr;
    }

    /**
     * Returnerer indeholdt væske i liter.
     * @return indeholdt væske i liter
     */
    public double getIndeholdtVæskeILiter() {
        return indeholdtVæskeILiter;
    }

    /**
     * Registrerer typen på fadet.
     * @param fadType er fadets nye type.
     */
    public void setFadType(FadType fadType) {
        this.fadType = fadType;
    }

    /**
     * Returnerer hylden hvor fadet er opbevaret.
     * @return hylden hvor fadet er opbevaret
     */
    public Hylde getHylde() {
        return hylde;
    }

    /**
     * Registrerer indeholdt væske i liter.
     * @param indeholdtVæskeILiter er fadets nye indeholdt væske i liter.
     */
    public void setIndeholdtVæskeILiter(double indeholdtVæskeILiter) {
        this.indeholdtVæskeILiter = indeholdtVæskeILiter;
    }

    /**
     * Returnerer leverandøren for fadet.
     * @return leverandøren for fadet
     */
    public FadLeverandør getFadLeverandør() {
        return fadLeverandør;
    }

    /**
     * Registrerer hylden hvor fadet er opbevaret.
     * @param hylde er fadets nye hylde.
     */
    public void setHylde(Hylde hylde){
        if (hylde == null) {
            throw new RuntimeException("Fadet skal være tilknyttet en hylde.");
        }

        if(this.hylde != hylde){
            Hylde oldhylde = this.hylde;
            oldhylde.removeFad(this);
            this.hylde = hylde;
            hylde.addFad(this);
        }
    }
}
