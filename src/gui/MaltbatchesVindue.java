package gui;

import application.model.Maltbatch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MaltbatchesVindue extends Stage {
    private ListView<Maltbatch> lvwMaltbatches = new ListView<>();
    private Button btnVælg = new Button("Vælg");
    private Button btnAnnuller = new Button("Annuller");
    private Button btnNy = new Button("Ny...");
    private Button btnSlet = new Button("Slet");
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
        // lvwMaltbatches.getItems().setAll();
        pane.add(btnVælg, 0, 1);
        pane.add(btnAnnuller, 1, 1);
        pane.add(btnNy, 3, 1);
        btnNy.setOnAction(event -> this.nyAction());
        pane.add(btnSlet, 5, 1);
    }

    private void nyAction() {
        OpretMaltbatchVindue window = new OpretMaltbatchVindue();
        window.showAndWait();
    }
}
