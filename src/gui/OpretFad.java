package gui;

import application.model.FadLeverandør;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretFad extends Stage {
    public OpretFad() {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Opret fad");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private ComboBox<FadLeverandør> cbxFadLeverandører;

    private void initContent(GridPane pane) {
        // Formateringsjusteringer
        pane.setAlignment(Pos.CENTER);

        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        // Indhold
        Label lblFadLeverandører = new Label("Fadleverandører");
        pane.add(lblFadLeverandører, 0, 0);

        cbxFadLeverandører = new ComboBox<>();
        pane.add(cbxFadLeverandører,0,1);

        // Opret og Annullér knapper

        HBox hBox = new HBox();
        pane.add(hBox, 0, 6, 2, 1);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        Button btnAnnuller = new Button("Annullér");
        hBox.getChildren().add(btnAnnuller);
        btnAnnuller.setOnAction(event -> this.close());

        Button btnOpret = new Button("Opret");
        hBox.getChildren().add(btnOpret);
        btnOpret.setOnAction(event -> opretFadAction());

        // Tilføj fadleverandører til drop-down

    }

    private void opretFadAction() {

    }
}
