package gui.gui_lagerstyring;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class FlaskePane extends GridPane {
    private TextArea txaBeskrivelse;
    private TextArea txaHistorik;



    public FlaskePane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setAlignment(Pos.CENTER);


        Label lblFlaske = new Label("Flasker");
        this.add(lblFlaske, 0, 0);

        ListView lvwFlaske = new ListView();
        lvwFlaske.setMinWidth(240);
        lvwFlaske.setMinHeight(300);
        lvwFlaske.setMaxWidth(240);
        this.add(lvwFlaske, 0, 1);




        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse, 1, 0);

        txaBeskrivelse = new TextArea();
        txaBeskrivelse.setMinWidth(120);
        txaBeskrivelse.setMinHeight(300);
        txaBeskrivelse.setMaxWidth(200);
        txaBeskrivelse.setDisable(true);

        this.add(txaBeskrivelse, 1, 1);

        Label lblHistorik = new Label("Historik");
        this.add(lblHistorik, 2, 0);

        txaHistorik = new TextArea();
        txaHistorik.setMinWidth(120);
        txaHistorik.setMinHeight(300);
        txaHistorik.setMaxWidth(200);
        txaHistorik.setDisable(true);
        this.add(txaHistorik, 2, 1);

    }


}
