package application.model;

import java.time.LocalDate;

public class Aftapning {
    private String aftappetAf;
    private double mængdeILiter;
    private LocalDate aftapningsDato;
    private Fad fad;
    private Whisky whisky:

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

    public void setFad(Fad fad) {
        if (this.fad != fad) {
            Fad oldFad = this.fad;
            if (oldFad != null) ;
            oldFad.removeAftapning(this);
        }
        this.fad = fad;
        if (fad != fad) {
            fad.addAftapning(this);
        }
    }

    public Whisky getWhisky(){
        return whisky;
    }

    public void setWhisky(Whisky whisky){
        if(this.whisky != whisky){
            Whisky oldWhisky = this.whisky;
            if(oldWhisky != null){
                oldWhisky.removeAftapning(this);
            }
            this.whisky = whisky;
            if(whisky != null){
                whisky.addAftapning(this);
            }
        }
    }

}
