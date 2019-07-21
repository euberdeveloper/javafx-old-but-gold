package unitn.euber.hanoitower;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The class of the controller of the FXML layout
 * @author Eugenio Vinicio Berretta
 */
public class HanoiTowerController implements Initializable {
    
    // References to the ui elements
    @FXML
    private BorderPane bpField;
    @FXML
    private TextField tfFrom;
    @FXML
    private TextField tfTo;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnMove;
    
    // Resets the selected sticks
    @FXML
    private void clear() {
        setFrom(-1);
        setTo(-1);
    }
    // Closes the program
    @FXML
    private void close() {
        alert.close();
        alert = null;
        btnClose.setDisable(true);
    }
    // Resets the game
    @FXML
    private void reset() {
        clear();
        field.reset();
    }
    // Moves the upper ring in the first selected stick to the top of the second selected stick
    @FXML
    private void move() {
        if (from != -1 && to != -1) {
            Palo f = field.getPalo(from);
            Palo t = field.getPalo(to);
            t.add(f.remove());
            clear();
        }
        else {
            showAlert("Pali non selezionati");
        }
    }
    
    // The field element
    private final Field field = new Field(this);
    // The stage of the alert window
    private Stage alert = null;
    
    // Selected sticks indexes
    private int from = -1;
    private int to = -1;
    
    // Shows an alert with a given text
    private void showAlert(String text) {
        if (alert != null) {
            alert.close();
        }
        alert = new Stage();
        alert.setTitle("Attenzione");
        alert.setScene(new Scene(new Label(text), 400, 100));
        alert.show();
        alert.setOnHidden(event -> btnClose.setDisable(true));
        btnClose.setDisable(false);  
    }
    
    /**
     * Selects a stick. It will be the first if no stick is already selected, the second if one is already selected and an alert will be shown otherwise
     * @param index the stick to select
     */
    public void selectPalo(int index) {
        if (from == -1) {
            if (field.getPalo(index).isEmpty()) {
                showAlert("Palo vuoto");
            }
            else {
                this.setFrom(index);
            }
        }
        else if (to == -1) {
            if (from == index) {
                showAlert("Palo già selezionato");
            }
            else if (field.getPalo(index).top() > field.getPalo(from).top()) {
                showAlert("Palo-movimento non corretto");
            }
            else {
                this.setTo(index);
            }
        }
        else {
            showAlert("Entrambi i pali sono settati");
        }
    }
    
    /**
     * Overriding of the method initialize
     * @param url the url of the FXML file
     * @param rb the resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnClose.setDisable(true);
        bpField.setCenter(field);
    }    
    
    // Selects the from stick
    private void setFrom(int from) {
        this.from = from;
        tfFrom.setText("" + (this.from == -1 ? "" : this.from));
    }
    // Selects the to stick
    private void setTo(int to) {
        this.to = to;
        tfTo.setText("" + (this.to == -1 ? "" : this.to));
    }
    
}
