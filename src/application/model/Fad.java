package application.model;

public class Fad {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private static int antalFade;
    private double indeholdtVæskeILiter;
    private Hylde hylde;
    private Leverandør leverandør;

    /**
     * Opretter et nyt objekt af Fad-klassen.
     * indeholdtVæskeILiter sættes til 0.
     * fadNr sættes til et unikt nummer baseret på antallet af fade-objekter.
     * @param fadType fadets tidligere indhold, f.eks. sherry, bourbon, osv.
     * @param størrelseILiter fadets volumen i liter.
     * @param leverandør fadets leverandør.
     * @param hylde hylden, fadet er placeret på i lageret.
     */
    public Fad(FadType fadType, double størrelseILiter, Leverandør leverandør, Hylde hylde) {
        antalFade++;
        this.fadNr = antalFade;
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.indeholdtVæskeILiter = 0;
        this.leverandør = leverandør;
        this.hylde = hylde;
    }

    public FadType getFadType() {
        return fadType;
    }

    public double getStørrelseILiter() {
        return størrelseILiter;
    }

    public int getFadNr() {
        return fadNr;
    }

    public double getIndeholdtVæskeILiter() {
        return indeholdtVæskeILiter;
    }

    public void setFadType(FadType fadType) {
        this.fadType = fadType;
    }
        
    public Hylde getHylde() {
        return hylde;
    }

    public void setIndeholdtVæskeILiter(double indeholdtVæskeILiter) {
        this.indeholdtVæskeILiter = indeholdtVæskeILiter;
    }

    public Leverandør getLeverandør() {
        return leverandør;
    }

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
