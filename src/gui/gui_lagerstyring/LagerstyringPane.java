package gui.gui_lagerstyring;

import application.controller.Controller;
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

        // #--- Error label ---#
        lblError = new Label(" ");
        this.add(lblError, 0, 3, 3, 1);
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setStyle("-fx-text-fill: red");

        // #--- Opdatér listviews ---#
        updateControls();
    }

    private void opretLager() {
        OpretLagerVindue window = new OpretLagerVindue();
        window.showAndWait();
        updateControls();
    }

    private void sletLager() {
        BekræftSletVindue window = new BekræftSletVindue();
        window.showAndWait();
        boolean valg = window.getValg();
        if (valg) {
            Lager valgtLager = lvwLagre.getSelectionModel().getSelectedItem();
            if (valgtLager != null) {
                try {
                    controller.removeLager(valgtLager);
                }
                catch (RuntimeException e) {
                    lblError.setText(e.getMessage());
                }
                updateControls();
            }
        }
    }

    private void opretHylde() {
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
        Hylde valgtHylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (valgtHylde != null) {
            try {
//            controller.removeHylde(valgtHylde); //TODO: Fjern kommentar når metoden er lavet
            }
            catch (RuntimeException e) {
                lblError.setText(e.getMessage());
            }
            updateHylder();
            clearError();
        } else {
            lblError.setText("Vælg en hylde");
        }
    }

    private void opretFad() {
        Hylde valgtHylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (valgtHylde != null) {
            OpretFadVindue window = new OpretFadVindue(valgtHylde);
            window.showAndWait();
            updateControls();
            clearError();
        } else {
            lblError.setText("Vælg en hylde");
        }
    }

    private void sletFad() {
        BekræftSletVindue window = new BekræftSletVindue();
        window.showAndWait();
        boolean valg = window.getValg();
        if (valg) {
            Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
            if (valgtFad != null) {
//                controller.removeFad(valgtFad); //TODO: Fjern kommentar når metoden er lavet
                updateFade();
            }
        }
    }

    public void updateControls() {
        updateLagre();
        updateHylder();
        updateFade();
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

    private void clearError() {
        lblError.setText(" ");
    }
}
