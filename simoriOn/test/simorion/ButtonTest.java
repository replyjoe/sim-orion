package simorion;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;


/**
 * @author Vlad
 * @author Paul
 */
public class ButtonTest {
    
    public ButtonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Testing the getState method of Button class.
     * @author Vlad
     */
    
    @Test
    public void testGetState(){
        //instantiating a new Button
        Button rbn = new Button("");
        //forcing a click event to happen.
        rbn.changeColour();
        System.out.println("The button has been clicked for the first time.");
        boolean currentState = rbn.getState();
        //asserting that the button is toggled after the first click.
        assertEquals(currentState, true);
        System.out.println("The current state of toggled is: " + currentState);
        
        //second click event
        rbn.changeColour();
        System.out.println("The button has been clicked for the second time.");
        boolean newState = rbn.getState();
        //asserting that the button is not toggled after the second click.
        assertEquals(newState, false);
        System.out.println("The current state of toggled is: " + newState);
    }
    
    /**
     * Testing the changeColour method of Button class.
     * @author Vlad
     */
    
    @Test
    public void testChangeColour(){
        //instantiating a new Button with an empty label
        Button rbn = new Button("");
        //first call - not toggled so change colour to orange
        rbn.changeColour();
        Color buttonColour = rbn.getBackground();
        Color firstColour = Color.ORANGE;
        assertEquals(buttonColour, firstColour);
        System.out.println("The button has been toggled and its color is now orange");
 
        //second call - already toggled so should change colour back to white
        rbn.changeColour();
        Color secondButtonColour = rbn.getBackground();
        Color secondColour = Color.WHITE;
        assertEquals(secondButtonColour, secondColour);
        System.out.println("The button has been deactivated and its color is now white");
    }
    
    /**
     * Testing the clockHandOrange method of Button class.
     * @author Paul
     */
    @Test
    public void testClockHandOrange() {
        //instantiating a new clock hand called ch
        Button ch = new Button("");
        //changing the background colour to orange
        ch.clockHandOrange();
        Color chColour = ch.getBackground();
        assertEquals(chColour, Color.ORANGE);
        System.out.println("The colour of the clock hand is orange.");
    
    }
    
    /**
     * Testing the clockHandWhite method of Button class.
     * @author Paul
     */
    @Test
    public void testClockHandWhite() {
        //instantiating a new clock hand called ch
        Button ch = new Button("");
        //changing the background colour to orange and toggling it
        ch.clockHandOrange();
        ch.clockHandWhite();
        Color chColour = ch.getBackground();
        assertEquals(chColour, Color.WHITE);
        System.out.println("The colour of the clock hand is white.");
    }
    
    /**
     * Testing the resetColour method of Button class.
     * @author Paul
     */
    @Test
    public void testResetColour() {
        //instantiating a new clock hand called ch
        Button ch = new Button("");
        //changing the background colour to orange
        ch.clockHandOrange();
        //resetting its colour - should turn to white.
        ch.resetColour();
        Color chColour = ch.getBackground();
        assertEquals(chColour, Color.WHITE);
        System.out.println("The colour of the clock has been reset to white");
    }
    
    /**
     * Testing the turnOff method of Button class.
     * @author Paul
     */
    @Test
    public void testTurnOff() {
        //instantiating a new clock hand called ch
        Button rbn = new Button("");
        //changing the background colour and toggling a button.
        rbn.changeColour();
        //turning the button off and checking its colour and toggled state
        rbn.turnOff();
        Color rbnColour = rbn.getBackground();
        boolean rbnToggled = rbn.getState();
        assertEquals(rbnColour, Color.WHITE);
        assertEquals(rbnToggled, false);
        System.out.println("The colour of the button has been reset to white");
        System.out.println("The button's toggled state is false");
        
    }
}
