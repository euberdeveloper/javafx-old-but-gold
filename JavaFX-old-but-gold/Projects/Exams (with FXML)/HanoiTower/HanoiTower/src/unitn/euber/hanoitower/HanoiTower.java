package unitn.euber.hanoitower;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class of the hanoi tower
 * @author Eugenio Vinicio Berretta
 */
public class HanoiTower extends Application {
    
    /**
     * Overriding of the method start. Called when the application starts
     * @param stage the primary stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HanoiTower.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Hanoi Tower");
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
