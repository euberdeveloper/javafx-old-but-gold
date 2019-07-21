package unitn.euber.cargame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

/**
 * The class of a parking area
 * @author Eugenio Vinicio Berretta
 */
public class ParkingArea extends GridPane {

    // Static fields to specify the ui
    private static final int DEFAULT_SIZE = 8;
    private static final int INTERVAL = 500;

    // Random generator
    private final Random randomGenerator = new Random(System.currentTimeMillis());
    // Timeline to the time interval (with callback)
    private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(ParkingArea.INTERVAL), param -> this.moveCars(null)));
    // Parent instance
    private final MainArea parent;
    // Number of rows and cols
    private final int rows, cols;

    // Is car selected and is game beginned
    private boolean addCarSelected = false;
    private boolean isGameBeginned = false;

    /** 
     * Constructor of the class ParkingArea
     * @param parent the parent instance
    */
    public ParkingArea(MainArea parent) {
        this(parent, ParkingArea.DEFAULT_SIZE);
    }

    /** 
     * Constructor of the class ParkingArea
     * @param parent the parent instance
     * @param size the number of rows and cols
    */
    public ParkingArea(MainArea parent, int size) {
        this(parent, size, size);
    }

    /** 
     * Constructor of the class ParkingArea
     * @param parent the parent instance
     * @param rows the number of rows
     * @param cols the number of cols
    */
    public ParkingArea(MainArea parent, int rows, int cols) {
        this.parent = parent;
        this.rows = rows;
        this.cols = cols;
        this.initStyle();
        this.initTerreni();
    }

    // Sets the terreno with that row and col (it will be overwritten)
    private void setTerreno(Terreno terreno, int row, int col) {
        // Removes the old terreno
        this.getChildren().remove(this.getTerreno(row, col));
        // Adds the new terreno
        this.add(terreno, col, row);
    }

    // Gets a terreno given a col and a row
    private Terreno getTerreno(int row, int col) {
        // Checks the range of row and col
        if (0 <= row && row < this.rows && 0 <= col && col < this.cols) {
            // Given the list of children, filters the ones with that row and cols
            final Optional<Node> result = this.getChildrenUnmodifiable()
                    .filtered((Node node) -> {
                        final int r = GridPane.getRowIndex(node);
                        final int c = GridPane.getColumnIndex(node);
                        return r == row && c == col;
                    })
                    .stream()
                    .findFirst();
            // If it is found it will be returned, otherwise null will be returned
            return (Terreno) (result.isPresent() ? result.get() : null);
        }
        // If the range is bad returns null
        return null;
    }

    // Returns true if the given location is on the border of the grid
    private boolean isOnBorder(int row, int col) {
        return (row == 0 || row == this.rows - 1 || col == 0 || col == this.cols - 1);
    }

    // Initializes the ui
    private void initStyle() {
        // Creates the constraints
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100.0 / this.cols);
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100.0 / this.rows);
        // Adds the constraints
        for (int i = 0; i < this.rows; i++) {
            this.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < this.cols; i++) {
            this.getRowConstraints().add(rc);
        }
    }

    // Initializes the terreni
    private void initTerreni() {
        // If it is on the border it will be a prato, otherwise a strada
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.rows; j++) {
                this.setTerreno(this.isOnBorder(i, j) ? new Prato(this) : new Strada(this), i, j);
            }
        }
    }

    // If in the given location there is a strada with a car it will be returned, null otherwise
    private Strada hasCar(int row, int col) {
        Terreno terreno = this.getTerreno(row, col);
        return (terreno instanceof Strada && ((Strada) terreno).hasCar() ? (Strada) terreno : null);
    }

    // Returns true if the value given as the first argument is between 0 (included) and the second argument (excluded)
    private boolean betweenZeroAnd(int val, int max) {
        return 0 <= val && val < max;
    }

    // Returns a random direction
    private Direction getRandomDirection() {
        Direction[] directions = new Direction[]{Direction.TOP, Direction.BOTTOM, Direction.RIGHT, Direction.LEFT};
        return directions[this.randomGenerator.nextInt(4)];
    }

    // It moves the car that stays in the row and col given as second and third arguments to the direction given as first argument
    private Movement moveCar(Direction direction, int row, int col) {
        // Gets the strada with the right row and col (null if there is a prato or there is no strada)
        Strada strada = this.hasCar(row, col);
        // If there is not a strada
        if (strada != null) {
            // Inits local variables
            Coordinates coordinates = null;
            final int carId = strada.getCarId();
            boolean allowed = false;
            // Switch the direction
            switch (direction) {
                case TOP:
                    // top gets the new row coordinate
                    final int top = row - 1;
                    // Sets allowed to true if the move is possible, false otherwise
                    if (this.betweenZeroAnd(top, this.rows) && (this.getTerreno(top, col) instanceof Strada)) {
                        // The coordinates of the car after the movement
                        coordinates = new Coordinates(top, col);
                        allowed = true;
                    } else {
                        // The coordinates of the car after the movement remain the same
                        coordinates = new Coordinates(row, col);
                        allowed = false;
                    }
                    break;
                case BOTTOM:
                    // bottom gets the new row coordinate
                    final int bottom = row + 1;
                    // Sets allowed to true if the move is possible, false otherwise
                    if (this.betweenZeroAnd(bottom, this.rows) && (this.getTerreno(bottom, col) instanceof Strada)) {
                        // The coordinates of the car after the movement
                        coordinates = new Coordinates(bottom, col);
                        allowed = true;
                    } else {
                        // The coordinates of the car after the movement remain the same
                        coordinates = new Coordinates(row, col);
                        allowed = false;
                    }
                    break;
                case RIGHT:
                    // right gets the new column coordinate
                    final int right = col + 1;
                    // Sets allowed to true if the move is possible, false otherwise
                    if (this.betweenZeroAnd(right, this.cols) && (this.getTerreno(row, right) instanceof Strada)) {
                        // The coordinates of the car after the movement
                        coordinates = new Coordinates(row, right);
                        allowed = true;
                    } else {
                        // The coordinates of the car after the movement remain the same
                        coordinates = new Coordinates(row, col);
                        allowed = false;
                    }
                    break;
                case LEFT:
                    // left gets the new column coordinate
                    final int left = col - 1;
                    // Sets allowed to true if the move is possible, false otherwise
                    if (this.betweenZeroAnd(left, this.cols) && (this.getTerreno(row, left) instanceof Strada)) {
                        // The coordinates of the car after the movement
                        coordinates = new Coordinates(row, left);
                        allowed = true;
                    } else {
                        // The coordinates of the car after the movement remain the same
                        coordinates = new Coordinates(row, col);
                        allowed = false;
                    }
                    break;
            }
            // Logs the movement
            String strAllowed = allowed ? "allowed" : "not allowed";
            System.out.println("Car " + carId + " in position (" + row + ", " + col + ") moving " + direction.name() + " - move " + strAllowed);
            // Returns the created movement
            return new Movement(coordinates, carId, direction);
        }
        return null;
    }

    // Given a direction, gets the list of movements generated in base of that direction
    private List<Movement> getMovements(Direction direction) {
        List<Movement> movements = new ArrayList<>();
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                // Note: If the direction is null, for each car will be picked a random direction
                Movement mov = this.moveCar(direction == null ? this.getRandomDirection() : direction, i, j);
                if (mov != null) {
                    movements.add(mov);
                    ((Strada) this.getTerreno(i, j)).popCar();
                }
            }
        }
        return movements;
    }

    // Given the movements, it applies them and if there is a crush terminates the program after an alert
    private void applyMovements(List<Movement> movements) {
        for (Movement movement : movements) {
            Coordinates coordinates = movement.getCoordinates();
            int id = movement.getCarId();
            Direction direction = movement.getDirection();
            // If it is not possible to push a car in the new coordinates (there is already a car)
            if (!((Strada) this.getTerreno(coordinates.getX(), coordinates.getY())).pushCar()) {
                // Stops the time interval
                this.stopTimer();
                // Shows the alert which will finish the application
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Crush");
                alert.setHeaderText("CRUSH");
                alert.setContentText("CRUSH ON CAR " + id + " MOVING TOWARD " + direction.name());
                alert.setOnHidden((DialogEvent event) -> Platform.exit());
                alert.show();
            }
        }
    }

    /**
     * Replaces a strada with a prato and vice versa. Only if the game is not beginned.
     * @param terreno The terreno to replace
     */
    public void replaceTerreno(Terreno terreno) {
        if (!this.isGameBeginned) {
            Coordinates coords = new Coordinates(GridPane.getRowIndex(terreno), GridPane.getColumnIndex(terreno));
            this.setTerreno(terreno instanceof Strada ? new Prato(this) : new Strada(this), coords.getX(), coords.getY());
        }
    }

    /**
     * Getter of the field addCarSelected
     * @return The value of the field addCarSelected
     */
    public boolean getAddCarSelected() {
        return this.addCarSelected;
    }

    /**
     * Setter of the field addCarSelected
     * @param addCarSelected the new value of addCarSelected
     */
    public void setAddCarSelected(boolean addCarSelected) {
        this.addCarSelected = addCarSelected;
        // If it is setted to false, it will call the parent method carAdded
        if (!this.addCarSelected) {
            this.parent.carAdded();
        }
    }

    /**
     * Method called when the game is beginned
     */
    public void gameBeginned() {
        // addCarSelected is setted to false
        this.addCarSelected = false;
        // isGameBeginned is setted to true
        this.isGameBeginned = true;
    }

    /**
     * Moves the cars to a certain direction
     * @param direction the direction of the movements
     */
    public void moveCars(Direction direction) {
        // Gets the movements
        List<Movement> movements = this.getMovements(direction);
        // Applies the movements
        this.applyMovements(movements);
    }

    /**
     * Starts the time interval
     */
    public void playTimer() {
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     * Stops the time interval
     */
    public void stopTimer() {
        this.timeline.stop();
    }

}

/**
 * Class of the Coordinates
 */
class Coordinates {

    // Coordinates x and y
    private final int x, y;

    /**
     * Constructor of the class Coordinates
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of the field x
     * @return The value of the x field
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter of the field y
     * @return The value of the y field
     */
    public int getY() {
        return this.y;
    }
}

/**
 * The class of a movement
 */
class Movement {

    // Coordinates after the movement is applied
    private final Coordinates coordinates;
    // The id of the moved card
    private final int carId;
    // The direction in which the car gets moved
    private final Direction direction;

    /**
     * Constructor of the class Movement
     * @param coordinates Coordinates after the movement is applied
     * @param carId The id of the moved card
     * @param directionThe direction in which the car gets moved
     */
    public Movement(Coordinates coordinates, int carId, Direction direction) {
        this.coordinates = coordinates;
        this.carId = carId;
        this.direction = direction;
    }

    /**
     * Getter of the field coordinates
     * @return The value of the coordinates field
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * Getter of the field carId
     * @return The value of the carId field
     */
    public int getCarId() {
        return this.carId;
    }

    /**
     * Getter of the field direction
     * @return The value of the direction field
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Overriding of the method toString
     * @return The movement as a String
     */
    @Override
    public String toString() {
        return this.getCarId() + "(" + this.coordinates.getX() + ", " + this.coordinates.getY() + ") - " + this.direction.name();
    }
}
