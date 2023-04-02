package gui.gui_fade;

import application.controller.ControllerForLager;
import application.model.*;
import gui.BekræftSletVindue;
import gui.RegistrerAlkoholProcentVindue;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class FadePane extends GridPane {
    private final ComboBox<FadLeverandør> cbxFadLeverandør;
    private final TextField txfStørrelse;
    private final ComboBox<Lager> cbxLager;
    private final ComboBox<FadType> cbxFadType;
    private final ComboBox<Hylde> cbxHylde;
    private final Button btnRegistrerAlkoholprocent = new Button("Registrer alkoholprocent...");
    private final Button btnOpretFravælg;
    private final Button btnOpretLeverandør;
    private final Label lblError;
    private final Button btnSlet;
    private ControllerForLager controllerForLager = ControllerForLager.getController();
    private ListView<Påfyldning> lvwPåfyldninger;
    private ListView<Fad> lvwFade;

    public FadePane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblFadLeverandør = new Label("Fadleverandør");
        this.add(lblFadLeverandør, 0, 0);

        cbxFadLeverandør = new ComboBox<>();
        cbxFadLeverandør.setMinWidth(150);
        cbxFadLeverandør.setMaxWidth(150);
        this.add(cbxFadLeverandør, 0, 1);

        btnOpretLeverandør = new Button("Opret fadleverandør");
        btnOpretLeverandør.setOnAction(event -> opretFadLeverandørAction());
        this.add(btnOpretLeverandør, 0, 2, 1, 2);
        GridPane.setValignment(btnOpretLeverandør, VPos.TOP);

        Label lblLager = new Label("Lager");
        this.add(lblLager, 0, 4);

        cbxLager = new ComboBox<>();
        cbxLager.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.updateHylde());
        GridPane.setValignment(cbxLager, VPos.TOP);
        cbxLager.setMinWidth(150);
        cbxLager.setMaxWidth(150);
        this.add(cbxLager, 0, 5);

        Label lblStørrelse = new Label("Størrelse (Liter)");
        this.add(lblStørrelse, 1,0);

        txfStørrelse = new TextField();
        this.add(txfStørrelse, 1, 1);

        Label lblFadType = new Label("Fadtype");
        this.add(lblFadType, 1, 2);

        cbxFadType = new ComboBox<>();
        this.add(cbxFadType, 1, 3);

        Label lblHylde = new Label("Hylde");
        this.add(lblHylde, 1, 4);

        cbxHylde = new ComboBox<>();
        GridPane.setValignment(cbxHylde, VPos.TOP);
        cbxHylde.setMinWidth(150);
        cbxHylde.setMaxWidth(150);
        cbxHylde.setDisable(true);
        this.add(cbxHylde, 1, 5);

        Separator sep1 = new Separator(Orientation.VERTICAL);
        this.add(sep1, 2, 0,1,8);

        Label lblPåfyldninger = new Label("Påfyldninger");
        this.add(lblPåfyldninger, 3, 0);

        lvwPåfyldninger = new ListView<>();
        this.add(lvwPåfyldninger, 3, 1,1,7);
        lvwPåfyldninger.setMaxHeight(200);
        lvwPåfyldninger.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Påfyldning påfyldning, boolean empty) {
                super.updateItem(påfyldning, empty);
                if (empty || påfyldning == null) {
                    setText(null);
                } else {
                    setText(String.format("New Make: %s\nMængde: %.2f\nAlkohol: %.2f%%\nDato: %s\nMedarbejder: %s",
                            påfyldning.getDestillat().getNewMakeNr(), påfyldning.getMængdeILiter(),
                            påfyldning.getDestillat().getAlkoholProcent(), påfyldning.getPåfyldningsDato(),
                            påfyldning.getPåfyldtAf()));
                }
            }
        });

        Separator sep2 = new Separator(Orientation.VERTICAL);
        this.add(sep2, 4, 0, 1, 12);

        Label lblAlleFade = new Label("Alle fade");
        this.add(lblAlleFade, 5, 0);

        lvwFade = new ListView<>();
        lvwFade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectionChanged());
        this.add(lvwFade, 5, 1,1,10);

        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        this.add(sep3, 0, 8,4, 1);

        btnOpretFravælg = new Button("Opret");
        btnOpretFravælg.setOnAction(event -> opretFravælgAction());
        this.add(btnOpretFravælg, 0, 9, 2, 1);
        GridPane.setHalignment(btnOpretFravælg, HPos.CENTER);

        btnSlet = new Button("Slet");
        btnSlet.setOnAction(event -> sletAction());
        this.add(btnSlet, 5, 11);
        GridPane.setHalignment(btnSlet, HPos.CENTER);

        this.add(btnRegistrerAlkoholprocent, 0, 6);
        btnRegistrerAlkoholprocent.setDisable(true);
        btnRegistrerAlkoholprocent.setOnAction(event -> registrerAlkoholprocentAction());

        // #--- ErrorLabel ---#
        lblError = new Label(" ");
        this.add(lblError, 0,7,2,1);
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setStyle("-fx-text-fill: red");

        // #--- Update controls ---#
        updateControls();
    }

    private void registrerAlkoholprocentAction() {
        clearError();
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        RegistrerAlkoholProcentVindue registrerAlkoholProcentVindue = new RegistrerAlkoholProcentVindue(valgtFad);
        registrerAlkoholProcentVindue.showAndWait();
    }

    private void selectionChanged() {
        clearError();
        if (lvwFade.getSelectionModel().getSelectedItem() != null) {
            btnOpretFravælg.setText("Fravælg");
            updatePåfyldninger();
            setControlsDisabled(true);
            getInfo();
            btnSlet.setDisable(false);
        } else {
            btnOpretFravælg.setText("Opret");
            updatePåfyldninger();
            setControlsDisabled(false);
            clearInfo();
            btnSlet.setDisable(true);
        }
    }

    private void setControlsDisabled(Boolean b) {
        cbxFadLeverandør.setMouseTransparent(b);
        cbxFadLeverandør.setFocusTraversable(!b);
        txfStørrelse.setEditable(!b);
        cbxFadType.setMouseTransparent(b);
        cbxFadType.setFocusTraversable(!b);
        cbxLager.setMouseTransparent(b);
        cbxLager.setFocusTraversable(!b);
        cbxHylde.setMouseTransparent(b);
        cbxHylde.setFocusTraversable(!b);
        btnOpretLeverandør.setDisable(b);

        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        btnRegistrerAlkoholprocent.setDisable(fad == null
                || fad.getFadIndhold() == null
                || !fad.getFadIndhold().isModnet()
                || fad.getFadIndhold().getAlkoholProcentEfterModning() != -1);
    }

    private void opretFadLeverandørAction(){
        OpretFadLeverandør window = new OpretFadLeverandør();
        window.showAndWait();
        updateFadleverandører();
    }

    private void updateFadleverandører() {
        cbxFadLeverandør.getItems().setAll(controllerForLager.getFadLeverandører());
    }

    private void updateFade() {
        lvwFade.getItems().setAll(controllerForLager.getAlleFade());
    }

    private void updateInfo() {
        cbxFadLeverandør.getItems().setAll(controllerForLager.getFadLeverandører());
        cbxFadType.getItems().setAll(FadType.values());
        cbxLager.getItems().setAll(controllerForLager.getLagre());
    }

    private void updateHylde() {
        Lager valgtLager = cbxLager.getSelectionModel().getSelectedItem();
        if (valgtLager != null) {
            cbxHylde.getItems().setAll(valgtLager.getHylder());
            cbxHylde.setDisable(false);
        } else {
            cbxHylde.setDisable(true);
            cbxHylde.getItems().clear();
        }
    }

    private void updatePåfyldninger() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFad != null && !valgtFad.isEmpty()) {
            if (valgtFad.getFadIndhold() != null) {
                lvwPåfyldninger.getItems().setAll(valgtFad.getFadIndhold().getPåfyldninger());
            } else {
                lvwPåfyldninger.getItems().clear();
            }
        } else {
            lvwPåfyldninger.getItems().clear();
        }
    }

    private void getInfo() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFad != null) {
            cbxFadLeverandør.getSelectionModel().select(valgtFad.getFadLeverandør());
            txfStørrelse.setText(String.valueOf(valgtFad.getStørrelseILiter()));
            cbxFadType.getSelectionModel().select(valgtFad.getFadType());
            cbxHylde.getSelectionModel().select(valgtFad.getHylde());
            cbxLager.getSelectionModel().select(valgtFad.getHylde().getLager());
        }
    }

    private void clearInfo() {
        cbxFadLeverandør.getSelectionModel().clearSelection();
        txfStørrelse.clear();
        cbxFadType.getSelectionModel().clearSelection();
        cbxHylde.getItems().clear();
        cbxHylde.setValue(null);
        cbxLager.getSelectionModel().clearSelection();
    }

    public void updateControls() {
        updateFadleverandører();
        updateFade();
        updatePåfyldninger();
        updateInfo();
    }

    private void opretFravælgAction() {
        clearError();
        if (btnOpretFravælg.getText().equalsIgnoreCase("opret")) {
            FadLeverandør fadLeverandør = cbxFadLeverandør.getSelectionModel().getSelectedItem();
            FadType fadType = cbxFadType.getSelectionModel().getSelectedItem();
            Hylde hylde = cbxHylde.getSelectionModel().getSelectedItem();
            double størrelse = 0;
            try {
                størrelse = Double.parseDouble(txfStørrelse.getText());
            } catch (NumberFormatException e) {
                lblError.setText("Størrelse skal være et tal");
                return;
            }

            if (fadLeverandør == null) {
                lblError.setText("Vælg en leverandør");
            } else if (fadType == null) {
                lblError.setText("Vælg en fadtype");
            } else if (størrelse == 0) {
                lblError.setText("Vælg en størrelse");
            } else if (størrelse < 0) {
                lblError.setText("Størrelse skal være større end 0");
            } else if (hylde == null) {
                lblError.setText("Vælg en hylde");
            }
            else {
                controllerForLager.createFad(fadType, størrelse, fadLeverandør, hylde);
                clearInfo();
                updateFade();
            }
        } else {
            lvwFade.getSelectionModel().clearSelection();
            clearInfo();
        }
    }

    private void sletAction() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        BekræftSletVindue vindue = new BekræftSletVindue("Slet fad");
        vindue.showAndWait();
        if (vindue.getValg()) {
            controllerForLager.removeFad(valgtFad);
            updateFade();
            clearInfo();
        }
        clearError();
    }

    private void clearError() {
        lblError.setText(" ");
    }

    public void setFad(Fad fad) {
        if (fad != null && lvwFade.getItems().contains(fad)) {
            lvwFade.getSelectionModel().select(fad);
        }
    }
}
