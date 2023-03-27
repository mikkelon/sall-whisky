package gui.gui_lagerstyring;

import application.controller.Controller;
import application.model.Destillat;
import application.model.Fad;
import application.model.Hylde;
import application.model.Lager;
import gui.BekræftSletVindue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LagerstyringPane extends GridPane {
    private Label lblError;
    private Controller controller = Controller.getController();
    private ListView<Lager> lvwLagre;
    private ListView<Hylde> lvwHylder;
    private ListView<Fad> lvwFade;
    private ListView<Destillat> lvwDestillater;

    public LagerstyringPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // #--- Lagre ---#
        Label lblLagre = new Label("Lagre");
        this.add(lblLagre, 0, 0);

        lvwLagre = new ListView<>();
        lvwLagre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateHylder());
        lvwLagre.setMinWidth(200);
        lvwLagre.setMaxWidth(200);
        lvwLagre.setMinHeight(400);
        this.add(lvwLagre, 0, 1);

        HBox hBoxLagre = new HBox(10);
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
        lvwHylder.setMinWidth(200);
        lvwHylder.setMaxWidth(200);
        lvwHylder.setMinHeight(400);
        lvwHylder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFade());
        this.add(lvwHylder, 1, 1);

        HBox hBoxHylder = new HBox(10);
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
        lvwFade.setMinWidth(200);
        lvwFade.setMaxWidth(200);
        lvwFade.setMinHeight(400);
        this.add(lvwFade, 2, 1);

        HBox hBoxFade = new HBox(10);
        this.add(hBoxFade, 2, 2);

        Button btnOpretFad = new Button("Opret Fad");
        btnOpretFad.setOnAction(event -> opretFad());
        hBoxFade.getChildren().add(btnOpretFad);

        Button btnSletFad = new Button("Slet Fad");
        btnSletFad.setOnAction(event -> sletFad());
        hBoxFade.getChildren().add(btnSletFad);

        // #--- Destillater ---#
        Label lblDestillater = new Label("Destillater");
        this.add(lblDestillater, 3, 0);

        lvwDestillater = new ListView<>();
        lvwDestillater.setMinWidth(200);
        lvwDestillater.setMaxWidth(200);
        lvwDestillater.setMinHeight(400);
        this.add(lvwDestillater, 3, 1);

        // #--- Error label ---#
        lblError = new Label(" ");
        this.add(lblError, 0, 3, 4, 1);
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setStyle("-fx-text-fill: red");

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
                    controller.removeLager(valgtLager);
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
            controller.createHylde(valgtLager);
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
                    controller.removeHylde(valgtHylde);
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

    private void opretFad() {
        clearError();
        Hylde valgtHylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (valgtHylde != null) {
            OpretFadVindue window = new OpretFadVindue(valgtHylde);
            window.showAndWait();
            updateFade();
            clearError();
        } else {
            lblError.setText("Vælg en hylde");
        }
    }

    private void sletFad() {
        clearError();
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFad != null) {
            BekræftSletVindue window = new BekræftSletVindue("Slet fad");
            window.showAndWait();
            boolean valg = window.getValg();
            if (valg) {
                try {
                    controller.removeFad(valgtFad);
                }
                catch (RuntimeException e) {
                    lblError.setText(e.getMessage());
                }
                updateFade();
            }
        } else {
            lblError.setText("Vælg et fad");
        }
    }

    public void updateControls() {
        updateLagre();
        updateHylder();
        updateFade();
        updateDestillater();
    }

    private void updateLagre() {
        lvwLagre.getItems().setAll(controller.getLagre());
    }

    private void updateHylder() {
        Lager valgtLager = lvwLagre.getSelectionModel().getSelectedItem();
        if (valgtLager != null) {
            lvwHylder.getItems().setAll(controller.getHylder(valgtLager));
        } else {
            lvwHylder.getItems().clear();
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

    private void updateDestillater() {
        lvwDestillater.getItems().setAll(controller.getDestillater());
    }

    private void clearError() {
        lblError.setText("");
    }
}
