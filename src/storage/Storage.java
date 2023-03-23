package storage;

import application.model.Fad;
import application.model.Leverandør;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Storage {

    // #--- Leverandører ---#
    private static HashSet<Leverandør> leverandører = new HashSet<>();

    public static void addLeverandør(Leverandør leverandør) {
        leverandører.add(leverandør);
    }

    public static void removeLeverandør(Leverandør leverandør) {
        leverandører.remove(leverandør);
    }

    public static HashSet<Leverandør> getLeverandører() {
        return new HashSet<>(leverandører);
    }

    // #--- Fade ---#
    private static HashSet<Fad> fade = new HashSet<>();

    public static void addFad(Fad fad) {
        fade.add(fad);
    }

    public static void removeFad(Fad fad) {
        fade.remove(fad);
    }

    public static HashSet<Fad> getFade() {
        return new HashSet<>(fade);
    }
}
