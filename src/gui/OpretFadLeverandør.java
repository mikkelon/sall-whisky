package gui;

import application.controller.Controller;
import application.model.FadLeverandør;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class OpretFadLeverandør extends Stage {
    private FadLeverandør fadLeverandør;
    private Controller controller = Controller.getController();
    private TextField txfNavn;
    private TextField txfLand;
    private Label lblError;

    public OpretFadLeverandør(){
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setHeight(225);
        this.setWidth(200);

        this.setTitle("Opret fadleverandør");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane){
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0,0);

        txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);

        Label lblLand = new Label("Land");
        pane.add(lblLand, 0, 2);

        txfLand = new TextField();
        pane.add(txfLand, 0, 3);

        HBox hBoxBtns = new HBox();
        hBoxBtns.setSpacing(10);
        hBoxBtns.setAlignment(Pos.CENTER);
        pane.add(hBoxBtns, 0, 5);

        Button btnOpret = new Button("Opret");
        hBoxBtns.getChildren().add(btnOpret);
        btnOpret.setOnAction(event -> opretAction());

        Button btnAnnuller = new Button("Annullér");
        hBoxBtns.getChildren().add(btnAnnuller);
        btnAnnuller.setOnAction(event -> this.close());

        lblError = new Label(" ");
        pane.add(lblError,0, 4);
        lblError.setStyle("-fx-text-fill: red");
        GridPane.setHalignment(lblError, HPos.CENTER);
    }

    private void opretAction(){
        String navn = txfNavn.getText().trim();
        String land = txfLand.getText().trim();
        if (txfNavn.getText().isEmpty()){
            lblError.setText("Navn er ikke udfyldt");
        } else if (txfLand.getText().isEmpty()){
            lblError.setText("Land er ikke udfyldt");
        } else{
            controller.createFadLeverandør(navn, land);
            this.close();
        }
    }
}
