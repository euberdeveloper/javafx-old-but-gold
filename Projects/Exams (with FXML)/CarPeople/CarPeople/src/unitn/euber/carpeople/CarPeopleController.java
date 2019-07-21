package unitn.euber.carpeople;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * The controller class of the main FXML layout
 * @author Eugenio Vinicio Berretta
 */
public class CarPeopleController implements Initializable {
    
    // References to the ui elements
    @FXML
    private TextArea taPersone;
    @FXML
    private TextArea taCars;
    
    // Shuffles the people
    @FXML
    private void mescolaPersone() {
        Collections.shuffle(Persona.PERSONE);
        updateText();
    };
    // Shuffles the cars
    @FXML
    private void mescolaCars() {
        Collections.shuffle(Car.CARS);
        updateText();
    };
    
    // Sorts the people
    @FXML
    private void ordinaPersone() {
        Collections.sort(Persona.PERSONE);
        updateText();
    };
    // Sorts the cars
    @FXML
    private void ordinaCars() {
        Collections.sort(Car.CARS);
        updateText();
    };
    
    // Counts the people
    @FXML
    private void contaPersone() {
        taPersone.setText("" + Persona.PERSONE.size());
    };
    // Counts the cars
    @FXML
    private void contaCars() {
        taCars.setText("" + Car.CARS.size());
    };
    
    // Sorts the people in base of the year (this is why a list and not a set is used)
    @FXML
    private void ordinaAnnoPersone() {
        Collections.sort(Persona.PERSONE, Record.getComparator());
        updateText();
    };
    // Sorts the cars in base of the year (this is why a list and not a set is used)
    @FXML
    private void ordinaAnnoCars() {
        Collections.sort(Car.CARS, Record.getComparator());
        updateText();
    };
    
    // Shows the window to add a new person
    @FXML
    private void addPersona() {
        Stage stage = new Stage();
        stage.setTitle("Aggiungi persona");
        stage.setScene(new Scene(new AddPerson(this)));
        stage.show();
    };
    // Shows the window to add a new car
    @FXML
    private void addCar() {
        Stage stage = new Stage();
        stage.setTitle("Aggiungi macchina");
        stage.setScene(new Scene(new AddCar(this)));
        stage.show();
    };
    
    /**
     * Overriding of the initialize method.
     * @param url the url of the FXML file
     * @param rb the resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }    
    
    // Updates the layout when the controller starts
    private void init() {
        updateText();
    }
    
    /**
     * Updates the ui
     */
    public void updateText() {
        String persone = "";
        String macchine = "";
        for (Persona p : Persona.PERSONE) {
            persone += p.toString() + "\n";
        }
        for (Car c : Car.CARS) {
            macchine += c.toString() + "\n";
        }
        taPersone.setText(persone);
        taCars.setText(macchine);
    }
    
}
