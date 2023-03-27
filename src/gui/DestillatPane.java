package gui;

import application.controller.Controller;
import application.model.Destillat;
import application.model.RygeMateriale;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.Date;

public class DestillatPane extends GridPane {
    private Controller controller = Controller.getController();
    private TextField txfnewMakeNummer = new TextField();
    private TextField txfMedarbejder = new TextField();
    private TextField txfAlkoholProcent = new TextField();
    private TextField txfAntalDestilleringer = new TextField();
    private DatePicker datePickerStartDato = new DatePicker();
    private DatePicker datePickerSlutDato = new DatePicker();
    private TextField txfMængdeILiter = new TextField();
    private ComboBox<RygeMateriale> cbxRygeMateriale = new ComboBox<>();
    private TextArea txaKommentarer = new TextArea();
    private Button btnOpretDestillat = new Button("Opret");
    private ListView<Destillat> lvwDestillater = new ListView<>();
    private Button btnSletDestillat = new Button("Slet");
    public DestillatPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // #--- Destillater ---#
        Label lblNewMakeNummer = new Label("New make Nummer");
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

        this.add(new Separator(), 0, 8, 3, 1);
        this.add(new Separator(Orientation.VERTICAL), 3, 0, 1, 10);

        Label lblDestillater = new Label("Destillater");
        this.add(lblDestillater, 4, 0);
        this.add(lvwDestillater, 4, 1, 1, 7);
        lvwDestillater.getItems().setAll(controller.getDestillater());

        this.add(btnOpretDestillat, 0, 9, 3, 1);
        GridPane.setHalignment(btnOpretDestillat, HPos.CENTER);
        btnOpretDestillat.setOnAction(event -> opretDestillatAction());

        this.add(btnSletDestillat, 4, 9);
        GridPane.setHalignment(btnSletDestillat, HPos.CENTER);
        btnSletDestillat.setOnAction(event -> sletDestillatAction());
    }

    private void opretDestillatAction() {
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

            controller.createDestillat(newMakeNummer, medarbejder, alkoholProcent, antalDestilleringer, startDato, slutDato, mængdeILiter, kommentarer, rygeMateriale);
            updateControls();
            clearFields();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Noget gik galt");
            alert.setHeaderText(null);
            alert.setContentText("Du skal udfylde alle felter korrekt");
            alert.showAndWait();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Noget gik galt");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage() + " Venligst ret fejlen og prøv igen.");
            alert.showAndWait();
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
                    controller.removeDestillat(valgtDestillat);
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
            controller.removeDestillat(valgtDestillat);
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
        lvwDestillater.getItems().setAll(controller.getDestillater());
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
    }
}
