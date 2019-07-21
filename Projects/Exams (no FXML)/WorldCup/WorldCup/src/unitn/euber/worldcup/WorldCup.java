/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitn.euber.worldcup;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Eugenio Vinicio Berretta
 */
public class WorldCup extends Application {
    
    private static final float WIDTH = 700.0f;
    private static final float HEIGHT = 700.0f;
    
    @Override
    public void start(Stage primaryStage) {
        // Main scene
        Scene scene = new Scene(new MainView(), WorldCup.WIDTH, WorldCup.HEIGHT);
        // Main stage
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
