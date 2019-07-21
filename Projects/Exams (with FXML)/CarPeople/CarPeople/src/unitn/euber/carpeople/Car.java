package unitn.euber.carpeople;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The car class
 * @author Eugenio Vinicio Berretta
 */
public class Car extends Record {

    // Set of all the instanciated cars
    private static final Set<Car> CARS_SET = new HashSet<>();
    /**  List of all the instanciated cars */
    public static final List<Car> CARS = new ArrayList<>();

    // True if the last instances was added to the set
    private static boolean LAST_ADDED;

    /**
     * Getter of the static field LAST_ADDED
     * @return true if the last instanciated car was added to the set, false otherwise
     */
    public static final boolean getLastAdded() {
        return Car.LAST_ADDED;
    }

    /**
     * Constructor of the class Car
     * @param marca the brand of the car
     * @param modello the model of the car
     * @param prezzo the price of the car
     */
    public Car(String marca, String modello, Integer prezzo) {
        // Assigns the fields (super constructor)
        super(marca, modello, prezzo);
        // Adds the car to the set if it does not exists yet
        Car.LAST_ADDED = CARS_SET.add(this);
        if (Car.getLastAdded()) {
            // Adds it also to the list, in case the set add worked
            CARS.add(this);
        }
    }

    /**
     * Overriding of the equals method
     * @param o the car to compare
     * @return true if the cars are equal (same class and fields)
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && o instanceof Car;
    }

}
