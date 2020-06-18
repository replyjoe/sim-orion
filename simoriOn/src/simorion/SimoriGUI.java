package simorion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.Serializable;

/**
 * @author Paul
 */
public class SimoriGUI extends JFrame implements Serializable {
    
    //a variable to determine which layer is currently active
    //between 0 and 15 to represent 16 possible layers
    protected int currentLayer;
    //loop point variable, should only be between 0 and 15
    //dictates the clock hand loop
    protected int loopPoint;
    //note velocity
    protected int velocity;
    //between 0 and 160, if 0, nothing is played
    protected double loopSpeed;
    //current mode
    private ModeType currentMode;
    //filename
    protected String filename;
    protected String restorefile;
    //voice
    protected int voice;
    
    protected Label            display;
    protected OnOffButton      onOffBtn;
    protected ModeButton       btnL1_ChangeVoice;
    protected ModeButton       btnL2_ChangeVelocity;
    protected ModeButton       btnL3_ChangeLoopSpeed;
    protected ModeButton       btnL4_ChangeLoopPoint;
    protected ModeButton       btnR1_ChangeLayer;
    protected ModeButton       btnR2_SaveConfig;
    protected ModeButton       btnR3_LoadConfig;
    protected ModeButton       btnR4_MasterSlave;
    protected MatrixLayer[]    matrixLayers;
    protected OKButton         okBtn;
    protected MatrixButton[][] theMatrix;
    
    public static final SimoriGUI INSTANCE = new SimoriGUI();
    
    /**
     * Simori-On GUI constructor.
     * 
     * @author Joe
     * @author Paul
     */
    private SimoriGUI() {
        //state variables
        this.voice = 0; //piano
        this.currentLayer = 0;
        this.loopPoint = 15;
        this.velocity = 10;
        this.loopSpeed = 125;
        this.currentMode = ModeType.PERFORMANCE;
        this.filename = "";
        this.restorefile = "";
        
        //buttons
        this.display                = new Label();
        this.onOffBtn               = new OnOffButton( "On" );
        this.btnL1_ChangeVoice      = new ModeButton( "L1" );
        this.btnL2_ChangeVelocity   = new ModeButton( "L2" );
        this.btnL3_ChangeLoopSpeed  = new ModeButton( "L3" );
        this.btnL4_ChangeLoopPoint  = new ModeButton( "L4" );
        this.btnR1_ChangeLayer      = new ModeButton( "R1" );
        this.btnR2_SaveConfig       = new ModeButton( "R2" );
        this.btnR3_LoadConfig       = new ModeButton( "R3" );
        this.btnR4_MasterSlave      = new ModeButton( "R4" );
        this.matrixLayers           = new MatrixLayer[16];
        this.okBtn                  = new OKButton( "OK" );
        this.theMatrix              = new MatrixButton[16][16];
        
        setTitle( "Simori-On" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //extra 20 for the matrix buttons to look square
        setSize(700, 720);
        
        //splitting the frame into 5 different panels
        JPanel panelUp      = new JPanel();
        JPanel panelCenter  = new JPanel();
        JPanel panelRight   = new JPanel();
        JPanel panelLeft    = new JPanel();
        JPanel panelDown    = new JPanel();

        add(panelUp, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelRight, BorderLayout.EAST);
        add(panelLeft, BorderLayout.WEST);
        add(panelDown, BorderLayout.SOUTH);

        panelUp.setPreferredSize(new Dimension(70, 70));
        panelCenter.setPreferredSize(new Dimension(560, 560));    
        panelRight.setPreferredSize(new Dimension(70,70));
        panelLeft.setPreferredSize(new Dimension(70,70));
        panelDown.setPreferredSize(new Dimension(70,70));
    
        //turns center panel into a 16 by 16 grid (J)
        panelCenter.setLayout(new GridLayout(16,16,0,0));
        
        //grid panel origin is top left.
        //create new coordinates to have origin bottom left.
        int logicalX = 15;
        //build the central panel
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                this.theMatrix[logicalX][y] = new MatrixButton("", logicalX, y);
                //add new matrix button to grid
                panelCenter.add(this.theMatrix[logicalX][y]);
            }
            logicalX--;
        }
        
        //build the matrixLayer array
        for (int layer = 0; layer <= 15; layer++) {
            this.matrixLayers[layer] = new MatrixLayer();
        }
        
        //button positioning
        panelUp.setLayout(null);
        panelDown.setLayout(null);
        panelLeft.setLayout(null);
        panelRight.setLayout(null);
        
        this.display.setBounds ( 140, 15, 260, 30 );  panelDown.add( this.display );
        this.display.setBackground(Color.WHITE);
        this.okBtn.setBounds( 440, 5, 60, 60 ); panelDown.add( this.okBtn );
        this.onOffBtn.setBounds( 320, 10, 60, 60 ); panelUp.add( this.onOffBtn );
        
        this.btnL1_ChangeVoice.setBounds( 5, 50, 60, 60 ); panelLeft.add( this.btnL1_ChangeVoice );
        this.btnL2_ChangeVelocity.setBounds( 5, 150, 60, 60 ); panelLeft.add( this.btnL2_ChangeVelocity );
        this.btnL3_ChangeLoopSpeed.setBounds( 5, 250, 60, 60 ); panelLeft.add( this.btnL3_ChangeLoopSpeed );
        this.btnL4_ChangeLoopPoint.setBounds( 5, 350, 60, 60 ); panelLeft.add( this.btnL4_ChangeLoopPoint );
        
        this.btnR1_ChangeLayer.setBounds( 5, 50, 60, 60 ); panelRight.add( this.btnR1_ChangeLayer);
        this.btnR2_SaveConfig.setBounds( 5, 150, 60, 60 ); panelRight.add( this.btnR2_SaveConfig);
        this.btnR3_LoadConfig.setBounds( 5, 250, 60, 60 ); panelRight.add( this.btnR3_LoadConfig);
        this.btnR4_MasterSlave.setBounds( 5, 350, 60, 60 ); panelRight.add( this.btnR4_MasterSlave);
    }
    
    /**
     * Instance access method for singleton class.
     * 
     * @return INSTANCE
     * @author Paul
     */
    public static SimoriGUI getInstance() {
        return INSTANCE;
    }
    
    /**
     * @return currentLayer
     * @author Paul
     */
    protected int getLayer() {
        return this.currentLayer;
    }
    
    /**
     * A method to set the current layer.
     * 
     * @param  layer  the new current layer
     * @author Paul
     */
    protected void setLayer(int layer) {
        //check whether given layer is valid
        SimoriGUI gui = SimoriGUI.getInstance();
        if (layer < 0 || layer > 15) {
            gui.display.setText("Invalid layer.");
        } else {
            this.currentLayer = layer;
        }
    }
    
    /**
     * @return loopPoint
     * @author Paul
     */
    protected int getLoopPoint() {
        return this.loopPoint;
    }
    
    /**
     * A method to change loop point.
     * 
     * @param  newLoopPoint  the new loop point
     * @author Paul
     */
    protected void setLoopPoint (int newLoopPoint) {
        //check whether loop point is valid
        SimoriGUI gui = SimoriGUI.getInstance();
        if (newLoopPoint < 0 || newLoopPoint > 15) {
           gui.display.setText("Invalid loop point.");
        } else {
            this.loopPoint = newLoopPoint;
        }
    }
    
    /**
     * @return velocity
     * @author Paul
     */
    protected int getVelocity() {
        return this.velocity;
    }
    
    /**
     * A method to change note velocity.
     * 
     * @param  newVelocity  the new velocity
     * @author Paul
     */
    protected void setVelocity (int newVelocity){
        SimoriGUI gui = SimoriGUI.getInstance();
        //check whether velocity is valid
        if (newVelocity < 0 || newVelocity > 127) {
            gui.display.setText("Invalid velocity.");
        } else {
            this.velocity = newVelocity;
        }
    }
    
    /**
     * @return loopSpeed
     * @author Paul
     */
    protected double getLoopSpeed() {
        return this.loopSpeed;
    }
    
    /**
     * A method to change loop speed.
     * 
     * @param  newSpeed  the new loop speed
     * @author Paul
     */
    protected void setLoopSpeed (double newSpeed) {
        SimoriGUI gui = SimoriGUI.getInstance();
        //check whether loop speed is valid
        if (newSpeed < 0 || newSpeed > 160) {
            gui.display.setText("Invalid loop speed.");
        } else {
            this.loopSpeed = newSpeed;
        }
    }
    
    /**
     * @return current mode type.
     * @author Paul
     */
    protected ModeType getModeType() {
        return this.currentMode;
    }
    
    /**
     * A method to change the mode type.
     * 
     * @param  newMode  the new mode
     * @author Paul
     */
    protected void setModeType(ModeType newMode) {
        this.currentMode = newMode;
    }
    
    
    
    /**
     * A method to reset the matrix grid colour.
     * 
     * @author Paul
     */
    protected void resetMatrixColour() {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                this.theMatrix[row][col].resetColour();
            }
        }
    }
    
    /**
     * A method to disable other mode buttons while one is pressed.
     * Currently disables all buttons.
     * 
     * @param  enabled  whether to enable or disable the button
     * @author Paul
     */
    protected void enableModeButtons(boolean enabled) {
        this.btnL1_ChangeVoice.setEnabled(enabled);
        this.btnL2_ChangeVelocity.setEnabled(enabled);
        this.btnL3_ChangeLoopSpeed.setEnabled(enabled);
        this.btnL4_ChangeLoopPoint.setEnabled(enabled);
        this.btnR1_ChangeLayer.setEnabled(enabled);
        this.btnR2_SaveConfig.setEnabled(enabled);
        this.btnR3_LoadConfig.setEnabled(enabled);
        this.btnR4_MasterSlave.setEnabled(enabled);
    }
    
    /**
     * A method to turn off all mode buttons.
     * Uses RoundButton method to reset colour as well as disable
     * 
     * @author Paul
     */
    protected void turnOffModeButtons() {
        this.btnL1_ChangeVoice.turnOff();
        this.btnL2_ChangeVelocity.turnOff();
        this.btnL3_ChangeLoopSpeed.turnOff();
        this.btnL4_ChangeLoopPoint.turnOff();
        this.btnR1_ChangeLayer.turnOff();
        this.btnR2_SaveConfig.turnOff();
        this.btnR3_LoadConfig.turnOff();
        this.btnR4_MasterSlave.turnOff();
    }
    
    /**
     * A method to revert the matrix button listeners back to performance mode.
     * 
     * @author Paul
     * @author Vlad
     */
    protected void setPerformanceMode() {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                //remove all listeners (mode light up listeners)
                this.theMatrix[row][col].resetButton();
                this.theMatrix[row][col].addColourChangeListener();
                this.theMatrix[row][col].addLayerStateUpdateListener();
                if (this.matrixLayers[this.currentLayer].matrixGrid[row][col] == true) {
                    //redisplay toggled buttons
                    this.theMatrix[row][col].changeColour();
                }
            }
        }
    }
    
    /**
     * A method to reset the GUI.
     * 
     * @author Paul
     */
    protected void resetGUI() {
        //reset state variables
        this.currentLayer = 0;
        this.loopPoint = 15;
        this.velocity = 0;
        this.loopSpeed = 125;
        this.filename = "";
        //clear display
        this.display.setText("");
        //disable buttons
        this.turnOffModeButtons();
        this.okBtn.turnOff();
        //remove colour listeners to matrix buttons
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                this.theMatrix[row][col].turnOff();
            }
            this.matrixLayers[row].resetGrid();
        }
        //reset mode type to performance
        this.setModeType(ModeType.PERFORMANCE);
    }
}