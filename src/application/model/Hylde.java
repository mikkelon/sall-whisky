package application.model;

public class Hylde {
    private int hyldeNr;
    private Lager lager;

    public Hylde(int hyldeNr, Lager lager) {
        this.hyldeNr = hyldeNr;
        this.lager = lager;
    }

    public int getHyldeNr() {
        return hyldeNr;
    }

    public void setHyldeNr(int hyldeNr) {
        this.hyldeNr = hyldeNr;
    }

    public Lager getLager() {
        return lager;
    }

    public void setLager(Lager lager) {
        if (this.lager == lager) {
            Lager oldLager = this.lager;
            if (oldLager != null) {
                oldLager.removeHylde(this);
            }
            this.lager = lager;
            if (lager != null) {
                lager.addHylde(this);
            }
        }
    }
}
