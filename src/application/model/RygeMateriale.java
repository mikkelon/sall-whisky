package application.model;

public enum RygeMateriale {
    TØRV("Tørv"),
    INTET("Tørv");

    private final String navn;

    RygeMateriale(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
