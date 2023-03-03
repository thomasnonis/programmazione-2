/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Thomas
 */
public class Automobile extends Record{
    static ArrayList<Automobile> automobili = new ArrayList<>();
    
    public Automobile(String marca, String modello, Integer prezzo) {
        super(marca, modello, prezzo);
        //TODO: checking
        automobili.add(this);
    }

    public String getMarca() {
        return field1;
    }

    public String getModello() {
        return field2;
    }

    public Integer getPrezzo() {
        return field3;
    }

    public void setMarca(String marca) {
        this.field1 = marca;
    }

    public void setModello(String modello) {
        this.field2 = modello;
    }

    public void setPrezzo(Integer prezzo) {
        this.field3 = prezzo;
    }

    public static VBox getInterface() {
        Text txt = new Text();
        
        Button shuffleBtn = new Button("Mescola");
        Button orderBtn = new Button("Ordina");
        Button countBtn = new Button("Conta");
        Button orderSpecialBtn = new Button("Ordina per modello??");
        Button newBtn = new Button("New Auto");
        
        return new VBox(txt, shuffleBtn, orderBtn, countBtn, orderSpecialBtn, newBtn);
    }
    
    
}
