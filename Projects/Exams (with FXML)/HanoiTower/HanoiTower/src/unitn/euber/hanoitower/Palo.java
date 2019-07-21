package unitn.euber.hanoitower;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * Class of a stick
 * @author Eugenio Vinicio Berretta
 */
public class Palo extends StackPane implements Initializable {

    // References to the ui elements
    @FXML
    private Rectangle rctgPalo;

    // When a palo is clicked, it calls the method on the parent instance
    @FXML
    private void selectPalo() {
        parent.selectPalo(index);
    }

    // The parent instance
    private final HanoiTowerController parent;
    // The rings
    private final List<Anello> anelli = new ArrayList<>();
    // The index of the stick
    private final int index;

    /**
     * Constructor of the class Palo
     */
    public Palo(HanoiTowerController parent, int index) {
        // Gets the FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Palo.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        // Loads the FXML layout
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Assigns the instance and the index
        this.parent = parent;
        this.index = index;
        // If it is the first, it adds the rings
        if (this.index == 0) {
            generateAnelli();
            updateAnelli();
        }
        // Inits the layoutss
        initPalo();
    }

    /** Overriding of the initialize method
     * @param location the url of the FXML file
     * @param resources the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // Generates the rings
    private void generateAnelli() {
        for (int i = 0; i < 4; i++) {
            anelli.add(new Anello(this, i + 1));
        }
    }

    // Updates the ui (lord) of the rings
    private void updateAnelli() {
        getChildren().removeAll(anelli);
        Collections.sort(anelli);
        int translate = 0;
        for (Anello anello : anelli) {
            getChildren().add(anello);
            anello.setTranslateY(-translate);
            translate += anello.getHeight();
        }
    }

    // Initializes the style of the stick
    private void initPalo() {
        // The stick dimensions are bound to the window dimension
        heightProperty().addListener(event -> {
            rctgPalo.setHeight(getHeight() - getHeight() / 15);
        });
        widthProperty().addListener(event -> {
            rctgPalo.setWidth(getWidth() / 10);
        });
    }

    /**
     * Checks if there are no rings on the stick
     * @return true if there are no rings, false otherwise
     */
    public boolean isEmpty() {
        return anelli.isEmpty();
    }

    /**
     * Returns the number of the biggest ring in the stick
     * @return the number of the biggest ring in the stick
     */
    public int top() {
        int max = Integer.MIN_VALUE;
        for (Anello anello : anelli) {
            if (anello.getSize() > max) {
                max = anello.getSize();
            }
        }
        return max;
    }

    /**
     * Removes the upper ring
     * @return the removed ring
     */
    public Anello remove() {
        Anello anello = anelli.remove(anelli.size() - 1);
        updateAnelli();
        return anello;
    }

    /**
     * Adds a ring to the top of the stick
     * @param anello the ring to add
     */
    public void add(Anello anello) {
        anelli.add(anello);
        updateAnelli();
    }

}
