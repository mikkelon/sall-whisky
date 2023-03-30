package application.model;

import java.time.LocalDate;

public class Aftapning {
    private String aftappetAf;
    private double mængdeILiter;
    private LocalDate aftapningsDato;
    private Fad fad;
    private Whisky whisky;

    public Aftapning(String aftappetAf, double mængdeILiter, LocalDate aftapningsDato, Fad fad, Whisky whisky) {
        this.aftappetAf = aftappetAf;
        this.mængdeILiter = mængdeILiter;
        this.aftapningsDato = aftapningsDato;
        this.fad = fad;
        this.whisky = whisky;
    }

    public String getAftappetAf() {
        return aftappetAf;
    }

    public double getMængdeILiter() {
        return mængdeILiter;
    }

    public LocalDate getAftapningsDato() {
        return aftapningsDato;
    }

    public Fad getFad(){
        return fad;
    }

    public Whisky getWhisky(){
        return whisky;
    }
}
