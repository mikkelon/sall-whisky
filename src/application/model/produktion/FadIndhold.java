package application.model.produktion;

import application.model.lager.Fad;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Modellerer et fad indhold som ligger på fadet.
 */
public class FadIndhold {
    private double alkoholProcentEfterModning;
    private LocalDate modningStartDato;
    private Set<Påfyldning> påfyldninger = new HashSet<>();
    private Set<Aftapning> aftapninger = new HashSet<>();
    private Set<Omhældning> fjernedeOmhældninger = new HashSet<>();
    private Set<Omhældning> tilføjedeOmhældninger = new HashSet<>();
    private Fad fad;

    /**
     * Opretter et fad indhold.
     * @param fad fadet som fad indholdet ligger på
     */
    public FadIndhold(Fad fad) {
        this.alkoholProcentEfterModning = -1;
        this.fad = fad;
        this.modningStartDato = null;
    }

    /**
     * Registrerer alkoholprocenten efter modning.
     * @param alkoholProcentEfterModning alkoholprocenten efter modning
     */
    public void setAlkoholProcentEfterModning(double alkoholProcentEfterModning) {
        this.alkoholProcentEfterModning = alkoholProcentEfterModning;
    }

    /**
     * Returnerer alkoholprocenten efter modning.
     * @return alkoholprocenten efter modning
     */
    public double getAlkoholProcentEfterModning() {
        return alkoholProcentEfterModning;
    }

    /**
     * Registrerer datoen for hvornår modningsprocessen er startet.
     * @param modningStartDato datoen for hvornår modningsprocessen er startet
     */
    public void setModningStartDato(LocalDate modningStartDato) {
        this.modningStartDato = modningStartDato;
    }

    /**
     * Returnerer datoen for seneste påfyldning.
     * @return datoen for seneste påfyldning
     */
    public LocalDate getModningStartDato() {
        return modningStartDato;
    }

    /**
     * Returnerer datoen for forventet færdig produceret.
     * @return datoen for forventet færdig produceret
     */
    public LocalDate forventetFærdigProduceret() {
        return modningStartDato.plusYears(3);
    }

    /**
     * Returnerer påfyldningerne der er foretaget på fadet.
     * @return påfyldningerne der er foretaget på fadet
     */
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
        if (modningStartDato == null || påfyldning.getPåfyldningsDato().isAfter(modningStartDato)) {
            modningStartDato = påfyldning.getPåfyldningsDato();
        }
    }

    /**
     * Returnerer aftapninger fra fadet.
     * @return aftapninger fra fadet
     */
    public Set getAftapninger() {
        return new HashSet<>(aftapninger);
    }

    /**
     * Tilføjer en aftapning til fadet.
     * @param aftapning aftapningen der skal tilføjes
     */
    public void addAftapning(Aftapning aftapning) {
        if (!aftapninger.contains(aftapning)) {
            aftapninger.add(aftapning);
        }
    }

    /**
     * Fjerner en aftapning fra fadet.
     * @param aftapning aftapningen der skal fjernes
     */
    public void removeAftapning(Aftapning aftapning) {
        aftapninger.remove(aftapning);
    }

    /**
     * Returnerer fadet som fad indholdet ligger på.
     * @return fadet som fad indholdet ligger på
     */
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
        for (Omhældning omhældning : fjernedeOmhældninger) {
            mængde -= omhældning.getMængdeILiter();
        }
        for (Omhældning omhældning : tilføjedeOmhældninger) {
            mængde += omhældning.getMængdeILiter();
        }
        return mængde;
    }

    /**
     * Returnerer omhældninger fra dette fad.
     * @return omhældninger fra dette fad
     */
    public Set<Omhældning> getFjernedeOmhældninger() {
        return new HashSet<>(fjernedeOmhældninger);
    }

    /**
     * Tilføjer en omhældning fra dette fad.
     * @param omhældning omhældningen der skal tilføjes fra dette fad
     */
    public void addOmhældningFra(Omhældning omhældning) {
        fjernedeOmhældninger.add(omhældning);
    }

    /**
     * Returnerer omhældninger til dette fad.
     * @return omhældninger til dette fad
     */
    public Set<Omhældning> getTilføjedeOmhældninger() {
        return new HashSet<>(tilføjedeOmhældninger);
    }

    /**
     * Tilføjer en omhældning til dette fad.
     * @param omhældning omhældningen der skal tilføjes til dette fad
     */
    public void addOmhældningTil(Omhældning omhældning) {
        tilføjedeOmhældninger.add(omhældning);
    }

    /**
     * Returnerer om fadet er modnet.
     * @return om fadet er modnet
     */
    public boolean isModnet() {
        return modningStartDato != null && modningStartDato.plusYears(3).isBefore(LocalDate.now());
    }

    /**
     * Returnerer en tekststreng med historik for fad indholdet.
     * @return en tekststreng med historik for fad indholdet
     */
    public String hentHistorik() {
        String historik = fad.hentHistorik() + "\n";

        for (Påfyldning påfyldning : påfyldninger) {
            historik += påfyldning.hentHistorik() + "\n";
        }

        if (tilføjedeOmhældninger.size() > 0) {
            historik += "Omhældninger:\n\n";
        }
        for (Omhældning omhældning : tilføjedeOmhældninger) {
            historik += omhældning.hentHistorik() + "\n";
        }

        return historik;
    }
}
