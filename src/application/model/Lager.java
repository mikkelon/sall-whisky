package application.model;

import java.util.*;

/**
 * Modellerer et lager med hylder, hvorpå der opbevares whiskyfade.
 */
public class Lager {
    private String adresse;
    private String navn;
    private double kvm;
    private int antalHylder;
    private ArrayList<Hylde> hylder = new ArrayList<>();

    /**
     * Initialiserer et nyt lager med adresse, navn, og kvm.
     *
     * @param adresse lagerets adresse
     * @param navn    lagerets navn
     * @param kvm     lagerets kvm
     */
    public Lager(String adresse, String navn, double kvm) {
        this.adresse = adresse;
        this.navn = navn;
        this.kvm = kvm;
        this.antalHylder = 0;
    }

    /**
     * Returnerer lagerets adresse.
     *
     * @return lagerets adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Registrerer lagerets adresse.
     *
     * @param adresse lagerets adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Returnerer lagerets navn.
     *
     * @return lagerets navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * Registrerer lagerets navn.
     *
     * @param navn lagerets navn
     */
    public void setNavn(String navn) {
        this.navn = navn;
    }

    /**
     * Returnerer lagerets kvm.
     *
     * @return lagerets kvm
     */
    public double getKvm() {
        return kvm;
    }

    /**
     * Registrerer lagerets kvm.
     *
     * @param kvm lagerets kvm
     */
    public void setKvm(double kvm) {
        this.kvm = kvm;
    }

    /**
     * Returnerer en ny liste med hylder fra lageret.
     *
     * @return en ny liste med hylder fra lageret
     */
    public ArrayList<Hylde> getHylder() {
        return new ArrayList<>(hylder);
    }

    /**
     * Opretter en ny hylde til lageret.
     *
     * @return en ny hylde til lageret
     */
    public Hylde createHylde() {
        antalHylder++;
        Hylde hylde = new Hylde(this);
        insertSorted(hylde);
        return hylde;
    }

    /**
     * Indsætter en hylde i sorteret rækkefølge.
     *
     * @param hylde hylde der skal indsættes
     */
    private void insertSorted(Hylde hylde) {
        int i = 0;
        while (i < hylder.size() && hylde.getHyldeNr() > hylder.get(i).getHyldeNr()) {
            i++;
        }
        hylder.add(i, hylde);
    }


    /**
     * Fjerner en hylde fra lageret.
     *
     * @param hylde lagerets hylde
     *              Pre: hylde er tom
     */
    public void removeHylde(Hylde hylde) {
        antalHylder--;
        hylder.remove(hylde);
    }

    /**
     * Returnerer antal hylder på lageret.
     *
     * @return antal hylder på lageret
     */
    public int getAntalHylder() {
        return antalHylder;
    }


    @Override
    public String toString() {
        return navn
                + "\n" + adresse
                + "\n" + kvm + " kvm";
    }
}
