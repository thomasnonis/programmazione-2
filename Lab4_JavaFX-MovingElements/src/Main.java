import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main (String args[]){
        launch(args);
    }

    @Override
    public void start(Stage window) {
        Button moveBtn = new Button("Move circle");
        Button resetBtn = new Button("Reset");
        Button exitBtn = new Button("Exit");
        Circle circle = new Circle(250.0, 150.0, 50.0, Color.OLIVE);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(moveBtn, resetBtn, exitBtn);
        buttons.setSpacing(8.0);
        buttons.setPadding(new Insets(0,3,3,0));

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(buttons, circle);
        anchorPane.setBottomAnchor(buttons, 8.0);
        anchorPane.setRightAnchor(buttons, 5.0);

        Scene scene = new Scene(anchorPane, 500, 300);
        window.setTitle("Escape!");
        window.setScene(scene);
        window.show();

        /*
        System.out.println("Scene width: " + scene.getWidth());
        System.out.println("Scene height: " + scene.getHeight());
        System.out.println("Window width: " + window.getWidth());
        System.out.println("Window height: " + window.getHeight());
         */

        circle.setOnMouseEntered(e -> {
            System.out.println("Entered circle");
            circle.setCenterX(circle.getCenterX() - 10.0);
            if(circle.getCenterX() < 0){
                circle.setCenterX(scene.getWidth());
            }
        });

        moveBtn.setOnAction(event -> {
            System.out.println("Button pressed");
            circle.setCenterX(circle.getCenterX() + 10.0);
            if(circle.getCenterX() > scene.getWidth()){
                circle.setCenterX(0d);
            }
        });

        resetBtn.setOnAction(e -> {
            System.out.println("Reset");
            circle.setCenterX(250.0);
            circle.setCenterY(150.0);
        });

        exitBtn.setOnAction(event -> {
            System.out.println("Closing application...");
            window.close();
            System.exit(0);
        });

        scene.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case UP:
                    System.out.println("Up pressed");
                    circle.setCenterY(circle.getCenterY() - 10.0);
                    if(circle.getCenterY() < 0){
                        circle.setCenterY(scene.getHeight());
                    }
                    break;

                case DOWN:
                    System.out.println("Down pressed");
                    circle.setCenterY(circle.getCenterY() + 10.0);
                    if(circle.getCenterY() > scene.getHeight()){
                        circle.setCenterY(0d);
                    }
                    break;
/*
                case LEFT:
                    System.out.println("Left pressed");
                    circle.setCenterX(circle.getCenterX() - 10.0);
                    if(circle.getCenterX() < 0){
                        circle.setCenterX(scene.getWidth());
                    }
                    break;

                case RIGHT:
                    System.out.println("Right pressed");
                    circle.setCenterX(circle.getCenterX() + 10.0);
                    if(circle.getCenterX() > scene.getWidth()){
                        circle.setCenterX(0d);
                    }

 */
            }
        });


    }

}


