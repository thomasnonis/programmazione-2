/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.untin.it
 */
public class SumCell extends Cell{


    public int getSum() {
        return value.get();
    }

    public void setSum(int value) {
        this.value.set(value);
    }

    public IntegerProperty sumProperty() {
        return value;
    }
    
    SumCell(Color color){
        super(color);
        setValue(0);
    }
    
}
