package application;

import application.controller.ControllerForLager;
import application.controller.ControllerForProduktion;
import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.HashSet;

public class Setup {
    public static void initMockData() {
        Storage storage = Storage.getStorage();
        ControllerForLager controllerForLager = ControllerForLager.getController();
        ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();

        FadLeverandør l1 = controllerForLager.createFadLeverandør("Garrison Brothers", "USA");
        FadLeverandør l2 = controllerForLager.createFadLeverandør("Basque Moonshiners", "Spanien");
        FadLeverandør l3 = controllerForLager.createFadLeverandør("Mallorca Distillery", "Spanien");
        storage.addFadLeverandør(l1);
        storage.addFadLeverandør(l2);
        storage.addFadLeverandør(l3);

        // Tilføjer et lager
        Lager lager1 = controllerForLager.createLagerWithAntalHylder("Baldersgade 39", "Sall Whisky Lager", 100, 0);
        storage.addLager(lager1);

        //Tilføjer hylder til et lager
        Hylde h1 = controllerForLager.createHylde(lager1);
        Hylde h2 = controllerForLager.createHylde(lager1);
        Hylde h3 = controllerForLager.createHylde(lager1);
        Hylde h4 = controllerForLager.createHylde(lager1);

        //Tilføjer fade til hylderne
        Fad fad1 = controllerForLager.createFad(FadType.BOURBON, 80, l1, h1);
        Fad fad2 = controllerForLager.createFad(FadType.BOURBON, 90, l1, h1);
        Fad fad4 = controllerForLager.createFad(FadType.RØDVIN, 60, l1, h2);
        Fad fad3 = controllerForLager.createFad(FadType.BOURBON, 90, l1, h1);
        Fad fad5 = controllerForLager.createFad(FadType.RØDVIN, 70, l1, h2);
        Fad fad6 = controllerForLager.createFad(FadType.RØDVIN, 80, l1, h2);
        Fad fad7 = controllerForLager.createFad(FadType.SHERRY, 110, l1, h3);
        Fad fad8 = controllerForLager.createFad(FadType.SHERRY, 120, l1, h3);
        Fad fad9 = controllerForLager.createFad(FadType.SHERRY, 105, l1, h3);
        Fad fad10 = controllerForLager.createFad(FadType.UBRUGT, 90, l1, h4);
        Fad fad11 = controllerForLager.createFad(FadType.UBRUGT, 70, l1, h4);
        Fad fad12 = controllerForLager.createFad(FadType.UBRUGT, 60, l1, h4);


        Maltbatch m1 = controllerForProduktion.createMaltbatch("Evergreen", "mark1", "Lars' bondegård", "Lars", true);
        Maltbatch m2 = controllerForProduktion.createMaltbatch("Evergreen", "mark2", "Lars' bondegård", "Lars", true);
        Maltbatch m3 = controllerForProduktion.createMaltbatch("Evergreen", "mark3", "Lars' bondegård", "Lars", true);

        //Tilføjer destillater
        Destillat d1 = controllerForProduktion.createDestillat("77p", "Mikkel", 53, 2, LocalDate.of(2023, 3, 27), LocalDate.of(2023, 3, 30), 80, "Kommentar", RygeMateriale.INTET);
        Destillat d2 = controllerForProduktion.createDestillat("78p", "Frederikke", 60, 2, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 2), 90.1, "Kommentar", RygeMateriale.INTET);
        Destillat d3 = controllerForProduktion.createDestillat("79p", "Anders", 61, 2, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 5), 80.5, "Kommentar", RygeMateriale.TØRV);
        Destillat d4 = controllerForProduktion.createDestillat("80p", "Mads", 59, 2, LocalDate.of(2023, 4, 3), LocalDate.of(2023, 4, 6), 87.4, "Kommentar", RygeMateriale.TØRV);
        Destillat d5 = controllerForProduktion.createDestillat("81p", "Mikkel", 62, 2, LocalDate.of(2023, 4, 10), LocalDate.of(2023, 4, 20), 72.43, "Kommentar", RygeMateriale.INTET);

        controllerForProduktion.addMaltbatchToDestillat(d1, m1);
        controllerForProduktion.addMaltbatchToDestillat(d2, m1);
        controllerForProduktion.addMaltbatchToDestillat(d3, m2);
        controllerForProduktion.addMaltbatchToDestillat(d4, m3);
        controllerForProduktion.addMaltbatchToDestillat(d5, m3);

        // Tilføjer påfyldninger
        LocalDate datoGammel = LocalDate.of(2019, 1,1);
        LocalDate datoNy = LocalDate.of(2023, 1,1);
        controllerForProduktion.createPåfyldning(d1, fad1, "Mikkel", 80, datoNy);
        controllerForProduktion.createPåfyldning(d2, fad2, "Mikkel", 20, datoGammel);
        controllerForProduktion.createPåfyldning(d3, fad2, "Mikkel", 40, datoGammel);
        controllerForProduktion.createPåfyldning(d4, fad4, "Mikkel", 10, datoGammel);
        controllerForProduktion.createPåfyldning(d5, fad4, "Mikkel", 10, datoGammel);
        controllerForProduktion.createPåfyldning(d2, fad5, "Mikkel", 10, datoNy);
        controllerForProduktion.createPåfyldning(d2, fad6, "Mikkel", 10, datoGammel);
        controllerForProduktion.createPåfyldning(d2, fad7, "Mikkel", 10, datoGammel);

        // Sæt alkoholprocent efter modning
        controllerForProduktion.setAlkoholprocentEfterModning(fad2.getFadIndhold(), 52);
        controllerForProduktion.setAlkoholprocentEfterModning(fad4.getFadIndhold(), 56);

        // Laver aftapninger
        HashSet<Aftapning> aftapningHashSet = new HashSet<>();
        Aftapning a1 = controllerForProduktion.createAftapning("Mikkel", 10, LocalDate.now(), fad4);
        aftapningHashSet.add(a1);
        Aftapning a2 = controllerForProduktion.createAftapning("Mikkel", 10, LocalDate.now(), fad2);
        aftapningHashSet.add(a2);

        // Tilføjer en whisky
        controllerForProduktion.createWhisky(aftapningHashSet, 20, "Begravet Dal", "Lækker whisky :)");

        //Opretter en omhældning
        controllerForProduktion.createOmhældning("Mikkel",10,LocalDate.of(2023,4,10),fad4.getFadIndhold(),fad5.getFadIndhold());


        controllerForProduktion.createOmhældning("Mads",20,LocalDate.of(2023,4,10),fad1.getFadIndhold(),fad2.getFadIndhold());
    }
}
