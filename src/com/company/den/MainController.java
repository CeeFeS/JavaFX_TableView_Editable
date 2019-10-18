package com.company.den;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Für ComboBoxen
 * https://www.youtube.com/watch?v=pi5dSeC0GBs
 */

public class MainController {

    private TableColumn col_nr;

    private TableColumn col_schrank1;

    private TableColumn col_geraet1;

    private TableColumn col_slot;

    private TableColumn col_nach;

    private TableColumn col_port;

    private TableColumn col_geraet2;

    private TableColumn col_schrank2;

    private TableColumn col_laenge;


    private TableColumn col_bemerkung;
    private TableColumn col_fiber_type;
    private TableColumn col_anbindung;

    @FXML
    private Button hinzufuegen;
    @FXML
    private Button ausgabe;
    @FXML
    private Button anwenden;
    @FXML
    private TableView tabelle;
    @FXML
    private TextArea verkabelung_text;

    private ArrayList<Verbindung> routerworkpack_verbindungen;

    private int nr;

    public MainController() {
    }

    @FXML
    private void initialize() {
        anwenden.setText("\u2BA8");
        this.nr = 1;
        spalten_vorbereiten();
        routerworkpack_verbindungen = new ArrayList<>();

        hinzufuegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buildalleZeilen();
                ObservableList<Verbindung> list = getVerbindungList();
                tabelle.setItems(list);
                nr++;
            }
        });

        anwenden.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                verkabelung_text_setzen();
            }
        });

        ausgabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("=======================================================================================================================");
                for (Verbindung verbindung : routerworkpack_verbindungen)
                    System.out.println(verbindung);
            }
        });
/**Zellen die als CHoicebox dargestellt werden sollen */
        col_laenge.setCellFactory(column -> new Tabelle_ChoiceBox("laenge"));
        col_slot.setCellFactory(column -> new Tabelle_ChoiceBox("slot"));
        col_port.setCellFactory(column -> new Tabelle_ChoiceBox("port"));
        col_geraet1.setCellFactory(column -> new Tabelle_ChoiceBox("geraet1"));
        col_geraet2.setCellFactory(column -> new Tabelle_ChoiceBox("geraet2"));
        col_anbindung.setCellFactory(column -> new Tabelle_ChoiceBox("anbindung"));

        /**Zellen, die frei editierbar sein sollen*/
        editierbareZellen(col_schrank1, "schrank1");
        editierbareZellen(col_schrank2, "schrank2");
        editierbareZellen(col_bemerkung, "bemerkung");


        tabelle.getColumns().addAll(col_nr, col_schrank1, col_geraet1, col_slot, col_nach, col_port, col_geraet2, col_schrank2, col_laenge, col_bemerkung, col_fiber_type, col_anbindung);


    }


    private void editierbareZellen(TableColumn tableColumn, String attribut) {

        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Verbindung, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Verbindung, String> t) {

                        if (attribut.equals("schrank1")) {
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setSchrank(t.getNewValue());
                        } else if (attribut.equals("schrank2")) {
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setSchrank2(t.getNewValue());
                        } else if (attribut.equals("bemerkung")) {
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setBemerkung(t.getNewValue());
                        }

                    }
                }
        );
// Die Tabelle muss zum Editieren freigeschaltet werden.


        tabelle.setEditable(true);
    }

    private void buildalleZeilen() {
        col_bemerkung.setCellFactory(TextFieldTableCell.<Verbindung>forTableColumn());

        col_bemerkung.setMinWidth(200);

        // On Cell edit commit (for FullName column)


        Verbindung verbindung = new Verbindung(this.nr, "(auswählen)", "(auswählen)", "(auswählen)", "(auswählen)", "(auswählen)", "(auswählen)", "(auswählen)", "(hinzufügen)", "", "keine Anbindung");
        routerworkpack_verbindungen.add(verbindung);
    }

    private ObservableList<Verbindung> getVerbindungList() {
        ObservableList<Verbindung> list = FXCollections.observableArrayList(routerworkpack_verbindungen);
        return list;
    }

    private void verkabelung_text_setzen() {
        String gerät_anbindung1 = "XXXX";
        String gerät_anbindung2 = "XXXX";
        String slot_anbindung1 = "XXXX";
        String slot_anbindung2 = "XXXX";
        String nenr_anbindung2 = "XXXX";


        String port_anbindung2 = "XXXX";
        String schrank_anbindung1 = "XXXX";
        String schrank_anbindung2 = "XXXX";
        String nenr_anbindung1 = "XXXX";
        String port_anbindung1 = "XXXX";


        for (Verbindung verbindung : routerworkpack_verbindungen) {
            if (verbindung.getAnbindung().equals("1. Anbindung")) {
                gerät_anbindung1 = verbindung.getGeraet1();
                slot_anbindung1 = verbindung.getSlot();
                schrank_anbindung1 = verbindung.getSchrank();
                port_anbindung1 = verbindung.getPort();

            } else if (verbindung.getAnbindung().equals("2. Anbindung")) {
                gerät_anbindung2 = verbindung.getGeraet1();
                slot_anbindung2 = verbindung.getSlot();
                schrank_anbindung2 = verbindung.getSchrank();
                port_anbindung2 = verbindung.getPort();
            }
        }

        verkabelung_text.setText("Erste Anbindung von Router " + gerät_anbindung1 + " Port " + slot_anbindung1 + " auf den " + schrank_anbindung1 + " " + nenr_anbindung1 + " Port " + port_anbindung1 + " über\n" +
                "die DF XXXX zu Standort XXXX auf den XXXX XXXX Port XXXX zum Router XXXX\n" +
                "Port XXXX.\n\n" +
                "Zweite Anbindung von Router " + gerät_anbindung2 + " Port " + slot_anbindung2 + " auf den " + schrank_anbindung2 + " " + nenr_anbindung2 + " Port " + port_anbindung2 + " über die\n" +
                "DF XXXX zu Standort XXXX auf den XXXX XXXX Port XXXX zum Router XXXX Port\n" +
                "XXXX.");
    }

    private void spalten_vorbereiten() {

        col_nr = new TableColumn<Verbindung, Integer>("Nr.");
        col_nr.setPrefWidth(25);
        col_schrank1 = new TableColumn<Verbindung, String>("Schrank /\nFootprint");
        col_geraet1 = new TableColumn<Verbindung, String>("Gerät");
        col_slot = new TableColumn<Verbindung, String>("Slot /\nPort");
        col_port = new TableColumn<Verbindung, String>("Port");
        col_nach = new TableColumn<Verbindung, String>("Nach");
        col_nach.setPrefWidth(50);
        col_geraet2 = new TableColumn<Verbindung, String>("Gerät");
        col_schrank2 = new TableColumn<Verbindung, String>("Schrank /\nFootprint");
        col_laenge = new TableColumn<Verbindung, String>("Länge");

        col_bemerkung = new TableColumn<Verbindung, String>("Bemerkung");
        col_bemerkung.setPrefWidth(150);
        col_anbindung = new TableColumn<Verbindung, String>("Anbindung");
        col_fiber_type = new TableColumn<Verbindung, String>("Fiber Type");


        col_nr.setCellValueFactory(new PropertyValueFactory<>("nr"));
        col_schrank1.setCellValueFactory(new PropertyValueFactory<>("schrank"));
        col_geraet1.setCellValueFactory(new PropertyValueFactory<>("geraet1"));
        col_slot.setCellValueFactory(new PropertyValueFactory<>("slot"));
        col_port.setCellValueFactory(new PropertyValueFactory<>("port"));
        col_nach.setCellValueFactory(new PropertyValueFactory<>("nach"));
        col_geraet2.setCellValueFactory(new PropertyValueFactory<>("geraet2"));
        col_schrank2.setCellValueFactory(new PropertyValueFactory<>("schrank2"));
        col_laenge.setCellValueFactory(new PropertyValueFactory<>("laenge"));
        col_bemerkung.setCellValueFactory(new PropertyValueFactory<>("bemerkung"));
        col_anbindung.setCellValueFactory(new PropertyValueFactory<>("anbindung"));
        col_fiber_type.setCellValueFactory(new PropertyValueFactory<>("fiber_type"));

    }

    private class Tabelle_ChoiceBox extends TableCell<Verbindung, String> {
        ChoiceBox<String> choice;

        private String attribut = "";

        public Tabelle_ChoiceBox(String attribut) {
            this.attribut = attribut;

            choice = choiceBoxenSetzen(attribut);
            choice.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> {
                String value = choice.getItems().get(newValue.intValue());
                processEdit(value);
            });

        }


        public ChoiceBox choiceBoxenSetzen(String attribut) {
            ChoiceBox choiceBox = new ChoiceBox();
            String jsonobject_name = "";
            if (attribut.equals("laenge")) {
                jsonobject_name = "jverkabelungLaenge";
            } else if (attribut.equals("slot")) {
                jsonobject_name = "jverkabelungSlotPort";
            } else if (attribut.equals("port")) {
                jsonobject_name = "jverkabelungPatchfeld";
            } else if (attribut.equals("geraet1")) {
                jsonobject_name = "jverkabelungGeraet";
            } else if (attribut.equals("geraet2")) {
                jsonobject_name = "jverkabelungBucht";
            } else if (attribut.equals("anbindung")) {
                choiceBox.getItems().addAll("1. Anbindung", "2. Anbindung", "Keine Anbindung");
            }
            try {

                JSONParser parser = new JSONParser();
                Object object = parser.parse(new FileReader("CheckBoxInhalte.json"));
                JSONObject jsonObject = (JSONObject) object;

                JSONArray jsonArray = (JSONArray) jsonObject.get(jsonobject_name);
                for (Object newObject : jsonArray) {
                    JSONObject newJsonObject = (JSONObject) newObject;
                    choiceBox.getItems().addAll(newJsonObject.get("wert").toString());
                }
            } catch (Exception ex) {
            }
            return choiceBox;
        }

        private void processEdit(String value) {
            commitEdit(value);
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem());
            setGraphic(null);
        }

        @Override
        public void commitEdit(String value) {
            super.commitEdit(value);
            if (attribut.equals("laenge")) {
                ((Verbindung) this.getTableRow().getItem()).setLaenge(value);
            } else if (attribut.equals("port")) {
                ((Verbindung) this.getTableRow().getItem()).setPort(value);
            } else if (attribut.equals("slot")) {
                ((Verbindung) this.getTableRow().getItem()).setSlot(value);
            } else if (attribut.equals("geraet2")) {
                ((Verbindung) this.getTableRow().getItem()).setGeraet2(value);
            } else if (attribut.equals("anbindung")) {
                ((Verbindung) this.getTableRow().getItem()).setAnbindung(value);
            } else if (attribut.equals("geraet1")) {
                ((Verbindung) this.getTableRow().getItem()).setGeraet1(value);
            }
            setGraphic(null);
        }

        @Override
        public void startEdit() {
            super.startEdit();
            String value = getItem();
            if (value != null) {
                setGraphic(choice);
                setText(null);
            }
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setText(null);

            } else {
                setText(item);
            }
        }

    }
}
