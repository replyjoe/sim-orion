package simorion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Mode change button.
 * Used to implement L1-4 and R1-4 buttons
 * 
 * @author Paul
 */
public class ModeButton extends Button {
    
    //variable to keep track of button name
    private final String modeType;
    //variable to store result of mode selection
    //possible values depend on current mode
    private int value;
    
     /**
     * An action listener which changes mode on-click.
     * Determines mode using button label.
     * 
     * @author Paul
     * @author Vlad
     */
    private class ModeSelect implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent click ) {
            SimoriGUI gui = SimoriGUI.getInstance();
            if (gui.getModeType() == ModeType.PERFORMANCE) {
                gui.enableModeButtons(false);
                if (ModeType.valueOf(modeType) == ModeType.L1) { //voice mode
                    gui.btnL1_ChangeVoice.setEnabled(true);
                    changeToModeListener_Cross();
                    gui.setModeType(ModeType.L1);
                    gui.display.setText("Voice:");
                } else if (ModeType.valueOf(modeType) == ModeType.L2) { //velocity
                    gui.btnL2_ChangeVelocity.setEnabled(true);
                    changeToModeListener_Cross();
                    gui.setModeType(ModeType.L2);
                    gui.display.setText("Velocity:");
                } else if (ModeType.valueOf(modeType) == ModeType.L3) { //loop speed
                    gui.btnL3_ChangeLoopSpeed.setEnabled(true);
                    changeToModeListener_Cross();
                    gui.setModeType(ModeType.L3);
                    gui.display.setText("Loop speed:");
                } else if (ModeType.valueOf(modeType) == ModeType.L4) { //loop point
                    gui.btnL4_ChangeLoopPoint.setEnabled(true);
                    changeToModeListener_Col();
                    gui.setModeType(ModeType.L4);
                    gui.display.setText("Loop point:");
                } else if (ModeType.valueOf(modeType) == ModeType.R1) { //change layer
                    gui.btnR1_ChangeLayer.setEnabled(true);
                    changeToModeListener_Row();
                    gui.setModeType(ModeType.R1);
                    gui.display.setText("Layer:");
                } else if (ModeType.valueOf(modeType) == ModeType.R2) { //save config
                    gui.btnR2_SaveConfig.setEnabled(true);
                    changeToModeListener_Cross();
                    gui.setModeType(ModeType.R2);
                    gui.display.setText("Choose a filename");
                } else if (ModeType.valueOf(modeType) == ModeType.R3) { //load config
                    gui.btnR3_LoadConfig.setEnabled(true);
                    changeToModeListener_Cross();
                    gui.setModeType(ModeType.R3);
                    gui.display.setText("Enter name of file (without extension)");
                } else if (ModeType.valueOf(modeType) == ModeType.R4) { //master slave
                    gui.btnR4_MasterSlave.setEnabled(true);
                    System.out.println("R4");
                    gui.setModeType(ModeType.R4);
                    gui.display.setText("You are a slave");
                }
            } else {
                gui.enableModeButtons(true);
                gui.setPerformanceMode();
                gui.setModeType(ModeType.PERFORMANCE);
            }
        }
    }
    
    /**
     * ModeButton constructor.
     * 
     * @param  label  the button label
     * @author Paul
     */
    public ModeButton( String label ) {
        super( label );
        this.modeType = label;
        this.value = -1;
        this.addActionListener(new ModeSelect());
    }
    
    /**
     * A method to change the matrix button listeners.
     * Removes matrix layer update state listener and adds mode light up (row)
     * 
     * @author Paul
     */
    private void changeToModeListener_Row() {
        SimoriGUI gui = SimoriGUI.getInstance();
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                //remove all listeners (colour change and layer state update)
                gui.theMatrix[row][col].resetButton();
                gui.theMatrix[row][col].addRowLightUpListener();
                gui.theMatrix[row][col].addMatrixNumberListener();
            }
        }
    }
    
    /**
     * A method to change the matrix button listeners.
     * Removes matrix layer update state listener and adds mode light up (col)
     * 
     * @author Paul
     */
    private void changeToModeListener_Col() {
        SimoriGUI gui = SimoriGUI.getInstance();
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                //remove all listeners (colour change and layer state update)
                gui.theMatrix[row][col].resetButton();
                gui.theMatrix[row][col].addColLightUpListener();
                gui.theMatrix[row][col].addMatrixNumberListener();
            }
        }
    }
    
    /**
     * A method to change the matrix button listeners.
     * Removes matrix layer update state listener and adds mode light up (cross)
     * 
     * @author Paul
     */
    private void changeToModeListener_Cross() {
        SimoriGUI gui = SimoriGUI.getInstance();
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                //remove all listeners (colour change and layer state update)
                gui.theMatrix[row][col].resetButton();
                gui.theMatrix[row][col].addCrossLightUpListener();
                gui.theMatrix[row][col].addMatrixNumberListener();
            }
        }
    }
    
    /**
     * @return value
     * @author Paul
     */
    protected int getValue() {
        return this.value;
    }
    
    /**
     * A method to set the mode value.
     * 
     * @param  num  the number of the matrix button
     * @author Paul
     */
    protected void setValue(int num) {
        this.value = num;
    }
}
