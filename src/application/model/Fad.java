package application.model;

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
