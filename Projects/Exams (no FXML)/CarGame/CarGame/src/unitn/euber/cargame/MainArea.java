package unitn.euber.cargame;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The main area of the program
 * @author Eugenio Vinicio Berretta
 */
public class MainArea extends BorderPane {

    // Private fields
    private final ParkingArea parkingArea = new ParkingArea(this);
    private final MainController mainController = new MainController(this);

    private final Stage carControllerStage = new Stage();
    private final CarController carController = new CarController(this);

    /**
     * The constructor of the class MainArea
     */
    public MainArea() {
        // Starts the view
        this.initChildren();
        // Starts the car controller window
        this.initCarControllerStage();
    }

    // Sets the children
    private void initChildren() {
        // Sets the parking area
        this.setCenter(this.parkingArea);
        // Sets the controller area
        this.setBottom(this.mainController);
    }

    // Starts the car controller window
    private void initCarControllerStage() {
        Scene scene = new Scene(this.carController, 300, 300);
        this.carControllerStage.setTitle("CAR CONTROLLER");
        this.carControllerStage.setScene(scene);
    }

    /**
     * Method called when the button Add Car is clicked in the car controller. Calls the setAddCarSelected method in the ParkingArea
     */
    public void addCarSelected() {
        this.parkingArea.setAddCarSelected(true);
    }

    /**
     * Method called when a car is added by the parking area. Calls the carAdded method in the MainController
     */
    public void carAdded() {
        this.mainController.carAdded();
    }

    /**
     * Method called when the Begin button is clicked in the MainController. Shows the car controller.
     */
    public void beginGame() {
        this.carControllerStage.show();
        this.parkingArea.gameBeginned();
    }

    /**
     * Method called when a command is sent by the car controller window. Handles it.
     * @param command the sent command
     */
    public void onCommand(Command command) {
        switch (command) {
            case TOP:
                this.parkingArea.moveCars(Direction.TOP);
                break;
            case BOTTOM:
                this.parkingArea.moveCars(Direction.BOTTOM);
                break;
            case LEFT:
                this.parkingArea.moveCars(Direction.LEFT);
                break;
            case RIGHT:
                this.parkingArea.moveCars(Direction.RIGHT);
                break;
            case RANDOM:
                this.parkingArea.moveCars(null);
                break;
            case START:
                this.parkingArea.playTimer();
                break;
            case STOP:
                this.parkingArea.stopTimer();
                break;
        }
    }
}
