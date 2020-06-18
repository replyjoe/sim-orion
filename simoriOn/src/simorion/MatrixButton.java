package simorion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Matrix button.
 * Used to represent the 16 by 16 central panel buttons.
 * 
 * @author Paul
 */
public class MatrixButton extends Button {
    
    //button location
    private final int row;
    private final int col;
    //mode select listeners:
    private RowLightUp rowLightUpListener;
    private ColumnLightUp colLightUpListener;
    private CrossLightUp crossLightUpListener;
    private MatrixNumber matrixNumberListener;
    //performance mode listeners:
    private LayerStateUpdate layerStateUpdateListener;
    //for the keys in save/load made
    private String[] Alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    //variable to store the map of the voice/instrument name key-value pairs
    private Map<Integer, String> instrumentTable = new HashMap<Integer, String>(); 
    /**
     * A listener to update the state of the MatrixLayer matrix boolean grid.
     * 
     * @author Paul
     */
    private class LayerStateUpdate implements ActionListener {
        /**
         * Overridden actionPerformed method.
         * 
         * @param  click  the action event
         */
        @Override
        public void actionPerformed( ActionEvent click ) {
            //update state in matrix layer
            SimoriGUI gui = SimoriGUI.getInstance();
            gui.matrixLayers[gui.getLayer()].toggleButton(row, col);
        }
    }
    
    /**
     * A listener used during mode selection.
     * Highlights the entire row of a clicked button
     * 
     * @author Vlad
     */
    private class RowLightUp implements ActionListener {
        /**
         * Overridden actionPerformed method.
         * 
         * @param  click  the action event 
         */
        @Override
        public void actionPerformed(ActionEvent click) {
            SimoriGUI.getInstance().resetMatrixColour();
            for (int i = 0; i<16; i++)
                SimoriGUI.getInstance().theMatrix[row][i].setBackground(Color.ORANGE);
        }
    }
    
    /**
     * A listener used during mode selection.
     * Highlights the entire column of a clicked button
     * 
     * @author Paul
     */
    private class ColumnLightUp implements ActionListener {
        /**
         * Overridden actionPerformed method.
         * 
         * @param  click  the action event 
         */
        @Override
        public void actionPerformed(ActionEvent click) {
            SimoriGUI.getInstance().resetMatrixColour();
            for (int i = 0; i<16; i++)
                SimoriGUI.getInstance().theMatrix[i][col].setBackground(Color.ORANGE);
        }
    }
    /**
     * A listener used during mode selection.
     * Highlights the entire row and column of a clicked button
     * 
     * @author Vlad
     */
    private class CrossLightUp implements ActionListener {
        /**
         * Overridden actionPerformed method.
         * 
         * @param  click  the action event 
         */
        @Override
        public void actionPerformed(ActionEvent click) {
            SimoriGUI.getInstance().resetMatrixColour();
            for (int i = 0; i<16; i++)
                SimoriGUI.getInstance().theMatrix[i][col].setBackground(Color.ORANGE);
            for (int i = 0; i<16; i++)
                SimoriGUI.getInstance().theMatrix[row][i].setBackground(Color.ORANGE);
        }
    }
    
    /**
     * A listener used during mode selection.
     * Sets the button number in the corresponding mode button
     * 
     * @author Paul
     * @author Vlad
     * @author Joe
     */
    private class MatrixNumber implements ActionListener {
        /**
         * Overridden actionPerformed method.
         * 
         * @param  click  the action event 
         */
        @Override
        public void actionPerformed(ActionEvent click) {
            int val = row*16;
            if ((row % 2) == 0) {
                val = val + col;
            } else {
                val = val + (15 - col);
            }
            SimoriGUI gui = SimoriGUI.getInstance();
            ModeType current = gui.getModeType();
            if (current == ModeType.PERFORMANCE) {
                //do nothing
            } else if (current == ModeType.L1) { //voice mode
                if (val > 127) {
                    val = val - 128;
                }
                createMap();
                printToDisplay(val);
                gui.voice = val;
                gui.btnL1_ChangeVoice.setValue(val);
            } else if (current == ModeType.L2) { //velocity
                //if value is outside velocity range, bring it back in range
                if (val > 127) {
                    val = val - 128;
                }
                gui.display.setText("Velocity: " + Integer.toString(val));
                gui.btnL2_ChangeVelocity.setValue(val);
            } else if (current == ModeType.L3) { //loop speed
                //if value is outside speed range, bring it back in range
                if (val > 160) {
                    val = val - 161;
                }
                gui.display.setText("Loop speed: " + Integer.toString(val));
                gui.btnL3_ChangeLoopSpeed.setValue(val);
            } else if (current == ModeType.L4) { //loop point
                //use the x axis
                val = col;
                gui.display.setText("Loop point: " + Integer.toString(val));
                gui.btnL4_ChangeLoopPoint.setValue(val);
            } else if (current == ModeType.R1) { //change layer
                //use the y axis
                val = row;
                gui.display.setText("Layer: " + Integer.toString(val));
                gui.btnR1_ChangeLayer.setValue(val);
            } else if (current == ModeType.R2) { //save config
                //val 0 to 25 = letters from a to z
                if (val < 26) {
                    //set filename through keyboard
                    gui.filename = gui.filename + Alphabet[val];
                    //display currect filename
                    gui.display.setText(gui.filename);
                }
            } else if (current == ModeType.R3) { //load config
                if (val < 26) {
                    //set restorefile through keyboard
                    gui.restorefile = gui.restorefile + Alphabet[val];
                    //display currect restorefile
                    gui.display.setText(gui.restorefile);
                }
            } else if (current == ModeType.R4) { //master slave
                gui.btnR4_MasterSlave.setValue(val);
            }
        }
    }
    
    /**
     * MatrixButton constructor.
     * 
     * @param  emptyLabel  empty button label
     * @param  row  the button row
     * @param  col  the button column
     * 
     * @author Paul
     */
    protected MatrixButton( String emptyLabel, int row, int col ) {
        super( emptyLabel );
        this.row = row;
        this.col = col;
        this.layerStateUpdateListener = new LayerStateUpdate();
        this.addActionListener(this.layerStateUpdateListener);
    }
    
    /**
     * A method to add the matrix layer boolean state listener.
     * 
     * @author Paul
     */
    protected void addLayerStateUpdateListener() {
        //assign to variable so it can removed later
        this.layerStateUpdateListener = new LayerStateUpdate();
        this.addActionListener(this.layerStateUpdateListener);
    }
    
    /**
     * A method to remove the matrix layer boolean state listener.
     * 
     * @author Paul
     */
    protected void removeLayerStateUpdateListener() {
        this.removeActionListener(layerStateUpdateListener);
    }
    
    /**
     * A method to add the row light up listener.
     * 
     * @author Vlad
     */
    protected void addRowLightUpListener() {
        //assign to variable so it can be removed later
        this.rowLightUpListener = new RowLightUp();
        this.addActionListener(this.rowLightUpListener);
    }
    
    /**
     * A method to remove the row light up listener.
     * 
     * @author Paul
     */
    protected void removeRowLightUpListener() {
        this.removeActionListener(this.rowLightUpListener);
    }
    
    /**
     * A method to add the column light up listener.
     * 
     * @author Paul
     */
    protected void addColLightUpListener() {
        this.colLightUpListener = new ColumnLightUp();
        this.addActionListener(this.colLightUpListener);
    }
    
    /**
     * A method to remove the column light up listener.
     * 
     * @author Paul
     */
    protected void removeColLightUpListener() {
        this.removeActionListener(this.rowLightUpListener);
    }
    
    /**
     * A method to add the cross light up listener.
     * 
     * @author Vlad
     */
    protected void addCrossLightUpListener() {
        //add both row and column light up listeners
        this.crossLightUpListener = new CrossLightUp();
        this.addActionListener(this.crossLightUpListener);
    }
    
    /**
     * A method to remove the cross light up listener.
     * 
     * @author Paul
     */
    protected void removeCrossLightUpListener() {
        this.removeActionListener(this.crossLightUpListener);
    }
    
    /**
     * A method to add the matrix number listener.
     * 
     * @author Paul
     */
    protected void addMatrixNumberListener() {
        //assign to variable so it can removed later
        this.matrixNumberListener = new MatrixNumber();
        this.addActionListener(this.matrixNumberListener);
    }
    
    /**
     * A method to remove the matrix number listener.
     * 
     * @author Paul
     */
    protected void removeMatrixNumberListener() {
        this.removeActionListener(matrixNumberListener);
    }
    
    /**
     * Method to create a lookup table where each instrument index is matched to its string name.
     * Uses a hashmap.
     * @author Vlad
     */
    public Map<Integer, String> createMap() {
        instrumentTable.put(0, "Acoustic Grand Piano");
        instrumentTable.put(1, "Bright Acoustic Piano");
        instrumentTable.put(2, "Electric Grand Piano");
        instrumentTable.put(3, "Honky-tonk Piano");
        instrumentTable.put(4, "Rhodes Piano");
        instrumentTable.put(5, "Chorused Piano");
        instrumentTable.put(6, "Harpsichord");
        instrumentTable.put(7, "Clavinet");
        instrumentTable.put(8, "Celesta");
        instrumentTable.put(9, "Glockenspiel");
        instrumentTable.put(10, "Music Box");
        instrumentTable.put(11, "Vibraphone");
        instrumentTable.put(12, "Marimba");
        instrumentTable.put(13, "Xylophone");
        instrumentTable.put(14, "Tubular Bells");
        instrumentTable.put(15, "Dulcimer");
        instrumentTable.put(16, "Hammond Organ");
        instrumentTable.put(17, "Percussive Organ");
        instrumentTable.put(18, "Rock Organ");
        instrumentTable.put(19, "Church Organ");
        instrumentTable.put(20, "Reed Organ");
        instrumentTable.put(21, "Accordion");
        instrumentTable.put(22, "Harmonica");
        instrumentTable.put(23, "Tango Accordion");
        instrumentTable.put(24, "Acoustic Nylon Guitar");
        instrumentTable.put(25, "Acoustic Steel Guitar");
        instrumentTable.put(26, "Electric Jazz Guitar");
        instrumentTable.put(27, "Electric Clean Guitar");
        instrumentTable.put(28, "Electric Muted Guitar");
        instrumentTable.put(29, "Overdriven Guitar");
        instrumentTable.put(30, "Distortion Guitar");
        instrumentTable.put(31, "Guitar Harmonics");
        instrumentTable.put(32, "Acoustic Bass");
        instrumentTable.put(33, "Fingered Electric Bass");
        instrumentTable.put(34, "Plucked Electric Bass");
        instrumentTable.put(35, "Fretless Bass");
        instrumentTable.put(36, "Slap Bass1");
        instrumentTable.put(37, "Slap Bass 2");
        instrumentTable.put(38, "Synth Bass 1");
        instrumentTable.put(39, "Synth Bass 2");
        instrumentTable.put(40, "Violin");
        instrumentTable.put(41, "Viola");
        instrumentTable.put(42, "Cello");
        instrumentTable.put(43, "Contrabass");
        instrumentTable.put(44, "Tremolo Strings");
        instrumentTable.put(45, "Pizzicato Strings");
        instrumentTable.put(46, "Orchestral Harp");
        instrumentTable.put(47, "Timpani");
        instrumentTable.put(48, "String Ensemble 1");
        instrumentTable.put(49, "String Ensemble 2");
        instrumentTable.put(50, "Synth Strings 1");
        instrumentTable.put(51, "Synth Strings 2");
        instrumentTable.put(52, "Choir 'Aah'");
        instrumentTable.put(53, "Choir 'Ooh'");
        instrumentTable.put(54, "Synth Voice");
        instrumentTable.put(55, "Orchestral Hit");
        instrumentTable.put(56, "Trumpet");
        instrumentTable.put(57, "Trombone");
        instrumentTable.put(58, "Tuba");
        instrumentTable.put(59, "Muted Trumpet");
        instrumentTable.put(60, "French Horn");
        instrumentTable.put(61, "Brass Section");
        instrumentTable.put(62, "Synth Bass 1");
        instrumentTable.put(63, "Synth Bass 2");
        instrumentTable.put(64, "Soprano Sax");
        instrumentTable.put(65, "Alto Sax");
        instrumentTable.put(66, "Tenor Sax");
        instrumentTable.put(67, "Baritone Sax");
        instrumentTable.put(68, "Oboe");
        instrumentTable.put(69, "English Horn");
        instrumentTable.put(70, "Bassoon");
        instrumentTable.put(71, "Clarinet");
        instrumentTable.put(72, "Piccolo");
        instrumentTable.put(73, "Flute");
        instrumentTable.put(74, "Recorder");
        instrumentTable.put(75, "Pan Flute");
        instrumentTable.put(76, "Bottle Blow");
        instrumentTable.put(77, "Shakuhachi");
        instrumentTable.put(78, "Whistle");
        instrumentTable.put(79, "Ocarina");
        instrumentTable.put(80, "Square Wave Lead");
        instrumentTable.put(81, "Sawtooth Wave Lead");
        instrumentTable.put(82, "Calliope Lead");
        instrumentTable.put(83, "Chiff Lead");
        instrumentTable.put(84, "Charang Lead");
        instrumentTable.put(85, "Voice Lead");
        instrumentTable.put(86, "Fifths Lead");
        instrumentTable.put(87, "Bass Lead");
        instrumentTable.put(88, "New Age Pad");
        instrumentTable.put(89, "Warm Pad");
        instrumentTable.put(90, "Polysynth Pad");
        instrumentTable.put(91, "Choir Pad");
        instrumentTable.put(92, "Bowed Pad");
        instrumentTable.put(93, "Metallic Pad");
        instrumentTable.put(94, "Halo Pad");
        instrumentTable.put(95, "Sweep Pad");
        instrumentTable.put(96, "Rain Effect");
        instrumentTable.put(97, "Soundtrack Effect");
        instrumentTable.put(98, "Crystal Effect");
        instrumentTable.put(99, "Atmosphere Effect");
        instrumentTable.put(100, "Brightness Effect");
        instrumentTable.put(101, "Goblins Effect");
        instrumentTable.put(102, "Echoes Effect");
        instrumentTable.put(103, "Sci-Fi Effect");
        instrumentTable.put(104, "Sitar");
        instrumentTable.put(105, "Banjo");
        instrumentTable.put(106, "Shamisen");
        instrumentTable.put(107, "Koto");
        instrumentTable.put(108, "Kalimba");
        instrumentTable.put(109, "Bagpipe");
        instrumentTable.put(110, "Fiddle");
        instrumentTable.put(111, "Shanai");
        instrumentTable.put(112, "Tinkle Bell");
        instrumentTable.put(113, "Agogo");
        instrumentTable.put(114, "Steel Drums");
        instrumentTable.put(115, "Woodblock");
        instrumentTable.put(116, "Taiko Drum");
        instrumentTable.put(117, "Melodic Tom");
        instrumentTable.put(118, "Synth Drum");
        instrumentTable.put(119, "Reverse Cymbal");
        instrumentTable.put(120, "Guitar Fret Noise");
        instrumentTable.put(121, "Breath Noise");
        instrumentTable.put(122, "Seashore");
        instrumentTable.put(123, "Bird Tweet");
        instrumentTable.put(124, "Telephone Ring");
        instrumentTable.put(125, "Helicopter");
        instrumentTable.put(126, "Applause");
        instrumentTable.put(127, "Gun Shot");
        return instrumentTable;
    }
    /**
     * A method to print the instrument name to the display according to its voice index.
     * @author Vlad
     * @param voice 
     */
    
    public void printToDisplay(int voice){
        SimoriGUI gui = SimoriGUI.getInstance();
        String instrument = instrumentTable.get(voice);
        gui.display.setText(instrument);
    }
}