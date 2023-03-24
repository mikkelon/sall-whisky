package gui.gui_lagerstyring;

import application.controller.Controller;
import application.model.FadType;
import application.model.Hylde;
import application.model.Lager;
import application.model.FadLeverandør;
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

import java.util.Arrays;
import java.util.HashSet;

public class OpretFadVindue extends Stage {
    private Hylde hylde;
    private Controller controller = Controller.getController();
    public OpretFadVindue(Hylde hylde) {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.hylde = hylde;

        this.setHeight(300);
        this.setWidth(300);

        this.setTitle("Opret fad");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private ComboBox<FadLeverandør> cbxFadLeverandører;
    private ComboBox<Lager> cbxLagre;
    private ComboBox<Hylde> cbxHylder;
    private ComboBox<FadType> cbxFadType;
    private TextField txfStørrelse;
    private Label lblError;

    private void initContent(GridPane pane) {
        // Formateringsjusteringer
//        pane.setGridLinesVisible(true);
        pane.setAlignment(Pos.CENTER);

        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        // Indhold
        Label lblLeverandører = new Label("Leverandør");
        pane.add(lblLeverandører, 0, 0, 2,1);

        cbxFadLeverandører = new ComboBox<>();
        cbxFadLeverandører.setMinWidth(150);
        cbxFadLeverandører.setMaxWidth(150);
        pane.add(cbxFadLeverandører, 0, 1,2,1);

        Label lblStørrelse = new Label("Størrelse i liter");
        pane.add(lblStørrelse, 0, 2);

        txfStørrelse = new TextField();
        txfStørrelse.setMinWidth(30);
        txfStørrelse.setPrefWidth(50);
        txfStørrelse.setMaxWidth(70);
        pane.add(txfStørrelse, 0, 3,2,1);

        Label lblFadType = new Label("Fadtype");
        pane.add(lblFadType, 1, 2,2,1);

        cbxFadType = new ComboBox<>();
        cbxFadType.setMinWidth(100);
        cbxFadType.setMaxWidth(100);
        pane.add(cbxFadType, 1, 3,2,1);

        lblError = new Label(" ");
        pane.add(lblError, 0, 4, 2, 1);
        lblError.setStyle("-fx-text-fill: red");

        // Opret og Annullér knapper
        HBox hBox = new HBox();
        pane.add(hBox, 0, 5, 2, 1);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        Button btnAnnuller = new Button("Annullér");
        hBox.getChildren().add(btnAnnuller);
        btnAnnuller.setOnAction(event -> this.close());

        Button btnOpret = new Button("Opret");
        hBox.getChildren().add(btnOpret);
        btnOpret.setOnAction(event -> opretFadAction());

        // Tilføj leverandører til drop-down
        updateControls();
    }

    private void opretFadAction() {
        FadLeverandør fadLeverandør = cbxFadLeverandører.getSelectionModel().getSelectedItem();
        FadType fadType = cbxFadType.getSelectionModel().getSelectedItem();
        double størrelse = 0;
        try {
            størrelse = Double.parseDouble(txfStørrelse.getText());
        } catch (NumberFormatException e) {
            lblError.setText("Størrelse skal være et tal");
            return;
        }

        if (fadLeverandør == null) {
            lblError.setText("Vælg en leverandør");
        } else if (fadType == null) {
            lblError.setText("Vælg en fadtype");
        } else if (størrelse == 0) {
            lblError.setText("Vælg en størrelse");
        } else {
            controller.createFad(fadType, størrelse, fadLeverandør, hylde);
            this.close();
        }
    }

    private void updateControls() {
        updateFadLeverandører();
        updateFadTyper();
    }

    private void updateFadLeverandører() {
        cbxFadLeverandører.getItems().setAll(controller.getFadLeverandører());
    }

    private void updateFadTyper() {
        cbxFadType.getItems().setAll(Arrays.asList(FadType.values()));
    }

}
