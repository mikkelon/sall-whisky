package application.model;

public enum FadType {
    BOURBON("Bourbon"),
    SHERRY("Sherry"),
    RØDVIN("Rødvin"),
    UBRUGT("Ubrugt");

    private final String navn;

    /**
     * Initialiserer en ny fadtype med navn.
     * @param navn fadtypens navn
     * @Pre navn != null
     */
    FadType(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
