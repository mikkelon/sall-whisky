package application.model;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Modellerer et fad, som bruges til at modne destillat til whisky.
 */
public class Fad {
    private FadType fadType;
    private double størrelseILiter;
    private int fadNr;
    private static int antalFade;
    private Hylde hylde;
    private FadLeverandør fadLeverandør;
    private HashSet<Påfyldning> påfyldninger;
    private LocalDate senestPåfyldt;

    /**
     * Opretter et fad med en given størrelse, fadtype, fadleverandør og hylde.
     * fadNr sættes til et unikt nummer.
     *
     * @param fadType         fadets tidligere indhold, f.eks. "bourbon", "shery", osv.
     * @param størrelseILiter størrelsen på fadet i liter
     * @param fadLeverandør   fadleverandøren, der har leveret fadet
     * @param hylde           hylden hvor fadet er opbevaret
     */
    public Fad(FadType fadType, double størrelseILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        antalFade++;
        this.fadNr = antalFade;
        this.fadType = fadType;
        this.størrelseILiter = størrelseILiter;
        this.fadLeverandør = fadLeverandør;
        this.hylde = hylde;
        this.påfyldninger = new HashSet<>();
        hylde.addFad(this);
    }

    /**
     * Returnerer typen på fadet.
     *
     * @return typen på fadet
     */
    public FadType getFadType() {
        return fadType;
    }

    /**
     * Returnerer størrelsen på fadet i liter.
     *
     * @return størrelsen på fadet i liter
     */
    public double getStørrelseILiter() {
        return størrelseILiter;
    }

    /**
     * Returnerer det unikke nummer for fadet.
     *
     * @return unikt nummer for fadet
     */
    public int getFadNr() {
        return fadNr;
    }

    /**
     * Registrerer typen på fadet.
     *
     * @param fadType er fadets nye type.
     */
    public void setFadType(FadType fadType) {
        this.fadType = fadType;
    }

    /**
     * Returnerer hylden hvor fadet er opbevaret.
     *
     * @return hylden hvor fadet er opbevaret
     */
    public Hylde getHylde() {
        return hylde;
    }

    /**
     * Returnerer leverandøren for fadet.
     *
     * @return leverandøren for fadet
     */
    public FadLeverandør getFadLeverandør() {
        return fadLeverandør;
    }


    /**
     * Registrerer hylden hvor fadet er opbevaret.
     *
     * @param hylde er fadets nye hylde.
     */
    public void setHylde(Hylde hylde) {
        if (hylde == null) {
            throw new RuntimeException("Fadet skal være tilknyttet en hylde.");
        }
        if (this.hylde != hylde) {
            Hylde oldhylde = this.hylde;
            oldhylde.removeFad(this);
            this.hylde = hylde;
            hylde.addFad(this);
        }
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

    /**
     * Returnerer en kopi af påfyldningerne.
     * @return en kopi af påfyldningerne
     */
    public HashSet<Påfyldning> getPåfyldninger() {
    	return new HashSet<>(påfyldninger);
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

    /**
     * Udregner resterende plads i fadet i liter.
     * @return resterende plads i fadet i liter
     */
    public double resterendePladsILiter() {
    	return størrelseILiter - indeholdtVæskeILiter();
    }

    /**
     * Udregner forventet færdigproduceret dato.
     * @return forventet færdigproduceret dato
     */
    public LocalDate forventetFærdigproduceret() {
        return senestPåfyldt.plusYears(3);
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

    @Override
    public String toString() {
        return fadType + ", Nr: " + fadNr + ", Størrelse: " + størrelseILiter + "L, Indeholder: " + indeholdtVæskeILiter() + "L";
    }
}
