package unitn.euber.cargame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class of a car
 * @author Eugenio Vinicio Berretta
 */
public class Car extends Circle {
    
    // Static fields to specify the ui
    private static final double RADIUS = 30.0;
    private static final Color COLOUR = Color.RED;
    
    // Static field to count the number of istances and get a unique id
    private static int INSTANCES_COUNT = 0;
    
    // Unique id
    private final int id = ++Car.INSTANCES_COUNT;
    
    /**
     * Constructor of the class Car
     */
    Car() {
        // Initializes the style
        this.initStyle();
    }
    
    // Initializes the style
    private void initStyle() {
        this.setRadius(Car.RADIUS);
        this.setFill(Car.COLOUR);
    }
    
    // Gets the id
    public int getCarId() {
        return this.id;
    }
    
}
