package application.model;

public enum RygeMateriale {
    TØRV("Tørv"),
    INTET("Intet");

    private final String navn;

    RygeMateriale(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
