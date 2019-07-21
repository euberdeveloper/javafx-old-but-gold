package unitn.euber.tastomancante;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The main class of the program
 * @author Eugenio Vinicio Berretta
 */
public class TastoMancante extends Application {
    
    // Shows an alert with the rules
    private void showRules() {
        Alert rules = new Alert(AlertType.INFORMATION);
        rules.setTitle("RULES");
        rules.setHeaderText("RULES");
        rules.setContentText("Premere sopra ad un bottone o premere a tastiera il numero della sua posizione per farlo muovere verso il buco");
        rules.showAndWait();
    }
    
    /**
     * Overriding of the start method. Called when the application starts
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        // Creates the scene
        Scene scene = new Scene(new Container());
        scene.getStylesheets().add("unitn/euber/tastomancante/stylesheet.css");
        // Shows the stage
        primaryStage.setTitle("Tasto mancante");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Shows the alert with the rules
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
