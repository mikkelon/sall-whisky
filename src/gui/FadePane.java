package gui;

import application.controller.Controller;
import application.model.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.nio.channels.Pipe;

public class FadePane extends GridPane {
    private final ComboBox<FadLeverandør> cbxFadLeverandør;
    private Controller controller = Controller.getController();
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

        Button btnNyLeverandør = new Button("Opret fadleverandør");
        btnNyLeverandør.setOnAction(event -> opretFadLeverandørAction());
        this.add(btnNyLeverandør, 0, 2, 1, 2);
        GridPane.setValignment(btnNyLeverandør, VPos.TOP);

        Label lblLager = new Label("Lager");
        this.add(lblLager, 0, 4);

        ComboBox<Lager> cbxLager = new ComboBox<>();
        GridPane.setValignment(cbxLager, VPos.TOP);
        cbxLager.setMinWidth(150);
        cbxLager.setMaxWidth(150);
        this.add(cbxLager, 0, 5);

        Label lblStørrelse = new Label("Størrelse (Liter)");
        this.add(lblStørrelse, 1,0);

        TextField txfStørrelse = new TextField();
        this.add(txfStørrelse, 1, 1);

        Label lblFadType = new Label("Fadtype");
        this.add(lblFadType, 1, 2);

        TextField txfFadType = new TextField();
        this.add(txfFadType, 1, 3);

        Label lblHylde = new Label("Hylde");
        this.add(lblHylde, 1, 4);

        ComboBox<Hylde> cbxHylde = new ComboBox<>();
        GridPane.setValignment(cbxHylde, VPos.TOP);
        cbxHylde.setMinWidth(150);
        cbxHylde.setMaxWidth(150);
        this.add(cbxHylde, 1, 5);

        Separator sep1 = new Separator(Orientation.VERTICAL);
        this.add(sep1, 2, 0,1,7);


        Label lblPåfyldninger = new Label("Påfyldninger");
        this.add(lblPåfyldninger, 3, 0);

        lvwPåfyldninger = new ListView<>();
        this.add(lvwPåfyldninger, 3, 1,1,6);
        lvwPåfyldninger.setMaxHeight(200);

        Separator sep2 = new Separator(Orientation.VERTICAL);
        this.add(sep2, 4, 0, 1, 11);

        Label lblAlleFade = new Label("Alle fade");
        this.add(lblAlleFade, 5, 0);

        lvwFade = new ListView<>();
        lvwFade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updatePåfyldninger());
        this.add(lvwFade, 5, 1,1,9);

        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        this.add(sep3, 0, 7,4, 1);

        Button btnOpret = new Button("Opret");
        this.add(btnOpret, 0, 8, 2, 1);
        GridPane.setHalignment(btnOpret, HPos.CENTER);

        Button btnSlet = new Button("Slet");
        this.add(btnSlet, 5, 10);
        GridPane.setHalignment(btnSlet, HPos.CENTER);


        // #--- Add row constraints ---#
        for (int i = 0; i < this.getRowCount(); i++) {
            RowConstraints row = new RowConstraints();
            this.getRowConstraints().add(row);
        }

        RowConstraints row5 = this.getRowConstraints().get(5);
        row5.setVgrow(Priority.ALWAYS);

        // Set row constraint for row 6
        RowConstraints row6 = this.getRowConstraints().get(6);
        row6.setVgrow(Priority.NEVER);

        // #--- Update controls ---#
        updateControls();
    }

    private void opretFadLeverandørAction(){
        OpretFadLeverandør window = new OpretFadLeverandør();
        window.showAndWait();
        updateFadleverandører();
    }

    private void updateFadleverandører() {
        cbxFadLeverandør.getItems().setAll(controller.getFadLeverandører());
    }

    private void updateFade() {
        lvwFade.getItems().setAll(controller.getAlleFade());
    }

    private void updatePåfyldninger() {
        Fad valgtFade = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFade != null) {
            lvwPåfyldninger.getItems().setAll(valgtFade.getPåfyldninger());
        } else {
            lvwPåfyldninger.getItems().clear();
        }
    }

    public void updateControls() {
        updateFadleverandører();
        updateFade();
    }
}
