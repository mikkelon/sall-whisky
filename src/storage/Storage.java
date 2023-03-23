package storage;

import application.model.Fad;
import application.model.Lager;
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
