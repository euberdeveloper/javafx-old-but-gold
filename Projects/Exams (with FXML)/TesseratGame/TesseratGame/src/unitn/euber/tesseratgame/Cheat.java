package unitn.euber.tesseratgame;

import unitn.euber.tesseratgame.Cell;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * The class of the cheat window, which shows all the information about the cellss
 * @author Eugenio Vinicio Berretta
 */
public class Cheat extends VBox {
    
    // The cells
    private List<Cell> cells = new ArrayList<>();
    
    /**
     * Constructor of the class Cheat
     * @param cells the cells
     */
    public Cheat(List<Cell> cells) {
        // Assigns the cells
        this.cells = cells;
        // Gets the FXML layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cheat.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        // Loads the FXML layout
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        // Renders the ui
        this.initCells();
    }
    
    // Renders the ui to show the cells' information
    private void initCells() {
        for (Cell cell : cells) {
            Label label = new Label(cell.toString());
            getChildren().add(label);
        }
    }
    
}
