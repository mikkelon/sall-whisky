package application.model;

import java.util.ArrayList;

/**
 * Modellerer et whiskyprodukt som kan bestå af flere forskellige aftapninger på fade.
 */
public class Whisky {
    private double mængdeVandILiter;
    private String vandAfstamning;
    private String tekstBeskrivelse;
    private static int antalWhiskyProdukter = 0;
    private int whiskyNr;
    private ArrayList<Aftapning> aftapninger = new ArrayList<>();

    /**
     * Initialiserer et nyt whiskyprodukt med alkoholprocent, betegnelse, mængde vand i liter, vandafstamning, tekstbeskrivelse og et unikt nummer.
     * <pre>
     * Pre: mængdeVandILiter >= 0, tekstBeskrivelse != null, vandAfstamning != null
     * </pre>
     * @param mængdeVandILiter mængden af vand i liter der er brugt i whiskyproduktet
     * @param vandAfstamning afstamningen af vandet der er brugt i whiskyproduktet
     * @param tekstBeskrivelse tekstbeskrivelsen af whiskyproduktet
     */
    public Whisky(double mængdeVandILiter, String vandAfstamning, String tekstBeskrivelse) {
        this.whiskyNr = antalWhiskyProdukter;
        this.mængdeVandILiter = mængdeVandILiter;
        this.vandAfstamning = vandAfstamning;
        this.tekstBeskrivelse = tekstBeskrivelse;
    }

    public static void tælAntalWhiskyOp() {
        antalWhiskyProdukter++;
    }

    /**
     * Returnerer mængden af vand i liter der er brugt i whiskyproduktet.
     * @return mængden af vand i liter der er brugt i whiskyproduktet
     */
    public double getMængdeVandILiter() {
        return mængdeVandILiter;
    }

    /**
     * Returnerer afstamningen af vandet der er brugt i whiskyproduktet.
     * @return afstamningen af vandet der er brugt i whiskyproduktet
     */
    public String getVandAfstamning() {
        return vandAfstamning;
    }

    /**
     * Returnerer tekstbeskrivelsen af whiskyproduktet.
     * @return tekstbeskrivelsen af whiskyproduktet
     */
    public String getTekstBeskrivelse() {
        return tekstBeskrivelse;
    }

    /**
     * Returnerer et unikt nummer på whiskyproduktet.
     * @return et unikt nummer på whiskyproduktet
     */
    public int getWhiskyNr() {
        return whiskyNr;
    }

    /**
     * Returnerer en liste over aftapninger.
     * @return en liste over aftapninger
     */
    public ArrayList<Aftapning> getAftapninger(){
        return new ArrayList<>(aftapninger);
    }

    /**
     * Tilføjer en aftapning til en whisky.
     * @param aftapning tiløjes til en whisky
     */
    public void addAftapning(Aftapning aftapning){
        if(!aftapninger.contains(aftapning)){
            aftapninger.add(aftapning);
        }
    }

    /**
     * Returnerer betegnelsen på whiskyproduktet.
     * @return betegnelsen på whiskyproduktet
     */
    public Betegnelse getBetegnelse() {
        if (aftapninger.size() == 0) {
            return null;
        } else {
            boolean singleCask = true;
            Fad fad = aftapninger.get(0).getFadIndhold().getFad();
            int i = 1;
            while (i < aftapninger.size() && singleCask) {
                if (!aftapninger.get(i).getFadIndhold().getFad().equals(fad)) {
                    singleCask = false;
                }
                i++;
            }
            if (singleCask) {
                if (mængdeVandILiter == 0) return Betegnelse.CASK_STRENGTH;
                else return Betegnelse.SINGLE_CASK;
            }
            return Betegnelse.SINGLE_MALT;
        }
    }

    /**
     * Returnerer alkoholprocenten på whiskyproduktet.
     * @return alkoholprocenten på whiskyproduktet
     */
    public double getAlkoholProcent() {
        if (aftapninger.size() == 0) {
            return 0;
        } else {
            double væskeMængde = mængdeVandILiter;
            double alkoholMængde = 0.0;
            for (Aftapning aftapning : aftapninger) {
                væskeMængde += aftapning.getMængdeILiter();
                alkoholMængde += (aftapning.getMængdeILiter() / 100.0) * aftapning.getFadIndhold().getAlkoholProcentEfterModning();
            }
            return alkoholMængde / væskeMængde * 100;
        }
    }

    /**
     * Returnerer en tekststreng med historikken for whiskyproduktet.
     * @return en tekststreng med historikken for whiskyproduktet
     */
    public String hentHistorik() {
        String historik = "";
        for (Aftapning aftapning : aftapninger) {
            historik += aftapning.hentHistorik();
        }
        return historik;
    }

    @Override
    public String toString(){
        return "Whisky nr:" + " " + Integer.toString(whiskyNr);
    }
}
