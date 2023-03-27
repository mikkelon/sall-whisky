package gui;

import application.model.Fad;
import application.model.Lager;
import application.model.Påfyldning;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PåfyldningPane extends GridPane {
    public PåfyldningPane() {
        this.setPadding(new javafx.geometry.Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);

        Label lblPåfyldning = new Label("Destillater");
        this.add(lblPåfyldning, 0, 0);

        ListView<Påfyldning> lvwDestillater = new ListView<>();
        this.add(lvwDestillater, 0, 1, 1, 6);

        Label lblLager = new Label("Lager");
        this.add(lblLager, 1, 0, 2, 1);
        GridPane.setHalignment(lblLager, HPos.CENTER);

        ComboBox<Lager> comboBox = new ComboBox<>();
        this.add(comboBox, 1, 1, 2, 1);
        GridPane.setHalignment(comboBox, HPos.CENTER);

        Label lblMængde = new Label("Mængde (Liter)");
        this.add(lblMængde, 1, 2, 2, 1);
        GridPane.setHalignment(lblMængde, HPos.CENTER);

        TextField txtMængde = new TextField();
        this.add(txtMængde, 1, 3, 2, 1);
        GridPane.setHalignment(txtMængde, HPos.CENTER);
        txtMængde.setMaxSize(80, 20);

        Label lblPåfyldningsDato = new Label("Påfyldningsdato");
        this.add(lblPåfyldningsDato, 1, 4, 1, 1);


        DatePicker datePicker = new DatePicker();
        this.add(datePicker, 1, 5, 1, 1);

        Label lblPåFyldtAf = new Label("Påfyldt af");
        this.add(lblPåFyldtAf, 2, 4, 1, 1);

        TextField txtPåFyldtAf = new TextField();
        this.add(txtPåFyldtAf, 2, 5, 1, 1);

        Label lblFad = new Label("Fad");
        this.add(lblFad, 3, 0);

        Button btnPåfyld = new Button("Påfyld");
        this.add(btnPåfyld, 1, 6, 2, 1);
        btnPåfyld.setMinSize(100, 40);
        GridPane.setHalignment(btnPåfyld, HPos.CENTER);

        ListView<Fad> lvwFade = new ListView<>();
        this.add(lvwFade, 3, 1, 1, 6);
    }

}
