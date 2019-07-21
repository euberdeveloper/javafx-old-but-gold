package unitn.euber.cargame;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Class of a generic terreno
 * @author Eugenio Vinicio Berretta
 */
public abstract class Terreno extends StackPane implements EventHandler<MouseEvent> {
 
    // Static fields to specify the ui
    private static final float SIZE = 100.0f;
    private static final Color BORDER_COLOR = Color.BLACK;
    
    // The parent instance
    protected final ParkingArea parent;
    
    /**
     * Constructor of the class Terreno
     * @param parent the parent instance
     */
    Terreno(ParkingArea parent) {
        this.parent = parent;
        this.initStyle();
        this.initEvents();
    }
    
    // Initializes the ui
    private void initStyle() {
        this.setWidth(Terreno.SIZE);
        this.setHeight(Terreno.SIZE);
        this.setBorder(new Border(new BorderStroke(Terreno.BORDER_COLOR, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    // Adds the events
    private void initEvents() {
        this.setOnMouseClicked(this);
    }
    
    /**
     * Overriding of the handle method. It replaces a strada with a prato and a prato with a strada
     */
    @Override
    public void handle(MouseEvent event) {
        this.parent.replaceTerreno(this);
    }
    
}
