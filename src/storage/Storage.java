package storage;

import application.model.Lager;
import application.model.FadLeverandør;

import java.util.HashSet;

public class Storage {

    // #--- Fadleverandører ---#
    private static HashSet<FadLeverandør> fadLeverandører = new HashSet<>();

    public static void addFadLeverandør(FadLeverandør fadLeverandør) {
        fadLeverandører.add(fadLeverandør);
    }

    public static void removeFadLeverandør(FadLeverandør fadLeverandør) {
        fadLeverandører.remove(fadLeverandør);
    }

    public static HashSet<FadLeverandør> getFadLeverandører() {
        return new HashSet<>(fadLeverandører);
    }

    // #--- Lager ---#

    private static HashSet <Lager> lagre = new HashSet<>();

    public static void addLager(Lager lager){
        lagre.add(lager);
    }

    public static void removeLager(Lager lager){
        lagre.remove(lager);
    }

    public static HashSet<Lager> getLagre() {
        return new HashSet<>(lagre);
    }
}
