package gui.gui_lagerstyring;

import application.controller.Controller;
import application.model.Fad;
import application.model.Hylde;
import application.model.Lager;
import gui.BekræftSletVindue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LagerstyringPane extends GridPane {
    private Controller controller = Controller.getController();
    private ListView<Lager> lvwLagre;
    private ListView<Hylde> lvwHylder;
    private ListView<Fad> lvwFade;

    public LagerstyringPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblLagre = new Label("Lagre");
        this.add(lblLagre, 0, 0);

        lvwLagre = new ListView<>();
        lvwLagre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateHylder());
        lvwLagre.setMinWidth(200);
        lvwLagre.setMaxWidth(200);
        lvwLagre.setMinHeight(400);
        this.add(lvwLagre, 0, 1);

        Label lblHylder = new Label("Hylder");
        this.add(lblHylder, 1, 0);

        lvwHylder = new ListView<>();
        lvwHylder.setMinWidth(200);
        lvwHylder.setMaxWidth(200);
        lvwHylder.setMinHeight(400);
        lvwHylder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFade());
        this.add(lvwHylder, 1, 1);

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

        // #--- Opdatér listviews ---#
        updateControls();
    }

    private void opretFad() {
        OpretFadVindue window = new OpretFadVindue();
        window.showAndWait();
    }

    private void sletFad() {
        BekræftSletVindue window = new BekræftSletVindue();
        window.showAndWait();
        boolean valg = window.getValg();
        if (valg)
            System.out.println("Slet fad"); //TODO
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
        }
    }

    private void updateFade() {
        Hylde valgtHylde = lvwHylder.getSelectionModel().getSelectedItem();
        if (valgtHylde != null) {
            lvwFade.getItems().setAll(valgtHylde.getFade());
        }
    }
}
