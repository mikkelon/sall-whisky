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

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0,0);

        TextField txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);

        Label lblLand = new Label("Land");
        pane.add(lblLand, 0, 2);

        TextField txfLand = new TextField();
        pane.add(txfLand, 0, 3);

        Button btnGem = new Button("Gem");
        pane.add(btnGem, 0, 4);

        Button btnAnnuller = new Button("Annuller");
        pane.add(btnAnnuller, 0, 5);


    }
}
