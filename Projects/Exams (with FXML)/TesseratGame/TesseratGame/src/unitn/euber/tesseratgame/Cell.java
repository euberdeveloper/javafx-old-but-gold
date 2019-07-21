/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.tesseratgame;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * The class of a generic cell
 * @author Eugenio Vinicio Berretta
 */
public abstract class Cell extends StackPane implements EventHandler<MouseEvent> {

    // The parent instance
    protected final TesseratGameController parent;
    // If it is active (not hidden)
    protected boolean active = false;

    // References to the ui elements
    @FXML
    protected Label text;

    /**
     * Constructor of the class Cell
     * @param parent the parent instance
     */
    public Cell(TesseratGameController parent) {
        // Assigns the parent instances
        this.parent = parent;
        // Gets the FXML layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cell.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        // Loads the FXML laout
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        // Sets the event
        setOnMouseClicked(this);
    }

    /**
     * Getter of the field active
     * @return the value of the field active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Shows the cell if it is not already active
     */
    public void scopri() {
        if (!isActive()) {
            active = true;
        }
    }

    /**
     * Cover the cell if it is not already covered
     */
    public void copri() {
        if (isActive()) {
            active = false;
            text.setText(null);
        }
    }
    
    /**
     * Overriding of the handle event (mouse click). It covers or shows the cell
     */
    @Override
    public void handle(MouseEvent event) {
        if (isActive()) {
            this.copri();
        }
        else {
            this.scopri();
        }
    }
    
    /**
     * Overriding of the toString method. It returns the cell as a string
     * @return the cell as a String
     */
    @Override
    public String toString() {
        return "Cell";
    }

}
