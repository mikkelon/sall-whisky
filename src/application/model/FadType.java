package application.model;

public enum FadType {
    BOURBON("Bourbon"),
    SHERRY("Sherry"),
    RØDVIN("Rødvin"),
    UBRUGT("Ubrugt");

    private final String navn;

    FadType(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
