package application.model;

public enum Betegnelse {
    SINGLE_MALT("Single malt"),
    SINGLE_CASK("Single cask"),
    CASK_STRENGTH("Cask strength");

    private String navn;

    Betegnelse(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
