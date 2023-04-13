package application.controller;

import storage.Storage;

public class StorageController {
    private Storage storage;
    private static StorageController controller;
    private ControllerForLager controllerForLager;
    private ControllerForProduktion controllerForProduktion;

    private StorageController() {
        storage = Storage.getStorage();
        controllerForLager = ControllerForLager.getController();
        controllerForProduktion = ControllerForProduktion.getController();
    }

    public static StorageController getInstance() {
        if (controller == null)
            controller = new StorageController();
        return controller;
    }

    // #--- Methods for serializing ---#

    public void saveStorage() {
        storage.save();
    }

    public void loadStorage() {
        Storage.load();
        storage = Storage.getStorage();
        controllerForLager.updateStorage();
        controllerForProduktion.updateStorage();
    }
}
