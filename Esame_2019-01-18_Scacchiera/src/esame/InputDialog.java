package esame;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 * Input dialog displayed when the game is launched.
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 * @author Gian Pietro Picco
 */
public class InputDialog {
    /**
     * Displays a TextInputDialog and returns the user input
     * @return The user input if present, otherwise it returns null
     */
    static String getResponse() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Dimensione della griglia?");
        Optional<String> result = dialog.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
}
