package gui;

import application.controller.ControllerForLager;
import application.controller.ControllerForProduktion;
import application.model.lager.Fad;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import java.time.LocalDate;


public class OmhældningPane extends GridPane {
    private ListView<Fad> lvwFadeTil;
    private ListView<Fad> lvwFadeFra;
    private TextField txfMængde;
    private TextField txfOmhældtAf;
    private DatePicker datePicker;
    private Label lblError;
    private ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();
    private ControllerForLager controllerForLager = ControllerForLager.getController();

    public OmhældningPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);

        Label lblFadeFra = new Label("Fade");
        this.add(lblFadeFra, 0, 0);

        lvwFadeFra = new ListView<>();
        lvwFadeFra.setMinWidth(300);
        lvwFadeFra.setMaxWidth(300);
        lvwFadeFra.setMinHeight(300);
        this.add(lvwFadeFra, 0, 1, 1,7);

        Label lblMængde = new Label("Mængde (Liter)");
        GridPane.setHalignment(lblMængde, HPos.CENTER);
        this.add(lblMængde, 1, 1);

        txfMængde = new TextField();
        txfMængde.setMaxSize(150, 20);
        GridPane.setHalignment(txfMængde, HPos.CENTER);
        this.add(txfMængde, 1, 2);

        Label lblDato = new Label("Omhældningsdato");
        GridPane.setHalignment(lblDato, HPos.CENTER);
        this.add(lblDato, 1, 3);

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        GridPane.setValignment(datePicker, VPos.TOP);
        GridPane.setHalignment(datePicker, HPos.CENTER);
        datePicker.setMaxSize(150, 20);
        this.add(datePicker, 1, 4);

        Label lblOmhældtAf = new Label("Omhældt af");
        GridPane.setHalignment(lblOmhældtAf, HPos.CENTER);
        this.add(lblOmhældtAf, 1, 5);

        txfOmhældtAf = new TextField();
        txfOmhældtAf.setMaxSize(150, 20);
        GridPane.setHalignment(txfOmhældtAf, HPos.CENTER);
        this.add(txfOmhældtAf, 1, 6);

        Path arrow = createArrow();
        this.add(arrow, 1, 7, 1, 1);
        GridPane.setHalignment(arrow, HPos.CENTER);

        Button btnOmhæld = new Button("Omhæld");
        GridPane.setHalignment(btnOmhæld, HPos.CENTER);
        btnOmhæld.setMinSize(100, 40);
        btnOmhæld.setOnAction(event -> omhældAction());
        this.add(btnOmhæld, 1, 8);

        Label lblFadeTil = new Label("Fade");
        this.add(lblFadeTil, 2, 0);

        lvwFadeTil = new ListView<>();
        lvwFadeTil.setMinWidth(300);
        lvwFadeTil.setMaxWidth(300);
        lvwFadeTil.setMinHeight(300);
        this.add(lvwFadeTil, 2, 1, 1,7);

        lblError = new Label(" ");
        GridPane.setHalignment(lblError, HPos.CENTER);
        lblError.setTextFill(Color.RED);
        this.add(lblError, 1, 9);

    }
    private Path createArrow() {
        Path arrow = new Path();

        MoveTo moveTo = new MoveTo(0, 0);
        LineTo line1 = new LineTo(250, 0);
        MoveTo moveTo2 = new MoveTo(250, 0);
        LineTo line2 = new LineTo(240, -10);
        MoveTo moveTo3 = new MoveTo(250, 0);
        LineTo line3 = new LineTo(240, 10);

        arrow.getElements().addAll(moveTo, line1, moveTo2, line2, moveTo3, line3);
        arrow.setStrokeWidth(2);
        arrow.setStroke(Color.BLACK);
        arrow.setFill(null);

        return arrow;
    }

    private void omhældAction(){
        clearError();
        Fad fadFra = lvwFadeFra.getSelectionModel().getSelectedItem();
        Fad fadTil = lvwFadeTil.getSelectionModel().getSelectedItem();
        LocalDate omhældningsDato = datePicker.getValue();
        String omhældtAf = txfOmhældtAf.getText().trim();
        double mængde = 0;
        try{
            if(txfMængde.getText().trim().contains(","))
            {
                txfMængde.setText(txfMængde.getText().trim().replace(",","."));
            }
            mængde = Double.parseDouble(txfMængde.getText().trim());
        } catch (NumberFormatException e){
            lblError.setText("Mængde skal være et tal");
            return;
        }
        if(fadFra == null){
            lblError.setText("Vælg et fad");
        } else if(fadTil == null){
            lblError.setText("Vælg et fad");
        } else if(omhældningsDato == null){
            lblError.setText("Vælg en omhældningsdato");
        } else if(mængde <= 0){
            lblError.setText("Mængde skal være større end 0");
        } else if(omhældtAf.isEmpty()){
            lblError.setText("Indtast hvem der har omhældt");
        } else{
            try{
                controllerForProduktion.createOmhældning(omhældtAf, mængde, omhældningsDato, fadFra, fadTil);
                updateControls();
                txfMængde.clear();
                txfOmhældtAf.clear();
            }
            catch (RuntimeException e){
                lblError.setText(e.getMessage());
            }
        }
    }

    public void updateControls(){
        lvwFadeTil.getItems().setAll(controllerForLager.getIkkeFyldteFade());
        lvwFadeFra.getItems().setAll(controllerForLager.getIkkeTommeFade());
    }

    private void clearError(){
        lblError.setText(" ");
    }

}
