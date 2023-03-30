package gui;

import application.controller.ControllerForLager;
import application.controller.ControllerForProduktion;
import application.model.produktion.Destillat;
import application.model.lager.Fad;
import application.model.lager.Lager;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.time.LocalDate;

public class PåfyldningPane extends GridPane {

    private final ListView<Destillat> lvwDestillater;
    private final ListView<Fad> lvwFade;
    private final ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();
    private final ControllerForLager controllerForLager = ControllerForLager.getController();
    private final ComboBox<Lager> cbxLager;
    private final TextField txtPåfyldtAf;
    private final TextField txtMængde;
    private final DatePicker datePicker;
    private final Label lblError;

    public PåfyldningPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);

        Label lblPåfyldning = new Label("Destillater");
        this.add(lblPåfyldning, 0, 0);

        lvwDestillater = new ListView<>();
        lvwDestillater.setMinWidth(250);
        lvwDestillater.setMaxWidth(250);
        lvwDestillater.setMinHeight(300);
        this.add(lvwDestillater, 0, 1, 1, 6);

        Label lblLager = new Label("Lager");
        this.add(lblLager, 1, 0, 2, 1);
        GridPane.setHalignment(lblLager, HPos.CENTER);

        cbxLager = new ComboBox<>();
        cbxLager.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateFade());
        this.add(cbxLager, 1, 1, 2, 1);
        GridPane.setHalignment(cbxLager, HPos.CENTER);
        cbxLager.setMaxWidth(150);
        cbxLager.setPrefWidth(150);

        Label lblMængde = new Label("Mængde (Liter)");
        this.add(lblMængde, 1, 2, 2, 1);
        GridPane.setHalignment(lblMængde, HPos.CENTER);

        HBox hBoxMængde = new HBox();
        hBoxMængde.setSpacing(10);
        hBoxMængde.setAlignment(Pos.CENTER);
        this.add(hBoxMængde, 1, 3, 2, 1);

        txtMængde = new TextField();
        hBoxMængde.getChildren().add(txtMængde);
        txtMængde.setMaxSize(80, 20);

        Button btnMax = new Button("MAX");
        btnMax.setOnAction(event -> maxAction());
        hBoxMængde.getChildren().add(btnMax);

        Path arrow = createArrow();
        this.add(arrow, 1, 4, 2, 1);
        GridPane.setHalignment(arrow, HPos.CENTER);

        Label lblPåfyldningsDato = new Label("Påfyldningsdato");
        this.add(lblPåfyldningsDato, 1, 5, 1, 1);

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        GridPane.setValignment(datePicker, VPos.TOP);
        this.add(datePicker, 1, 6, 1, 1);

        Label lblPåFyldtAf = new Label("Påfyldt af");
        this.add(lblPåFyldtAf, 2, 5, 1, 1);

        txtPåfyldtAf = new TextField();
        GridPane.setValignment(txtPåfyldtAf, VPos.TOP);
        this.add(txtPåfyldtAf, 2, 6, 1, 1);

        Label lblFad = new Label("Fade");
        this.add(lblFad, 3, 0);

        lblError = new Label(" ");
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setTextFill(Color.RED);
        this.add(lblError, 1, 7, 2, 1);

        Button btnPåfyld = new Button("Påfyld");
        btnPåfyld.setOnAction(event -> påfyldAction());
        this.add(btnPåfyld, 1, 8, 2, 1);
        btnPåfyld.setMinSize(100, 40);
        GridPane.setHalignment(btnPåfyld, HPos.CENTER);

        lvwFade = new ListView<>();
        lvwFade.setMinWidth(250);
        lvwFade.setMaxWidth(250);
        lvwFade.setMinHeight(300);
        this.add(lvwFade, 3, 1, 1, 6);

        updateDestillater();
        updateLagre();
        if (controllerForLager.getLagre().size() > 0) {
            cbxLager.getSelectionModel().select(0);
        }
    }

    private void påfyldAction() {
        clearError();
        Destillat valgtDestillat = lvwDestillater.getSelectionModel().getSelectedItem();
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        String påfyldtAf = txtPåfyldtAf.getText().trim();
        LocalDate påfyldningsDato = datePicker.getValue();
        double mængde = 0;
        try {
            if (txtMængde.getText().trim().contains(","))
            {
                txtMængde.setText(txtMængde.getText().trim().replace(",", "."));
            }
            mængde = Double.parseDouble(txtMængde.getText().trim());
        } catch (NumberFormatException e) {
            lblError.setText("Mængde skal være et tal");
            return;
        }
        if (valgtDestillat == null) {
            lblError.setText("Vælg et destillat");
        } else if (valgtFad == null) {
            lblError.setText("Vælg et fad");
        } else if (påfyldtAf.isEmpty()) {
            lblError.setText("Indtast hvem der har påfyldt");
        } else if (påfyldningsDato == null) {
            lblError.setText("Vælg en påfyldningsdato");
        } else if (mængde <= 0) {
            lblError.setText("Mængde skal være større end 0");
        }  else {
            try {
                controllerForProduktion.createPåfyldning(valgtDestillat, valgtFad, påfyldtAf, mængde, påfyldningsDato);
                updateDestillater();
                updateFade();
                txtMængde.clear();
            }
            catch (RuntimeException e) {
                lblError.setText(e.getMessage());
            }

        }
    }

    private void clearError() {
        lblError.setText(" ");
    }

    private void updateDestillater() {
        Destillat valgtDestillat = lvwDestillater.getSelectionModel().getSelectedItem();
        lvwDestillater.getItems().setAll(controllerForProduktion.getDestillater());
        if (valgtDestillat != null)
            lvwDestillater.getSelectionModel().select(valgtDestillat);
    }

    private void updateFade() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        Lager valgtLager = cbxLager.getSelectionModel().getSelectedItem();
        if (valgtLager != null)
            lvwFade.getItems().setAll(controllerForLager.getAlleFade(valgtLager));
        if (valgtFad != null & lvwFade.getItems().contains(valgtFad))
            lvwFade.getSelectionModel().select(valgtFad);
    }

    private void updateLagre() {
        cbxLager.getItems().setAll(controllerForLager.getLagre());
    }

    public void updateControls() {
        updateDestillater();
        updateLagre();
        updateFade();
    }

    private void maxAction() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        Destillat valgtDestillat = lvwDestillater.getSelectionModel().getSelectedItem();

        if (valgtFad != null && valgtDestillat != null) {
            if (valgtFad.resterendePladsILiter() < valgtDestillat.resterendeMængdeILiter()) {
                txtMængde.setText(String.valueOf(String.format("%.2f", Math.floor(valgtFad.resterendePladsILiter() * 100) / 100)));
            } else {
                txtMængde.setText(String.valueOf(String.format("%.2f", Math.floor(valgtDestillat.resterendeMængdeILiter() * 100) / 100)));
            }
        }
    }

    private Path createArrow() {
        Path arrow = new Path();

        // Create the arrow shape using MoveTo and LineTo commands
        MoveTo moveTo = new MoveTo(0, 0);
        LineTo line1 = new LineTo(250, 0); // Extend the arrow by 100px
        MoveTo moveTo2 = new MoveTo(250, 0); // Update the arrowhead starting point
        LineTo line2 = new LineTo(240, -10);
        MoveTo moveTo3 = new MoveTo(250, 0); // Update the arrowhead starting point
        LineTo line3 = new LineTo(240, 10);

        arrow.getElements().addAll(moveTo, line1, moveTo2, line2, moveTo3, line3);
        arrow.setStrokeWidth(2);
        arrow.setStroke(Color.BLACK);
        arrow.setFill(null); // Set fill to null

        return arrow;
    }


}
