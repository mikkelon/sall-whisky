package application.model;

public class Lager {
    private String adresse;
    private String navn;
    private double kvm;

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
}
