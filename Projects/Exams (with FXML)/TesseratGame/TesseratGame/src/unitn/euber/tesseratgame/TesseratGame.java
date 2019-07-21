package unitn.euber.tesseratgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the program
 * @author Eugenio Vinicio Berretta
 */
public class TesseratGame extends Application {
    
    /**
     * Overriding of the start method. Called when the application starts
     * @param stage the primary stage
     * @throws java.lang.Exception error in loading the FXML
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Gets the root by loading the FXML layout
        Parent root = FXMLLoader.load(getClass().getResource("TesseratGame.fxml"));
        // Creates the scene
        Scene scene = new Scene(root);
        // Shows the stage
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
