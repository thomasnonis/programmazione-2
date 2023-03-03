package esame;

import javafx.application.Platform;
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
 * Lose Card.
 * When it is uncovered a prompt pops up, telling the user he lost, then closes the application
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class TesseraP extends Tessera{
    /**
     * The text displayed on the card, when it is uncovered.
     */
    private static final String TEXT = "Perdi subito!";
    
    /**
     * The message shown in the prompt, when it is uncovered.
     */
    private static final String MESSAGE = "Peccato, hai perso!";
    
    /**
     * Default constructor.
     */
    public TesseraP() {
        super();
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void cover() {
        covered = true;
        setText("");
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void uncover() {
        covered = false;
        setText(TEXT);
        System.out.println(MESSAGE);
        
        Label msg = new Label(MESSAGE);
        Button okBtn = new Button("OK");
        VBox root = new VBox();
        
        root.getChildren().addAll(msg, okBtn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(7.0);
        root.setPadding(new Insets(10.0));
        
        Scene scene = new Scene(root);
        Stage prompt = new Stage();
        
        
        okBtn.setOnAction(e -> {
            Platform.exit();
            System.exit(0); //is this needed?
        });
        
        prompt.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0); //is this needed?
        });
        
        
        prompt.setScene(scene);
        prompt.initModality(Modality.APPLICATION_MODAL);
        prompt.initStyle(StageStyle.UTILITY);
        prompt.setTitle(MESSAGE);
        prompt.setResizable(false);
        prompt.setAlwaysOnTop(true);
        prompt.showAndWait();
    }
    
    public String toString(){
        return "P";
    }
    
}
