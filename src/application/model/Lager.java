package application.model;

import java.util.ArrayList;

public class Lager {
    private String adresse;
    private String navn;
    private double kvm;
    private ArrayList<Hylde> hylder = new ArrayList<>();

    /**
     * Initialiserer et nyt lager med adresse, navn, og kvm.
     * @param adresse lagerets adresse
     * @param navn lagerets navn
     * @param kvm lagerets kvm
     */
    public Lager(String adresse, String navn, double kvm) {
        this.adresse = adresse;
        this.navn = navn;
        this.kvm = kvm;
    }

    /**
     * Returnerer lagerets adresse.
     * @return lagerets adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Registrerer lagerets adresse.
     * @param adresse lagerets adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Returnerer lagerets navn.
     * @return lagerets navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * Registrerer lagerets navn.
     * @param navn lagerets navn
     */
    public void setNavn(String navn) {
        this.navn = navn;
    }

    /**
     * Returnerer lagerets kvm.
     * @return lagerets kvm
     */
    public double getKvm() {
        return kvm;
    }

    /**
     * Registrerer lagerets kvm.
     * @param kvm lagerets kvm
     */
    public void setKvm(double kvm) {
        this.kvm = kvm;
    }

    /**
     * Returnerer en ny liste med hylder fra lageret.
     * @return en ny liste med hylder fra lageret
     */
    public ArrayList<Hylde> getHylder() {
        return new ArrayList<>(hylder);
    }

    /**
     * Opretter en ny hylde til lageret.
     * @return en ny hylde til lageret
     */
    public Hylde createHylde() {
        Hylde hylde = new Hylde(this);
        hylder.add(hylde);
        hylde.setLager(this);
        return hylde;
    }

    /**
     * Registrerer en ny hylde til lageret.
     * @param hylde lagerets hylde
     */
    public void addHylde(Hylde hylde) {
        if (!hylder.contains(hylde)) {
            hylder.add(hylde);
            hylde.setLager(this);
        }
    }

    /**
     * Fjerner en hylde fra lageret.
     * @param hylde lagerets hylde
     */
    public void removeHylde(Hylde hylde) {
        if (!hylde.getFade().isEmpty()) {
            throw new RuntimeException("Hylden kan ikke slettes, når der er fade liggende på hylden.");
        }

        if (hylder.contains(hylde)) {
            hylder.remove(hylde);
            hylde.setLager(null);
        }
    }
}
