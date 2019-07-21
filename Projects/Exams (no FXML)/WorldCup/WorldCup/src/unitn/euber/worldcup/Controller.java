/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class Controller extends GridPane {
    
    private static final double WIDTH = 300.0;
    private static final double HEIGHT = 200.0;
    
    private final MainView parent;
    
    private final Button firstGiornata = new Button("PRIMA GIORNATA");
    private final Button secondaGiornata = new Button("SECONDA GIORNATA");
    private final Button terzaGiornata = new Button("TERZA GIORNATA");
    private final Button quarti = new Button("QUARTI");
    private final Button semifinale = new Button("SEMIFINALE");
    private final Button finale = new Button("FINALE");
    
    Controller(MainView parent) {
        this.parent = parent;
        this.initStyle();
        this.addButtons();
    }
    
    private void add(Node button, int index) {
        super.add(button, index % 3, index / 3);
    }
    
    private void initStyle() {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100.0 / 3);
        this.getColumnConstraints().addAll(cc, cc, cc);
        
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100.0 / 2);
        this.getRowConstraints().addAll(rc, rc);
    }
    
    private void addButtons() {
        this.firstGiornata.setOnAction(event -> {
            this.nextGiornata();
            this.firstGiornata.setDisable(true);
            this.secondaGiornata.setDisable(false);
        });
        this.firstGiornata.setPrefWidth(WIDTH);
        this.firstGiornata.setPrefHeight(HEIGHT);
        this.add(new StackPane(this.firstGiornata), 0);
        this.secondaGiornata.setOnAction(event -> {
            this.nextGiornata();
            this.secondaGiornata.setDisable(true);
            this.terzaGiornata.setDisable(false);
        });
        this.secondaGiornata.setDisable(true);
        this.secondaGiornata.setPrefWidth(WIDTH);
        this.secondaGiornata.setPrefHeight(HEIGHT);
        this.add(new StackPane(this.secondaGiornata), 1);
        this.terzaGiornata.setOnAction(event -> {
            this.nextGiornata();
            this.terzaGiornata.setDisable(true);
            this.quarti.setDisable(false);
        });
        this.terzaGiornata.setDisable(true);
        this.terzaGiornata.setPrefWidth(WIDTH);
        this.terzaGiornata.setPrefHeight(HEIGHT);
        this.add(new StackPane(this.terzaGiornata), 2);
        this.quarti.setOnAction(event -> this.finish());
        this.quarti.setDisable(true);
        this.quarti.setPrefWidth(WIDTH);
        this.quarti.setPrefHeight(HEIGHT);
        this.add(new StackPane(this.quarti), 3);
        this.semifinale.setOnAction(null);
        this.semifinale.setDisable(true);
        this.semifinale.setPrefWidth(WIDTH);
        this.semifinale.setPrefHeight(HEIGHT);
        this.add(new StackPane(this.semifinale), 4);
        this.finale.setOnAction(null);
        this.finale.setDisable(true);
        this.finale.setPrefWidth(WIDTH);
        this.finale.setPrefHeight(HEIGHT);
        this.add(new StackPane(this.finale), 5);
    }
    
    private void nextGiornata() {
        this.parent.nextGiornata();
    }
    
    private void finish() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Non ancora implementato");
        alert.setHeaderText("Parte non ancora implementata");
        alert.show();
        alert.setOnHidden(event -> Platform.exit());
    }
    
}
