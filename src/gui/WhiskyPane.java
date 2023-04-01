package gui;

import application.model.Betegnelse;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;


public class WhiskyPane extends GridPane {
    private final ComboBox<Betegnelse> cbxBetegnelse;

    public WhiskyPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblVandILiter = new Label("Vand (Liter)");
        this.add(lblVandILiter, 0, 0);

        TextField txfVandILiter = new TextField();
        this.add(txfVandILiter, 0, 1);

        Label lblAlkoholprocent = new Label("Alkoholprocent");
        this.add(lblAlkoholprocent, 0, 2);

        TextField txfAlkoholprocent = new TextField();
        this.add(txfAlkoholprocent, 0, 3);

        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 0, 4);

        TextField txfBeskrivelse = new TextField();
        this.add(txfBeskrivelse, 0, 5);

        Label lblVandkilde = new Label("Vandkilde");
        this.add(lblVandkilde, 1, 0);

        TextField txfVandkilde = new TextField();
        this.add(txfVandkilde, 1, 1);

        Label lblBetegnelse = new Label("Betegnelse");
        this.add(lblBetegnelse, 1, 2);

        cbxBetegnelse = new ComboBox<>();
        GridPane.setValignment(cbxBetegnelse, VPos.TOP);
        cbxBetegnelse.setMinWidth(150);
        cbxBetegnelse.setMaxWidth(150);
        this.add(cbxBetegnelse, 1,3);


    }


}
