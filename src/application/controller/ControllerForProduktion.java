package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.*;

/**
 * Controller-klassen håndterer forretningslogik og binder GUI sammen med modellen og storage-laget.
 */
public class ControllerForProduktion {
    private Storage storage;
    private static ControllerForProduktion controllerForProduktion;

    private ControllerForProduktion() {
        storage = Storage.getStorage();
    }

    /**
     * Opretter ny instans af controlleren eller returnerer den eksisterende
     * @return controlleren
     */
    public static ControllerForProduktion getController(){
        if (controllerForProduktion == null){
            controllerForProduktion = new ControllerForProduktion();
        }
        return controllerForProduktion;
    }

    /**
     * Opretter en ny instans af controlleren med formål at teste denne
     * @return en ny instans af controlleren
     */
    public static ControllerForProduktion getTestController() {
        return new ControllerForProduktion();
    }

    /**
     * Opretter storage på ny med formål at teste denne.
     */
    public void clearStorage() {
        storage.clearStorage();
    }

    /**
     * Returnerer alle destillater
     * @return alle destillater
     */
    public HashSet<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    /**
     * Opretter et nyt destillat.
     * @param newMakeNr destillatets unikke nummer
     * @param medarbejder navnet på medarbejderen der har destilleret
     * @param alkoholProcent alkoholprocenten i destillatet
     * @param antalDestilleringer antallet af destilleringer
     * @param startDato startdatoen for destilleringen
     * @param slutDato slutdatoen for destilleringen
     * @param mængdeILiter mængden af destillat i liter
     * @param kommentar kommentar til destillatet
     * @param rygeMateriale evt rygemateriale der er brugt
     * @return det oprettede destillat
     * Pre: newMakeNr != null, medarbejder != null, alkoholProcent > 0, antalDestilleringer > 0,
     *      startDato != null, slutDato != null, mængdeILiter > 0, kommentar != null, rygeMateriale != null
     */
    public Destillat createDestillat(String newMakeNr, String medarbejder, double alkoholProcent,
                                     int antalDestilleringer, LocalDate startDato, LocalDate slutDato,
                                     double mængdeILiter, String kommentar, RygeMateriale rygeMateriale) {
        if (getDestillat(newMakeNr) != null) {
            throw new RuntimeException("Destillatet findes allerede.");
        }
        Destillat destillat = new Destillat(newMakeNr, medarbejder, alkoholProcent, antalDestilleringer, startDato, slutDato, mængdeILiter, kommentar, rygeMateriale);
        storage.addDestillat(destillat);
        return destillat;
    }

    /**
     * Returnerer det specifikke destillat, hvis det findes
     * @param newMakeNr destillatets unikke nummer
     * @return det specifikke destillat ellers null
     * Pre: newMakeNr != null
     */
    private Object getDestillat(String newMakeNr) {
        for (Destillat destillat : storage.getDestillater()) {
            if (destillat.getNewMakeNr().equals(newMakeNr)) {
                return destillat;
            }
        }
        return null;
    }

    /**
     * Fjerner det specifikke destillat fra lageret
     * @param destillat destillatet der skal fjernes
     * Pre: destillat != null
     */
    public void removeDestillat(Destillat destillat) {
        if (!destillat.getPåfyldninger().isEmpty()) {
            throw new RuntimeException("Destillatet er påført fad og kan derfor ikke fjernes.");
        }
        storage.removeDestillat(destillat);
     }
     
     /**
     * Opretter en ny påfyldning
     * Pre: destillat != null, fad != null, påfyldtAf != null, mængdeILiter > 0, påfyldningsDato != null, fad.getStørrelseILiter() >= mængdeILiter
     * @param destillat destillatet der skal fyldes på fadet
     * @param fad fadet hvorpå påfyldningen skal foregå
     * @param påfyldtAf navnet på den person der har påfyldt fadet
     * @param mængdeILiter mængden af destillat i fadet i liter
     * @param påfyldningsDato dato for påfyldning
     * @return den oprettede påfyldning
     */
    public Påfyldning createPåfyldning(Destillat destillat, Fad fad, String påfyldtAf,
                                       double mængdeILiter, LocalDate påfyldningsDato) {
        Påfyldning påfyldning = new Påfyldning(destillat, fad, påfyldtAf, mængdeILiter, påfyldningsDato);

        if (påfyldning.getMængdeILiter() > destillat.resterendeMængdeILiter()) {
            throw new RuntimeException("Påfyldningen er større end destillatets resterende mængde.");
        } else if (påfyldning.getMængdeILiter() > fad.resterendePladsILiter()) {
            throw new RuntimeException("Påfyldningen er større end fadets resterende mængde.");
        } else {
            destillat.addPåfyldning(påfyldning);
            fad.addPåfyldning(påfyldning);
        }
        return påfyldning;
    }

    /**
     * Opretter en aftapning.
     * <pre>
     * Pre: aftappetAf != null, mængdeILiter > 0, aftapningsDato != null, fad != null, whisky != null
     * </pre>
     * @param aftappetAf hvem det er aftappet af
     * @param mængdeILiter hvor mange liter der er aftappet
     * @param aftapningsDato hvilket dato det er aftappet
     * @param fad hvilket fad der er aftappet fra
     * @param whisky hvilken whisky aftapningen tilføjes til
     * @return den oprettede aftapning
     */
    public Aftapning createAftapning(String aftappetAf, double mængdeILiter, LocalDate aftapningsDato, Fad fad, Whisky whisky) {
        Aftapning aftapning = new Aftapning(aftappetAf, mængdeILiter, aftapningsDato, fad, whisky);
        if (aftapning.getMængdeILiter() > fad.resterendePladsILiter()) {
            throw new RuntimeException("Aftapningen er større end fadets resterende mængde.");
        } else {
            fad.addAftapning(aftapning);
            whisky.addAftapning(aftapning);
        }
        return aftapning;
    }

    /**
     * Returnerer alle maltbatches.
     * @return alle maltbatches
     */
    public HashSet<Maltbatch> getMaltbatches(){
        return storage.getMaltbatches();
    }

    /**
     * Opretter et maltbatch
     * <pre>
     * Pre: kornsort != null, mark != null, gård != null, dyrketAf != null, økologisk != null
     * </pre>
     * @param kornsort hvilken kornsort maltbatchen kommer fra
     * @param mark hvilken mark maltbatchen kommer fra
     * @param gård hvilken gård maltbatchen kommer fra
     * @param dyrketAf hvem der har dyrket maltbatchen
     * @param økologisk om maltbatchen er økologisk
     * @return det oprettede maltbatch
     */
   public Maltbatch createMaltbatches(String kornsort, String mark, String gård, String dyrketAf, boolean økologisk){
        Maltbatch maltbatch = new Maltbatch(kornsort, mark, gård, dyrketAf, økologisk);
        storage.addMaltbatch(maltbatch);
        return maltbatch;
   }

    /**
     * Fjerner et maltbatch
     * <pre>
     * pre: maltbatch != null
     * </pre>
     * @param maltbatch fjernes
     */
   public void removeMaltbatch(Maltbatch maltbatch){
        if(maltbatch.getAntalDestillater() != 0){
            throw new RuntimeException("Maltbatch kan ikke slettes, når der er destillater tilknyttet.");
        }
        storage.removeMaltbatch(maltbatch);
   }

    /**
     * Opretter en whisky
     * <pre>
     * pre: aftapninger.size() > 0, alkoholprocent > 0, betegnelse != null, mængdeVandILiter > 0, vandAfstamning != null, tekstBeskrivelse != null
     * </pre>
     * @param aftapninger hvilke aftapninger der er foretaget
     * @param alkoholProcent alkoholprocenten på whiskyen
     * @param betegnelse betegnelsen for whiskyen
     * @param mængdeVandILiter mængden af vand i whiskyen
     * @param vandAfstamning hvor vandet kommer fra
     * @param tekstBeskrivelse tekstbeskrivelse om whiskyen
     * @return den oprettede whisky
     */
   public Whisky createWhisky(Set<Aftapning> aftapninger, double alkoholProcent, Betegnelse betegnelse, double mængdeVandILiter, String vandAfstamning, String tekstBeskrivelse){
        Whisky whisky = new Whisky(alkoholProcent, betegnelse, mængdeVandILiter,vandAfstamning, tekstBeskrivelse);
        for(Aftapning a : aftapninger){
            whisky.addAftapning(a);
        }
        storage.addWhisky(whisky);
        return whisky;
   }

    /**
     * Returnerer alle whiskyerne
     * @return alle whiskyerne
     */
   public HashSet<Whisky> getWhiskyer(){
        return storage.getWhiskyer();
   }

    /**
     * Tilføjer mockdata til Storage
     */
    public void initMockData() {
        //Tilføjer destillater
        Destillat d1 = controllerForProduktion.createDestillat("77p", "Mikkel", 53, 2, LocalDate.of(2023, 3, 27), LocalDate.of(2023, 3, 30), 80, "Kommentar", RygeMateriale.INTET);
        Destillat d2 = controllerForProduktion.createDestillat("78p", "Frederikke", 60, 2, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 2), 90.1, "Kommentar", RygeMateriale.INTET);
        Destillat d3 = controllerForProduktion.createDestillat("79p", "Anders", 61, 2, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 5), 80.5, "Kommentar", RygeMateriale.TØRV);
        Destillat d4 = controllerForProduktion.createDestillat("80p", "Mads", 59, 2, LocalDate.of(2023, 4, 3), LocalDate.of(2023, 4, 6), 87.4, "Kommentar", RygeMateriale.TØRV);
        Destillat d5 = controllerForProduktion.createDestillat("81p", "Mikkel", 62, 2, LocalDate.of(2023, 4, 10), LocalDate.of(2023, 4, 20), 72.43, "Kommentar", RygeMateriale.INTET);
    }
}
