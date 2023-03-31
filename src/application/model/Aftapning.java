package application.model;

import java.time.LocalDate;

/**
 * Modellerer en aftapning af et fad på et bestemt whisky produkt.
 */
public class Aftapning {
    private String aftappetAf;
    private double mængdeILiter;
    private LocalDate aftapningsDato;
    private Fad fad;
    private Whisky whisky;

    /**
     * Opretter en aftapning af et fad på et bestemt whisky produkt.
     * <pre>
     * Pre: fad != null, whisky != null, mængdeILiter > 0, aftappetAf != null, aftapningsDato != null
     * </pre>
     * @param aftappetAf navnet på den person der aftapper
     * @param mængdeILiter mængden der aftappes i liter
     * @param aftapningsDato dato for aftapningen
     * @param fad fadet der aftappes
     * @param whisky whisky produktet som aftapningen er på
     */
    public Aftapning(String aftappetAf, double mængdeILiter, LocalDate aftapningsDato, Fad fad, Whisky whisky) {
        this.aftappetAf = aftappetAf;
        this.mængdeILiter = mængdeILiter;
        this.aftapningsDato = aftapningsDato;
        this.fad = fad;
        this.whisky = whisky;
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
     * Returnerer fadet der er aftappet.
     * @return fadet der er aftappet
     */
    public Fad getFad(){
        return fad;
    }

    /**
     * Returnerer whisky produktet som aftapningen er på.
     * @return whisky produktet som aftapningen er på
     */
    public Whisky getWhisky(){
        return whisky;
    }
}
