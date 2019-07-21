package unitn.euber.tastomancante;

import java.util.Random;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    
    // The index of the removed button
    private int removed;

    // Initializes the style
    private void setStyle() {
        // Adds column constraints to the GridPane
        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f / Tastierino.N_COLUMNS);
        this.getColumnConstraints().addAll(colConst, colConst, colConst);
        // Adds row constraints to the GridPane
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPercentHeight(100.0f / Tastierino.N_ROWS);
        this.getRowConstraints().addAll(rowConst, rowConst, rowConst);
        // Sets the padding
        this.setPadding(new Insets(Tastierino.PADDING));
    }
    
    // Generates and adds the buttons
    private void generateButtons() {
        // Picks randomly the removed index
        this.removed = (new Random()).nextInt(Tastierino.N_BUTTONS);
        // Generates the buttons
        for(int i = 0; i < Tastierino.N_BUTTONS; i++) {
            if(i != this.removed) {
                this.buttons[i] = new NumberButton(NumberButton.N_BUTTONS++, i, this);
            }
        }
        // Updates the ui
        this.update();
    }
    
    // Add a button in a certain index
    private void add(int index, NumberButton button) {
        this.add(button, index % Tastierino.N_COLUMNS, index / Tastierino.N_ROWS);
    }
    
    // Moves the button in position x to y
    private void swap(int x, int y) {
        this.buttons[x].setIndex(y);
        this.buttons[y] = this.buttons[x];
        this.buttons[x] = null;
        this.update();
    }
    
    // Updates the ui
    private void update() {
        this.getChildren().clear();
        for(int i = 0; i < this.buttons.length; i++) {
            if(this.buttons[i] != null) {
                this.add(i, this.buttons[i]);
            }
        }
    }

    // Check if the given number is in the range of buttons number
    private boolean inRange(int n) {
        return 0 <= n && n < Tastierino.N_BUTTONS;
    }
    
    // Checks if the given number can be horizontally moved
    private boolean horizontalRange(int n) {
        final int resto = this.removed % Tastierino.N_COLUMNS;
        if(resto == Tastierino.N_COLUMNS) {
            return this.removed - n == 1;
        }
        else if(resto == 0) {
            return n - this.removed == 1;
        } 
        else {
            return Math.abs(this.removed - n) == 1;
        }
    }
    
    // Checks if the given number can be vertically moved
    private boolean verticalRange(int n) {
        return Math.abs(this.removed - n) == Tastierino.N_COLUMNS;
    }

    /**
     * Constructor of the class Tastierino
     * @param parent the parent instance
     */
    public Tastierino(Container parent) {
        super();
        this.parent = parent;
        this.setStyle();
        this.generateButtons();
        this.setOnKeyTyped(this);
    }

    /**
     * Overriding of the handle method. Called on a key typed.
     */
    @Override
    public void handle(KeyEvent event) {
        // Gets the typed char
        final String typed = event.getCharacter();
        // If it is a number moves the button in that number
        try {
            final int n = Integer.parseInt(typed) - 1;
            this.move(n, event);
        }
        // If it is not a number shows the alert and stops the event
        catch (Exception exception) {
            this.parent.addAlert();
            event.consume();
        }
        // It always gets the focus back
        finally {
            this.requestFocus();
        }
    }
    
    /**
     * It tries to move the button in the given position
     * @param n the position of the button
     * @param event the event
     */
    public void move(int n, Event event) {
        // If it can be moved it will be moved
        if (this.inRange(n) && (this.verticalRange(n) || this.horizontalRange(n))) {
            this.swap(n, this.removed);
            this.removed = n;
        }
        // Otherwise consumes the event 
        else if (event != null) {
            event.consume();
        }
        // It always gets the focus back
        this.requestFocus();
    }

}
