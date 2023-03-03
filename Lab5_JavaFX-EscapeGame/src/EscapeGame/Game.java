package EscapeGame;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Game extends Application {
    /**
     * The width of the stage
     */
    public static double width = 500.0;

    /**
     * The height of the stage
     */
    public static double height = 500.0;

    /**
     * Represents the number every which a new enemy should be added to the game
     */
    final int ADD_EVERY = 10;

    /**
     * Keeps track of the number of iterations in the game
     */
    int iteration = 0;

    /**
     * Represents the game points earned by the user
     */
    int pts = 0;

    /**
     * Represents the current game status. If it's <i>true</i>, the game will stop
     */
    boolean gameOver = false;

    public static void main (String args[]){
        launch(args);
    }

    @Override
    public void start(Stage gameWindow) {

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Striker());
        enemies.add(new Wanderer());
        enemies.add(new Bubbler());

        User user = new User();


        /*
        *
        *
        * Layouts
        *
        *
         */

        //game window layout
        Group layout = new Group();
        layout.getChildren().add(user.circle);

        for(Enemy e : enemies){
            layout.getChildren().add(e.circle);
        }


        Scene scene = new Scene(layout, width, height);
        gameWindow.setTitle("EscapeGame.Escape!");
        gameWindow.setScene(scene);


        //pts window layout
        Stage ptsWindow = new Stage();
        BorderPane ptsLayout = new BorderPane();
        Text ptsTxt = new Text();
//        ptsLayout.getChildren().add(ptsTxt);
        ptsLayout.setCenter(ptsTxt);
        Scene ptsScene = new Scene(ptsLayout, 200.0, 100.0);
        ptsWindow.setScene(ptsScene);


        gameWindow.show();
        ptsWindow.show();

        ptsTxt.setText("Points: " + pts);
        /*
        *
        *
        * Event listeners
        *
        *
        */

        ChangeListener<Number> gameWindowSizeListener = (observable, oldValue, newValue) -> {
            System.out.println("Height: " + gameWindow.getHeight() + " Width: " + gameWindow.getWidth());
            width = gameWindow.getWidth();
            height = gameWindow.getHeight();
        };

        gameWindow.widthProperty().addListener(gameWindowSizeListener);
        gameWindow.heightProperty().addListener(gameWindowSizeListener);



        scene.setOnKeyPressed(e -> {
            if (e.getCode().isArrowKey() && !gameOver) {
                //move user
                switch (e.getCode()) {
                    case UP:
                        System.out.println("Up pressed");
                        user.moveBy(0, -10.0);
                        break;

                    case DOWN:
                        System.out.println("Down pressed");
                        user.moveBy(0, 10.0);
                        break;

                    case LEFT:
                        System.out.println("Left pressed");
                        user.moveBy(-10.0, 0);
                        break;

                    case RIGHT:
                        System.out.println("Right pressed");
                        user.moveBy(10.0, 0);


                }

                //trigger move() for every enemy
                for (Enemy enemy : enemies) {
                    enemy.move();

                    if(user.checkCollision(enemy)){
                        user.setColor(Color.RED);
                        enemy.setColor(Color.RED);
                        gameOver = true;
                    }
                }
                if(!gameOver){
                    pts += 100;
                    ptsTxt.setText("Points: " + pts);
                }else{
                    ptsTxt.setText("Points: " + pts + "\nGAME OVER");
                }




                //add new enemy if necessary
                if(iteration == ADD_EVERY-1){
                    Enemy newEnemy;
                    //if enemy spawned on top of user create new enemy
                    do{
                        newEnemy = getRandomEnemy();
                    }while (user.checkCollision(newEnemy));

                    enemies.add(newEnemy);
                    layout.getChildren().add(newEnemy.circle);

                }

                //increment iteration variable
                iteration = (++iteration) % ADD_EVERY;
            }






        });//end of keyPressed event listener


    }

    /**
     * Generates and returns a random enemy, with equal probabilities
     * @return a random enemy
     */
    Enemy getRandomEnemy(){
        Random random = new Random();
        int n = random.nextInt(3);
        if(n == 0){
            return new Striker();
        }
        if(n == 1){
            return new Wanderer();
        }
        return new Bubbler();
    }

}


