package application.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FadIndhold {
    private double alkoholProcentEfterModning;
    private LocalDate senestPåfyldt;
    private Set<Påfyldning> påfyldninger = new HashSet<>();
    private Set<Aftapning> aftapninger = new HashSet<>();
    private Set<Omhældning> omhældningerFra = new HashSet<>();
    private Set<Omhældning> omhældningerTil = new HashSet<>();
    private Fad fad;

    public FadIndhold(Fad fad) {
        this.alkoholProcentEfterModning = -1;
        this.fad = fad;
    }

    public void setAlkoholProcentEfterModning(double alkoholProcentEfterModning) {
        this.alkoholProcentEfterModning = alkoholProcentEfterModning;
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

    public Set<Påfyldning> getPåfyldninger() {
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

    public void addAftapning(Aftapning aftapning) {
        if (!aftapninger.contains(aftapning)) {
            aftapninger.add(aftapning);
        }
    }

    public void removeAftapning(Aftapning aftapning) {
        aftapninger.remove(aftapning);
    }

    public Fad getFad() {
        return fad;
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
    public double getMængde() {
        double mængde = 0;
        for (Påfyldning påfyldning : påfyldninger) {
            mængde += påfyldning.getMængdeILiter();
        }
        for (Aftapning aftapning : aftapninger) {
            mængde -= aftapning.getMængdeILiter();
        }
        return mængde;
    }

    /**
     * Returnerer omhældninger fra dette fad.
     * @return omhældninger fra dette fad
     */
    public Set<Omhældning> getOmhældningerFra() {
        return new HashSet<>(omhældningerFra);
    }

    /**
     * Tilføjer en omhældning fra dette fad.
     * @param omhældning omhældningen der skal tilføjes fra dette fad
     */
    public void addOmhældningFra(Omhældning omhældning) {
        omhældningerFra.add(omhældning);
    }

    /**
     * Returnerer omhældninger til dette fad.
     * @return omhældninger til dette fad
     */
    public Set<Omhældning> getOmhældningerTil() {
        return new HashSet<>(omhældningerTil);
    }

    /**
     * Tilføjer en omhældning til dette fad.
     * @param omhældning omhældningen der skal tilføjes til dette fad
     */
    public void addOmhældningTil(Omhældning omhældning) {
        omhældningerTil.add(omhældning);
    }

    public boolean isModnet() {
        return senestPåfyldt != null && senestPåfyldt.plusYears(3).isBefore(LocalDate.now());
    }

    public String hentHistorik() {
        String historik = fad.hentHistorik() + "\n";
        for (Påfyldning påfyldning : påfyldninger) {
            historik += påfyldning.hentHistorik() + "\n";
        }

        // TODO: Tilføj historik for omhældninger
        return historik;
    }
}
