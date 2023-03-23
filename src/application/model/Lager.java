package application.model;

import java.util.ArrayList;

public class Lager {
    private String adresse;
    private String navn;
    private double kvm;
    private ArrayList<Hylde> hylder = new ArrayList<>();

    public Lager(String adresse, String navn, double kvm) {
        this.adresse = adresse;
        this.navn = navn;
        this.kvm = kvm;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getKvm() {
        return kvm;
    }

    public void setKvm(double kvm) {
        this.kvm = kvm;
    }

    public ArrayList<Hylde> getHylder() {
        return new ArrayList<>(hylder);
    }

    public Hylde createHylde() {
        Hylde hylde = new Hylde(this);
        return hylde;
    }

    public void addHylde(Hylde hylde) {
        if (!hylder.contains(hylde)) {
            hylder.add(hylde);
            hylde.setLager(this);
        }
    }

    public void removeHylde(Hylde hylde) {
        if (hylder.contains(hylde)) {
            hylder.remove(hylde);
            hylde.setLager(null);
        }
    }
}
