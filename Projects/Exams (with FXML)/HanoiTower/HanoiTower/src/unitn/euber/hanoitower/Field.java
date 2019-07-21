package unitn.euber.hanoitower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * The class of the field
 * @author Eugenio Vinicio Berretta
 */
public class Field extends GridPane implements Initializable {
    
    // The parent instances
    private final HanoiTowerController parent;
    // The sticks
    private final Palo[] pali = { null, null, null };
    
    /**
     * Constructor of the class Field
     * @param parent the parent instance
     */
    public Field(HanoiTowerController parent) {
        // Assigns the parent instance
        this.parent = parent;
        // Gets the FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Field.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        // Loads the FXML layout
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Removes the given palo
    private void removePalo(Palo p) {
        if (p != null) {
            getChildren().remove(p);
        }
    }
    
    /**
     * Returns the stick with the given index
     * @param index the index of the returned stick
     * @return the stick with that index
     */
    public Palo getPalo(int index) {
        return pali[index];
    }
    
    /**
     * Resets the field, with the first stick having all the rings
     */
    public void reset() {
        // Removes all the sticks
        removePalo(pali[0]);
        removePalo(pali[1]);
        removePalo(pali[2]);
        // Creates the new sticks
        pali[0] = new Palo(parent, 0);
        pali[1] = new Palo(parent, 1);
        pali[2] = new Palo(parent, 2);
        // Adds the new sticks
        add(pali[0], 0, 0);
        add(pali[1], 1, 0);
        add(pali[2], 2, 0);
    }

    /**
     * Overriding of the initialize method
     * @param location the url of the FXML file
     * @param resources the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sets column constraints of the GridPane
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100.0 / 3);
        getColumnConstraints().addAll(cc, cc, cc);
        // Sets row constraints of the GridPane
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100.0);
        getRowConstraints().addAll(rc);
        // Adds the sticks with the rings
        reset();
    }
    
}
