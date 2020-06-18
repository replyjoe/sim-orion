package simorion;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Parent button class.
 * Implements a circular button which lights up when toggled.
 * 
 * @author Vlad and Paul
 */
public class Button extends CircleButton {
    
    //keep track of button state
    private boolean toggled;
    private ColourChangeListener ccl;
    
    /**
     * An action listener which adds on-click colour change.
     * Changes colour of the button to orange if clicked.
     * Changes colour back to white when re-clicked.
     * 
     * @author Paul
     */
    private class ColourChangeListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent click ) {
            changeColour();
        }
    }
    
    /**
     * Button constructor.
     * 
     * @param  label  button label
     * @author Paul
     */
    public Button(String label) {
        super ( label );
        this.toggled = false;
        this.setBackground(Color.WHITE);
        this.addActionListener(new ColourChangeListener());
        this.setEnabled(false);
    }
    
    /**
     * A method to change the button colour.
     * 
     * @author Vlad
     */
    protected void changeColour() {
        //if button already pressed, turn it back to white.
        if (this.toggled) {
            setBackground(Color.WHITE);
            this.toggled = false;
        //if button not currently pressed, set to orange.
        } else {
            setBackground(Color.ORANGE);
            this.toggled = true;
        }
    }
    
    /**
     * A method to add the button colour change listener.
     * 
     * @author Paul
     */
    protected void addColourChangeListener() {
        //assign to variable so it can removed later
        this.ccl = new ColourChangeListener();
        this.addActionListener(this.ccl);
    }
    
    protected void removeColourChangeListener() {
        this.removeActionListener(this.ccl);
    }
    
    /**
     * A method to change button colour to orange.
     * Used by the clock hand
     * 
     * @author Paul
     */
    protected void clockHandOrange() {
        //only change colour if button not already pressed
        if (this.getBackground() != Color.ORANGE) {
            this.setBackground(Color.ORANGE);
        }
    }
    
    /**
     * A method to change button colour to white.
     * Used by the clock hand
     * 
     * @author Paul
     */
    protected void clockHandWhite() {
        //only change colour if button not already white
        if (!this.toggled && this.getBackground() == Color.ORANGE) {
            this.setBackground(Color.WHITE);
        }
    }
    
    /**
     * @return toggled
     * @author Paul
     */
    protected boolean getState() {
        return this.toggled;
    }
    
    /**
     * A method to set the state of a button to true.
     * 
     * @author Paul
     */
    protected void toggleTrue() {
        this.toggled = true;
    }
    
    /**
     * A method to set the state of a button to false.
     * 
     * @author Paul
     */
    protected void toggleFalse() {
        this.toggled = false;
    }
    
    /**
     * A method to reset the button colour.
     * 
     * @author Paul
     */
    protected void resetColour() {
        if (this.getBackground() == Color.ORANGE) {
            this.setBackground(Color.WHITE);
        }
    }
    
    /**
     * A method to remove all listeners and reset colour.
     * 
     * @author Paul
     */
    protected void resetButton() {
        this.resetColour();
        this.toggled = false;
        for ( ActionListener al : this.getActionListeners() ) {
            this.removeActionListener( al );
        }
    }
    
    /**
     * A method to turn the button off.
     * Disables the button as well as resets colour.
     * 
     * @author Paul
     */
    protected void turnOff() {
        this.resetColour();
        this.toggled = false;
        this.setEnabled(false);
    }
}
    
