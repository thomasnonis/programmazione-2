/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Thomas
 */
public class Main extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println(new Carta(0, "J"));
    }
    
}
