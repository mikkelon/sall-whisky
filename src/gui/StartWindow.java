package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartWindow extends Application {

    @Override
    public void init() {
        // TODO: Evt. init mockdata
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sall Whisky - Sporing og lagerproduktion");
        GridPane pane = new GridPane();
        this.initContent(pane);
        stage.setResizable(false);

        stage.setWidth(500);
        stage.setHeight(500);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        Button btnOpretFad = new Button("Opret Fad");
        btnOpretFad.setOnAction(event -> opretFad());
        pane.add(btnOpretFad, 0,0);
    }

    private void opretFad() {
        OpretFad window = new OpretFad();
        window.showAndWait();
    }
}
