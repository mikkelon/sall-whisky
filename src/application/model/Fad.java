package application.model;

public class Fad {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private double indeholdtVæskeILiter;

    public Fad(FadType fadType, double størrelseILiter, int fadNr, double indeholdtVæskeILiter) {
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.fadNr = fadNr;
        this.indeholdtVæskeILiter = indeholdtVæskeILiter;
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

    public void setStørrelseILiter(double størrelseILiter) {
        this.størrelseILiter = størrelseILiter;
    }

    public void setFadNr(int fadNr) {
        this.fadNr = fadNr;
    }

    public void setIndeholdtVæskeILiter(double indeholdtVæskeILiter) {
        this.indeholdtVæskeILiter = indeholdtVæskeILiter;
    }
}
