package unitn.euber.carpeople;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

/**
 * Add Car class
 * @author Eugenio Vinicio Berretta
 */
public class AddCar extends AddRecord {
    
    /**
     * Constructor of the class AddCar
     * @param parent the parent instance
     */
    public AddCar(CarPeopleController parent) {
        super(parent);
    }
    
    /**
     * Overriding of the initialize method. The text ui will be initialized here.
     * @param location location of the fxml file
     * @param resources the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sets the ui text
        setHeader("AGGIUNGI MACCHINA");
        setLabels("Marca:", "Modello:", "Prezzo:");
        setBtnText("CONTROLLA E INSERISCI MACCHINA");
    }

    /**
     * Overriding of the checkPassed method in AddRecord. It will try to add the new car and if it works will update the parent window
     */
    @Override
    protected void checkPassed() {
        // Creates a new car. In its constructor, it will be automatically added in the list of cars if it does not exist yet.
        new Car(tfField1.getText(), tfField2.getText(), new Integer(tfField3.getText()));
        // If the car was added, it updates the parent window and closes the current window
        if (Car.getLastAdded()) {
            parent.updateText();
            ((Stage) (getScene().getWindow())).close();
        }
        // Otherwise it shows an alert
        else {
            checkFailed();
        }
    }
    
}
