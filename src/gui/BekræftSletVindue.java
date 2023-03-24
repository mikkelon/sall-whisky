package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BekræftSletVindue extends Stage {
    public BekræftSletVindue(String titel) {
        this.initStyle(StageStyle.DECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(titel);

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private boolean valg = false;
    private void initContent(GridPane pane) {
        // Formateringsjusteringer
        pane.setAlignment(Pos.CENTER);

        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        // Indhold
        Label lblBekræftelse = new Label("Er du sikker på at du vil slette?");
        pane.add(lblBekræftelse, 0, 0, 2,1);

        Label lblBekræftelse2 = new Label("Dette kan ikke fortrydes!");
        pane.add(lblBekræftelse2, 0, 1, 2,1);

        Label lblBekræftelse3 = new Label("Tryk på 'Ja' for at slette");
        pane.add(lblBekræftelse3, 0, 2, 2,1);

        Label lblBekræftelse4 = new Label("Tryk på 'Nej' for at annullere");
        pane.add(lblBekræftelse4, 0, 3, 2,1);

        HBox hBoxKnapper = new HBox();
        hBoxKnapper.setSpacing(10);
        hBoxKnapper.setAlignment(Pos.CENTER);
        pane.add(hBoxKnapper, 0, 4, 2,1);

        Button btnJa = new Button("Ja");
        btnJa.setOnAction(event -> jaAction());
        hBoxKnapper.getChildren().add(btnJa);

        Button btnNej = new Button("Nej");
        btnNej.setOnAction(event -> nejAction());
        hBoxKnapper.getChildren().add(btnNej);
    }

    public boolean getValg() {
        return valg;
    }

    private void jaAction() {
        valg = true;
        this.close();
    }

    private void nejAction() {
        valg = false;
        this.close();
    }
}
