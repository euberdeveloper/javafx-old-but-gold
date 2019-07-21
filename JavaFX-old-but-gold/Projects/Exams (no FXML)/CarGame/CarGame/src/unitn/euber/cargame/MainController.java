package unitn.euber.cargame;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Class of the main controller
 * @author Eugenio Vinicio Berretta
 */
public class MainController extends HBox {
    
    // Static fields for the ui and settings
    private static final int DEFAULT_CAR_AMOUNT = 3;
    private static final String LABEL_TEXT_PREFIX = "AVAILABLE CARS:\t";
    
    // The parent instance
    private final MainArea parent;
   
    // The ui elements
    private Label carLabel;
    private Button addCar;
    private Button beginGame;
    
    // The number of cars
    private int carCount = MainController.DEFAULT_CAR_AMOUNT;
    
    /**
     * Constructor of the class MainController
     * @param parent the parent instance
     */
    public MainController(MainArea parent) {
        this.parent = parent;
        this.initCarLabel();
        this.initAddCar();
        this.initBeginGame();
    }
    
    // Inits the label
    private void initCarLabel() {
        this.carLabel = new Label(MainController.LABEL_TEXT_PREFIX + this.carCount);
        this.getChildren().add(this.carLabel);
    }
    
    // Inits the addCar button
    private void initAddCar() {
        this.addCar = new Button("ADD CAR");
        this.addCar.setOnAction((ActionEvent event) -> {
            this.addCar.setDisable(true);
            this.parent.addCarSelected();
        });
        this.getChildren().add(this.addCar);
    }
    
    // Inits the begingame button
    private void initBeginGame() {
        this.beginGame = new Button("BEGIN GAME");
        this.beginGame.setOnAction((ActionEvent event) -> {
            this.beginGame.setDisable(true);
            this.addCar.setDisable(true);
            this.parent.beginGame();
        });
        this.getChildren().add(this.beginGame);
    }
    
    /**
     * Updates the main control after a car was added
     */
    public void carAdded() {
        if (--this.carCount > 0) {
            this.addCar.setDisable(false);
        }
        this.carLabel.setText(MainController.LABEL_TEXT_PREFIX + this.carCount);
    }
    
}
