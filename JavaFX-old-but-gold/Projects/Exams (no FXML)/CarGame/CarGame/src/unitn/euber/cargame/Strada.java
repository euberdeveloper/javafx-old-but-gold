package unitn.euber.cargame;

import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * The class of a strada
 * @author Eugenio Vinicio Berretta
 */
public class Strada extends Terreno {
    
    // The colour of a strada
    private static final Color COLOUR = Color.GRAY;
    
    // The car in the strada
    private Car car = null;
    
    /**
     * The constructor of the class Strada
     * @param parent the parent instance
     */
    Strada(ParkingArea parent) {
        super(parent);
        this.initStyle();
    }
    
    // Initializes the ui
    private void initStyle() {
        this.setBackground(new Background(new BackgroundFill(Strada.COLOUR, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    // Creates a car if it not creted yet
    private void createCar() {
        if (this.car == null) {
            this.car = new Car();
        }
    }
    
    /**
     * @return returns true if a car is on it and false otherwise
     */
    public boolean hasCar() {
        return this.getChildren().contains(this.car);
    }
    
    /**
     * Tries to add a car on it
     * @return True if the car was added
     */
    public boolean pushCar() {
        this.createCar();
        return (this.hasCar() ? false : this.getChildren().add(this.car));
    }
    /**
     * Tries to remove a car from it
     * @return true if the car was removed
     */
    public boolean popCar() {
        return (this.hasCar() ? this.getChildren().remove(this.car) : false);
    }
    
    /**
     * @return the id of the car on the strada
     */
    public int getCarId() {
        return this.car.getCarId();
    }
    
    /**
     * Overriding of the handle method. If Add Car was selected, it tries to add a car. Otherwise, it has its default behaviour.
     * @param event the event to handle
     */
    @Override
    public void handle(MouseEvent event) {
        if (this.parent.getAddCarSelected()) {
            this.parent.setAddCarSelected(!this.pushCar());
        }
        else if (!this.hasCar()) {
            super.handle(event);
        }
    }
}
