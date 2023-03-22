package application.model;

public class Fad {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private double indeholdtVæskeILiter;
    private Hylde hylde;

    private Leverandør leverandør;

    public Fad(FadType fadType, double størrelseILiter, int fadNr, double indeholdtVæskeILiter, Leverandør leverandør, Hylde hylde) {
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.fadNr = fadNr;
        this.indeholdtVæskeILiter = indeholdtVæskeILiter;
        this.hylde = hylde;
        this.leverandør = leverandør;
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
