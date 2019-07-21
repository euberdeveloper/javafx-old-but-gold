package unitn.euber.tesseratgame;

/**
 * The class of a win cell
 * @author Eugenio Vinicio Berretta
 */
public class WinCell extends Cell {

    /** Constructor of the class WinCell
     * @param parent the parent instances
     */
    public WinCell(TesseratGameController parent) {
        super(parent);
    }

    /**
     * Makes the cell active. The player wins the game
     */
    @Override
    public void scopri() {
        super.scopri();
        parent.win();
    }
    
    /**
     * Overriding of the method toString. Returns the cell as a String
     * @return the cell as a String
     */
    @Override
    public String toString() {
        return "WinCell";
    }

}
