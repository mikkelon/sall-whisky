package gui;

import application.controller.Controller;
import application.model.FadLeverandør;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class OpretFadLeverandør extends Stage {
    private FadLeverandør fadLeverandør;
    private Controller controller = Controller.getController();
    private Label lblNavn;
    private Label lblLand;
    private TextField txfNavn;
    private TextField txfLand;
    private Label lblError;

    public OpretFadLeverandør(FadLeverandør fadLeverandør){
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.fadLeverandør = fadLeverandør;

        this.setHeight(300);
        this.setWidth(300);

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

        lblNavn = new Label("Navn");
        pane.add(lblNavn, 0,0);

        txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);

        lblLand = new Label("Land");
        pane.add(lblLand, 0, 2);

        txfLand = new TextField();
        pane.add(txfLand, 0, 3);

        Button btnGem = new Button("Gem");
        pane.add(btnGem, 0, 5);
        btnGem.setOnAction(event -> opretGemAction());

        Button btnAnnuller = new Button("Annuller");
        pane.add(btnAnnuller, 0, 6);
        btnAnnuller.setOnAction(event -> this.close());

        lblError = new Label(" ");
        pane.add(lblError,0, 4);
    }

    private void opretGemAction(){
        String navn = txfNavn.getText();
        String land = txfLand.getText();
        if(txfNavn == null){
            lblError.setText("Navn er ikke udfyldt");
        } else if(txfLand == null){
            lblError.setText("Land er ikke udfyldt");
        } else{
            controller.createFadLeverandør(navn, land);
        }

    }
}
