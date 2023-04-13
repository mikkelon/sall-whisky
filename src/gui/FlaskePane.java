package gui;

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
    private TextArea txaLabel;


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
        lvwFlaske.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getInfo());
        lvwFlaske.getItems().setAll(controllerForLager.getFlasker());


        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 1, 0);

        txaBeskrivelse = new TextArea();
        txaBeskrivelse.setMinWidth(120);
        txaBeskrivelse.setMinHeight(300);
        txaBeskrivelse.setMaxWidth(200);
        txaBeskrivelse.setEditable(false);

        this.add(txaBeskrivelse, 1, 1);

        Label lblLabel = new Label("Label");
        this.add(lblLabel, 2, 0);

        txaLabel = new TextArea();
        txaLabel.setMinWidth(120);
        txaLabel.setMinHeight(300);
        txaLabel.setMaxWidth(200);
        txaLabel.setEditable(false);
        this.add(txaLabel, 2, 1);
    }

    private void getInfo() {
        Flaske valgtFlaske = lvwFlaske.getSelectionModel().getSelectedItem();
        if (valgtFlaske != null) {
            txaBeskrivelse.setText(valgtFlaske.getBeskrivelse());
            txaLabel.setText(valgtFlaske.hentLabel());
        } else {
            txaBeskrivelse.clear();
            txaLabel.clear();
        }
    }

    public void updateControls() {
        lvwFlaske.getItems().setAll(controllerForLager.getFlasker());
        getInfo();
    }

}
