package unitn.euber.dicegame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The class of the game buttons
 * @author Eugenio Vinicio Berretta
 */
public class GameButtons extends VBox {
    
    // Static fields to set the ui
    private static final double SPACING = 15.0;
    private static final double PADDING = 12.0;

    // Parent instance
    private final GameArea parent;
    // Buttons
    private final Button newGame = new Button();
    private final Button print = new Button();
    private final Button animation = new Button();

    /**
     * Constructor of the class GameButtons
     * @param parent the parent instance
     */
    public GameButtons(GameArea parent) {
        this.parent = parent;
        this.initNewGame();
        this.initPrint();
        this.initAnimation();
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(GameButtons.PADDING));
        this.setSpacing(GameButtons.SPACING);
    }

    // Adds the button newGame
    private void initNewGame() {
        this.newGame.setText("NUOVA PARTITA");
        this.newGame.setOnAction(event -> { this.parent.newGame(); });
        this.getChildren().add(this.newGame);
    }

    // Adds the button print
    private void initPrint() {
        this.print.setText("STAMPA");
        this.print.setOnAction(event -> { this.parent.print(); });
        this.getChildren().add(this.print);
    }

    // Adds the button animation
    private void initAnimation() {
        this.updateAnimation();
        this.animation.setOnAction(event -> { this.toggleAnimation(); });
        this.getChildren().add(this.animation);
    }

    // Gets the text of the button animation
    private String getAnimationText() {
        switch (this.parent.getAnimation()) {
            case TRASLATE:
                return "SPOSTAMENTO";
            case DISSOLVE:
                return "DISSOLVIMENTO";
            default:
                return null;
        }
    }

    // Toggles the animation
    private void toggleAnimation() {
        switch (this.parent.getAnimation()) {
            case TRASLATE:
                this.parent.setAnimation(Animation.DISSOLVE);
                break;
            case DISSOLVE:
                this.parent.setAnimation(Animation.TRASLATE);
                break;
        }
    }
    
    /**
     * Updates the toggle button text
     */
    public void updateAnimation() {
        this.animation.setText(this.getAnimationText());
    }

}
