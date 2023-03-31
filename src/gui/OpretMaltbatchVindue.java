package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretMaltbatchVindue extends Stage {
    private TextField txfKornsort = new TextField();
    private  TextField txfMark = new TextField();
    private TextField txfGård = new TextField();
    private TextField txfDyrketAf = new TextField();
    private CheckBox chbØkologisk = new CheckBox("Økologisk");
    private Button btnOpret = new Button("Opret");
    private Button btnAnnuller = new Button("Annuller");

    public OpretMaltbatchVindue() {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Nyt Maltbatch");

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
        pane.add(new Label("Kornsort"), 0, 0);
        pane.add(txfKornsort, 1, 0);
        pane.add(new Label("Mark"), 0, 1);
        pane.add(txfMark, 1, 1);
        pane.add(new Label("Gård"), 0, 2);
        pane.add(txfGård, 1, 2);
        pane.add(new Label("Dyrket af"), 0, 3);
        pane.add(txfDyrketAf, 1, 3);
        pane.add(chbØkologisk, 1, 4);

        pane.add(btnOpret, 0, 5);
        btnOpret.setOnAction(event -> this.opretAction());
        pane.add(btnAnnuller, 1, 5);
        btnAnnuller.setOnAction(event -> this.hide());
    }

    private void opretAction() {
    }
}
