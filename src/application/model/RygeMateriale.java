package application.model;

public enum RygeMateriale {
    TØRV("Tørv"),
    INTET("Intet");

    private final String navn;

    /**
     * Initialiserer et nyt rygemateriale med navn.
     * @param navn rygematerialets navn
     * @Pre navn != null
     */
    RygeMateriale(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
