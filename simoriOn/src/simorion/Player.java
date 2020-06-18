package simorion;

/**
 * A class to implement the sound player.
 * 
 * @author Paul
 */
public class Player extends Midi {
    
    /**
     * Player constructor.
     * 
     * @author Paul
     */
    public Player() {}
    
    /**
     * A method to play an instrument for each active button in a column.
     * 
     * @param gui SimoriGUI instance
     * @param col the column
     * @param layer the layer
     * @param layerVoice the layer voice
     * @author Paul
     */
    private void playCol( SimoriGUI gui, int col, int layer, int layerVoice ) {
        //pitch scale: C, D, E, F, G, A, B, middle C, D, E, F, G, A, B, C, D
        //play instrument(x, channel, instrument, pitch, velocity)
        if (gui.matrixLayers[layer].matrixGrid[0][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 48, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[1][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 50, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[2][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 52, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[3][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 53, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[4][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 55, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[5][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 57, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[6][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 59, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[7][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 60, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[8][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 62, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[9][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 64, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[10][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 65, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[11][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 67, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[12][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 69, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[13][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 71, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[14][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 72, (gui.velocity));
        }
        if (gui.matrixLayers[layer].matrixGrid[15][col] == true) {
            playInstrument(getSynthesizer(), layer, layerVoice, 74, (gui.velocity));
        }
    }
    
    
    
    /**
     * A method to play an percussion for each active button in a column.
     * 
     * @param gui SimoriGUI instance
     * @param col the column
     * @author Joe
     */
    private void playPercussionCol( SimoriGUI gui, int col) {
        //pitch changes the percussion type
        //change type needs to be implemented
        if (gui.matrixLayers[9].matrixGrid[0][col] == true) {
            playPercussion( getSynthesizer(), 48, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[1][col] == true) {
            playPercussion( getSynthesizer(), 50, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[2][col] == true) {
            playPercussion( getSynthesizer(), 52, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[3][col] == true) {
            playPercussion( getSynthesizer(), 53, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[4][col] == true) {
            playPercussion( getSynthesizer(), 55, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[5][col] == true) {
            playPercussion( getSynthesizer(), 57, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[6][col] == true) {
            playPercussion( getSynthesizer(), 59, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[7][col] == true) {
            playPercussion( getSynthesizer(), 60, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[8][col] == true) {
            playPercussion( getSynthesizer(), 62, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[9][col] == true) {
            playPercussion( getSynthesizer(), 64, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[10][col] == true) {
            playPercussion( getSynthesizer(), 65, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[11][col] == true) {
            playPercussion( getSynthesizer(), 67, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[12][col] == true) {
            playPercussion( getSynthesizer(), 69, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[13][col] == true) {
            playPercussion( getSynthesizer(), 71, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[14][col] == true) {
            playPercussion( getSynthesizer(), 72, (gui.velocity));
        }
        if (gui.matrixLayers[9].matrixGrid[15][col] == true) {
            playPercussion( getSynthesizer(), 74, (gui.velocity));
        }
    }
    
    /**
     * A method to play a sound given a column and row
     * 
     * @param col the column
     * @author Paul
     */
    protected void play( int col ) {
        SimoriGUI gui = SimoriGUI.getInstance();
        for (int layer = 0; layer < 16; layer++) {
            if (layer == 9){
                playPercussionCol(gui, col);
            } else {
                int layerVoice = gui.matrixLayers[layer].getVoice();
                playCol( gui, col, layer, layerVoice );
            }
        }
    }
    
    /**
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    this.wait();
                }
                
            } catch (InterruptedException e) {
                //do nothing
                return;
            }
            play(0);
        }
    }
    */
}
