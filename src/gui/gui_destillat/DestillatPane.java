package gui.gui_destillat;

import application.controller.ControllerForProduktion;
import application.model.produktion.Destillat;
import application.model.produktion.Maltbatch;
import application.model.RygeMateriale;
import gui.BekræftSletVindue;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

import java.time.LocalDate;
import java.util.ArrayList;

public class DestillatPane extends GridPane {
    private final Label lblError;
    private ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();
    private TextField txfnewMakeNummer = new TextField();
    private TextField txfMedarbejder = new TextField();
    private TextField txfAlkoholProcent = new TextField();
    private TextField txfAntalDestilleringer = new TextField();
    private DatePicker datePickerStartDato = new DatePicker();
    private DatePicker datePickerSlutDato = new DatePicker();
    private TextField txfMængdeILiter = new TextField();
    private ComboBox<RygeMateriale> cbxRygeMateriale = new ComboBox<>();
    private static TextArea txaMaltbatches = new TextArea();
    private TextArea txaKommentarer = new TextArea();
    private Button btnVælgMaltbatch = new Button("Vælg maltbatch...");
    private Button btnOpretFravælg = new Button("Opret");
    private ListView<Destillat> lvwDestillater = new ListView<>();
    private Button btnSletDestillat = new Button("Slet");
    private static ArrayList<Maltbatch> valgteMaltbatches = new ArrayList<>();
    public DestillatPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // #--- Destillater ---#
        Label lblNewMakeNummer = new Label("New make nummer");
        this.add(lblNewMakeNummer, 0, 0);
        this.add(txfnewMakeNummer, 0, 1);

        Label lblMedarbejder = new Label("Medarbejder");
        this.add(lblMedarbejder, 1, 0);
        this.add(txfMedarbejder, 1, 1);

        Label lblAlkoholProcent = new Label("Alkoholprocent");
        this.add(lblAlkoholProcent, 2, 0);
        this.add(txfAlkoholProcent, 2, 1);

        Label lblAntalDestilleringer = new Label("Antal destilleringer");
        this.add(lblAntalDestilleringer, 0, 2);
        this.add(txfAntalDestilleringer, 0, 3);

        Label lblStartDato = new Label("Startdato");
        this.add(lblStartDato, 1, 2);
        this.add(datePickerStartDato, 1, 3);

        Label lblSlutDato = new Label("Slutdato");
        this.add(lblSlutDato, 2, 2);
        this.add(datePickerSlutDato, 2, 3);

        Label lblMængdeILiter = new Label("Mængde (Liter)");
        this.add(lblMængdeILiter, 0, 4);
        this.add(txfMængdeILiter, 0, 5);

        Label lblRygeMateriale = new Label("Rygemateriale");
        this.add(lblRygeMateriale, 1, 4);
        cbxRygeMateriale.getItems().setAll(RygeMateriale.values());
        cbxRygeMateriale.getSelectionModel().select(RygeMateriale.INTET);
        this.add(cbxRygeMateriale, 1, 5);

        Label lblKommentarer = new Label("Kommentarer");
        this.add(lblKommentarer, 2, 4);
        txaKommentarer.setMinWidth(100);
        txaKommentarer.setMinHeight(50);
        txaKommentarer.setMaxWidth(100);
        txaKommentarer.setMaxHeight(50);
        this.add(txaKommentarer, 2, 5, 1, 2);

        Label lblMaltbatch = new Label("Maltbatch");
        this.add(lblMaltbatch, 0, 6);

//        cbxMaltbatch = new ComboBox<>();
//        GridPane.setValignment(cbxMaltbatch, VPos.TOP);
//        cbxMaltbatch.setMinWidth(150);
//        cbxMaltbatch.setMaxWidth(150);
//        cbxMaltbatch.getItems().setAll(controllerForProduktion.getMaltbatches());
//        this.add(cbxMaltbatch, 0,7);

        txaMaltbatches.setMinWidth(200);
        txaMaltbatches.setMinHeight(50);
        txaMaltbatches.setMaxWidth(200);
        txaMaltbatches.setMaxHeight(50);
        txaMaltbatches.setEditable(false);
        GridPane.setValignment(txaMaltbatches, VPos.TOP);
        this.add(txaMaltbatches, 0, 7);

        GridPane.setValignment(btnVælgMaltbatch, VPos.TOP);
        this.add(btnVælgMaltbatch, 1, 7);
        btnVælgMaltbatch.setOnAction(event -> vælgMaltbatchAction());

        this.add(new Separator(), 0, 8, 3, 1);
        this.add(new Separator(Orientation.VERTICAL), 3, 0, 1, 10);

        Label lblDestillater = new Label("Destillater");
        this.add(lblDestillater, 4, 0);
        this.add(lvwDestillater, 4, 1, 1, 7);
        lvwDestillater.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectionChanged());
        lvwDestillater.getItems().setAll(controllerForProduktion.getDestillater());

        this.add(btnOpretFravælg, 0, 9, 3, 1);
        GridPane.setHalignment(btnOpretFravælg, HPos.CENTER);
        btnOpretFravælg.setOnAction(event -> opretFravælgAction());

        this.add(btnSletDestillat, 4, 9);
        GridPane.setHalignment(btnSletDestillat, HPos.CENTER);
        btnSletDestillat.setOnAction(event -> sletDestillatAction());

        //#--- Error label ---#
        lblError = new Label(" ");
        this.add(lblError,0,7,3,1);
        GridPane.setHalignment(lblError, HPos.CENTER);
        GridPane.setValignment(lblError, VPos.BOTTOM);
        lblError.setStyle("-fx-text-fill: red");
    }

    private void vælgMaltbatchAction() {
        MaltbatchesVindue maltbatchesVindue = new MaltbatchesVindue();
        maltbatchesVindue.showAndWait();
        updateControls();
    }

    private void clearError() {
        lblError.setText(" ");
    }

    private void opretDestillat() {
        clearError();
        try {
            String newMakeNummer = txfnewMakeNummer.getText().trim();
            String medarbejder = txfMedarbejder.getText().trim();
            double alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText().trim());
            int antalDestilleringer = Integer.parseInt(txfAntalDestilleringer.getText().trim());
            LocalDate startDato = datePickerStartDato.getValue();
            LocalDate slutDato = datePickerSlutDato.getValue();
            double mængdeILiter = Double.parseDouble(txfMængdeILiter.getText().trim());
            RygeMateriale rygeMateriale = cbxRygeMateriale.getSelectionModel().getSelectedItem();
            String kommentarer = txaKommentarer.getText().trim();

            if (newMakeNummer.isEmpty()) {
                lblError.setText("Venligst indtast et New Make Nummer");
            } else if (medarbejder.isEmpty()) {
                lblError.setText("Venligst indtast et navn på medarbejderen");
            } else if (alkoholProcent < 0) {
                lblError.setText("Venligst indtast en positiv alkoholprocent");
            } else if (alkoholProcent > 100) {
                lblError.setText("Venligst indtast en alkoholprocent under 100%");
            } else if (antalDestilleringer <= 0) {
                lblError.setText("Venligst indtast et positivt antal destilleringer");
            } else if (startDato.isAfter(slutDato)) {
                lblError.setText("Venligst indtast en startdato før slutdatoen");
            } else if (mængdeILiter <= 0) {
                lblError.setText("Venligst indtast en positiv mængde i liter");
            } else if (rygeMateriale == null) {
                lblError.setText("Venligst vælg et rygemateriale");
            } else if(valgteMaltbatches.size() == 0){
                lblError.setText("Venligst vælg et maltbatch");
            } else {
                Destillat destillat = controllerForProduktion.createDestillat(newMakeNummer, medarbejder, alkoholProcent, antalDestilleringer, startDato, slutDato, mængdeILiter, kommentarer, rygeMateriale);
                for (Maltbatch maltbatch : valgteMaltbatches) {
                    controllerForProduktion.addMaltbatchToDestillat(destillat, maltbatch);
                }
                updateControls();
                clearFields();
            }
        } catch (NumberFormatException e) {
            lblError.setText("Venligst indtast tal i de relevante felter");
        } catch (RuntimeException e) {
            lblError.setText(e.getMessage());
        }
    }

    private void sletDestillatAction() {
        Destillat valgtDestillat = lvwDestillater.getSelectionModel().getSelectedItem();
        if (valgtDestillat != null) {
            BekræftSletVindue window = new BekræftSletVindue("Slet Destillat");
            window.showAndWait();
            boolean valg = window.getValg();
            if (valg) {
                try {
                    controllerForProduktion.removeDestillat(valgtDestillat);
                }
                catch (RuntimeException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Noget gik galt");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                updateControls();
            }
            controllerForProduktion.removeDestillat(valgtDestillat);
            updateControls();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inget destillat valgt");
            alert.setHeaderText(null);
            alert.setContentText("Vælg venligst et destillat fra listen.");
            alert.showAndWait();
        }
    }

    private void updateControls() {
        lvwDestillater.getItems().setAll(controllerForProduktion.getDestillater());
    }

    private void clearFields() {
        txfnewMakeNummer.clear();
        txfMedarbejder.clear();
        txfAlkoholProcent.clear();
        txfAntalDestilleringer.clear();
        datePickerStartDato.setValue(null);
        datePickerSlutDato.setValue(null);
        txfMængdeILiter.clear();
        cbxRygeMateriale.getSelectionModel().select(RygeMateriale.INTET);
        txaKommentarer.clear();
        txaMaltbatches.clear();
    }

    private void fillFields() {
        Destillat destillat = lvwDestillater.getSelectionModel().getSelectedItem();
        if (destillat != null) {
            txfnewMakeNummer.setText(destillat.getNewMakeNr());
            txfMedarbejder.setText(destillat.getMedarbejder());
            txfAlkoholProcent.setText(String.valueOf(destillat.getAlkoholProcent()));
            txfAntalDestilleringer.setText(String.valueOf(destillat.getAntalDestilleringer()));
            datePickerStartDato.setValue(destillat.getStartDato());
            datePickerSlutDato.setValue(destillat.getSlutDato());
            txfMængdeILiter.setText(String.valueOf(destillat.getMængdeILiter()));
            cbxRygeMateriale.getSelectionModel().select(destillat.getRygeMateriale());
            txaKommentarer.setText(destillat.getKommentar());

            fillMaltbatches(destillat.getMaltbatches());
        }
    }

    private void selectionChanged() {
        if (lvwDestillater.getSelectionModel().getSelectedItem() != null) {
            btnSletDestillat.setDisable(false);
            btnOpretFravælg.setText("Fravælg");
            setControlsDisable(true);
        } else {
            btnSletDestillat.setDisable(true);
            btnOpretFravælg.setText("Opret");
            setControlsDisable(false);
        }
        fillFields();
    }

    private void opretFravælgAction() {
        if (btnOpretFravælg.getText().equalsIgnoreCase("opret")) {
            opretDestillat();
        } else {
            lvwDestillater.getSelectionModel().clearSelection();
            clearFields();
        }
    }

    private void setControlsDisable(boolean disable) {
        txfnewMakeNummer.setEditable(!disable);
        txfMedarbejder.setEditable(!disable);
        txfAlkoholProcent.setEditable(!disable);
        txfAntalDestilleringer.setEditable(!disable);
        datePickerStartDato.setMouseTransparent(disable);
        datePickerStartDato.setFocusTraversable(!disable);
        datePickerSlutDato.setMouseTransparent(disable);
        datePickerSlutDato.setFocusTraversable(!disable);
        txfMængdeILiter.setEditable(!disable);
        cbxRygeMateriale.setMouseTransparent(disable);
        cbxRygeMateriale.setFocusTraversable(!disable);
        txaKommentarer.setEditable(!disable);
        txaMaltbatches.setEditable(!disable);
        btnVælgMaltbatch.setDisable(disable);
    }

    public static void fillMaltbatches(ArrayList<Maltbatch> maltbatches) {
        valgteMaltbatches = maltbatches;
        String maltbatchesString = "";
        for (Maltbatch maltbatch : maltbatches) {
            maltbatchesString += maltbatch + "\n";
        }
        txaMaltbatches.setText(maltbatchesString);
    }
}
