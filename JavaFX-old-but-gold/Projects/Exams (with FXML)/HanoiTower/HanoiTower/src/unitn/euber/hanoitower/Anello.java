package unitn.euber.hanoitower;

import javafx.scene.shape.Rectangle;

/**
 * The class of a ring
 * @author Eugenio Vinicio Berretta
 */
public class Anello extends Rectangle implements Comparable<Anello> {
    
    // The possible colors and the height of a ring
    private static final String[] COLORS = {"brown", "purple", "green", "gray"};
    private static final double HEIGHT = 15.0;
    
    // The parent instance
    private final Palo parent;
    // The size of the ring
    private final int size;
    
    /**
     * Constructor of the class Anello
     * @param parent the parent instance
     * @param size the size of the ring
     */
    public Anello(Palo parent, int size) {
        // Throws an exception if the size is too big
        if (size > 4) {
            throw new IllegalArgumentException("Dimensione anello troppo grande");
        }
        // Assigns the properties
        this.parent = parent;
        this.size = size;
        // The width is bound to the parent one
        this.parent.widthProperty().addListener(event -> {
            setWidth(this.parent.getWidth() - this.size * this.parent.getWidth() / 5);
        });
        // Sets the style
        setWidth(HEIGHT);
        setHeight(HEIGHT);
        setStyle("-fx-fill: " + Anello.COLORS[this.size - 1]);
    }
    
    /**
     * Overriding of the compareTo method
     * @param anello the ring to compare
     * @return a positive number if this ring is bigger, n = 0 if it is equal and a negative number if it is smaller
     */
    @Override
    public int compareTo(Anello anello) {
        return size - anello.size;
    }

    /**
     * Getter of the size field
     * @return the value of the size field
     */
    public int getSize() {
        return size;
    }
    
}
