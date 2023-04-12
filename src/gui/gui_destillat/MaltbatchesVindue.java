package gui.gui_destillat;

import application.controller.ControllerForProduktion;
import application.model.produktion.Maltbatch;
import gui.BekræftSletVindue;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class MaltbatchesVindue extends Stage {
    private ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();
    private ListView<Maltbatch> lvwMaltbatches = new ListView<>();
    private Button btnVælg = new Button("Vælg");
    private Button btnAnnuller = new Button("Annuller");
    private Button btnNy = new Button("Ny...");
    private Button btnSlet = new Button("Slet");

    private Label lblError;
    public MaltbatchesVindue() {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Maltbatches");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        // Formateringsjusteringer
        pane.setAlignment(Pos.CENTER);

        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        // Indhold
        pane.add(lvwMaltbatches, 0, 0, 3,1);
        lvwMaltbatches.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwMaltbatches.getItems().setAll(ControllerForProduktion.getController().getMaltbatches());

        pane.add(btnVælg, 0, 1);
        btnVælg.setOnAction(event -> vælgAction());

        pane.add(btnAnnuller, 1, 1);
        btnAnnuller.setOnAction(event -> this.hide());

        pane.add(btnNy, 3, 1);
        btnNy.setOnAction(event -> this.nyAction());

        pane.add(btnSlet, 5, 1);
        btnSlet.setOnAction(event -> this.sletAction());

        // #--- ErrorLabel ---#
        lblError = new Label(" ");
        pane.add(lblError, 0, 2, 4, 1);
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setStyle("-fx-text-fill: red");
    }

    private void sletAction() {
        clearError();
        Maltbatch valgtMaltbatch = lvwMaltbatches.getSelectionModel().getSelectedItem();
        if (valgtMaltbatch != null) {
            BekræftSletVindue window = new BekræftSletVindue("Slet Maltbatch");
            window.showAndWait();
            boolean valg = window.getValg();
            if(valg){
            try {
                controllerForProduktion.removeMaltbatch(valgtMaltbatch);
                updateControls();
            } catch (RuntimeException e) {
                lblError.setText(e.getMessage());
            }
        }
        } else {
            lblError.setText("Vælg et maltbatch");
        }
    }

    private void vælgAction() {
        clearError();
        ObservableList<Maltbatch> valgtMaltbatches = lvwMaltbatches.getSelectionModel().getSelectedItems();
        if (valgtMaltbatches.size() > 0) {
            ArrayList<Maltbatch> temp = new ArrayList<>();
            for (Maltbatch m : valgtMaltbatches) {
                temp.add(m);
            }
            DestillatPane.fillMaltbatches(temp);
            this.hide();
        } else {
            lblError.setText("Vælg et maltbatch");
        }
    }

    private void nyAction() {
        OpretMaltbatchVindue window = new OpretMaltbatchVindue();
        window.showAndWait();
        updateControls();
    }

    private void clearError() {
        lblError.setText(" ");
    }

    public void updateControls() {
        lvwMaltbatches.getItems().setAll(ControllerForProduktion.getController().getMaltbatches());
    }
}
