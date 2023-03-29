package application.model;

import java.util.ArrayList;

public class Whisky {
    private double alkoholProcent;
    private String betegnelse;
    private double mængdeVandILiter;
    private String vandAfstamning;
    private String tekstBeskrivelse;
    private int whiskyNr;
    private ArrayList<Aftapning> aftapninger = new ArrayList<>();

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
     * Fjerner en aftapning til en whisky.
     * @param aftapning fjernes fra en whisky
     */
    public void removeAftapning(Aftapning aftapning){
        if(aftapninger.contains(aftapning)){
            aftapninger.remove(aftapning);
        }
    }

}
