package storage;

import application.model.Fad;
import application.model.Lager;
import application.model.Leverandør;
import com.sun.source.tree.InstanceOfTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Storage {

    private static Storage storage;

    private Storage(){

    }

    public static Storage getStorage(){
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }



    // #--- Leverandører ---#
    private HashSet<Leverandør> leverandører = new HashSet<>();

    public void addLeverandør(Leverandør leverandør) {
        storage.leverandører.add(leverandør);
    }

    public void removeLeverandør(Leverandør leverandør) {
        storage.leverandører.remove(leverandør);
    }

    public HashSet<Leverandør> getLeverandører() {
        return new HashSet<>(storage.leverandører);
    }

    // #--- Lager ---#

    private HashSet <Lager> lagre = new HashSet<>();

    public void addLager(Lager lager){
        storage.lagre.add(lager);
    }

    public void removeLager(Lager lager){
        storage.lagre.remove(lager);
    }

    public HashSet<Lager> getLagre() {
        return new HashSet<>(storage.lagre);
    }
}
