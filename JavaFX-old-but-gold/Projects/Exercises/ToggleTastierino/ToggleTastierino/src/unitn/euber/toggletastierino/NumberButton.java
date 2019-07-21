package unitn.euber.toggletastierino;


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
    
    // Static fields for the ui and settings
    private static final String CSS_CLASS = "button-raised";
    private static final float SIZE = 200.0f;
    private static final float PADDING = 3.0f;
    
    // The number of the button
    private final int n;
    // The parent instances
    private final Tastierino parent;
    
    // Creates the button and returns it
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
     * @param parent the parent instance
     */
    public NumberButton(int n, Tastierino parent) {
        super();
        this.n = n;
        this.parent = parent;
        this.setStyle();
        this.getChildren().add(this.createButton());
    }

    /**
     * Overriding of the handle event (button clicked). Hides the button
     * @param event the event
     */
    @Override
    public void handle(ActionEvent event) {
        this.parent.toggle(this.n, event);
    }
    
}
