package storage;

import application.model.lager.FadLeverandør;
import application.model.lager.Lager;
import application.model.produktion.Destillat;
import application.model.produktion.Maltbatch;
import application.model.produktion.Whisky;

import java.util.HashSet;
import java.util.Set;

public class Storage {

    private static Storage storage;

    private Storage(){}

    public static Storage getStorage(){
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public void clearStorage(){
        storage = new Storage();
    }

    // #--- Fadleverandører ---#
    private final Set<FadLeverandør> fadLeverandører = new HashSet<>();

    public void addFadLeverandør(FadLeverandør fadLeverandør) {
        fadLeverandører.add(fadLeverandør);
    }

    public void removeFadLeverandør(FadLeverandør fadLeverandør) {
        fadLeverandører.remove(fadLeverandør);
    }

    public Set<FadLeverandør> getFadLeverandører() {
        return new HashSet<>(fadLeverandører);
    }

    // #--- Lager ---#

    private final Set<Lager> lagre = new HashSet<>();

    public void addLager(Lager lager){
        lagre.add(lager);
    }

    public void removeLager(Lager lager){
        lagre.remove(lager);
    }

    public HashSet<Lager> getLagre() {
        return new HashSet<>(lagre);
    }

    // #--- Destillation ---#

    private final Set<Destillat> destillater = new HashSet<>();

    public void addDestillat(Destillat destillat){
        destillater.add(destillat);
    }

    public void removeDestillat(Destillat destillat){
        destillater.remove(destillat);
    }

    public HashSet<Destillat> getDestillater() {
        return new HashSet<>(destillater);
    }

    // #--- Maltbatch ---#
    private final Set<Maltbatch> maltbatches = new HashSet<>();

    public void addMaltbatch(Maltbatch maltbatch){
        maltbatches.add(maltbatch);
    }

    public void removeMaltbatch(Maltbatch maltbatch){
        maltbatches.remove(maltbatch);
    }

    public HashSet<Maltbatch> getMaltbatches() {
        return new HashSet<>(maltbatches);
    }

    // #--- Whisky ---#

    private final Set<Whisky> whiskyer = new HashSet<>();

    public void addWhisky(Whisky whisky){
        whiskyer.add(whisky);
    }

    public void removeWhisky(Whisky whisky){
        whiskyer.remove(whisky);
    }

    public Set<Whisky> getWhiskyer(){
        return whiskyer;
    }
}

