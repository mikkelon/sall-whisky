package gui;

import application.model.Aftapning;
import application.model.Destillat;
import application.model.Fad;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
        this.setAlignment(Pos.CENTER);




        Label lblFade = new Label("Fade");
        this.add(lblFade, 0,0,2,1);
        GridPane.setHalignment(lblFade, HPos.CENTER);

        lvwDestillater = new ListView<>();
        lvwDestillater.setMinWidth(200);
        lvwDestillater.setMinHeight(300);
        this.add(lvwDestillater, 0, 1, 2, 12);


        Button btnRegistrer = new Button("Registrer alkoholprocent");
        this.add(btnRegistrer,0,13,2,1 );
        GridPane.setHalignment(btnRegistrer, HPos.CENTER);


        Button vælg = new Button("Vælg");
        this.add(vælg, 2,1,2,1 );
        GridPane.setHalignment(vælg, HPos.CENTER);

        Label lblaftapningsDato = new Label("Aftapningsdato");
        this.add(lblaftapningsDato, 2,2,2,1);
        GridPane.setHalignment(lblaftapningsDato, HPos.CENTER);

        datePicker = new DatePicker();
        this.add(datePicker, 2,3,2,1);
        GridPane.setHalignment(datePicker, HPos.CENTER);
        datePicker.setMaxSize(100,50 );

        Label lblAftappetAf = new Label("Aftappet af");
        this.add(lblAftappetAf, 2,4,2,1);
        GridPane.setHalignment(lblAftappetAf, HPos.CENTER);

        txfAftappetAf = new TextField();
        this.add(txfAftappetAf, 2,5,2,1);
        GridPane.setHalignment(txfAftappetAf, HPos.CENTER);
        txfAftappetAf.setMaxSize(100,50);

        Label lblMængde = new Label("Mængde (liter)");
        this.add(lblMængde, 2,6,2,1);
        GridPane.setHalignment(lblMængde, HPos.CENTER);

        txfMængde = new TextField();
        this.add(txfMængde, 2,7,2,1);
        GridPane.setHalignment(txfMængde, HPos.CENTER);
        txfMængde.setMaxSize(100,50);


        lvwAftapninger = new ListView<>();
        lvwAftapninger.setMinWidth(200);
        lvwAftapninger.setMinHeight(300);
        this.add(lvwAftapninger, 4, 1, 2, 12);


        Label lblAftapninger = new Label("Aftapninger");
        this.add(lblAftapninger, 4,0,2,1);
        GridPane.setHalignment(lblAftapninger, HPos.CENTER);


        Button btnNulstil = new Button("Nulstil");
        this.add(btnNulstil, 4,13,2,1);
        GridPane.setHalignment(btnNulstil, HPos.CENTER);


        Button btnFravælg = new Button("Fravælg");
        this.add(btnFravælg, 2,8,2,1);
        GridPane.setHalignment(btnFravælg, HPos.CENTER);

        Separator separator = new Separator(Orientation.VERTICAL);
        this.add(separator, 6,0,1,14);


        Label lblWhiskyProdukt = new Label("Whisky produkt");
        this.add(lblWhiskyProdukt, 8,0,1,1);
        GridPane.setHalignment(lblWhiskyProdukt, HPos.CENTER);


        Label lblVolume = new Label("Total volumen (liter)");
        this.add(lblVolume, 7,1,1,1);
        GridPane.setHalignment(lblVolume, HPos.CENTER);


        TextField txfVolume = new TextField();
        this.add(txfVolume, 7,2,1,1);
        GridPane.setHalignment(txfVolume, HPos.CENTER);
        txfVolume.setMaxSize(75,50);

        Label lblFlaskeStørrelse = new Label("Flaske størrelse (cl)");
        this.add(lblFlaskeStørrelse, 9,1,1,1);
        GridPane.setHalignment(lblFlaskeStørrelse, HPos.CENTER);

        TextField txfFlaskeStørrelse = new TextField();
        this.add(txfFlaskeStørrelse, 9,2,1,1);
        GridPane.setHalignment(txfFlaskeStørrelse, HPos.CENTER);
        txfFlaskeStørrelse.setMaxSize(75,50);

        Label lblAntalFlasker = new Label("Antal flasker");
        this.add(lblAntalFlasker, 8,3,1,1);
        GridPane.setHalignment(lblAntalFlasker, HPos.CENTER);

        TextField txfAntalFlasker = new TextField();
        this.add(txfAntalFlasker, 8,4,1,1);
        GridPane.setHalignment(txfAntalFlasker, HPos.CENTER);
        txfAntalFlasker.setMaxSize(75,50);


        Separator separator1 = new Separator(Orientation.HORIZONTAL);
        this.add(separator1, 7,5,3,1);


        Label lblVandILiter = new Label("Vand (liter)");
        this.add(lblVandILiter, 7,6,1,1);
        GridPane.setHalignment(lblVandILiter, HPos.CENTER);

        TextField txfVandILiter = new TextField();
        this.add(txfVandILiter, 7,7,1,1);
        GridPane.setHalignment(txfVandILiter, HPos.CENTER);
        txfVandILiter.setMaxSize(75,50);

        Label lblVandKilde = new Label("Vandkilde");
        this.add(lblVandKilde, 8,6,2,1);
        GridPane.setHalignment(lblVandKilde, HPos.CENTER);

        //Combobox hvor der skal være vandkilder
        ComboBox<String> cbxVandKilde = new ComboBox<>();
        this.add(cbxVandKilde, 8,7,2,1);
        GridPane.setHalignment(cbxVandKilde, HPos.CENTER);
        cbxVandKilde.setMinSize(120,20);

        Label lblAlkoholProcent = new Label("Alkoholprocent");
        this.add(lblAlkoholProcent, 7,8,1,1);
        GridPane.setHalignment(lblAlkoholProcent, HPos.CENTER);

        TextField txfAlkoholProcent = new TextField();
        this.add(txfAlkoholProcent, 7,9,1,1);
        GridPane.setHalignment(txfAlkoholProcent, HPos.CENTER);
        txfAlkoholProcent.setMaxSize(75,50);

        Label lblBetegnelse = new Label("Betegnelse");
        this.add(lblBetegnelse, 8,8,2,1);
        GridPane.setHalignment(lblBetegnelse, HPos.CENTER);

        ComboBox<String> cbxBetegnelse = new ComboBox<>();
        this.add(cbxBetegnelse, 8,9,2,1);
        GridPane.setHalignment(cbxBetegnelse, HPos.CENTER);
        cbxBetegnelse.setMinSize(120,20);

        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 7,10,1,1);

        TextArea txaBeskrivelse = new TextArea();
        this.add(txaBeskrivelse, 7,11,3,1);
        GridPane.setHalignment(txaBeskrivelse, HPos.CENTER);

        Button btnBekræft = new Button("Bekræft aftapning");
        this.add(btnBekræft, 7,12,3,3);
        GridPane.setHalignment(btnBekræft, HPos.CENTER);





    }
}
