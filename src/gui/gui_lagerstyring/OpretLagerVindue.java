package gui.gui_lagerstyring;

import application.controller.Controller;
import application.model.FadLeverandør;
import application.model.FadType;
import application.model.Hylde;
import application.model.Lager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;

public class OpretLagerVindue extends Stage {
    private Controller controller = Controller.getController();
    public OpretLagerVindue() {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setHeight(300);
        this.setWidth(300);

        this.setTitle("Opret lager");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
    private TextField txfAdresse, txfNavn, txfKvm, txfAntalHylder;
    private Label lblError;

    private void initContent(GridPane pane) {
        // Formateringsjusteringer
//        pane.setGridLinesVisible(true);
        pane.setAlignment(Pos.CENTER);

        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        // Indhold
        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);

        Label lblAdresse = new Label("Adresse");
        pane.add(lblAdresse, 1, 0);

        txfAdresse = new TextField();
        pane.add(txfAdresse, 1, 1);

        Label lblKvm = new Label("Kvm");
        pane.add(lblKvm, 0, 2);

        txfKvm = new TextField();
        pane.add(txfKvm, 0, 3);

        Label lblAntalHylder = new Label("Antal hylder");
        pane.add(lblAntalHylder, 1, 2);

        txfAntalHylder = new TextField();
        pane.add(txfAntalHylder, 1, 3);

        lblError = new Label(" ");
        pane.add(lblError, 0, 6, 2, 1);
        lblError.setStyle("-fx-text-fill: red");

        // Opret og Annullér knapper
        HBox hBox = new HBox();
        pane.add(hBox, 0, 7, 2, 1);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        Button btnAnnuller = new Button("Annullér");
        hBox.getChildren().add(btnAnnuller);
        btnAnnuller.setOnAction(event -> this.close());

        Button btnOpret = new Button("Opret");
        hBox.getChildren().add(btnOpret);
        btnOpret.setOnAction(event -> opretLagerAction());

        // Opdatering af controls
        updateControls();
    }

    private void opretLagerAction() {
        String navn = txfNavn.getText().trim();
        String adresse = txfAdresse.getText().trim();
        double kvm = 0;
        int antalHylder = 0;

        if (navn.length() == 0) lblError.setText("Navn skal udfyldes");
        else if (adresse.length() == 0) lblError.setText("Adresse skal udfyldes");

        else {
            try {
                kvm = Double.parseDouble(txfKvm.getText().trim());
            } catch (NumberFormatException e) {
                lblError.setText("Kvm skal være et tal");
                return;
            }
            try {
                antalHylder = Integer.parseInt(txfAntalHylder.getText().trim());
            } catch (NumberFormatException e) {
                lblError.setText("Antal hylder skal være et tal");
                return;
            }
            if (kvm <= 0) lblError.setText("Kvm skal være større end 0");
            else if (antalHylder < 0) lblError.setText("Antal hylder skal være 0 eller et positivt tal");
            else {
                controller.createLagerWithAntalHylder(adresse, navn, kvm, antalHylder);
                this.close();
            }
        }


    }
}
