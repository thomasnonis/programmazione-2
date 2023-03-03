/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Thomas
 */
public class Persona extends Record{
    private static ArrayList<Persona> persone = new ArrayList<>();
    
    public Persona(String nome, String cognome, Integer annoDiNascita) {
        super(nome, cognome, annoDiNascita);
        //TODO: checking
        persone.add(this);
    }
    
    public String getNome() {
        return field1;
    }

    public String getCognome() {
        return field2;
    }

    public Integer getAnnoDiNascita() {
        return field3;
    }
    
    public ArrayList<Persona> getPersone(){
        return persone;
    }

    public void setNome(String nome) {
        field1 = nome;
    }

    public void setCognome(String cognome) {
        field2 = cognome;
    }

    public void setAnnoDiNascita(Integer annoDiNascita) {
        field3 = annoDiNascita;
    }

    public static VBox getInterface() {
        Text txt = new Text();
        
        Button shuffleBtn = new Button("Mescola");
        Button orderBtn = new Button("Ordina");
        Button countBtn = new Button("Conta");
        Button orderSpecialBtn = new Button("Ordina per anno");
        Button newBtn = new Button("New Persona");
        
        shuffleBtn.setOnAction(e -> {
            System.out.println("Shuffling...");
            Collections.shuffle(persone);
            //easyPrint
        });
        
        newBtn.setOnAction(e -> {
            Stage insert = new Stage();
            AnchorPane root = new AnchorPane();
            
            Button confirmBtn = new Button("controlla e inserisci");
            
            TextField field1 = new TextField();
            TextField field2 = new TextField();
            TextField field3 = new TextField();
            Label field1Label = new Label("Field 1");
            Label field2Label = new Label("Field 2");
            Label field3Label = new Label("Field 3");
            
            
            VBox labels = new VBox(field1Label, field2Label, field3Label);
            VBox fields = new VBox(field1, field2, field3);
            HBox layout = new HBox(labels, fields);
            
            root.getChildren().addAll(layout, confirmBtn);
            AnchorPane.setTopAnchor(layout, 0.0);
            AnchorPane.setBottomAnchor(confirmBtn, 0.0);
            AnchorPane.setLeftAnchor(confirmBtn, 0.0);
            
           Scene scene = new Scene(root);
           insert.setScene(scene);
           insert.initModality(Modality.APPLICATION_MODAL);
           insert.showAndWait();
        });
        
        
        
        return new VBox(txt, shuffleBtn, orderBtn, countBtn, orderSpecialBtn, newBtn);
    }
    
    
    
}
