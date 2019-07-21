package unitn.euber.dicegame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The class of the game labels
 * @author Eugenio Vinicio Berretta
 */
public class GameLabels extends TilePane {
    
    // Static fields for ui
    private static final double SPACING = 15.0;
    private static final double PADDING = 12.0;
    private static final Color LABEL_COLOR = Color.WHITE;
    private static final String TOTAL_LABEL_PREFIX = "TOTALE: ";
    private static final String SCORE_LABEL_PREFIX = "PUNTEGGIO: ";
    
    // Parent instance
    private final GameArea parent;
    // Label elements
    private final Text total = new Text();
    private final Text score = new Text();

    /**
     * The constructor of the class containing the status labels
     * @param parent The parent of the instance
     */
    public GameLabels(GameArea parent) {
        this.parent = parent;
        this.setTotalLabel();
        this.setScoreLabel();
        this.total.setFill(GameLabels.LABEL_COLOR);
        this.score.setFill(GameLabels.LABEL_COLOR);
        this.getChildren().addAll(this.total, this.score);
        this.setAlignment(Pos.CENTER);
        this.setPrefColumns(2);
        this.setHgap(GameLabels.SPACING);
        this.setVgap(GameLabels.SPACING);
        this.setPadding(new Insets(GameLabels.PADDING));
    }
    
    // Gets the label of the total
    private String getTotalLabel() {
        return GameLabels.TOTAL_LABEL_PREFIX + this.parent.getTotal();
    }
    // Gets the label of the score
    private String getScoreLabel() {
        return GameLabels.SCORE_LABEL_PREFIX + this.parent.getScore();
    }
    
    /**
     * Updates the total displayed by the label
     */
    public final void setTotalLabel() {
        this.total.setText(this.getTotalLabel());
    }

    /**
     * Updates the score displayed by the label
     */
    public final void setScoreLabel() {
        this.score.setText(this.getScoreLabel());
    }
    
}
