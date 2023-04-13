package storage;

import application.Setup;
import application.model.lager.FadLeverandør;
import application.model.lager.Flaske;
import application.model.lager.Lager;
import application.model.produktion.Destillat;
import application.model.produktion.Maltbatch;
import application.model.produktion.Whisky;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Storage implements Serializable {
    public static final String PATH = "src/storage/storage.ser";
    private static Storage instance;
    private Storage(){}

    public static Storage getStorage() {
        if (instance == null)
            if (new File(PATH).exists()) load();
            else {
                instance = new Storage();
                Setup.initMockData();
            }
        return instance;
    }

    public void clearStorage(){
        instance = new Storage();
    }

    // #--- Metoder for Serializable ---#
    public void save() {
        try {
            FileOutputStream f_out = new FileOutputStream(PATH);
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(instance);
            System.out.println("Storage is serialized...");
            obj_out.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void load() {
        Storage storage = null;
        try {
            FileInputStream f_in = new FileInputStream(PATH);
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            Object obj = obj_in.readObject();
            if (obj instanceof Storage) {
                storage = (Storage) obj;
            }
            obj_in.close();
            f_in.close();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        instance = storage;
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

    // #--- Flasker ---#
    private Set<Flaske> flasker = new TreeSet<>();

    public void addFlaske(Flaske flaske){
        flasker.add(flaske);
    }

    public void removeFlaske(Flaske flaske){
        flasker.remove(flaske);
    }

    public Set<Flaske> getFlasker(){
        return new TreeSet<>(flasker);
    }
}

