package simorion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * On/Off button.
 * Used to turn the Simori-On on and off.
 * 
 * @author Paul
 */
public class OnOffButton extends Button {
    
    //a variable to determine whether on or off
    private boolean onOff;
    //clock hand thread
    //new clock hand is started everytime Simori-On is turned on
    private Thread clockHand;
    
    /**
     * An action listener which adds an on-click Simori-On activation.
     * Action is to turn on the Simori-On and activate all buttons.
     * Updates state of Simori-On, turns off if already on.
     * 
     * @author Paul
     */
    private class Activate implements ActionListener {
        /**
         * Overridden actionPerformed method.
         * 
         * @param  click  the action event
         */
        @Override
        public void actionPerformed( ActionEvent click ) {
            SimoriGUI gui = SimoriGUI.getInstance();
            if (!onOff) { //turn on
                //welcome message
                gui.display.setText("Welcome");
                //enable mode and ok buttons
                gui.enableModeButtons(true);
                gui.okBtn.setEnabled(true);
                //enable matrix buttons
                for (int row = 0; row < 16; row++) {
                    for (int col = 0; col < 16; col++) {
                        gui.theMatrix[row][col].setEnabled(true);
                    }
                }
                //update state
                onOff = true;
                //gui.player.start();
                //start new clock hand
                startClock();
            } else { //turn off
                gui.resetGUI();
                //update state
                onOff = false;
                //stop the clock hand
                stopClock();
                //gui.player.interrupt();
            }
        }
    }
    
    /**
     * OnOffButton constructor.
     * Adds listeners on construction so that button is always ready.
     * 
     * @param  onOffLabel  button label
     * @author Paul
     */
    public OnOffButton( String onOffLabel ) {
        super( onOffLabel );
        this.onOff = false;
        this.setEnabled(true);
        this.addActionListener(new Activate());
    }
    
    /**
     * A method to start the clock hand.
     * 
     * @author Paul
     */
    protected void startClock() {
        Player player = new Player();
        this.clockHand = new Thread(new ClockHand(player));
        this.clockHand.start();
    }
    
    /**
     * A method to stop the clock hand.
     * 
     * @author Paul
     */
    protected void stopClock() {
        this.clockHand.interrupt();
    }
}
