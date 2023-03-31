package application.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FadIndhold {
    private double alkoholProcentEfterModning;
    private LocalDate senestPåfyldt;
    private Set<Påfyldning> påfyldninger = new HashSet<>();
    private Set<Aftapning> aftapninger = new HashSet<>();
    private Fad fad;

    public FadIndhold(double alkoholProcentEfterModning, LocalDate senestPåfyldt, Fad fad) {
        this.alkoholProcentEfterModning = alkoholProcentEfterModning;
        this.senestPåfyldt = senestPåfyldt;
        this.fad = fad;
    }

    public double getAlkoholProcentEfterModning() {
        return alkoholProcentEfterModning;
    }

    public LocalDate getSenestPåfyldt() {
        return senestPåfyldt;
    }

    public LocalDate forventetFærdigProduceret() {
        return senestPåfyldt.plusYears(3);
    }

    public String hentHistorik() {
        return "";
    }

    public Set getPåfyldninger() {
        return new HashSet<>(påfyldninger);
    }

    /**
     * Tilføjer en påfyldning til fadet.
     * Pre: påfyldning != null, påfyldning.getMængdeILiter() <= resterendePladsILiter()
     * @param påfyldning påfyldningen der skal tilføjes
     */
    public void addPåfyldning(Påfyldning påfyldning) {
        påfyldninger.add(påfyldning);

        // Opdatér senestPåfyldt
        if (senestPåfyldt == null || påfyldning.getPåfyldningsDato().isAfter(senestPåfyldt)) {
            senestPåfyldt = påfyldning.getPåfyldningsDato();
        }
    }

    public Set getAftapninger() {
        return new HashSet<>(aftapninger);
    }

    public void addAfTapning(Aftapning aftapning) {
        if (!aftapninger.contains(aftapning)) {
            aftapninger.add(aftapning);
        }
    }

    public Fad getFad() {
        return fad;
    }

    public void setFad(Fad fad) {
        this.fad = fad;
        fad.setFadInhold(this);
    }

    /**
     * Udregner alkoholprocenten i fadet.
     * @return alkoholprocenten i fadet
     */
    public double getAlkoholProcent() {
        // Udregn alkoholprocent baseret på alle påfyldninger
        double alkohol = 0.0;
        double væske = 0.0;
        for (Påfyldning påfyldning : påfyldninger) {
            double alkoholProcent = påfyldning.getDestillat().getAlkoholProcent() / 100.0;
            alkohol += alkoholProcent * påfyldning.getMængdeILiter();
            væske += påfyldning.getMængdeILiter();
        }
        return væske > 0 ? alkohol / væske * 100 : 0.0;
    }

    /**
     * Udregner den samlede mængde af væske i fadet i liter.
     * @return den samlede mængde af væske i fadet i liter
     */
    public double indeholdtVæskeILiter() {
        double væske = 0;
        for (Påfyldning påfyldning : påfyldninger) {
            væske += påfyldning.getMængdeILiter();
        }
        return væske;
    }
}
