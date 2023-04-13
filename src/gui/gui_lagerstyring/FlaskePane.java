package gui.gui_lagerstyring;

import application.controller.ControllerForLager;
import application.model.lager.Flaske;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class FlaskePane extends GridPane {

    private ControllerForLager controllerForLager = ControllerForLager.getController();

    private ListView<Flaske> lvwFlaske;
    private TextArea txaBeskrivelse;
    private TextArea txaHistorik;


    public FlaskePane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);


        Label lblFlaske = new Label("Flasker");
        this.add(lblFlaske, 0, 0);

        lvwFlaske = new ListView<>();
        lvwFlaske.setMinWidth(240);
        lvwFlaske.setMinHeight(300);
        lvwFlaske.setMaxWidth(240);
        this.add(lvwFlaske, 0, 1);
        lvwFlaske.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectionChanged());
        lvwFlaske.getItems().setAll(controllerForLager.getFlasker());


        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 1, 0);

        txaBeskrivelse = new TextArea();
        txaBeskrivelse.setMinWidth(120);
        txaBeskrivelse.setMinHeight(300);
        txaBeskrivelse.setMaxWidth(200);
        txaBeskrivelse.setDisable(true);

        this.add(txaBeskrivelse, 1, 1);

        Label lblHistorik = new Label("Historik");
        this.add(lblHistorik, 2, 0);

        txaHistorik = new TextArea();
        txaHistorik.setMinWidth(120);
        txaHistorik.setMinHeight(300);
        txaHistorik.setMaxWidth(200);
        txaHistorik.setDisable(true);
        this.add(txaHistorik, 2, 1);

    }


    private void getInfo() {
        Flaske valgtFlaske = lvwFlaske.getSelectionModel().getSelectedItem();
        if (valgtFlaske != null) {
            txaBeskrivelse.setText(valgtFlaske.getBeskrivelse());
            txaBeskrivelse.setDisable(false);
            txaBeskrivelse.setEditable(false);
            txaHistorik.setText(valgtFlaske.hentHistorik());
            txaHistorik.setEditable(false);
            txaHistorik.setDisable(false);

        }
    }

    private void selectionChanged() {
        if (lvwFlaske.getSelectionModel().getSelectedItem() != null) {
            getInfo();
        }
    }

}
