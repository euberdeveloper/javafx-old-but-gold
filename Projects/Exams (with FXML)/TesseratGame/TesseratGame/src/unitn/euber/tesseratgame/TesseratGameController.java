package unitn.euber.tesseratgame;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * The class of the controller of the FXML layout
 * @author Eugenio Vinicio Berretta
 */
public class TesseratGameController implements Initializable {

    // The number of cells and number of columns
    private int SIZE;
    private int COLS;

    // The cells
    private List<Cell> cells = new ArrayList<>();

    // References to the ui elements
    @FXML
    private Label lbPunteggio;
    @FXML
    private Label lbPartiteVinte;
    @FXML
    private GridPane grdGrid;
    
    /**
     * Resets the game
     * @param event the event
     */
    @FXML
    public void reset(ActionEvent event) {
        this.setPartiteVinte(0);
        this.setPunteggio(0);
        this.addCells();
    }
    
    /**
     * Shows an alert which contains all the information about the cells
     */
    @FXML
    public void cheat(ActionEvent event) {
        Stage cheatStage = new Stage();
        cheatStage.setTitle("Cheat");
        cheatStage.setScene(new Scene(new Cheat(cells)));
        cheatStage.show();
    }

    /**
     * The constructor of the class TesseratGameController.
     */
    public TesseratGameController() {
        // Shows an alert and gets the number of cells. The number of cols will be setted inorder to get a square grid of cells
        String response = this.getResponse();
        try {
            SIZE = Integer.parseInt(response);
        }
        catch(Exception e) {
            SIZE = 9;
        }
        finally {
            COLS = (int) Math.floor(Math.sqrt(SIZE));
        }
    }

    // Score and won games
    private int punteggio = 0;
    private int partiteVinte = 0;

    // Shows an alert to ask the grid size and returns the answer
    private String getResponse() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Dimensione della griglia?");
        Optional<String> result = dialog.showAndWait();
        return (result.isPresent() ? result.get() : null);
    }
    
    // Adds a cell to the grid
    private void gridAdd(Node node, int index) {
        grdGrid.add(node, index % COLS, index / COLS);
    }

    // Generates the cells, adds them to the list of cells and shuffles them
    private void generateCells() {
        cells.clear();
        cells.add(new WinCell(this));
        cells.add(new LoseCell(this));
        for (int i = 2; i < SIZE; i++) {
            cells.add(new ValueCell(this));
        }
        Collections.shuffle(cells);
    }

    // Adds the cells to the grid
    private void addCells() {
        grdGrid.getChildren().clear();
        generateCells();
        for (int i = 0; i < SIZE; i++) {
            gridAdd(cells.get(i), i);
        }
    }

    // Inits the grid of cells
    private void initGrid() {
        // Creates column constraints
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100.0 / COLS);
        // Creates rows constraints
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100.0 / COLS);
        // Adds constraints
        for (int i = 0; i < COLS; i++) {
            grdGrid.getColumnConstraints().add(cc);
            grdGrid.getRowConstraints().add(rc);
        }
        // Adds the cells
        addCells();
    }

    // Sets the number of won games and update the ui
    private void setPartiteVinte(int partiteVinte) {
        this.partiteVinte = partiteVinte;
        lbPartiteVinte.setText("" + this.partiteVinte);
    }

    // Sets the score and updates the ui
    private void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
        lbPunteggio.setText("" + this.punteggio);
        
        lbPunteggio.getStyleClass().removeAll("blue", "red", "green");
        if (this.punteggio > 10) {
            lbPunteggio.getStyleClass().add("red");
        }
        else if (this.punteggio < 10) {
            lbPunteggio.getStyleClass().add("blue");
        }
        else {
            lbPunteggio.getStyleClass().add("green");
            this.win();
        }
    }
    
    /**
     * Adds the given score to the current score and updates the ui
     * @param punteggio the score to add
     */
    public void addPunteggio(int punteggio) {
        this.setPunteggio(this.punteggio + punteggio);
    }

    /**
     * The game is won. It shows an alert to say it and restarts the game
     */
    public void win() {
        // Shows the alert
        System.out.println("Hai vinto");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hai vinto");
        alert.setHeaderText("Bravo hai vinto");
        alert.showAndWait();
        // Restarts the game with won game added by one
        this.setPartiteVinte(partiteVinte + 1);
        this.setPunteggio(0);
        this.addCells();
    }

    /**
     * Overriding of the method initialize
     * @param url the url of the FXML file
     * @param rb the resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initGrid();
        lbPunteggio.setText("" + punteggio);
        lbPartiteVinte.setText("" + partiteVinte);
    }

}