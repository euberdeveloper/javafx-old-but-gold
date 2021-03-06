package unitn.euber.tastomancante;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;

/**
 * Button wich compose the "tastierino"
 * @author Eugenio Vinicio Berretta
 */
public class NumberButton extends StackPane implements EventHandler<ActionEvent> {
    
    // Static fields for the ui
    private static final String CSS_CLASS = "button-raised";
    private static final float SIZE = 200.0f;
    private static final float PADDING = 3.0f;
    
    /** The number of button instantiated */
    public static int N_BUTTONS = 0;
    
    // The number of the button
    private final int n;
    // The parent instance
    private final Tastierino parent;
    
    // The index of the button
    private int index;
    
    // Creates the button
    private JFXButton createButton() {
        JFXButton button = new JFXButton("" + (this.n + 1));
        button.getStyleClass().add(NumberButton.CSS_CLASS);
        button.setPrefSize(NumberButton.SIZE, NumberButton.SIZE);
        button.setOnAction(this);
        return button;
    }
    
    // Inits the ui
    private void setStyle() {
        this.setPadding(new Insets(NumberButton.PADDING));
    }
    
    /**
     * Constructor of the class NumberButton
     * @param n the number of the button
     * @param index the index (position) of the button
     * @param parent the parent instance
     */
    public NumberButton(int n, int index, Tastierino parent) {
        super();
        this.n = n;
        this.index = index;
        this.parent = parent;
        this.setStyle();
        this.getChildren().add(this.createButton());
    }
    
    /**
     * Setter of the field index
     * @param index the new value of the field index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Overriding of the method handle. It tries to move the clicked button
     * @param event the event
     */
    @Override
    public void handle(ActionEvent event) {
        this.parent.move(this.index, event);
    }
    
}
