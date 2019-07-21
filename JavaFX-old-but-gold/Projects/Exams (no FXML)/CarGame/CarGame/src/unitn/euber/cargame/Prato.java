package unitn.euber.cargame;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Class of prato
 * @author Eugenio Vinicio Berretta
 */
public class Prato extends Terreno {
    
    // The colour of the prato
    private static final Color COLOUR = Color.GREEN;
    
    /**
     * The constructor of the class Prato
     * @param parent the parent instance
     */
    Prato(ParkingArea parent) {
        super(parent);
        this.initStyle();
    }
    
    // Initializes the style of the prato
    private void initStyle() {
        this.setBackground(new Background(new BackgroundFill(Prato.COLOUR, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
}
