package unitn.euber.cargame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the program
 * @author Eugenio Vinicio Berretta
 */
public class CarGame extends Application {
    
    /**
     * The method called when the applicationstarts
     * @param primaryStage the primary stage of JavaFX 
     */
    @Override
    public void start(Stage primaryStage) {        
        Scene scene = new Scene(new MainArea(), 600, 600);
        
        primaryStage.setTitle("Car Game");
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
