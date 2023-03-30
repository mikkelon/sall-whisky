package application.model;

import java.util.ArrayList;

/**
 * Modellerer et whiskyprodukt som kan bestå af flere forskellige aftapninger på fade.
 */
public class Whisky {
    private double alkoholProcent;
    private Betegnelse betegnelse;
    private double mængdeVandILiter;
    private String vandAfstamning;
    private String tekstBeskrivelse;
    private static int antalWhiskyProdukter = 0;
    private int whiskyNr;
    private ArrayList<Aftapning> aftapninger = new ArrayList<>();

    /**
     * Initialiserer et nyt whiskyprodukt med alkoholprocent, betegnelse, mængde vand i liter, vandafstamning, tekstbeskrivelse og et unikt nummer.
     * Pre: 0 < alkoholProcent < 100, mængdeVandILiter >= 0, tekstBeskrivelse != null, betegnelse != null, vandAfstamning != null
     * @param alkoholProcent alkoholprocenten på whiskyproduktet
     * @param betegnelse betegnelsen på whiskyproduktet
     * @param mængdeVandILiter mængden af vand i liter der er brugt i whiskyproduktet
     * @param vandAfstamning afstamningen af vandet der er brugt i whiskyproduktet
     * @param tekstBeskrivelse tekstbeskrivelsen af whiskyproduktet
     */
    public Whisky(double alkoholProcent, Betegnelse betegnelse, double mængdeVandILiter, String vandAfstamning, String tekstBeskrivelse) {
        antalWhiskyProdukter++;
        this.whiskyNr = antalWhiskyProdukter;
        this.alkoholProcent = alkoholProcent;
        this.betegnelse = betegnelse;
        this.mængdeVandILiter = mængdeVandILiter;
        this.vandAfstamning = vandAfstamning;
        this.tekstBeskrivelse = tekstBeskrivelse;
    }

    /**
     * Returnerer alkoholprocenten på whiskyproduktet.
     * @return alkoholprocenten på whiskyproduktet
     */
    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    /**
     * Returnerer betegnelsen på whiskyproduktet.
     * @return betegnelsen på whiskyproduktet
     */
    public Betegnelse getBetegnelse() {
        return betegnelse;
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

}
