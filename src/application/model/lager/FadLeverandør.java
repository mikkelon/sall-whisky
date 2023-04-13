package application.model.lager;

import java.io.Serializable;

/**
 * Modellerer en leverandør, som leverer fade til Sall Whisky.
 */
public class FadLeverandør implements Serializable {
    private String navn;
    private String land;
    private int antalFade;

    /**
     * Initialiserer en ny leverandørs navn og land.
     * @param navn leverandørens navn
     * @param land leverandørens land
     * @Pre navn != null && land != null
     */
    public FadLeverandør(String navn, String land) {
        this.navn = navn;
        this.land = land;
        antalFade = 0;
    }

    /**
     * Returnerer leverandørens navn
     * @return leverandørens navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * Returnerer leverandørens land
     * @return leverandørens land
     */
    public String getLand() {
        return land;
    }

    /**
     * Returnerer antal fade leverandøren har leveret.
     * @return antal fade leverandøren har leveret.
     */
    public int getAntalFade() {
        return antalFade;
    }

    /**
     * Tilføjer et fad til det totale leveringsantal.
     */
    public void tilføjFad() {
        antalFade++;
    }

    /**
     * Fjerner et fad fra det totale leveringsantal.
     */
    public void fjernFad() {
        antalFade--;
    }

    @Override
    public String toString() {
        return navn + " (" + land + ")";
    }
}

