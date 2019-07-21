package unitn.euber.toggletastierino;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * The main class of the program
 * @author Eugenio Vinicio Berretta
 */
public class ToggleTastierino extends Application {
    
    // Shows an alert with the game's rules
    private void showRules() {
        Alert rules = new Alert(Alert.AlertType.INFORMATION);
        rules.setTitle("RULES");
        rules.setHeaderText("RULES");
        rules.setContentText("Premere sopra ad un bottone o premere a tastiera il numero della sua posizione per farlo comparire/scomparire");
        rules.showAndWait();
    }
    
    /**
     * Overriding of the method start. Called when the application starts
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        // Creates the scene
        Scene scene = new Scene(new Container());
        // Adds the css to the scene
        scene.getStylesheets().add("unitn/euber/toggletastierino/stylesheet.css");
        // Shows the stage
        primaryStage.setTitle("Toggle tastierino");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        this.showRules();
    }

    /**
     * The main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
