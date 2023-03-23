package application.controller;

import application.model.*;
import storage.Storage;

public class Controller {

    private Storage storage;

    private static Controller controller;


    private Controller() {
        storage = Storage.getStorage();
    }


    public static Controller getController(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public Lager createLager(String adresse, String navn, double kvm) {
        Lager lager = new Lager(adresse, navn, kvm);
        storage.addLager(lager);
        return lager;
    }

    public Leverandør createLeverandør(String navn, String land) {
        Leverandør leverandør = new Leverandør(navn, land);
        storage.addLeverandør(leverandør);
        return leverandør;
    }

    public Hylde createHyldeForLager(Lager lager) {
        Hylde hylde = lager.createHylde();
        return hylde;
    }

    public Fad createFadForHylde(FadType fadType, double størrelseILiter, double indeholdtVæskeILiter, Leverandør leverandør, Hylde hylde) {
        Fad fad = new Fad(fadType, størrelseILiter, indeholdtVæskeILiter, leverandør, hylde);
        return fad;
    }

    /**
     * Tilføjer mockdata til Storage
     */
    public void initMockData() {
        // Tilføjer leverandører
        Leverandør l1 = controller.createLeverandør("Garrison Brothers", "USA");
        Leverandør l2 = controller.createLeverandør("Basque Moonshiners", "Spanien");
        Leverandør l3 = controller.createLeverandør("Mallorca Distillery", "Spanien");
        storage.addLeverandør(l1);
        storage.addLeverandør(l2);
        storage.addLeverandør(l3);

        // Tilføjer et lager
        Lager lager1 = controller.createLager("Baldersgade 39", "Sall Whisky Lager", 100);
        storage.addLager(lager1);

        //Tilføjer hylder til et lager
        Hylde h1 = controller.createHyldeForLager(lager1);
        Hylde h2 = controller.createHyldeForLager(lager1);
        Hylde h3 = controller.createHyldeForLager(lager1);
        Hylde h4 = controller.createHyldeForLager(lager1);

        //Tilføjer fade til hylderne
        Fad fad1 = controller.createFadForHylde(FadType.BOURBON, 80, 70, l1, h1);
        Fad fad2 = controller.createFadForHylde(FadType.BOURBON, 90, 88, l1, h1);
        Fad fad3 = controller.createFadForHylde(FadType.BOURBON, 90, 88, l1, h1);
        Fad fad4 = controller.createFadForHylde(FadType.RØDVIN, 60, 50, l1, h2);
        Fad fad5 = controller.createFadForHylde(FadType.RØDVIN, 70, 69, l1, h2);
        Fad fad6 = controller.createFadForHylde(FadType.RØDVIN, 80, 65, l1, h2);
        Fad fad7 = controller.createFadForHylde(FadType.SHERRY, 110, 100, l1, h3);
        Fad fad8 = controller.createFadForHylde(FadType.SHERRY, 120, 115, l1, h3);
        Fad fad9 = controller.createFadForHylde(FadType.SHERRY, 105, 102, l1, h3);
        Fad fad10 = controller.createFadForHylde(FadType.UBRUGT, 90, 0, l1, h4);
        Fad fad11 = controller.createFadForHylde(FadType.UBRUGT, 70, 0, l1, h4);
        Fad fad12 = controller.createFadForHylde(FadType.UBRUGT, 60, 0, l1, h4);
    }
}
