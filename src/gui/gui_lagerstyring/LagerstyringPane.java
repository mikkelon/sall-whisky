package gui.gui_lagerstyring;

import application.controller.ControllerForLager;
import application.model.lager.Fad;
import application.model.lager.Hylde;
import application.model.lager.Lager;
import gui.BekræftSletVindue;
import gui.StartVindue;
import gui.gui_fade.FadePane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LagerstyringPane extends GridPane {
    private Label lblError;
    private ControllerForLager controllerForLager = ControllerForLager.getController();
    private ListView<Lager> lvwLagre;
    private ListView<Hylde> lvwHylder;
    private ListView<Fad> lvwFade;
    private StartVindue startVindue;

    public LagerstyringPane(StartVindue startVindue) {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.startVindue = startVindue;

        // #--- Faste variabler ---#
        final double LVW_WIDTH = 250;

        // #--- Lagre ---#
        Label lblLagre = new Label("Lagre");
        this.add(lblLagre, 0, 0);

        lvwLagre = new ListView<>();
        lvwLagre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateHylder());
        lvwLagre.setMinWidth(LVW_WIDTH);
        lvwLagre.setMaxWidth(LVW_WIDTH);
        lvwLagre.setMinHeight(400);
        this.add(lvwLagre, 0, 1);

        HBox hBoxLagre = new HBox(10);
        hBoxLagre.setAlignment(Pos.CENTER);
        GridPane.setHalignment(hBoxLagre, HPos.CENTER);
        this.add(hBoxLagre, 0, 2);

        Button btnOpretLager = new Button("Opret Lager");
        btnOpretLager.setOnAction(event -> opretLager());
        hBoxLagre.getChildren().add(btnOpretLager);

        Button btnSletLager = new Button("Slet Lager");
        btnSletLager.setOnAction(event -> sletLager());
        hBoxLagre.getChildren().add(btnSletLager);

        // #--- Hylder ---#
        Label lblHylder = new Label("Hylder");
        this.add(lblHylder, 1, 0);

        lvwHylder = new ListView<>();
        lvwHylder.setMinWidth(LVW_WIDTH);
        lvwHylder.setMaxWidth(LVW_WIDTH);
        lvwHylder.setMinHeight(400);
        lvwHylder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFade());
        this.add(lvwHylder, 1, 1);

        HBox hBoxHylder = new HBox(10);
        hBoxHylder.setAlignment(Pos.CENTER);
        GridPane.setHalignment(hBoxHylder, HPos.CENTER);
        this.add(hBoxHylder, 1, 2);

        Button btnOpretHylde = new Button("Opret Hylde");
        btnOpretHylde.setOnAction(event -> opretHylde());
        hBoxHylder.getChildren().add(btnOpretHylde);

        Button btnSletHylde = new Button("Slet Hylde");
        btnSletHylde.setOnAction(event -> sletHylde());
        hBoxHylder.getChildren().add(btnSletHylde);

        // #--- Fade ---#
        Label lblFade = new Label("Fade");
        this.add(lblFade, 2, 0);

        lvwFade = new ListView<>();
        lvwFade.setMinWidth(LVW_WIDTH);
        lvwFade.setMaxWidth(LVW_WIDTH);
        lvwFade.setMinHeight(400);
        this.add(lvwFade, 2, 1);

        Button btnVisFad = new Button("Vis");
        btnVisFad.setOnAction(event -> visFad());
        this.add(btnVisFad, 2, 2);
        GridPane.setHalignment(btnVisFad, HPos.CENTER);

        // #--- Error label ---#
        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red");
        this.add(lblError, 0, 3, 3, 1);
        GridPane.setHalignment(lblError, HPos.CENTER);

        // #--- Opdatér listviews ---#
        updateControls();
    }

    private void opretLager() {
        clearError();
        OpretLagerVindue window = new OpretLagerVindue();
        window.showAndWait();
        updateControls();
    }

    private void sletLager() {
        clearError();
        Lager valgtLager = lvwLagre.getSelectionModel().getSelectedItem();
        if (valgtLager != null) {
            BekræftSletVindue window = new BekræftSletVindue("Slet lager");
            window.showAndWait();
            boolean valg = window.getValg();
            if (valg) {
                try {
                    controllerForLager.removeLager(valgtLager);
                }
                catch (RuntimeException e) {
                    lblError.setText(e.getMessage());
                }
                updateControls();
            }
        } else {
            lblError.setText("Vælg et lager");
        }
    }

    private void opretHylde() {
        clearError();
        Lager valgtLager = lvwLagre.getSelectionModel().getSelectedItem();
        if (valgtLager != null) {
            controllerForLager.createHylde(valgtLager);
            updateHylder();
            clearError();
        } else {
            lblError.setText("Vælg et lager");
        }
    }

    private void sletHylde() {
        clearError();
        Hylde valgtHylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (valgtHylde != null) {
            BekræftSletVindue window = new BekræftSletVindue("Slet hylde");
            window.showAndWait();
            boolean valg = window.getValg();
            if (valg) {
                try {
                    controllerForLager.removeHylde(valgtHylde);
                }
                catch (RuntimeException e) {
                    lblError.setText(e.getMessage());
                }
                updateHylder();
            }
        } else {
            lblError.setText("Vælg en hylde");
        }
    }

    public void updateControls() {
        updateLagre();
        updateHylder();
        updateFade();
    }

    private void updateLagre() {
        lvwLagre.getItems().setAll(controllerForLager.getLagre());
    }

    private void updateHylder() {
        Lager valgtLager = lvwLagre.getSelectionModel().getSelectedItem();
        if (valgtLager != null) {
            lvwHylder.getItems().setAll(controllerForLager.getHylder(valgtLager));
        } else {
            lvwHylder.getItems().clear();
        }
    }

    private void visFad() {
        clearError();
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFad != null) {
            Pane pane = startVindue.skiftTab(1);
            if (pane != null) {
                FadePane fadPane = (FadePane) pane;
                fadPane.setFad(valgtFad);
            }
        } else {
            lblError.setText("Vælg et fad");
        }
    }

    private void updateFade() {
        Hylde valgtHylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (valgtHylde != null) {
            lvwFade.getItems().setAll(valgtHylde.getFade());
        } else {
            lvwFade.getItems().clear();
        }
    }

   private void clearError() {
        lblError.setText("");
    }
}
