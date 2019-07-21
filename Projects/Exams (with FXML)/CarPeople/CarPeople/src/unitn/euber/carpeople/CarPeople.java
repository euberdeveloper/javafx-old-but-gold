package unitn.euber.carpeople;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the program
 * @author Eugenio Vinicio Berretta
 */
public class CarPeople extends Application {
    
    /** 
     * Overriding of the start metohd. Called when the application starts. It loads the FXML and shows the stage.
     * @param stage the primary stage
     * @throws java.lang.Exception
    */
    @Override
    public void start(Stage stage) throws Exception {
        // Gets the root of the ui by loading the FXML layout
        Parent root = FXMLLoader.load(getClass().getResource("CarPeople.fxml"));
        // Creates the scene
        Scene scene = new Scene(root);
        // Creates the stage and shows it
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
