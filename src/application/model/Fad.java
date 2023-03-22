package application.model;

public class Fad {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private double indeholdtVæskeILiter;

    private Leverandør leverandør;

    public Fad(FadType fadType, double størrelseILiter, int fadNr, double indeholdtVæskeILiter, Leverandør leverandør) {
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.fadNr = fadNr;
        this.indeholdtVæskeILiter = indeholdtVæskeILiter;
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

    public Leverandør getLeverandør() {
        return leverandør;
    }
}
