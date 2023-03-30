package gui;

import application.controller.ControllerForProduktion;
import gui.gui_lagerstyring.LagerstyringPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartVindue extends Application {
    private ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();
    private TabPane tabPane;
    @Override
    public void init() {
        controllerForLager.initMockData();
        controllerForProduktion.initMockData();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sall Whisky - Sporing og lagerproduktion");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        stage.setResizable(false);

        stage.setWidth(900);
        stage.setHeight(600);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void initContent(BorderPane pane) {
        tabPane = new TabPane();
        this.initTabPane();
        pane.setCenter(tabPane);
    }

    public void initTabPane() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab lagerstyringTab = new Tab("Lagerstyring");
        tabPane.getTabs().add(lagerstyringTab);
        LagerstyringPane lagerstyringPane = new LagerstyringPane(this);
        lagerstyringPane.setAlignment(Pos.TOP_CENTER);
        lagerstyringTab.setContent(lagerstyringPane);
        lagerstyringTab.setOnSelectionChanged(event -> lagerstyringPane.updateControls());

        Tab fadeTab = new Tab("Fade");
        tabPane.getTabs().add(fadeTab);
        FadePane fadePane = new FadePane();
        fadePane.setAlignment(Pos.TOP_CENTER);
        fadeTab.setContent(fadePane);
        fadeTab.setOnSelectionChanged(event -> fadePane.updateControls());

        Tab destillatTab = new Tab("Destillat");
        tabPane.getTabs().add(destillatTab);
        DestillatPane destillatPane = new DestillatPane();
        destillatPane.setAlignment(Pos.TOP_CENTER);
        destillatTab.setContent(destillatPane);
//        destillatTab.setOnSelectionChanged(event -> destillatPane.updateControls());

        Tab påfyldningTab = new Tab("Påfyldning");
        tabPane.getTabs().add(påfyldningTab);
        PåfyldningPane påfyldningPane = new PåfyldningPane();
        påfyldningPane.setAlignment(Pos.TOP_CENTER);
        påfyldningTab.setContent(påfyldningPane);
        påfyldningTab.setOnSelectionChanged(event -> påfyldningPane.updateControls());
    }

    public Pane skiftTab(int index) {
        tabPane.getSelectionModel().select(index);
        // Return the content of the selected tab
        return (Pane) tabPane.getSelectionModel().getSelectedItem().getContent();
    }

}
