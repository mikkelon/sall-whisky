package application.model;

public class Whisky {
    private double alkoholProcent;
    private String betegnelse;
    private double mængdeVandILiter;
    private String vandAfstamning;
    private String tekstBeskrivelse;
    private int whiskyNr;

    public Whisky(double alkoholProcent, String betegnelse, double mængdeVandILiter, String vandAfstamning, String tekstBeskrivelse, int whiskyNr) {
        this.alkoholProcent = alkoholProcent;
        this.betegnelse = betegnelse;
        this.mængdeVandILiter = mængdeVandILiter;
        this.vandAfstamning = vandAfstamning;
        this.tekstBeskrivelse = tekstBeskrivelse;
        this.whiskyNr = whiskyNr;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getBetegnelse() {
        return betegnelse;
    }

    public double getMængdeVandILiter() {
        return mængdeVandILiter;
    }

    public String getVandAfstamning() {
        return vandAfstamning;
    }

    public String getTekstBeskrivelse() {
        return tekstBeskrivelse;
    }

    public int getWhiskyNr() {
        return whiskyNr;
    }
}
