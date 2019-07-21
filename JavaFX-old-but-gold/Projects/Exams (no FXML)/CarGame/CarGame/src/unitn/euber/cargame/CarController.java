package unitn.euber.cargame;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Class of the car controller
 * @author Eugenio Vinicio Berretta
 */
public class CarController extends BorderPane {

    // Privare fields
    private final MainArea parent;
    private Button top;
    private Button bottom;
    private Button left;
    private Button right;
    private Button random;
    private Button start;
    private Button stop;
    
    // Initializes the top of the BorderPane
    private void initTop() {
        // Creates a button
        this.top = new Button("TOP");
        this.top.setOnAction((ActionEvent event) -> {
            this.parent.onCommand(Command.TOP);
        });
        // Adds the button
        this.setTop(this.top);
        BorderPane.setAlignment(this.top, Pos.CENTER);
    }
    
    // Initializes the bottom of the BorderPane
    private void initBottom() {
        // Creates a button
        this.bottom = new Button("BOTTOM");
        this.bottom.setOnAction((ActionEvent event) -> {
            this.parent.onCommand(Command.BOTTOM);
        });
        // Adds the button
        this.setBottom(this.bottom);
        BorderPane.setAlignment(this.bottom, Pos.CENTER);
    }
    
    // Initializes the left of the BorderPane
    private void initLeft() {
        // Creates a button
        this.left = new Button("LEFT");
        this.left.setOnAction((ActionEvent event) -> {
            this.parent.onCommand(Command.LEFT);
        });
        // Adds the button
        this.setLeft(this.left);
        BorderPane.setAlignment(this.left, Pos.CENTER);
    }
    
    // Initializes the right of the BorderPane
    private void initRight() {
        // Creates a button
        this.right = new Button("RIGHT");
        this.right.setOnAction((ActionEvent event) -> {
            this.parent.onCommand(Command.RIGHT);
        });
        // Adds the button
        this.setRight(this.right);
        BorderPane.setAlignment(this.right, Pos.CENTER);
    }
    
    // Initializes the center of the BorderPane
    private void initCenter() {
        // Creates and adds the buttons
        this.random = new Button("RANDOM");
        this.random.setOnAction((ActionEvent event) -> {
            this.parent.onCommand(Command.RANDOM);
        });
        
        this.start = new Button("START");
        this.start.setOnAction((ActionEvent event) -> {
            this.parent.onCommand(Command.START);
        });
        
        this.stop = new Button("STOP");
        this.stop.setOnAction((ActionEvent event) -> {
            this.parent.onCommand(Command.STOP);
        });
        
        HBox center = new HBox();
        center.getChildren().addAll(this.random, this.start, this.stop);
        center.setAlignment(Pos.CENTER);
        this.setCenter(center);
        BorderPane.setAlignment(center, Pos.CENTER);
    }
    
    /**
     * Constructor of the class CarController
     * @param parent the parent instance
     */
    public CarController(MainArea parent) {
        this.parent = parent;
        this.initTop();
        this.initBottom();
        this.initLeft();
        this.initRight();
        this.initCenter();
    }
    
    
}
