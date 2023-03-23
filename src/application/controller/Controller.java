package application.controller;

import application.model.*;
import storage.Storage;

public class Controller {

    public static Lager createLager(String adresse, String navn, double kvm) {
        Lager lager = new Lager(adresse, navn, kvm);
        Storage.addLager(lager);
        return lager;
    }

    public static FadLeverandør createFadLeverandør(String navn, String land) {
        FadLeverandør fadLeverandør = new FadLeverandør(navn, land);
        Storage.addFadLeverandør(fadLeverandør);
        return fadLeverandør;
    }

    public static Hylde createHyldeForLager(Lager lager) {
        Hylde hylde = lager.createHylde();
        return hylde;
    }

    public static Fad createFadForHylde(FadType fadType, double størrelseILiter, double indeholdtVæskeILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        Fad fad = new Fad(fadType, størrelseILiter, indeholdtVæskeILiter, fadLeverandør, hylde);
        return fad;
    }

    /**
     * Tilføjer mockdata til Storage
     */
    public static void initMockData() {
        // Tilføjer leverandører
        FadLeverandør l1 = Controller.createFadLeverandør("Garrison Brothers", "USA");
        FadLeverandør l2 = Controller.createFadLeverandør("Basque Moonshiners", "Spanien");
        FadLeverandør l3 = Controller.createFadLeverandør("Mallorca Distillery", "Spanien");
        Storage.addFadLeverandør(l1);
        Storage.addFadLeverandør(l2);
        Storage.addFadLeverandør(l3);

        // Tilføjer et lager
        Lager lager1 = Controller.createLager("Baldersgade 39", "Sall Whisky Lager", 100);
        Storage.addLager(lager1);

        //Tilføjer hylder til et lager
        Hylde h1 = Controller.createHyldeForLager(lager1);
        Hylde h2 = Controller.createHyldeForLager(lager1);
        Hylde h3 = Controller.createHyldeForLager(lager1);
        Hylde h4 = Controller.createHyldeForLager(lager1);

        //Tilføjer fade til hylderne
        Fad fad1 = Controller.createFadForHylde(FadType.BOURBON, 80, 70, l1, h1);
        Fad fad2 = Controller.createFadForHylde(FadType.BOURBON, 90, 88, l1, h1);
        Fad fad3 = Controller.createFadForHylde(FadType.BOURBON, 90, 88, l1, h1);
        Fad fad4 = Controller.createFadForHylde(FadType.RØDVIN, 60, 50, l1, h2);
        Fad fad5 = Controller.createFadForHylde(FadType.RØDVIN, 70, 69, l1, h2);
        Fad fad6 = Controller.createFadForHylde(FadType.RØDVIN, 80, 65, l1, h2);
        Fad fad7 = Controller.createFadForHylde(FadType.SHERRY, 110, 100, l1, h3);
        Fad fad8 = Controller.createFadForHylde(FadType.SHERRY, 120, 115, l1, h3);
        Fad fad9 = Controller.createFadForHylde(FadType.SHERRY, 105, 102, l1, h3);
        Fad fad10 = Controller.createFadForHylde(FadType.UBRUGT, 90, 0, l1, h4);
        Fad fad11 = Controller.createFadForHylde(FadType.UBRUGT, 70, 0, l1, h4);
        Fad fad12 = Controller.createFadForHylde(FadType.UBRUGT, 60, 0, l1, h4);


    }
}
