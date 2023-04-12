package gui.gui_fade;

import application.controller.ControllerForLager;
import application.model.*;
import application.model.lager.Fad;
import application.model.lager.FadLeverandør;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import application.model.produktion.Aftapning;
import application.model.produktion.Omhældning;
import application.model.produktion.Påfyldning;
import gui.BekræftSletVindue;
import gui.RegistrerAlkoholProcentVindue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.HashSet;
import java.util.TreeSet;

public class FadePane extends GridPane {
    private final ComboBox<FadLeverandør> cbxFadLeverandør;
    private final TextField txfStørrelse;
    private final ComboBox<Lager> cbxLager;
    private final ComboBox<FadType> cbxFadType;
    private final ComboBox<Hylde> cbxHylde;
    private final Button btnRegistrerAlkoholprocent = new Button("Registrer alkoholprocent...");
    private final Button btnOpretFravælg, btnOpretLeverandør, btnSlet;
    private final Label lblError;
    private final ListView<Aftapning> lvwAftapninger;
    private final RadioButton rbtnModnet, rbtnIkkeModnet, rbtnFyldt, rbtnDelvistFyldt, rbtnTom;
    private ControllerForLager controllerForLager = ControllerForLager.getController();
    private ListView<Påfyldning> lvwPåfyldninger;
    private ListView<Fad> lvwFade;
    private ListView<Omhældning> lvwOmhældninger;

    public FadePane() {
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
        this.add(lblStørrelse, 1, 0);

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
        this.add(sep1, 2, 0, 1, 7);

        Label lblPåfyldninger = new Label("Påfyldninger");
        this.add(lblPåfyldninger, 3, 0);

        lvwPåfyldninger = new ListView<>();
        this.add(lvwPåfyldninger, 3, 1, 1, 7);
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

        lvwAftapninger = new ListView<>();
        this.add(lvwAftapninger, 3, 9, 1, 2);
        lvwAftapninger.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Aftapning aftapning, boolean empty) {
                super.updateItem(aftapning, empty);
                if (empty || aftapning == null) {
                    setText(null);
                } else {
                    setText(String.format("Whisky #%s\n" +
                                    "Mængde: %.2f\n" +
                                    "Dato: %s\n" +
                                    "Medarbejder: %s",
                            aftapning.getWhisky().getWhiskyNr(), aftapning.getMængdeILiter(), aftapning.getAftapningsDato(),
                            aftapning.getAftappetAf()));
                }
            }
        });

        lvwOmhældninger = new ListView<>();
        this.add(lvwOmhældninger, 0, 9, 2, 1);
        lvwOmhældninger.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Omhældning omhældning, boolean empty) {
                super.updateItem(omhældning, empty);
                if (empty || omhældning == null) {
                    setText(null);
                } else {
                    setText(String.format("Fra %s\n" +
                                    "Mængde: %.2f\n" +
                                    "Dato: %s\n" +
                                    "Medarbejder: %s",
                            omhældning.getFraFadIndhold().getFad(), omhældning.getMængdeILiter(), omhældning.getOmhældningsDato(),
                            omhældning.getOmhældtAf()));
                }
            }

        });



        Separator sep2 = new Separator(Orientation.VERTICAL);
        this.add(sep2, 4, 0, 1, 13);

        Label lblAlleFade = new Label("Alle fade");
        this.add(lblAlleFade, 5, 0);

        lvwFade = new ListView<>();
        lvwFade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectionChanged());
        this.add(lvwFade, 5, 1, 1, 10);

        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        this.add(sep3, 0, 7, 2, 1);

        btnOpretFravælg = new Button("Opret");
        btnOpretFravælg.setOnAction(event -> opretFravælgAction());
        this.add(btnOpretFravælg, 0, 12, 2, 1);
        GridPane.setHalignment(btnOpretFravælg, HPos.CENTER);

        btnSlet = new Button("Slet");
        btnSlet.setOnAction(event -> sletAction());
        this.add(btnSlet, 5, 11);
        GridPane.setHalignment(btnSlet, HPos.CENTER);

        this.add(btnRegistrerAlkoholprocent, 0, 6);
        btnRegistrerAlkoholprocent.setDisable(true);
        btnRegistrerAlkoholprocent.setOnAction(event -> registrerAlkoholprocentAction());

        Label lblOmhældning = new Label("Omhældninger");
        this.add(lblOmhældning, 0, 8);

        ListView<String> lvwOmhældning = new ListView<>();
        this.add(lvwOmhældning, 0, 9, 2, 1);

        Label lblAftapninger = new Label("Aftapninger");
        this.add(lblAftapninger, 3, 8);

        Separator sep4 = new Separator(Orientation.HORIZONTAL);
        this.add(sep4, 0, 11, 4, 1);

        // #--- Filtrer ---#
        GridPane gridPaneFilter = new GridPane();
        gridPaneFilter.setAlignment(Pos.TOP_CENTER);
        gridPaneFilter.setHgap(10);
        gridPaneFilter.setVgap(10);
        gridPaneFilter.setGridLinesVisible(false);
        this.add(gridPaneFilter, 6, 0,1,12);

        Label lblFilter = new Label("Filtrer");
        gridPaneFilter.add(lblFilter, 0, 0, 2, 1);

        ToggleGroup tgrpModnet = new ToggleGroup();

        rbtnModnet = new RadioButton("Modnet");
        rbtnModnet.setToggleGroup(tgrpModnet);
        gridPaneFilter.add(rbtnModnet, 0, 1);

        rbtnIkkeModnet = new RadioButton("Ikke modnet");
        rbtnIkkeModnet.setToggleGroup(tgrpModnet);
        gridPaneFilter.add(rbtnIkkeModnet, 0, 2);

        ToggleGroup tgrpFyldt = new ToggleGroup();

        rbtnFyldt = new RadioButton("Fyldt");
        rbtnFyldt.setToggleGroup(tgrpFyldt);
        gridPaneFilter.add(rbtnFyldt, 1, 1);

        rbtnDelvistFyldt = new RadioButton("Delvist fyldt");
        rbtnDelvistFyldt.setToggleGroup(tgrpFyldt);
        gridPaneFilter.add(rbtnDelvistFyldt, 1, 2);

        rbtnTom = new RadioButton("Tom");
        rbtnTom.setToggleGroup(tgrpFyldt);
        gridPaneFilter.add(rbtnTom, 1, 3);

        HBox hBoxFilter = new HBox();
        hBoxFilter.setSpacing(10);
        hBoxFilter.setAlignment(Pos.CENTER);
        gridPaneFilter.add(hBoxFilter, 0, 4, 2, 1);

        Button btnFiltrer = new Button("Filtrer");
        btnFiltrer.setOnAction(event -> filtrerAction());
        hBoxFilter.getChildren().add(btnFiltrer);

        Button btnFjernFilter = new Button("Fjern filter");
        btnFjernFilter.setOnAction(event -> fjernFilterAction());
        hBoxFilter.getChildren().add(btnFjernFilter);

        // #--- ErrorLabel ---#
        lblError = new Label(" ");
        this.add(lblError, 0, 7, 2, 1);
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setStyle("-fx-text-fill: red");

        // #--- Update controls ---#
        updateControls();
    }

    private void filtrerAction() {
        ObservableList<Fad> fadObservableList = FXCollections.observableArrayList();
        fadObservableList.addAll(controllerForLager.getAlleFade());
        FilteredList<Fad> filteredList = new FilteredList<>(fadObservableList, fad -> {
            if (rbtnModnet.isSelected() && rbtnFyldt.isSelected()) {
                return !fad.isEmpty() && fad.getFadIndhold().isModnet() && fad.isFull();
            } else if (rbtnModnet.isSelected() && rbtnDelvistFyldt.isSelected()) {
                return !fad.isEmpty() && fad.getFadIndhold().isModnet() && !fad.isFull();
            } else if (rbtnModnet.isSelected() && rbtnTom.isSelected()) {
                return !fad.isEmpty() && fad.getFadIndhold().isModnet() && fad.isFull();
            } else if (rbtnIkkeModnet.isSelected() && rbtnFyldt.isSelected()) {
                return !fad.isEmpty() && !fad.getFadIndhold().isModnet() && fad.isFull();
            } else if (rbtnIkkeModnet.isSelected() && rbtnDelvistFyldt.isSelected()) {
                return !fad.isEmpty() && !fad.getFadIndhold().isModnet() && !fad.isFull();
            } else if (rbtnIkkeModnet.isSelected() && rbtnTom.isSelected()) {
                return !fad.isEmpty() && !fad.getFadIndhold().isModnet() && fad.isFull();
            } else if (rbtnModnet.isSelected()) {
                return !fad.isEmpty() && fad.getFadIndhold().isModnet();
            } else if (rbtnIkkeModnet.isSelected()) {
                return !fad.isEmpty() && !fad.getFadIndhold().isModnet();
            } else if (rbtnFyldt.isSelected()) {
                return fad.isFull();
            } else if (rbtnDelvistFyldt.isSelected()) {
                return fad.getFadIndhold() != null && fad.getFadIndhold().getMængde() != fad.getStørrelseILiter();
            } else if (rbtnTom.isSelected()) {
                return fad.isEmpty();
            } else {
                return true;
            }
        });
        lvwFade.getItems().setAll(filteredList);
    }

    private void fjernFilterAction() {
        rbtnModnet.setSelected(false);
        rbtnIkkeModnet.setSelected(false);
        rbtnFyldt.setSelected(false);
        rbtnDelvistFyldt.setSelected(false);
        rbtnTom.setSelected(false);
        lvwFade.getItems().setAll(controllerForLager.getAlleFade());
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
            updateAftapninger();
            updateOmhældning();
            setControlsDisabled(true);
            getInfo();
            btnSlet.setDisable(false);
        } else {
            btnOpretFravælg.setText("Opret");
            updatePåfyldninger();
            updateAftapninger();
            updateOmhældning();
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

    private void opretFadLeverandørAction() {
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
            lvwPåfyldninger.getItems().setAll(valgtFad.getFadIndhold().getPåfyldninger());
        } else {
            lvwPåfyldninger.getItems().clear();
        }

    }

    private void updateAftapninger() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFad != null && !valgtFad.isEmpty()) {
            lvwAftapninger.getItems().setAll(valgtFad.getFadIndhold().getAftapninger());
        } else {
            lvwAftapninger.getItems().clear();
        }

    }

    private void updateOmhældning() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFad != null && !valgtFad.isEmpty()) {
            lvwOmhældninger.getItems().setAll(valgtFad.getFadIndhold().getTilføjedeOmhældninger());
        } else {
            lvwOmhældninger.getItems().clear();
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
        updateAftapninger();
        updateOmhældning();
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
            } else {
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
