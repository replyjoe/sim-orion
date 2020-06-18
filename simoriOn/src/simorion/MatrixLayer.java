package simorion;

import java.io.Serializable;

/**
 * A class to represent logical grid layers.
 * 
 * @author Paul
 */
public class MatrixLayer implements Serializable{
    //representation of grid
    protected boolean[][] matrixGrid;
    //voice variable
    private int voice;
    
    /**
     * MatrixLayer constructor.
     * 
     * @author Paul
     */
    public MatrixLayer() {
        //when initialised, default values are false
        this.matrixGrid = new boolean[16][16];
        this.voice = 0;
    }
    
    /**
     * A method to reset the grid.
     * 
     * @author Paul
     */
    protected void resetGrid() {
        this.matrixGrid = new boolean[16][16];
    }
    
    /**
     * A method to update the state of the grid.
     * Alters grid to true if button is toggled on and vice versa
     * 
     * @param  row  the buttons row
     * @param  col  the buttons column
     * @author Paul
     */
    protected void toggleButton(int row, int col) {
        //find out whether the button is toggled or not
        boolean state = SimoriGUI.getInstance().theMatrix[row][col].getState();
        //updated in layer before actual button
        this.matrixGrid[row][col] = !state;
    }
    
    /**
     * A method to change voice.
     * 
     * @param  newVoice  the new voice
     * @author Paul
     */
    protected void setVoice (int newVoice){
        //check whether voice is valid
        if (newVoice < 0 || newVoice > 175) {
            System.out.println("Invalid voice.");
        } else {
            this.voice = newVoice;
        }
    }
    
    /**
     * @return voice
     * @author Paul
     */
    protected int getVoice() {
        return this.voice;
    }
}