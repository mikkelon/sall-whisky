package application.model;

import java.time.LocalDate;

public class Aftapning {
    private String aftappetAf;
    private double mængdeILiter;
    private LocalDate aftapningsDato;

    public Aftapning(String aftappetAf, double mængdeILiter, LocalDate aftapningsDato) {
        this.aftappetAf = aftappetAf;
        this.mængdeILiter = mængdeILiter;
        this.aftapningsDato = aftapningsDato;
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
}
