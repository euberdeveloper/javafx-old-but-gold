package unitn.euber.carpeople;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

/**
 * Add Car class
 * @author Eugenio Vinicio Berretta
 */
public class AddPerson extends AddRecord {
    
    /**
     * Constructor of the class AddPerson
     * @param parent the parent instance
     */
    public AddPerson(CarPeopleController parent) {
        super(parent);
    }
    
    /**
     * Overriding of the initialize method. The text ui will be initialized here.
     * @param location location of the fxml file
     * @param resources the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHeader("AGGIUNGI PERSONA");
        setLabels("Nome:", "Cognome:", "Anni:");
        setBtnText("CONTROLLA E INSERISCI PERSONA");
    }

    /**
     * Overriding of the checkPassed method in AddRecord. It will try to add the new person and if it works will update the parent window
     */
    @Override
    protected void checkPassed() {
        // Creates a new person. In its constructor, it will be automatically added in the list of people if it does not exist yet
        new Persona(tfField1.getText(), tfField2.getText(), new Integer(tfField3.getText()));
        // If the person was added, it updates the parent window and closes the current window
        if (Persona.getLastAdded()) {
            parent.updateText();
            ((Stage) (getScene().getWindow())).close();
        }
        // Otherwise it shows an alert
        else {
            checkFailed();
        }
    }
    
}
