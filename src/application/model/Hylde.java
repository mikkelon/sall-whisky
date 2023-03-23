package application.model;

public class Hylde {
    private int hyldeNr;
    private static int antalHylder = 0;
    private Lager lager;

    public Hylde(Lager lager) {
        antalHylder++;
        this.hyldeNr = antalHylder;
        this.lager = lager;
    }

    public int getHyldeNr() {
        return hyldeNr;
    }

    public Lager getLager() {
        return lager;
    }

    public void setLager(Lager lager) {
        if (lager == null) {
            throw new RuntimeException("En hylde skal være tilknyttet et lager.");
        }

        if (this.lager != lager) {
            Lager oldLager = this.lager;
            oldLager.removeHylde(this);
            this.lager = lager;
            lager.addHylde(this);
        }
    }
}
