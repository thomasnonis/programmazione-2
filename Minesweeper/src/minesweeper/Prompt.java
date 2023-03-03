package minesweeper;

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
 * Prompt class, used to display the WIN/LOSS popup
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Prompt {
    public static enum Status{
        VICTORY("Hai vinto!"),
        LOSS("Hai perso :(");
        
        private String msg;
        Status(String msg){
            this.msg = msg;
        }
        
        public String getMsg(){
            return msg;
        }
    }
    
    Prompt(Status status){
        Button btn = new Button("Ok");
        Label txt = new Label(status.getMsg());
        VBox root = new VBox(txt, btn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5.0);
        root.setPadding( new Insets(5.0, 15.0, 5.0, 15.0));
        Scene scene = new Scene(root);
        Stage prompt = new Stage();
        
        btn.setOnAction(e -> {
            prompt.close();
        });
        
        prompt.setScene(scene);
        prompt.sizeToScene();
        prompt.initModality(Modality.APPLICATION_MODAL);
        prompt.initStyle(StageStyle.UTILITY);
        prompt.showAndWait();
    }
}
