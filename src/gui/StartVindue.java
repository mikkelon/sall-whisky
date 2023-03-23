package gui;

import application.controller.Controller;
import gui.gui_lagerstyring.LagerstyringPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartVindue extends Application {
    private Controller controller = Controller.getController();

    @Override
    public void init() {
        controller.initMockData();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sall Whisky - Sporing og lagerproduktion");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        stage.setResizable(false);

        //stage.setWidth(500);
        //stage.setHeight(500);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    public void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab lagerstyringTab = new Tab("Lagerstyring");
        tabPane.getTabs().add(lagerstyringTab);
        LagerstyringPane lagerstyringPane = new LagerstyringPane();
        lagerstyringTab.setContent(lagerstyringPane);
        lagerstyringTab.setOnSelectionChanged(event -> lagerstyringPane.updateControls());

    }


}
