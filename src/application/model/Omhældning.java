package application.model;

import java.time.LocalDate;

public class Omhældning {
    private String omhældtAf;
    private double mængdeILiter;
    private LocalDate omhældningsDato;
    private FadIndhold fraFadIndhold;
    private FadIndhold tilFadIndhold;

    public Omhældning(String omhældtAf, double mængdeILiter, LocalDate omhældningsDato, FadIndhold fraFadIndhold, FadIndhold tilFadIndhold) {
        this.omhældtAf = omhældtAf;
        this.mængdeILiter = mængdeILiter;
        this.omhældningsDato = omhældningsDato;
        this.fraFadIndhold = fraFadIndhold;
        fraFadIndhold.addOmhældningFra(this);
        this.tilFadIndhold = tilFadIndhold;
        tilFadIndhold.addOmhældningTil(this);
    }

    public String getOmhældtAf() {
        return omhældtAf;
    }

    public double getMængdeILiter() {
        return mængdeILiter;
    }

    public LocalDate getOmhældningsDato() {
        return omhældningsDato;
    }

    public FadIndhold getFraFadIndhold() {
        return fraFadIndhold;
    }

    public FadIndhold getTilFadIndhold() {
        return tilFadIndhold;
    }

    public String hentHistorik() {
        return fraFadIndhold.hentHistorik();
    }
}
