package gui;

import application.controller.Controller;
import application.model.Fad;
import application.model.Påfyldning;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FadePane extends GridPane {
    private Controller controller = Controller.getController();
    private ListView<Påfyldning> lvwPåfyldninger;
    private ListView<Fad> lvwFade;

    public FadePane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        VBox vBoxFørsteRække = new VBox(10);
        this.add(vBoxFørsteRække, 1, 1, 1, 1);

        Label lblFadLeverandør = new Label("Fadleverandør");
        vBoxFørsteRække.getChildren().add(lblFadLeverandør);

        TextField txfFadLeverandør = new TextField();
        vBoxFørsteRække.getChildren().add(txfFadLeverandør);

        Button btnNyLeverandør = new Button("Ny Leverandør");
        vBoxFørsteRække.getChildren().add(btnNyLeverandør);

        Label lblLager = new Label("Lager");
        vBoxFørsteRække.getChildren().add(lblLager);

        TextField txfLager = new TextField();
        vBoxFørsteRække.getChildren().add(txfLager);

        VBox vBoxAndenRække = new VBox(10);
        this.add(vBoxAndenRække, 2, 2, 2, 2);

        Label lblStørrelse = new Label("Størrelse (Liter)");
        vBoxAndenRække.getChildren().add(lblStørrelse);

        TextField txfStørrelse = new TextField();
        vBoxAndenRække.getChildren().add(txfStørrelse);

        Label lblFadType = new Label("Fadtype");
        vBoxAndenRække.getChildren().add(lblFadType);

        TextField txfFadType = new TextField();
        vBoxAndenRække.getChildren().add(txfFadType);
    }
}
