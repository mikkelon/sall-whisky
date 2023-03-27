package gui;

import application.model.Destillat;
import application.model.Fad;
import application.model.Lager;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class PåfyldningPane extends GridPane {

    private final ListView<Destillat> lvwDestillater;
    private final ListView<Fad> lvwFade;

    public PåfyldningPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);

        Label lblPåfyldning = new Label("Destillater");
        this.add(lblPåfyldning, 0, 0);

        lvwDestillater = new ListView<>();
        lvwDestillater.setMinWidth(200);
        lvwDestillater.setMaxWidth(200);
        lvwDestillater.setMinHeight(300);
        this.add(lvwDestillater, 0, 1, 1, 7);

        Label lblLager = new Label("Lagre");
        this.add(lblLager, 1, 0, 2, 1);
        GridPane.setHalignment(lblLager, HPos.CENTER);

        ComboBox<Lager> comboBox = new ComboBox<>();
        this.add(comboBox, 1, 1, 2, 1);
        GridPane.setHalignment(comboBox, HPos.CENTER);
        comboBox.setMaxWidth(150);
        comboBox.setPrefWidth(150);

        Label lblMængde = new Label("Mængde (Liter)");
        this.add(lblMængde, 1, 2, 2, 1);
        GridPane.setHalignment(lblMængde, HPos.CENTER);

        TextField txtMængde = new TextField();
        this.add(txtMængde, 1, 3, 2, 1);
        GridPane.setHalignment(txtMængde, HPos.CENTER);
        txtMængde.setMaxSize(80, 20);

        Path arrow = createArrow();
        this.add(arrow, 1, 4, 2, 1);
        GridPane.setHalignment(arrow, HPos.CENTER);

        Label lblPåfyldningsDato = new Label("Påfyldningsdato");
        this.add(lblPåfyldningsDato, 1, 5, 1, 1);

        DatePicker datePicker = new DatePicker();
        this.add(datePicker, 1, 6, 1, 1);

        Label lblPåFyldtAf = new Label("Påfyldt af");
        this.add(lblPåFyldtAf, 2, 5, 1, 1);

        TextField txtPåFyldtAf = new TextField();
        this.add(txtPåFyldtAf, 2, 6, 1, 1);

        Label lblFad = new Label("Fade");
        this.add(lblFad, 3, 0);

        Button btnPåfyld = new Button("Påfyld");
        this.add(btnPåfyld, 1, 8, 2, 1);
        btnPåfyld.setMinSize(100, 40);
        GridPane.setHalignment(btnPåfyld, HPos.CENTER);

        lvwFade = new ListView<>();
        lvwFade.setMinWidth(200);
        lvwFade.setMaxWidth(200);
        lvwFade.setMinHeight(300);
        this.add(lvwFade, 3, 1, 1, 7);
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
