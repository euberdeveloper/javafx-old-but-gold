package unitn.euber.carpeople;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Add Record class
 * @author Eugenio Vinicio Berretta
 */
public abstract class AddRecord extends BorderPane implements Initializable {

    // The parent instance
    protected final CarPeopleController parent;

    // References to the ui elements
    @FXML
    private Label lbHeader;
    @FXML
    private Label lbField1;
    @FXML
    private Label lbField2;
    @FXML
    private Label lbField3;
    @FXML
    protected TextField tfField1;
    @FXML
    protected TextField tfField2;
    @FXML
    protected TextField tfField3;
    @FXML
    private Button btnAdd;

    // Adds a new record
    @FXML
    protected void addRecord() {
        if (checkData()) {
            this.checkPassed();
        } else {
            this.checkFailed();
        }
    }

    // It shows an alert because the record cannot be added
    protected void checkFailed() {
        System.out.println("Errore nei campi");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errere nei campi");
        alert.setHeaderText("UNO O PIU' CAMPI SONO ERRATI");
        alert.showAndWait();
    }

    // What will be done if the check passes
    protected abstract void checkPassed();

    /**
     * Constructor of the class AddRecord
     * @param parent the parent instance
     */
    public AddRecord(CarPeopleController parent) {
        // Assigns the parent instance
        this.parent = parent;
        // Gets the FXML layout
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddRecord.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        // Loads the FXML layout
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // Returns true if the data are compatible for a new car
    protected boolean checkData() {
        // Gets the data
        String first = tfField1.getText();
        String second = tfField2.getText();
        String third = tfField3.getText();
        // Checks the data
        try {
            int th = Integer.parseInt(third);
            return (first != null && !first.isEmpty() && second != null && !second.isEmpty());
        } catch (Exception exception) {
            return false;
        }
    }

    // Sets the text of the header
    protected void setHeader(String header) {
        lbHeader.setText(header);
    }

    // Sets the text of the labels
    protected void setLabels(String first, String second, String third) {
        lbField1.setText(first);
        lbField2.setText(second);
        lbField3.setText(third);
    }

    // Sets the text of the button
    protected void setBtnText(String text) {
        btnAdd.setText(text);
    }

    /**
     * Overriding of the initialize method
     * @param location the location of the FXML file
     * @param resources the resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
