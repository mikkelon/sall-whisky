package gui.gui_lagerstyring;

import application.model.Hylde;
import application.model.Lager;
import application.model.FadLeverandør;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretFadVindue extends Stage {
    public OpretFadVindue() {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setHeight(300);
        this.setWidth(300);

        this.setTitle("Opret fad");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private ComboBox<FadLeverandør> cbxLeverandører;
    private ComboBox<Lager> cbxLagre;
    private ComboBox<Hylde> cbxHylder;
    private TextField txfStørrelse;

    private void initContent(GridPane pane) {
        // Formateringsjusteringer
        pane.setAlignment(Pos.CENTER);

        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        // Indhold
        Label lblLeverandører = new Label("Leverandør");
        pane.add(lblLeverandører, 0, 0, 2,1);

        cbxLeverandører = new ComboBox<>();
        cbxLeverandører.setMinWidth(150);
        pane.add(cbxLeverandører, 0, 1,2,1);

        Label lblStørrelse = new Label("Størrelse i liter");
        pane.add(lblStørrelse, 0, 2,2,1);

        txfStørrelse = new TextField();
        txfStørrelse.setMinWidth(30);
        txfStørrelse.setPrefWidth(50);
        txfStørrelse.setMaxWidth(70);
        pane.add(txfStørrelse, 0, 3,2,1);

        Label lblLager = new Label("Lager");
        pane.add(lblLager, 0, 4);

        cbxLagre = new ComboBox<Lager>();
        cbxLagre.setMinWidth(100);
        pane.add(cbxLagre,0,5);

        Label lblHylde = new Label("Hylde");
        pane.add(lblHylde, 1, 4);

        cbxHylder = new ComboBox<>();
        cbxHylder.setMinWidth(100);
        pane.add(cbxHylder, 1, 5);

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

        // Centrér alle elementer
        for (int i = 0; i < pane.getChildren().size(); i++) {
            GridPane.setHalignment(pane.getChildren().get(i), HPos.CENTER);
        }

        // Tilføj leverandører til drop-down
        // TODO
    }

    private void opretFadAction() {
        // TODO
    }
}
