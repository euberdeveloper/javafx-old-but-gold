package unitn.euber.dicegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the program
 * @author Eugenio VInicio Berretta
 */
public class DiceGame extends Application {
    
    /**
     * Overriding of the method start. It is called when the application starts.
     */
    @Override
    public void start(Stage primaryStage) {
        // Creates the scene
        Scene scene = new Scene(new GameArea(), 800, 600);
        // Shows the stage with that scene
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
