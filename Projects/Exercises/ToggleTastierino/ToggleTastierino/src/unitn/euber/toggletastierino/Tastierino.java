/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.toggletastierino;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * The class of the tastierino
 * @author Eugenio Vinicio Berretta
 */
public class Tastierino extends GridPane implements EventHandler<KeyEvent> {

    // Static fields for the ui and settings
    private static final int N_BUTTONS = 9;
    private static final int N_COLUMNS = 3;
    private static final int N_ROWS = 3;
    private static final float PADDING = 15.0f;

    // The buttons
    private final NumberButton[] buttons = new NumberButton[Tastierino.N_BUTTONS];
    // The parent instance
    private final Container parent;

    // Initializes the ui
    private void setStyle() {
        // Adds the column constraints
        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f / Tastierino.N_COLUMNS);
        this.getColumnConstraints().addAll(colConst, colConst, colConst);
        // Adds the rows constraints
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPercentHeight(100.0f / Tastierino.N_ROWS);
        this.getRowConstraints().addAll(rowConst, rowConst, rowConst);
        // Sets the padding
        this.setPadding(new Insets(Tastierino.PADDING));
    }
    
    // Adds a node to the GridPane
    private void add(int index, Node node) {
        this.add(node, index % Tastierino.N_COLUMNS, index / Tastierino.N_ROWS);
    }
    
    // Removes a node from the GridPane
    private void del(int index) {
        this.getChildren().remove(this.buttons[index]);
    }
    
    // Adds all the given nodes to the GridPane
    private void addAll(Node[] nodes) {
        for(int i = 0; i < nodes.length; i++) {
            this.add(i, nodes[i]);
        }
    }

    // Creates and adds the buttons
    private void addButtons() {
        for (int i = 0; i < Tastierino.N_BUTTONS; i++) {
            this.buttons[i] = new NumberButton(i, this);
        }
        this.addAll(this.buttons);
    }

    /**
     * Constructor of the class Tastierino
     * @param parent the parent instance
     */
    public Tastierino(Container parent) {
        super();
        this.parent = parent;
        this.setStyle();
        this.addButtons();
        this.setOnKeyTyped(this);
    }

    /**
     * Overriding of the handle method (key event). Shows / hides the button with the typed key
     * @param event the event
     */
    @Override
    public void handle(KeyEvent event) {
        final String typed = event.getCharacter();
        try {
            final int n = Integer.parseInt(typed) - 1;
            this.toggle(n, event);
        } catch (Exception exception) {
            this.parent.addAlert();
            event.consume();
        } finally {
            this.requestFocus();
        }
    }
    
    /**
     * Shows/Hides the button with the given number
     * @param n the number of the butotn
     * @param event the event
     */
    public void toggle(int n, Event event) {
        if (0 <= n && n < Tastierino.N_BUTTONS) {
            if (this.getChildren().contains(this.buttons[n])) {
                this.del(n);
            } else {
                this.add(n, this.buttons[n]);
            }
        } else if (event != null) {
            event.consume();
        }
    }

}
