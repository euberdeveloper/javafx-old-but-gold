package unitn.euber.dicegame;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The class of the game status window
 * @author Eugenio Vinicio Berretta
 */
public class GameStatus extends VBox {

    // Parent instances
    private final GameArea parent;

    // Labels
    private final Text total = new Text();
    private final Text score = new Text();

    /**
     * Constructor of the class GameStatus
     * @param parent the parent instance
     */
    public GameStatus(GameArea parent) {
        this.parent = parent;
        this.total.setText("TOTALE " + this.parent.getTotal());
        this.score.setText("PUNTEGGIO " + this.parent.getScore());
        this.getChildren().addAll(this.total, this.score);
        
        if (this.parent.getDices().size() > 0) {
            for (Dice dice : this.parent.getDices()) {
                Text text = new Text("Dado: " + dice.getIndex() + " Valore: " + dice.getValue());
                this.getChildren().add(text);
            }
        }
    }

}
