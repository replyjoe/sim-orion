package simorion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;


/**
 * OK button.
 * Used to confirm the mode selection.
 * 
 * @author Paul
 * @author Vlad
 * @author Joe
 */
public class OKButton extends Button {
    
    /**
     * An action listener which adds on-click mode selection.
     * Sets the chosen mode and returns to performance mode
     * 
     * @author Paul
     * @author Vlad
     */
    private class ModeConfirmation implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent click ) {
            SimoriGUI gui = SimoriGUI.getInstance();
            ModeType current = gui.getModeType();
            if (current == ModeType.PERFORMANCE) {
                //do nothing
            } else {
                //set mode and return to performance mode
                gui.enableModeButtons(true);
                gui.setModeType(ModeType.PERFORMANCE);
                //display current layer and instrument? (P)
                if (current == ModeType.L1) { //voice mode
                    gui.btnL1_ChangeVoice.resetColour();
                    int voice = gui.btnL1_ChangeVoice.getValue();
                    int currentLayer = gui.getLayer();
                    gui.matrixLayers[currentLayer].setVoice(voice);
                    gui.btnL1_ChangeVoice.toggleFalse();
                } else if (current == ModeType.L2) { //velocity
                    gui.btnL2_ChangeVelocity.resetColour();
                    int velocity = gui.btnL2_ChangeVelocity.getValue();
                    gui.setVelocity(velocity);
                    gui.btnL2_ChangeVelocity.toggleFalse();
                } else if (current == ModeType.L3) { //loop speed
                    gui.btnL3_ChangeLoopSpeed.resetColour();
                    int lSpeed = gui.btnL3_ChangeLoopSpeed.getValue();
                    gui.setLoopSpeed(lSpeed);
                    gui.btnL3_ChangeLoopSpeed.toggleFalse();
                } else if (current == ModeType.L4) { //loop point
                    gui.btnL4_ChangeLoopPoint.resetColour();
                    int lPoint = gui.btnL4_ChangeLoopPoint.getValue();
                    gui.setLoopPoint(lPoint);
                    gui.btnL4_ChangeLoopPoint.toggleFalse();
                } else if (current == ModeType.R1) { //change layer
                    gui.btnR1_ChangeLayer.resetColour();
                    int layer = gui.btnR1_ChangeLayer.getValue();
                    gui.setLayer(layer);
                    gui.btnR1_ChangeLayer.toggleFalse();
                } else if (current == ModeType.R2) { //save config
                    //make file depending on what current filename is
                    try {
                        String filename = gui.filename + ".song";
                        FileOutputStream saveFile = new FileOutputStream(filename);
                        ObjectOutputStream save = new ObjectOutputStream(saveFile);
                        //save configuration
                        save.writeObject(gui.loopPoint);
                        save.writeObject(gui.velocity);
                        save.writeObject(gui.loopSpeed);
                        save.writeObject(gui.matrixLayers);
                        save.close();
                    } catch(Exception exc) {
                        exc.printStackTrace();
                    }
                    gui.btnR2_SaveConfig.resetColour();
                    gui.display.setText("Save configuration");
                    gui.btnR2_SaveConfig.toggleFalse();
                } else if (current == ModeType.R3) { //load config
                    try{
                        //check for existed file in current directory
                        String restorefile = gui.restorefile + ".song";
                        File f = new File("./" + restorefile);
                        if(f.exists()){
                            FileInputStream saveFile = new FileInputStream(restorefile);
                            ObjectInputStream save = new ObjectInputStream(saveFile);
                            //retrieve configuration
                            gui.loopPoint = (int) save.readObject();
                            gui.velocity = (int) save.readObject();
                            gui.loopSpeed = (Double) save.readObject();
                            gui.matrixLayers = (MatrixLayer[]) save.readObject();
                            save.close();
                            gui.display.setText("Load configuration");
                        }
                        else{
                            //if file doesnt exist set this as display and continue 
                            gui.display.setText("No such file name");
                        }
                    }
                    catch(Exception exc){
                        exc.printStackTrace(); //if there was an error print the info
                    }
                    gui.restorefile = "";
                    gui.btnR3_LoadConfig.resetColour();
                    gui.btnR3_LoadConfig.toggleFalse();
                } else if (current == ModeType.R4) { //master slave
                    gui.btnR4_MasterSlave.resetColour();
                    gui.display.setText("Master/Slave");
                    gui.btnR4_MasterSlave.toggleFalse();
                }
                gui.setPerformanceMode();
            }
        }
    }
    
    /**
     * OKButton constructor.
     * 
     * @param  label  button label
     * @author Paul
     */
    public OKButton( String label ) {
        super( label );
        this.addActionListener(new ModeConfirmation());
    }
}