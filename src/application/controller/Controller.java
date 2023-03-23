package application.controller;

import application.model.Fad;
import application.model.FadType;
import application.model.Leverandør;
import storage.Storage;

public class Controller {

    /**
     * Tilføjer mockdata til Storage
     */
    public static void initMockData() {
        // Tilføjer leverandører
        Leverandør l1 = new Leverandør("Garrison Brothers", "USA");
        Leverandør l2 = new Leverandør("Basque Moonshiners", "Spanien");
        Leverandør l3 = new Leverandør("Mallorca Distillery", "Spanien");
        Storage.addLeverandør(l1);
        Storage.addLeverandør(l2);
        Storage.addLeverandør(l3);
    }
}
