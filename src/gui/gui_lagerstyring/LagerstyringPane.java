package gui.gui_lagerstyring;

import application.model.Fad;
import application.model.Hylde;
import application.model.Lager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LagerstyringPane extends GridPane {
    private ListView<Lager> lvwLagre;
    private ListView<Hylde> lvwHylder;
    private ListView<Fad> lvwFade;

    public LagerstyringPane() {
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblLagre = new Label("Lagre");
        this.add(lblLagre, 0, 0);

        lvwLagre = new ListView<>();
        lvwLagre.setMinWidth(200);
        lvwLagre.setMaxWidth(200);
        lvwLagre.setMinHeight(400);
        this.add(lvwLagre, 0, 1);



        Label lblHylder = new Label("Hylder");
        this.add(lblHylder, 1, 0);

        lvwHylder = new ListView<>();
        lvwHylder.setMinWidth(200);
        lvwHylder.setMaxWidth(200);
        lvwHylder.setMinHeight(400);
        this.add(lvwHylder, 1, 1);

        Label lblFade = new Label("Fade");
        this.add(lblFade, 2, 0);

        lvwFade = new ListView<>();
        lvwFade.setMinWidth(200);
        lvwFade.setMaxWidth(200);
        lvwFade.setMinHeight(400);
        this.add(lvwFade, 2, 1);

        HBox hBoxFade = new HBox(10);
        this.add(hBoxFade, 2, 2);

        Button btnOpretFad = new Button("Opret Fad");
        btnOpretFad.setOnAction(event -> opretFad());
        hBoxFade.getChildren().add(btnOpretFad);

        Button btnSletFad = new Button("Slet Fad");
        btnSletFad.setOnAction(event -> sletFad());
        hBoxFade.getChildren().add(btnSletFad);
    }

    private void opretFad() {
        OpretFadVindue window = new OpretFadVindue();
        window.showAndWait();
    }

    private void sletFad() {

    }

    public void updateControls() {
        //TODO: Update controls
    }
}
