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
    private Leverandør leverandør;
    
    public Fad(FadType fadType, double størrelseILiter, double indeholdtVæskeILiter, Leverandør leverandør, Hylde hylde) {
        antalFade++;
        this.fadNr = antalFade;
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.indeholdtVæskeILiter = indeholdtVæskeILiter;
        this.leverandør = leverandør;
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
    public Leverandør getLeverandør() {
        return leverandør;
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
