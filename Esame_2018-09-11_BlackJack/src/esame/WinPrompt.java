package esame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Utility class for the prompt shown when a user wins
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class WinPrompt {
    /**
     * Shows a prompt window, congratulating the winner. When the prompt is closed, the application is stopped
     * @param msg 
     */
    public static void showPrompt(String msg){
        System.out.println(msg);
            
        Label txt = new Label(msg);
        Button closeBtn = new Button("Close");
        VBox root = new VBox(txt, closeBtn);
        Scene scene = new Scene(root);
        Stage winPrompt = new Stage();

        root.setSpacing(10.0);
        root.setPadding(new Insets(20.0));
        root.setAlignment(Pos.CENTER);

        closeBtn.setOnAction(e -> {
            System.exit(0);
        });

        winPrompt.setOnCloseRequest(e -> {
            System.exit(0);
        });

        winPrompt.setScene(scene);
        winPrompt.sizeToScene();
        winPrompt.setResizable(false);
        winPrompt.setTitle("Vittoria!");
        winPrompt.initModality(Modality.APPLICATION_MODAL);
        winPrompt.initStyle(StageStyle.UTILITY);
        winPrompt.showAndWait();
    }
    
}
