package unitn.euber.toggletastierino;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * The class of the container of the game's ui
 * @author Eugenio Vinicio Berretta
 */
public class Container extends StackPane {
    
    // The alert
    private final StackPane alert;
    
    // Creates the alert and returns it
    private StackPane createAlert() {
        StackPane pane = new StackPane(new Label("Premere un numero da 1 a 9"));
        pane.getStyleClass().add("alert");
        pane.setOnMouseClicked((MouseEvent event) -> {
            this.getChildren().remove(this.alert);
        });
        return pane;
    }
    
    // Creates the tastierino
    private Tastierino createTastierino(Container parent) {
        Tastierino tastierino = new Tastierino(parent);
        return tastierino;
    }
    
    /**
     * Constructor of the class container
     */
    public Container() {
        super();
        this.alert = this.createAlert();
        this.getChildren().add(this.createTastierino(this));
    }
    
    /**
     * Shows the alert
     */
    public void addAlert() {
        if(!this.getChildren().contains(this.alert)) {
            this.getChildren().add(this.alert);
            StackPane.setAlignment(this.alert, Pos.TOP_CENTER);
        }
    }
    
}
