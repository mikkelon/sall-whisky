package gui;

import application.model.Betegnelse;
import application.model.Whisky;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;


public class WhiskyPane extends GridPane {

    public WhiskyPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblWhisky = new Label("Whisky");
        this.add(lblWhisky, 0, 0);

        ListView<Whisky> lvwWhiskyer = new ListView<>();
        lvwWhiskyer.setMinWidth(200);
        lvwWhiskyer.setMinHeight(300);
        this.add(lvwWhiskyer,0, 1, 1, 6);

        Label lblVandILiter = new Label("Vand (Liter)");
        this.add(lblVandILiter, 1, 1);

        TextField txfVandILiter = new TextField();
        this.add(txfVandILiter, 1, 2);

        Label lblAlkoholprocent = new Label("Alkoholprocent");
        this.add(lblAlkoholprocent, 1, 3);

        TextField txfAlkoholprocent = new TextField();
        this.add(txfAlkoholprocent, 1, 4);

        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 1, 5);

        TextArea txaBeskrivelse = new TextArea();
        txaBeskrivelse.setMinWidth(180);
        txaBeskrivelse.setMinHeight(100);
        txaBeskrivelse.setMaxWidth(180);
        txaBeskrivelse.setMaxHeight(100);
        GridPane.setValignment(txaBeskrivelse, VPos.TOP);
        this.add(txaBeskrivelse, 1, 6, 1, 2);

        Label lblVandkilde = new Label("Vandkilde");
        this.add(lblVandkilde, 2, 1);

        ComboBox<String> cbxVandKilde = new ComboBox<>();
        GridPane.setValignment(cbxVandKilde, VPos.TOP);
        cbxVandKilde.setMinWidth(150);
        cbxVandKilde.setMaxWidth(150);
        this.add(cbxVandKilde, 2,2);

        Label lblBetegnelse = new Label("Betegnelse");
        this.add(lblBetegnelse, 2, 3);

        ComboBox<Betegnelse> cbxBetegnelse = new ComboBox<>();
        GridPane.setValignment(cbxBetegnelse, VPos.TOP);
        cbxBetegnelse.setMinWidth(150);
        cbxBetegnelse.setMaxWidth(150);
        this.add(cbxBetegnelse, 2,4);

        Label lblHistorik = new Label("Historik");
        this.add(lblHistorik, 3, 0);

        TextArea txaHistorik = new TextArea();
        txaHistorik.setMinWidth(200);
        //txaHistorik.setMinHeight(300);
        txaHistorik.setMaxWidth(200);
        //txaHistorik.setMaxHeight(300);
        this.add(txaHistorik, 3, 1, 1 , 6);


    }


}
