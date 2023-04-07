package gui;

import application.controller.ControllerForProduktion;
import application.model.Betegnelse;
import application.model.Whisky;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;


public class WhiskyPane extends GridPane {
    private ControllerForProduktion controllerForProduktion = ControllerForProduktion.getController();
    private ListView<Whisky> lvwWhiskyer;
    private TextField txfVandILiter;
    private TextField txfAlkoholprocent;
    private TextArea txaBeskrivelse;
    private ComboBox<Betegnelse> cbxBetegnelse;
    private TextArea txaHistorik;
    private TextField txfVandAfstamning;

    public WhiskyPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblWhisky = new Label("Whisky");
        this.add(lblWhisky, 0, 0);

        lvwWhiskyer = new ListView<>();
        lvwWhiskyer.setMinWidth(200);
        lvwWhiskyer.setMinHeight(300);
        this.add(lvwWhiskyer,0, 1, 1, 6);
        lvwWhiskyer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectionChanged());
        lvwWhiskyer.getItems().setAll(controllerForProduktion.getWhiskyer());

        Label lblVandILiter = new Label("Vand (Liter)");
        this.add(lblVandILiter, 2, 1);

        txfVandILiter = new TextField();
        txfVandILiter.setDisable(true);
        this.add(txfVandILiter, 2, 2);

        Label lblAlkoholprocent = new Label("Alkoholprocent");
        this.add(lblAlkoholprocent, 2, 3);

        txfAlkoholprocent = new TextField();
        txfAlkoholprocent.setDisable(true);
        this.add(txfAlkoholprocent, 2, 4);

        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 2, 5);

        txaBeskrivelse = new TextArea();
        txaBeskrivelse.setMinWidth(180);
        txaBeskrivelse.setMinHeight(100);
        txaBeskrivelse.setMaxWidth(180);
        txaBeskrivelse.setMaxHeight(100);
        GridPane.setValignment(txaBeskrivelse, VPos.TOP);
        txaBeskrivelse.setDisable(true);
        this.add(txaBeskrivelse, 2, 6, 1, 2);

        Label lblVandkilde = new Label("Vandkilde");
        this.add(lblVandkilde, 3, 1);

        txfVandAfstamning = new TextField();
        txfVandAfstamning.setDisable(true);
        this.add(txfVandAfstamning, 3, 2);

        Label lblBetegnelse = new Label("Betegnelse");
        this.add(lblBetegnelse, 3, 3);

        cbxBetegnelse = new ComboBox<>();
        GridPane.setValignment(cbxBetegnelse, VPos.TOP);
        cbxBetegnelse.setMinWidth(150);
        cbxBetegnelse.setMaxWidth(150);
        cbxBetegnelse.getItems().setAll(Betegnelse.values());
        cbxBetegnelse.setDisable(true);
        this.add(cbxBetegnelse, 3,4);

        Label lblHistorik = new Label("Historik");
        this.add(lblHistorik, 5, 0);

        txaHistorik = new TextArea();
        txaHistorik.setMinWidth(250);
        //txaHistorik.setMinHeight(300);
        txaHistorik.setMaxWidth(250);
        //txaHistorik.setMaxHeight(300);
        txaHistorik.setDisable(true);
        this.add(txaHistorik, 5, 1, 1 , 6);

        Separator sep1 = new Separator(Orientation.VERTICAL);
        this.add(sep1, 1, 0, 1, 8);

        Separator sep2 = new Separator(Orientation.VERTICAL);
        this.add(sep2, 4, 0, 1, 8);

    }

    private void getInfo(){
        Whisky valgtWhisky = lvwWhiskyer.getSelectionModel().getSelectedItem();
        if(valgtWhisky != null){
            txfVandILiter.setText(String.valueOf(valgtWhisky.getMængdeVandILiter()));
            txfVandILiter.setDisable(false);
            txfVandILiter.setEditable(false);
            txfAlkoholprocent.setText(String.valueOf(valgtWhisky.getAlkoholProcent()));
            txfAlkoholprocent.setDisable(false);
            txfAlkoholprocent.setEditable(false);
            txaBeskrivelse.setText(String.valueOf(valgtWhisky.getTekstBeskrivelse()));
            txaBeskrivelse.setDisable(false);
            txaBeskrivelse.setEditable(false);
            txfVandAfstamning.setText(String.valueOf(valgtWhisky.getVandAfstamning()));
            txfVandAfstamning.setDisable(false);
            txfVandAfstamning.setEditable(false);
            cbxBetegnelse.getSelectionModel().select(valgtWhisky.getBetegnelse());
            cbxBetegnelse.setDisable(false);
            cbxBetegnelse.setMouseTransparent(true);
            cbxBetegnelse.setFocusTraversable(false);
            txaHistorik.setText(String.valueOf(valgtWhisky.hentHistorik()));
            txaHistorik.setDisable(false);
            txaHistorik.setEditable(false);


        }
    }

    private void selectionChanged() {
        if (lvwWhiskyer.getSelectionModel().getSelectedItem() != null) {
            getInfo();
        }
    }

    public void updateControls() {
        lvwWhiskyer.getItems().setAll(controllerForProduktion.getWhiskyer());
        getInfo();
    }

}
