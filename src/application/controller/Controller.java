package application.controller;

import application.model.*;
import storage.Storage;

public class Controller {

    private Storage storage;

    private static Controller controller;


    private Controller() {
        storage = Storage.getStorage();
    }


    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public Lager createLager(String adresse, String navn, double kvm) {
        Lager lager = new Lager(adresse, navn, kvm);
        storage.addLager(lager);
        return lager;
    }

    public FadLeverandør createFadLeverandør(String navn, String land) {
        FadLeverandør fadLeverandør = new FadLeverandør(navn, land);
        storage.addFadLeverandør(fadLeverandør);
        return fadLeverandør;
    }

    public Hylde createHyldeForLager(Lager lager) {
        Hylde hylde = lager.createHylde();
        return hylde;
    }

    public Fad createFadForHylde(FadType fadType, double størrelseILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        Fad fad = new Fad(fadType, størrelseILiter, fadLeverandør, hylde);
        return fad;
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
        Lager lager1 = controller.createLager("Baldersgade 39", "Sall Whisky Lager", 100);
        storage.addLager(lager1);

        //Tilføjer hylder til et lager
        Hylde h1 = controller.createHyldeForLager(lager1);
        Hylde h2 = controller.createHyldeForLager(lager1);
        Hylde h3 = controller.createHyldeForLager(lager1);
        Hylde h4 = controller.createHyldeForLager(lager1);

        //Tilføjer fade til hylderne
        Fad fad1 = controller.createFadForHylde(FadType.BOURBON, 80, l1, h1);
        Fad fad2 = controller.createFadForHylde(FadType.BOURBON, 90, l1, h1);
        Fad fad3 = controller.createFadForHylde(FadType.BOURBON, 90, l1, h1);
        Fad fad4 = controller.createFadForHylde(FadType.RØDVIN, 60, l1, h2);
        Fad fad5 = controller.createFadForHylde(FadType.RØDVIN, 70, l1, h2);
        Fad fad6 = controller.createFadForHylde(FadType.RØDVIN, 80, l1, h2);
        Fad fad7 = controller.createFadForHylde(FadType.SHERRY, 110, l1, h3);
        Fad fad8 = controller.createFadForHylde(FadType.SHERRY, 120, l1, h3);
        Fad fad9 = controller.createFadForHylde(FadType.SHERRY, 105, l1, h3);
        Fad fad10 = controller.createFadForHylde(FadType.UBRUGT, 90, l1, h4);
        Fad fad11 = controller.createFadForHylde(FadType.UBRUGT, 70, l1, h4);
        Fad fad12 = controller.createFadForHylde(FadType.UBRUGT, 60, l1, h4);
    }
}
