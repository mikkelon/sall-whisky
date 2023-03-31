package application.model;

import java.time.LocalDate;

/**
 * Modellerer en aftapning af et fad på et bestemt whisky produkt.
 */
public class Aftapning {
    private String aftappetAf;
    private double mængdeILiter;
    private LocalDate aftapningsDato;
    private FadIndhold fadIndhold;
    private Whisky whisky;

    /**
     * Opretter en aftapning af et fad på et bestemt whisky produkt.
     * <pre>
     * Pre: fad != null, whisky != null, mængdeILiter > 0, aftappetAf != null, aftapningsDato != null
     * </pre>
     * @param aftappetAf navnet på den person der aftapper
     * @param mængdeILiter mængden der aftappes i liter
     * @param aftapningsDato dato for aftapningen
     * @param fadIndhold fad indholdet der aftappes
     */
    public Aftapning(String aftappetAf, double mængdeILiter, LocalDate aftapningsDato, FadIndhold fadIndhold) {
        this.aftappetAf = aftappetAf;
        this.mængdeILiter = mængdeILiter;
        this.aftapningsDato = aftapningsDato;
        this.fadIndhold = fadIndhold;
        fadIndhold.addAftapning(this);
    }


    /**
     * Returnerer navnet på den person der har aftappet fadet.
     * @return navnet på den person der har aftappet fadet
     */
    public String getAftappetAf() {
        return aftappetAf;
    }

    /**
     * Returnerer mængden der er aftappet i liter.
     * @return mængden der er aftappet i liter
     */
    public double getMængdeILiter() {
        return mængdeILiter;
    }

    /**
     * Returnerer datoen for aftapningen.
     * @return datoen for aftapningen
     */
    public LocalDate getAftapningsDato() {
        return aftapningsDato;
    }

    /**
     * Returnerer fad indholdet der er aftappet fra.
     * @return fad indholdet der er aftappet fra
     */
    public FadIndhold getFadIndhold() {
        return fadIndhold;
    }

    /**
     * Returnerer whisky produktet som aftapningen er på.
     * @return whisky produktet som aftapningen er på
     */
    public Whisky getWhisky(){
        return whisky;
    }

    /**
     * Sætter whisky produktet som aftapningen er på.
     * @param whisky er whisky produktet som aftapningen er på
     */
    public void setWhisky(Whisky whisky) {
        this.whisky = whisky;
    }

    public String hentHistorik() {
        return fadIndhold.hentHistorik();
    }
}
