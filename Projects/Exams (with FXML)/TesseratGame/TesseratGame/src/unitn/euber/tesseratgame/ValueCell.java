package unitn.euber.tesseratgame;

import unitn.euber.tesseratgame.Cell;
import java.util.Random;
import unitn.euber.tesseratgame.TesseratGameController;

/**
 * The class of a value cell
 * @author Eugenio Vinicio Berretta
 */
public class ValueCell extends Cell {
    
    // The random generator
    private final Random random = new Random(System.currentTimeMillis());
    // The value of the cell
    private final int value;
    
    /** Constructor of the class ValueCell
     * @param parent the parent instances
     */
    public ValueCell(TesseratGameController parent) {
        super(parent);
        // Sets the value to a random number
        value = random.nextInt(9) + 1;
    }
    
    /**
     * Makes the cell active. Adds the value to the score
     */
    @Override
    public void scopri() {
        super.scopri();
        text.setText("" + value);
        parent.addPunteggio(value);
    }
    
    /**
     * Covers the cell. Substracts the value from the score
     */
    @Override
    public void copri() {
        super.copri();
        parent.addPunteggio(-value);
    }
    
    /**
     * Overriding of the method toString. Returns the cell as a String
     * @return the cell as a String
     */
    @Override
    public String toString() {
        return "ValueCell " + value;
    }
    
}
