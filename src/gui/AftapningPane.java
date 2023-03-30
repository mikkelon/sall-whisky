package gui;

import application.model.Aftapning;
import application.model.Destillat;
import application.model.Fad;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class AftapningPane extends GridPane {

    private final ListView<Fad> lvwDestillater;
    private final DatePicker datePicker;
    private final TextField txfAftappetAf;
    private final TextField txfMængde;
    private final ListView<Aftapning> lvwAftapninger;





    public AftapningPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);



        Label lblFade = new Label("Fade");
        this.add(lblFade, 0,0,2,1);
        GridPane.setHalignment(lblFade, HPos.CENTER);

        lvwDestillater = new ListView<>();
        lvwDestillater.setMinWidth(100);
        lvwDestillater.setMaxWidth(250);
        lvwDestillater.setMinHeight(300);
        this.add(lvwDestillater, 0, 1, 2, 6);


        Button btnRegistrer = new Button("Registrer alkoholprocent");
        this.add(btnRegistrer,0,7,2,1 );
        GridPane.setHalignment(btnRegistrer, HPos.CENTER);



        Button vælg = new Button("Vælg");
        this.add(vælg, 2,1,2,1 );
        GridPane.setHalignment(vælg, HPos.CENTER);

        datePicker = new DatePicker();
        this.add(datePicker, 2,2,2,1);
        GridPane.setHalignment(datePicker, HPos.CENTER);

        txfAftappetAf = new TextField();
        this.add(txfAftappetAf, 2,3,2,1);
        GridPane.setHalignment(txfAftappetAf, HPos.CENTER);

        txfMængde = new TextField();
        this.add(txfMængde, 2,4,2,1);
        GridPane.setHalignment(txfMængde, HPos.CENTER);


        lvwAftapninger = new ListView<>();
        lvwAftapninger.setMinWidth(250);
        lvwAftapninger.setMaxWidth(250);
        lvwAftapninger.setMinHeight(300);
        this.add(lvwAftapninger, 4, 1, 2, 6);


        Label lblAftapninger = new Label("Aftapninger");
        this.add(lblAftapninger, 4,0,2,1);
        GridPane.setHalignment(lblAftapninger, HPos.CENTER);


        Button btnNulstil = new Button("Nulstil");
        this.add(btnNulstil, 4,7,2,1);
        GridPane.setHalignment(btnNulstil, HPos.CENTER);

        Separator separator = new Separator(Orientation.VERTICAL);
        this.add(separator, 7,0,1,8);


    }
}
