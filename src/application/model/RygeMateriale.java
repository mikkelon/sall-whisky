package application.model;

import java.io.Serializable;

public enum RygeMateriale implements Serializable {
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
