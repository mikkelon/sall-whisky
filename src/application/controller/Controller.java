package application.controller;

import application.model.*;
import storage.Storage;

import java.util.HashSet;

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
     */
    public void removeLager(Lager lager) {
        if (!lager.getHylder().isEmpty()) {
            throw new RuntimeException("Lageret kan ikke slettes, når der er hylder tilknyttet.");
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
     */
    public FadLeverandør createFadLeverandør(String navn, String land) {
        FadLeverandør fadLeverandør = new FadLeverandør(navn, land);
        storage.addFadLeverandør(fadLeverandør);
        return fadLeverandør;
    }

    /**
     * Fjerner en fadleverandør fra systemet
     * @param fadLeverandør fadleverandøren
     */
    public void removeFadLeverandør(FadLeverandør fadLeverandør) {
        storage.removeFadLeverandør(fadLeverandør); //TODO: efterlader fade der peger på fadleverandøren der ikke længere eksisterer
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
     */
    public Hylde createHylde(Lager lager) {
        Hylde hylde = lager.createHylde();
        return hylde;
    }

    /**
     * Fjerner det specifikke fad fra en hylde
     * @param fad
     */
    public void removeFad(Fad fad) {
        fad.getHylde().removeFad(fad);
    }

    /**
     * Fjerne den specifikke hylde fra et lager
     * @param hylde
     */
    public void removeHylde(Hylde hylde) {
        hylde.getLager().removeHylde(hylde);
        // TODO: må kun fjernes hvis den er tom
    }

    /**
     * Returnerer alle hylder i alle lagre
     * @return alle hylder i alle lagre
     */
    public HashSet<Hylde> getHylder() {
        HashSet<Hylde> hylder = new HashSet<>();
        for (Lager lager : storage.getLagre()) {
            hylder.addAll(lager.getHylder());
        }
        return hylder;
    }

    /**
     * Returnerer alle hylder i et givent lager
     * @param lager lageret
     * @return alle hylder i et givent lager
     */
    public HashSet<Hylde> getHylder(Lager lager) {
        return lager.getHylder();
    }

    /**
     * Opretter et nyt fad
     * @param fadType fadets tidligere indhold, f.eks. sherry, bourbon, osv.
     * @param størrelseILiter fadets volumen i liter
     * @param fadLeverandør fadleverandøren
     * @param hylde hylde hvor fadet skal placeres
     * @return det oprettede fad
     */
    public Fad createFad(FadType fadType, double størrelseILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        Fad fad = new Fad(fadType, størrelseILiter, fadLeverandør, hylde);
        return fad;
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
    }
}
