package gui;

import application.controller.ControllerForLager;
import application.controller.ControllerForProduktion;
import application.model.Aftapning;
import application.model.Betegnelse;
import application.model.Fad;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;
import java.util.HashSet;


public class AftapningPane extends GridPane {

    private final TextField txfVolume, txfAntalFlasker, txfFlaskeStørrelse, txfVandILiter, txfVandKilde, txfAlkoholProcent, txfBetegnelse;
    private final TextArea txaBeskrivelse;
    ControllerForLager controllerForLager = ControllerForLager.getController();
    ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();
    private final ListView<Fad> lvwFade;
    private final DatePicker dpAftapningsDato;
    private final TextField txfAftappetAf;
    private final TextField txfMængde;
    private final ListView<Aftapning> lvwAftapninger;
    private final Label lblError;
    private HashSet<Aftapning> aftapninger = new HashSet<>();

    public AftapningPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);

        // #--- Column 0 ---#

        Label lblFade = new Label("Fade");
        this.add(lblFade, 0,0);
        GridPane.setHalignment(lblFade, HPos.CENTER);

        lvwFade = new ListView<>();
        lvwFade.setMinWidth(200);
        lvwFade.setMinHeight(300);
        this.add(lvwFade, 0, 1, 1, 12);

        Button btnRegistrerAlkoholprocent = new Button("Registrer alkoholprocent");
        btnRegistrerAlkoholprocent.setOnAction(event -> this.registrerAlkoholprocentAction());
        this.add(btnRegistrerAlkoholprocent,0,13);
        GridPane.setHalignment(btnRegistrerAlkoholprocent, HPos.CENTER);

        // #--- Column 1 ---#

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        GridPane.setHalignment(vBox, HPos.CENTER);
        this.add(vBox, 1,0, 1, 13);

        Path leftArrow = leftArrow();
        vBox.getChildren().add(leftArrow);

        Button btnFravælg = new Button("Fravælg");
        btnFravælg.setOnAction(event -> this.fravælgAction());
        vBox.getChildren().add(btnFravælg);

        Label lblaftapningsDato = new Label("Aftapningsdato");
        vBox.getChildren().add(lblaftapningsDato);

        dpAftapningsDato = new DatePicker();
        vBox.getChildren().add(dpAftapningsDato);
        dpAftapningsDato.setMaxSize(100,50 );
        dpAftapningsDato.setValue(LocalDate.now());

        Label lblAftappetAf = new Label("Aftappet af");
        vBox.getChildren().add(lblAftappetAf);

        txfAftappetAf = new TextField();
        vBox.getChildren().add(txfAftappetAf);
        txfAftappetAf.setMaxSize(100,50);

        Label lblMængde = new Label("Mængde (liter)");
        vBox.getChildren().add(lblMængde);

        txfMængde = new TextField();
        vBox.getChildren().add(txfMængde);
        txfMængde.setMaxSize(100,50);

        Button btnVælg = new Button("Vælg");
        btnVælg.setOnAction(event -> this.vælgAction());
        vBox.getChildren().add(btnVælg);

        Path rightArrow = rightArrow();
        vBox.getChildren().add(rightArrow);

        // #--- Column 2 ---#

        Label lblAftapninger = new Label("Aftapninger");
        this.add(lblAftapninger, 2,0);
        GridPane.setHalignment(lblAftapninger, HPos.CENTER);

        lvwAftapninger = new ListView<>();
        lvwAftapninger.setMinWidth(200);
        lvwAftapninger.setMinHeight(300);
        this.add(lvwAftapninger, 2, 1, 1, 12);

        Button btnNulstil = new Button("Nulstil");
        btnNulstil.setOnAction(event -> this.nulstilAction());
        this.add(btnNulstil, 2,13);
        GridPane.setHalignment(btnNulstil, HPos.CENTER);

        // #--- Column 3 ---#

        Separator separator = new Separator(Orientation.VERTICAL);
        this.add(separator, 3,0,1,14);

        // #--- Column 4 & 5 ---#

        Label lblWhiskyProdukt = new Label("Whisky produkt");
        this.add(lblWhiskyProdukt, 4,0,2,1);
        GridPane.setHalignment(lblWhiskyProdukt, HPos.CENTER);

        Label lblVolume = new Label("Mængde (L)");
        this.add(lblVolume, 4,1);
        GridPane.setHalignment(lblVolume, HPos.CENTER);

        txfVolume = new TextField();
        txfVolume.setEditable(false);
        this.add(txfVolume, 4,2);
        GridPane.setHalignment(txfVolume, HPos.CENTER);
        txfVolume.setMaxSize(75,50);

        Label lblFlaskeStørrelse = new Label("Flaske størrelse (CL)");
        this.add(lblFlaskeStørrelse, 5,1);
        GridPane.setHalignment(lblFlaskeStørrelse, HPos.CENTER);

        txfFlaskeStørrelse = new TextField("70");
        // TextFormatter, der sikrer at der kun bliver indtastet tal
        txfFlaskeStørrelse.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
        txfFlaskeStørrelse.textProperty().addListener((o, ov, nv) -> updateWhiskyInfo());
        this.add(txfFlaskeStørrelse, 5,2);
        GridPane.setHalignment(txfFlaskeStørrelse, HPos.CENTER);
        txfFlaskeStørrelse.setMaxSize(75,50);

        Label lblAntalFlasker = new Label("Antal flasker");
        this.add(lblAntalFlasker, 4,3,2,1);
        GridPane.setHalignment(lblAntalFlasker, HPos.CENTER);

        txfAntalFlasker = new TextField();
        txfAntalFlasker.setEditable(false);
        this.add(txfAntalFlasker, 4,4,2,1);
        GridPane.setHalignment(txfAntalFlasker, HPos.CENTER);
        txfAntalFlasker.setMaxSize(75,50);

        Separator separator1 = new Separator(Orientation.HORIZONTAL);
        this.add(separator1, 4,5,3,1);

        Label lblVandILiter = new Label("Vand (liter)");
        this.add(lblVandILiter, 4,6);
        GridPane.setHalignment(lblVandILiter, HPos.CENTER);

        txfVandILiter = new TextField();
        txfVandILiter.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
        txfVandILiter.textProperty().addListener((o, ov, nv) -> updateWhiskyInfo());
        this.add(txfVandILiter, 4,7);
        GridPane.setHalignment(txfVandILiter, HPos.CENTER);
        txfVandILiter.setMaxSize(75,50);

        Label lblVandKilde = new Label("Vandkilde");
        this.add(lblVandKilde, 5,6);
        GridPane.setHalignment(lblVandKilde, HPos.CENTER);

        txfVandKilde = new TextField();
        this.add(txfVandKilde, 5,7);
        GridPane.setHalignment(txfVandKilde, HPos.CENTER);
        txfVandKilde.setMinSize(120,20);

        Label lblAlkoholProcent = new Label("Alkoholprocent");
        this.add(lblAlkoholProcent, 4,8);
        GridPane.setHalignment(lblAlkoholProcent, HPos.CENTER);

        txfAlkoholProcent = new TextField();
        txfAlkoholProcent.setEditable(false);
        this.add(txfAlkoholProcent, 4,9,1,1);
        GridPane.setHalignment(txfAlkoholProcent, HPos.CENTER);
        txfAlkoholProcent.setMaxSize(75,50);

        Label lblBetegnelse = new Label("Betegnelse");
        this.add(lblBetegnelse, 5,8);
        GridPane.setHalignment(lblBetegnelse, HPos.CENTER);

        txfBetegnelse = new TextField(" ");
        txfBetegnelse.setEditable(false);
        this.add(txfBetegnelse, 5,9);
        GridPane.setHalignment(txfBetegnelse, HPos.CENTER);
        txfBetegnelse.setMinSize(120,20);

        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 4,10,2,1);

        txaBeskrivelse = new TextArea();
        this.add(txaBeskrivelse, 4,11,2,1);
        GridPane.setHalignment(txaBeskrivelse, HPos.CENTER);

        Button btnBekræft = new Button("Bekræft whisky");
        btnBekræft.setOnAction(event -> bekræftWhiskyAction());
        this.add(btnBekræft, 4,12,2,1);
        GridPane.setHalignment(btnBekræft, HPos.CENTER);

        // #--- Error Label ---#
        lblError = new Label(" ");
        this.add(lblError, 1, 11, 1, 2);
        lblError.setTextFill(Color.RED);
        GridPane.setHalignment(lblError, HPos.CENTER);
        GridPane.setValignment(lblError, VPos.BOTTOM);
        lblError.setWrapText(true);
        lblError.setTextAlignment(TextAlignment.CENTER);
        lblError.setMaxWidth(100);

        updateControls();
    }

    public void updateControls() {
        updateFade();
        updateAftapninger();
        updateWhiskyInfo();
    }

    private void updateFade() {
        lvwFade.getItems().setAll(controllerForLager.getModneFadeMedRegistreretAlkoholProcent());
    }

    private void updateAftapninger() {
        lvwAftapninger.getItems().setAll(aftapninger);
    }

    private void updateWhiskyInfo() {
        if (!aftapninger.isEmpty()) {
            // Opdaterer mængde
            double mængdeVand = 0.0;
            if (!txfVandILiter.getText().isBlank()) {
                mængdeVand = Double.parseDouble(txfVandILiter.getText().trim());
            }
            double mængde = mængdeVand;
            double alkoholMængde = 0.0;
            for (Aftapning aftapning : aftapninger) {
                mængde += aftapning.getMængdeILiter();
                alkoholMængde += (aftapning.getFadIndhold().getAlkoholProcentEfterModning() / 100.0) * aftapning.getMængdeILiter();
            }
            txfVolume.setText("" + mængde);
            txfAlkoholProcent.setText("" + String.format("%.2f", alkoholMængde / mængde * 100));

            // Opdaterer antal flasker
            if (txfAntalFlasker.getText().isBlank()) {
                txfAntalFlasker.setText("0");
            } else {
                double flaskeStørrelse = Double.parseDouble(txfFlaskeStørrelse.getText().trim());
                if (flaskeStørrelse >= 0) {
                    flaskeStørrelse /= 100; // Omregner til L fra CL
                }

                int antalFlasker = flaskeStørrelse > 0 && mængde > 0 ? (int) (mængde / flaskeStørrelse) : 0;
                txfAntalFlasker.setText("" + antalFlasker);
            }

            // Opdaterer betegnelse
            Betegnelse betegnelse = controllerForProduktion.udregnBetegnelse(aftapninger, mængdeVand);
            txfBetegnelse.setText(betegnelse.toString());
        } else {
            txfVolume.setText("0");
            txfAntalFlasker.setText("0");
            txfAlkoholProcent.setText("0");
            txfBetegnelse.setText(" ");
        }
    }

    private void bekræftWhiskyAction() {
        String beskrivelse = txaBeskrivelse.getText().trim();
        String vandKilde = txfVandKilde.getText().trim();
        double vandMængde = 0.0;
        if (beskrivelse.isBlank()) {
            lblError.setText("Angiv beskrivelse");
        } else if (vandKilde.isBlank()){
            lblError.setText("Angiv vand kilde");
        } else {
            try {
                vandMængde = Double.parseDouble(txfVandILiter.getText().trim());
            } catch (NumberFormatException e) {
                lblError.setText("Vand mængde skal være et tal");
                return;
            }
            if (vandMængde < 0) {
                lblError.setText("Vand mængde skal være større end 0");
            } else {
                controllerForProduktion.createWhisky(aftapninger, vandMængde, vandKilde, beskrivelse);
                aftapninger.clear();
                updateControls();
                txfVandILiter.clear();
                txfVandKilde.clear();
                txaBeskrivelse.clear();
                txfFlaskeStørrelse.setText("70");
            }
        }
    }

    private void vælgAction() {
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        if (valgtFad != null) {
            String aftappetAf = txfAftappetAf.getText().trim();
            LocalDate dato = dpAftapningsDato.getValue();
            double mængde = 0.0;
            if (aftappetAf.isBlank()) {
                 lblError.setText("Angiv aftappet af");
            } else if (dato == null) {
                 lblError.setText("Angiv dato");
            } else {
                try {
                    mængde = Double.parseDouble(txfMængde.getText().trim());
                } catch (NumberFormatException e) {
                     lblError.setText("Mængde skal være et tal");
                    return;
                }
                if (mængde <= 0) {
                     lblError.setText("Mængde skal være større end 0");
                } else {
                    try {
                        Aftapning aftapning = controllerForProduktion.createAftapning(aftappetAf, mængde, dato, valgtFad);
                        aftapninger.add(aftapning);

                        updateControls();
                        if (lvwFade.getItems().contains(valgtFad)) {
                            lvwFade.getSelectionModel().select(valgtFad);
                        }
                    } catch (RuntimeException e) {
                        lblError.setText(e.getMessage());
                    }
                }
            }
        } else {
             lblError.setText("Vælg et fad");
        }
    }

    private void fravælgAction() {
        Aftapning valgtAftapning = lvwAftapninger.getSelectionModel().getSelectedItem();
        if (valgtAftapning != null) {
            controllerForProduktion.removeAftapning(valgtAftapning);
        }
        aftapninger.remove(valgtAftapning);
        updateControls();
    }

    private void registrerAlkoholprocentAction() {
        clearError();
        Fad valgtFad = lvwFade.getSelectionModel().getSelectedItem();
        RegistrerAlkoholProcentVindue registrerAlkoholProcentVindue = new RegistrerAlkoholProcentVindue(valgtFad);
        registrerAlkoholProcentVindue.showAndWait();
        updateFade();
    }

    private void nulstilAction() {
        for (Aftapning aftapning : aftapninger) {
            controllerForProduktion.removeAftapning(aftapning);
        }
        txfVandILiter.clear();
        txfVandKilde.clear();
        txaBeskrivelse.clear();
        txfFlaskeStørrelse.setText("70");
        aftapninger.clear();
        updateControls();
    }

    private void clearError() {
        lblError.setText(" ");
    }

    private Path leftArrow() {
        Path arrow = new Path();

        // Create the arrow shape using MoveTo and LineTo commands
        MoveTo moveTo = new MoveTo(0, 0);
        LineTo line1 = new LineTo(-100, 0); // Extend the arrow by 100px
        MoveTo moveTo2 = new MoveTo(-100, 0); // Update the arrowhead starting point
        LineTo line2 = new LineTo(-90, 10);
        MoveTo moveTo3 = new MoveTo(-100, 0); // Update the arrowhead starting point
        LineTo line3 = new LineTo(-90, -10);

        arrow.getElements().addAll(moveTo, line1, moveTo2, line2, moveTo3, line3);
        arrow.setStrokeWidth(2);
        arrow.setStroke(Color.BLACK);
        arrow.setFill(null); // Set fill to null

        return arrow;
    }

    private Path rightArrow() {
        Path arrow = new Path();

        // Create the arrow shape using MoveTo and LineTo commands
        MoveTo moveTo = new MoveTo(0, 0);
        LineTo line1 = new LineTo(100, 0); // Extend the arrow by 100px
        MoveTo moveTo2 = new MoveTo(100, 0); // Update the arrowhead starting point
        LineTo line2 = new LineTo(90, -10);
        MoveTo moveTo3 = new MoveTo(100, 0); // Update the arrowhead starting point
        LineTo line3 = new LineTo(90, 10);

        arrow.getElements().addAll(moveTo, line1, moveTo2, line2, moveTo3, line3);
        arrow.setStrokeWidth(2);
        arrow.setStroke(Color.BLACK);
        arrow.setFill(null); // Set fill to null

        return arrow;
    }
}
