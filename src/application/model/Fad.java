package application.model;

public class Fad {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private double indeholdtVæskeILiter;
    private Hylde hylde;
    private Leverandør leverandør;
    
    public Fad(FadType fadType, double størrelseILiter, int fadNr, double indeholdtVæskeILiter, Leverandør leverandør, Hylde hylde) {
        // TODO: fadNr skal måske automatisk tilføjes eller valideres, så der ikke kan være to fade med samme fadNr?
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.fadNr = fadNr;
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
        if(this.hylde != hylde){
            Hylde oldhylde = this.hylde;
            if(oldhylde != null){
                oldhylde.removeFad(this);
            }
            this.hylde = hylde;
            if(hylde != null){
                hylde.addFad(this);
            }
        }
    }
}
