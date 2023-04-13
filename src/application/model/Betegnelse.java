package application.model;

public enum Betegnelse {
    SINGLE_MALT("Single malt"),
    SINGLE_CASK("Single cask"),
    CASK_STRENGTH("Cask strength");

    private String navn;


    /**
     * Initialiserer en ny betegnelse med navn.
     * @param navn betegnelsens navn
     * @Pre navn != null
     */
    Betegnelse(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
