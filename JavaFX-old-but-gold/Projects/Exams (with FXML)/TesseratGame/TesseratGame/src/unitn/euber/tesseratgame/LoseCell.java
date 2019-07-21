package unitn.euber.tesseratgame;

import unitn.euber.tesseratgame.Cell;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import unitn.euber.tesseratgame.TesseratGameController;

/**
 * The class of a lose cell
 * @author Eugenio Vinicio Berretta
 */
public class LoseCell extends Cell {

    /** Constructor of the class LoseCell
     * @param parent the parent instances
     */
    public LoseCell(TesseratGameController parent) {
        super(parent);
    }
    
    /**
     * Makes the cell active. The player loses the game
     */
    @Override
    public void scopri() {
        // Shows an alert that says to the player that he is a looser. Logs it
        System.out.println("Peccato hai perso");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hai perso");
        alert.setHeaderText("Peccato hai perso");
        alert.showAndWait();
        Platform.exit();
    }
    
    /**
     * Overriding of the method toString. Returns the cell as a String
     * @return the cell as a String
     */
    @Override
    public String toString() {
        return "LoseCell";
    }

}
