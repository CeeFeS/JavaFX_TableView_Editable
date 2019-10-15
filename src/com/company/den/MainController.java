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

    private TableColumn col_geraet;

    private TableColumn col_slot;

    private TableColumn col_nach;

    private TableColumn col_port;

    private TableColumn col_bucht;

    private TableColumn col_schrank2;

    private TableColumn col_laenge;
    private TableColumn col_lwl;

    private TableColumn col_bemerkung;

    private TableColumn col_anbindung;

    @FXML
    private Button hinzufuegen;
    @FXML
    private Button ausgabe;
    @FXML
    private TableView tabelle;

    private ArrayList<Verbindung> allezeilen;

    private int nr;

    public MainController() {
    }

    @FXML
    private void initialize() {
        this.nr = 1;
        erstelleSpalten();
        spalten_vorbereiten();
        allezeilen = new ArrayList<>();

        hinzufuegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buildalleZeilen();
                ObservableList<Verbindung> list = getVerbindungList();
                tabelle.setItems(list);
                nr++;
            }
        });

        ausgabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("=======================================================================================================================");
                for (Verbindung verbindung : allezeilen)
                    System.out.println(verbindung);
            }
        });
/**Zellen die als CHoicebox dargestellt werden sollen */
        col_laenge.setCellFactory(column -> new Tabelle_ChoiceBox("laenge"));
        col_lwl.setCellFactory(column -> new Tabelle_ChoiceBox("lwl"));
        col_slot.setCellFactory(column -> new Tabelle_ChoiceBox("slot"));
        col_port.setCellFactory(column -> new Tabelle_ChoiceBox("port"));
        col_bucht.setCellFactory(column -> new Tabelle_ChoiceBox("bucht"));
        col_anbindung.setCellFactory(column -> new Tabelle_ChoiceBox("anbindung"));

        /**Zellen, die frei editierbar sein sollen*/
        editierbareZellen(col_schrank1, "schrank1");
        editierbareZellen(col_schrank2, "schrank2");
        editierbareZellen(col_bemerkung, "bemerkung");


        tabelle.getColumns().addAll(col_nr, col_schrank1, col_geraet, col_slot, col_nach, col_port, col_bucht, col_schrank2, col_laenge, col_lwl, col_bemerkung, col_anbindung);


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
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setFootprint(t.getNewValue());
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


        Verbindung verbindung = new Verbindung(this.nr, "DC1 ext", "Gerät", "", "", "", "schrank", "", "", " ", "");
        allezeilen.add(verbindung);
    }

    private ObservableList<Verbindung> getVerbindungList() {
        ObservableList<Verbindung> list = FXCollections.observableArrayList(allezeilen);
        return list;
    }

    private void spalten_vorbereiten() {

        col_nr = new TableColumn<Verbindung, Integer>("Nr.");
        col_schrank1 = new TableColumn<Verbindung, String>("Schrank");
        col_geraet = new TableColumn<Verbindung, String>("Gerät");
        col_slot = new TableColumn<Verbindung, String>("Slot");
        col_port = new TableColumn<Verbindung, String>("Port");
        col_nach = new TableColumn<Verbindung, String>("nach");
        col_bucht = new TableColumn<Verbindung, String>("Bucht");
        col_schrank2 = new TableColumn<Verbindung, String>("Footprint");
        col_laenge = new TableColumn<Verbindung, String>("Länge");
        col_lwl = new TableColumn<Verbindung, String>("LWL");
        col_bemerkung = new TableColumn<Verbindung, String>("Bemerkung");
        col_anbindung = new TableColumn<Verbindung, String>("Anbindung");


        col_nr.setCellValueFactory(new PropertyValueFactory<>("nr"));
        col_schrank1.setCellValueFactory(new PropertyValueFactory<>("schrank"));
        col_geraet.setCellValueFactory(new PropertyValueFactory<>("geraet"));
        col_slot.setCellValueFactory(new PropertyValueFactory<>("slot"));
        col_port.setCellValueFactory(new PropertyValueFactory<>("port"));
        col_nach.setCellValueFactory(new PropertyValueFactory<>("nach"));
        col_bucht.setCellValueFactory(new PropertyValueFactory<>("bucht"));
        col_schrank2.setCellValueFactory(new PropertyValueFactory<>("footprint"));
        col_laenge.setCellValueFactory(new PropertyValueFactory<>("laenge"));
        col_lwl.setCellValueFactory(new PropertyValueFactory<>("lwl"));
        col_bemerkung.setCellValueFactory(new PropertyValueFactory<>("bemerkung"));
        col_anbindung.setCellValueFactory(new PropertyValueFactory<>("anbindung"));

    }

    public void erstelleSpalten() {
        col_nr = new TableColumn<Verbindung, Integer>();
        col_schrank1 = new TableColumn<Verbindung, String>();
        col_geraet = new TableColumn<Verbindung, String>();
        col_slot = new TableColumn<Verbindung, String>();
        col_port = new TableColumn<Verbindung, String>();
        col_nach = new TableColumn<Verbindung, String>();
        col_bucht = new TableColumn<Verbindung, String>();
        col_schrank2 = new TableColumn<Verbindung, String>();
        col_laenge = new TableColumn<Verbindung, String>();
        col_lwl = new TableColumn<Verbindung, String>();
        col_bemerkung = new TableColumn<Verbindung, String>();
        col_anbindung = new TableColumn<Verbindung, String>();


    }

    private class Tabelle_ChoiceBox extends TableCell<Verbindung, String> {

        ChoiceBox<String> buySellBox = new ChoiceBox<>();
        private String attribut = "";

        public Tabelle_ChoiceBox(String attribut) {
            this.attribut = attribut;
            buySellBox.getItems().addAll("Testelement1", "Testelement2", "Testelement3");
            buySellBox.getSelectionModel().selectedIndexProperty().addListener((obs, oldValue, newValue) -> {
                String value = buySellBox.getItems().get(newValue.intValue());
                processEdit(value);
            });

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
            } else if (attribut.equals("lwl")) {
                ((Verbindung) this.getTableRow().getItem()).setLwl(value);
            } else if (attribut.equals("port")) {
                ((Verbindung) this.getTableRow().getItem()).setPort(value);
            } else if (attribut.equals("slot")) {
                ((Verbindung) this.getTableRow().getItem()).setSlot(value);
            } else if (attribut.equals("bucht")) {
                ((Verbindung) this.getTableRow().getItem()).setBucht(value);
            } else if (attribut.equals("anbindung")) {
                ((Verbindung) this.getTableRow().getItem()).setAnbindung(value);
            }
            setGraphic(null);
        }

        @Override
        public void startEdit() {
            super.startEdit();
            String value = getItem();
            if (value != null) {
                setGraphic(buySellBox);
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
