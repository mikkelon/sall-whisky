package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.*;

/**
 * Controller-klassen håndterer forretningslogik og binder GUI sammen med modellen og storage-laget.
 */
public class Controller {
    private Storage storage;
    private static Controller controller;

    private Controller() {
        storage = Storage.getStorage();
    }

    /**
     * Opretter ny instans af controlleren eller returnerer den eksisterende
     * @return controlleren
     */
    public static Controller getController(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    /**
     * Opretter en ny instans af controlleren med formål at teste denne
     * @return en ny instans af controlleren
     */
    public static Controller getTestController() {
        return new Controller();
    }

    /**
     * Opretter et nyt lager med en angivet antal hylder
     * @param adresse lagerets adresse
     * @param navn lagerets navn
     * @param kvm lagerets kvm
     * @param antalHylder antal hylder der skal oprettes
     * @return det oprettede lager
     * Pre: adresse != null, navn != null, kvm > 0, antalHylder >= 0
     */
    public Lager createLagerWithAntalHylder(String adresse, String navn, double kvm, int antalHylder) {
        Lager lager = new Lager(adresse, navn, kvm);
        storage.addLager(lager);

        for (int i = 0; i < antalHylder; i++) {
            lager.createHylde();
        }

        return lager;
    }

    /**
     * Fjerner et lager fra systemet
     * @param lager lageret
     * Pre: lager != null
     */
    public void removeLager(Lager lager) {
        for (Hylde hylde : lager.getHylder()) {
            if (!hylde.getFade().isEmpty()) {
                throw new RuntimeException("Lageret kan ikke slettes, når der er fade tilknyttet til en eller flere hylder.");
            }
        }
        storage.removeLager(lager);
    }

    /**
     * Returnerer alle lagre
     * @return alle lagre
     */
   public HashSet<Lager> getLagre() {
        return storage.getLagre();
    }

    /**
     * Opretter en ny fadleverandør
     * @param navn navnet på fadleverandøren
     * @param land landet fadleverandøren kommer fra
     * @return den oprettede fadleverandør
     * Pre: navn != null, land != null
     */
    public FadLeverandør createFadLeverandør(String navn, String land) {
        FadLeverandør fadLeverandør = new FadLeverandør(navn, land);
        storage.addFadLeverandør(fadLeverandør);
        return fadLeverandør;
    }

    /**
     * Fjerner en fadleverandør fra systemet
     * @param fadLeverandør fadleverandøren
     * Pre: fadLeverandør != null
     */
    public void removeFadLeverandør(FadLeverandør fadLeverandør) {
        if (fadLeverandør.getAntalFade() != 0) {
            throw new RuntimeException("Fadleverandøren kan ikke slettes, når der er fade tilknyttet.");
        }
        storage.removeFadLeverandør(fadLeverandør);
    }

    /**
     * Returnerer alle fadleverandører
     * @return alle fadleverandører
     */
    public HashSet<FadLeverandør> getFadLeverandører() {
        return storage.getFadLeverandører();
    }

    /**
     * Opretter en ny hylde i et givent lager
     * @param lager lageret
     * @return den oprettede hylde
     * Pre: lager != null
     */
    public Hylde createHylde(Lager lager) {
        Hylde hylde = lager.createHylde();
        return hylde;
    }

    /**
     * Fjerne den specifikke hylde fra et lager
     * @param hylde hylden der skal fjernes
     * Pre: hylde != null
     */
    public void removeHylde(Hylde hylde) {
        if (!hylde.getFade().isEmpty()) {
            throw new RuntimeException("Hylden kan ikke slettes, når der er fade liggende på hylden.");
        }

        hylde.getLager().removeHylde(hylde);
    }

    /**
     * Returnerer alle hylder i alle lagre
     * @return alle hylder i alle lagre
     */
    public ArrayList<Hylde> getHylder() {
        ArrayList<Hylde> hylder = new ArrayList<>();
        for (Lager lager : storage.getLagre()) {
            hylder.addAll(lager.getHylder());
        }
        return hylder;
    }

    /**
     * Returnerer alle hylder i et givent lager
     * @param lager lageret
     * @return alle hylder i et givent lager
     * Pre: lager != null
     */
    public ArrayList<Hylde> getHylder(Lager lager) {
        return lager.getHylder();
    }

    /**
     * Opretter et nyt fad
     * @param fadType fadets tidligere indhold, f.eks. sherry, bourbon, osv.
     * @param størrelseILiter fadets volumen i liter
     * @param fadLeverandør fadleverandøren
     * @param hylde hylde hvor fadet skal placeres
     * @return det oprettede fad
     * Pre: fadType != null, størrelseILiter > 0, fadLeverandør != null, hylde != null
     */
    public Fad createFad(FadType fadType, double størrelseILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        Fad fad = new Fad(fadType, størrelseILiter, fadLeverandør, hylde);
        fad.getFadLeverandør().tilføjFad();
        return fad;
    }

    /**
     * Fjerner det specifikke fad fra en hylde
     * @param fad fadet der skal fjernes
     * Pre: fad != null
     */
    public void removeFad(Fad fad) {
        fad.getHylde().removeFad(fad);
        fad.getFadLeverandør().fjernFad();
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
     * Returnerer alle fade
     * Pre: destillat != null, fad != null, påfyldtAf != null, mængdeILiter > 0, påfyldningsDato != null
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

        if (påfyldning.getMængdeILiter() > fad.getStørrelseILiter()) {
            throw new RuntimeException("Påfyldningen er større end fadets størrelse.");
        } else {
            destillat.addPåfyldning(påfyldning);
            fad.addPåfyldning(påfyldning);
        }

        return påfyldning;
    }

    /**
     * Tilføjer mockdata til Storage
     */
    public void initMockData() {
        // Tilføjer leverandører

        FadLeverandør l1 = controller.createFadLeverandør("Garrison Brothers", "USA");
        FadLeverandør l2 = controller.createFadLeverandør("Basque Moonshiners", "Spanien");
        FadLeverandør l3 = controller.createFadLeverandør("Mallorca Distillery", "Spanien");
        storage.addFadLeverandør(l1);
        storage.addFadLeverandør(l2);
        storage.addFadLeverandør(l3);

        // Tilføjer et lager
        Lager lager1 = controller.createLagerWithAntalHylder("Baldersgade 39", "Sall Whisky Lager", 100, 0);
        storage.addLager(lager1);

        //Tilføjer hylder til et lager
        Hylde h1 = controller.createHylde(lager1);
        Hylde h2 = controller.createHylde(lager1);
        Hylde h3 = controller.createHylde(lager1);
        Hylde h4 = controller.createHylde(lager1);

        //Tilføjer fade til hylderne
        Fad fad1 = controller.createFad(FadType.BOURBON, 80, l1, h1);
        Fad fad2 = controller.createFad(FadType.BOURBON, 90, l1, h1);
        Fad fad4 = controller.createFad(FadType.RØDVIN, 60, l1, h2);
        Fad fad3 = controller.createFad(FadType.BOURBON, 90, l1, h1);
        Fad fad5 = controller.createFad(FadType.RØDVIN, 70, l1, h2);
        Fad fad6 = controller.createFad(FadType.RØDVIN, 80, l1, h2);
        Fad fad7 = controller.createFad(FadType.SHERRY, 110, l1, h3);
        Fad fad8 = controller.createFad(FadType.SHERRY, 120, l1, h3);
        Fad fad9 = controller.createFad(FadType.SHERRY, 105, l1, h3);
        Fad fad10 = controller.createFad(FadType.UBRUGT, 90, l1, h4);
        Fad fad11 = controller.createFad(FadType.UBRUGT, 70, l1, h4);
        Fad fad12 = controller.createFad(FadType.UBRUGT, 60, l1, h4);

        //Tilføjer destillater
        Destillat d1 = controller.createDestillat("77p", "Mikkel", 53, 2, LocalDate.of(2023, 3, 27), LocalDate.of(2023, 3, 30), 80, "Kommentar", RygeMateriale.INTET);
        Destillat d2 = controller.createDestillat("78p", "Frederikke", 60, 2, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 2), 90.1, "Kommentar", RygeMateriale.INTET);
        Destillat d3 = controller.createDestillat("79p", "Anders", 61, 2, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 5), 80.5, "Kommentar", RygeMateriale.TØRV);
        Destillat d4 = controller.createDestillat("80p", "Mads", 59, 2, LocalDate.of(2023, 4, 3), LocalDate.of(2023, 4, 6), 87.4, "Kommentar", RygeMateriale.TØRV);
        Destillat d5 = controller.createDestillat("81p", "Mikkel", 62, 2, LocalDate.of(2023, 4, 10), LocalDate.of(2023, 4, 20), 72.43, "Kommentar", RygeMateriale.INTET);
    }
}
