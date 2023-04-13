package application.model.produktion;

import java.io.Serializable;

/**
 * Modellerer en maltbatch, som bliver brugt destillering af whisky.
 */
public class Maltbatch implements Comparable<Maltbatch>, Serializable {
    private String kornsort;
    private String mark;
    private String gård;
    private String dyrketAf;
    private boolean økologisk;
    private int antalDestillater;
    private static int antalMaltbatches = 0;
    private int maltbatchNr;

    /**
     * Opretter en maltbatch.
     * <pre>
     * Pre: kornsort != null, mark != null, gård != null, dyrketAf != null
     * </pre>
     * @param kornsort maltets kornsort
     * @param mark marken hvor maltet er dyrket
     * @param gård gården hvor maltet er dyrket
     * @param dyrketAf navnet på den person der har dyrket maltet
     * @param økologisk om maltet er økologisk
     */
    public Maltbatch(String kornsort, String mark, String gård, String dyrketAf, boolean økologisk) {
        antalMaltbatches++;
        maltbatchNr = antalMaltbatches;
        this.kornsort = kornsort;
        this.mark = mark;
        this.gård = gård;
        this.dyrketAf = dyrketAf;
        this.økologisk = økologisk;
        antalDestillater = 0;
    }

    /**
     * Returnerer maltets kornsort.
     * @return maltets kornsort
     */
    public String getKornsort() {
        return kornsort;
    }

    /**
     * Returnerer marken hvor maltet er dyrket.
     * @return marken hvor maltet er dyrket
     */
    public String getMark() {
        return mark;
    }

    /**
     * Returnerer maltbatchens nummer.
     * @return maltbatchens nummer
     */
    public int getMaltbatchNr() {
        return maltbatchNr;
    }

    /**
     * Returnerer gården hvor maltet er dyrket.
     * @return gården hvor maltet er dyrket
     */
    public String getGård() {
        return gård;
    }

    /**
     * Returnerer navnet på den person der har dyrket maltet.
     * @return navnet på den person der har dyrket maltet
     */
    public String getDyrketAf() {
        return dyrketAf;
    }

    /**
     * Returnerer om maltet er økologisk.
     * @return om maltet er økologisk
     */
    public boolean isØkologisk() {
        return økologisk;
    }

    /**
     * Returnerer antal destillater der har det specifikke maltbatch.
     * @return antal destillater der har det specifikke maltbatch
     */
    public int getAntalDestillater(){
        return antalDestillater;
    }

    /**
     * Tiljøjer et destillat til det totale antal.
     */
    public void tilføjDestillat(){
        antalDestillater++;
    }

    /**
     * Fjerner et destillat fra totale antal.
     */
    public void fjernDestillat(){
        antalDestillater--;
    }

    /**
     * Returnerer maltbatchens historik.
     * @return maltbatchens historik
     */
    public String hentHistorik() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Maltbatch nr: " + maltbatchNr + ", " + kornsort + " fra " + gård + (økologisk ? " (økologisk)" : "");
    }

    @Override
    public int compareTo(Maltbatch o) {
        return this.maltbatchNr - o.maltbatchNr;
    }
}
