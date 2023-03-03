package minesweeper;

import javafx.scene.control.Label;

/**
 * Normal cell
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Normal extends Cell{
    /**
     * Keeps track of the cell's status
     */
    private boolean isActive;
    
    /**
     * The label that represents the number of surrounding bombs
     */
    Label lbl = new Label();
    
    Normal(){
        isActive = true;
    }
    
    /**
     * Triggers the cell
     * @param mainRef The reference to the main Class
     */
    public void trigger(Minesweeper mainRef){
        if(isActive){
            super.trigger(mainRef);
            this.getChildren().add(lbl);
            mainRef.cellsLeft--;
            isActive = false;
        }
    }
    
    /**
     * Computes the number of surrounding bombs
     * @param cells The grid of cells, that keeps the locations
     * @param x The X coordinate of the current cell
     * @param y The Y coordinate of the current cell
     */
    public void setCloseBombs(Cell[][] cells, int x, int y){
        int n = 0;
        
        //LOL
        if(x > 0 && y > 0 && cells[x-1][y-1] instanceof Bomb) n++;    //top left
        if(x > 0 && cells[x-1][y] instanceof Bomb) n++;      //left
        if(x > 0 && y < cells.length-1 && cells[x-1][y+1] instanceof Bomb) n++;    //bottom left
        if(y > 0 && cells[x][y-1] instanceof Bomb) n++;      //top
        if(x < cells.length - 1 && y > 0 && cells[x+1][y-1] instanceof Bomb) n++;    //top right
        if(x < cells.length-1 && cells[x+1][y] instanceof Bomb) n++;      //right
        if(y < cells.length-1 && cells[x][y+1] instanceof Bomb) n++;      //bottom
        if(x < cells.length-1 && y < cells.length-1 && cells[x+1][y+1] instanceof Bomb) n++;    //bottom right

        lbl.setText( Integer.toString(n) );
    }
}
