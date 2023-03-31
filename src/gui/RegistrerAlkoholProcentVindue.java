package gui;

import application.controller.ControllerForLager;
import application.model.Fad;
import application.model.Maltbatch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegistrerAlkoholProcentVindue extends Stage {
    private ControllerForLager controllerForLager = ControllerForLager.getController();
    private ListView<Fad> lvwFade = new ListView<>();
    private TextField txfAlkoholProcent = new TextField();
    private Button btnRegistrer = new Button("Registrer");
    private Button btnLuk = new Button("Luk");

    public RegistrerAlkoholProcentVindue() {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Registrer alkoholprocent");

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
        pane.add(lvwFade, 0, 0, 1, 4);
        lvwFade.getItems().setAll(controllerForLager.getModneFade());
        pane.add(new Label("Alkoholprocent"), 2, 0);
        pane.add(txfAlkoholProcent, 2, 1);
        pane.add(btnRegistrer, 2, 2);
        pane.add(btnLuk, 1, 5);
    }

    public void setFad(Fad fad) {
        if (lvwFade.getItems().contains(fad)) {
            lvwFade.getSelectionModel().select(fad);
        }
    }
}
