package gui;

import application.controller.ControllerForLager;
import application.model.lager.Fad;
import javafx.geometry.HPos;
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
    private Label lblError;
    private Fad valgtFad;
    public RegistrerAlkoholProcentVindue(Fad fad) {
        if (fad != null && fad.getFadIndhold().getAlkoholProcentEfterModning() == -1) {
            valgtFad = fad;
        }

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
        lvwFade.getItems().setAll(controllerForLager.getModneFadeUdenRegistreretAlkoholProcent());
        lvwFade.getSelectionModel().select(valgtFad);

        pane.add(new Label("Alkoholprocent"), 2, 0);
        pane.add(txfAlkoholProcent, 2, 1);

        pane.add(btnRegistrer, 2, 2);
        btnRegistrer.setOnAction(event -> registrerAlhokolProcentEfterModningAction());

        pane.add(btnLuk, 1, 5);
        btnLuk.setOnAction(event -> this.hide());

        // #--- ErrorLabel ---#
        lblError = new Label(" ");
        pane.add(lblError, 2, 3);
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setStyle("-fx-text-fill: red");
    }

    private void registrerAlhokolProcentEfterModningAction() {
        clearError();
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad == null) {
            lblError.setText("Vælg et fad");
            return;
        }
        if (txfAlkoholProcent.getText().isEmpty()) {
            lblError.setText("Indtast alkoholprocent");
            return;
        }
        double alkoholProcentEfterModning = 0;
        try {
            alkoholProcentEfterModning = Double.parseDouble(txfAlkoholProcent.getText());
        } catch (NumberFormatException e) {
            lblError.setText("Alkoholprocent skal \n være et tal");
            return;
        }
        if (alkoholProcentEfterModning < 0 || alkoholProcentEfterModning > 100) {
            lblError.setText("Alkoholprocent skal \n være mellem 0 og 100");
            return;
        }
        controllerForLager.setAlkoholProcentEfterModning(fad, alkoholProcentEfterModning);
        updateControls();
    }

    public void setFad(Fad fad) {
        if (lvwFade.getItems().contains(fad)) {
            lvwFade.getSelectionModel().select(fad);
        }
    }

    private void clearError() {
        lblError.setText(" ");
    }

    private void updateControls() {
        lvwFade.getItems().setAll(controllerForLager.getModneFadeUdenRegistreretAlkoholProcent());
        txfAlkoholProcent.clear();
    }
}
