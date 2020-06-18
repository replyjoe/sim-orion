package simorion;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Color;

/**
 * A test of the SimoriGUI class.
 * 
 * @author Paul
 * @author Vlad
 */
public class SimoriGUITest {
    SimoriGUI instance;
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Testing SimoriGUI class.");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished testing SimoriGUI class.");
    }
    
    @Before
    public void setUp() {
        instance = SimoriGUI.getInstance();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of SimoriGUI constructor.
     * 
     * @author Paul
     */
    @Test
    public void testConstructor() {
        //check mode buttons are of correct type
        if (!(instance.btnL1_ChangeVoice instanceof ModeButton)) {
            fail("Button: btnL1_ChangeVoice not properly constructed.");
        } else if (!(instance.btnL2_ChangeVelocity instanceof ModeButton)) {
            fail("Button: btnL2_ChangeVelocity not properly constructed.");
        } else if (!(instance.btnL3_ChangeLoopSpeed instanceof ModeButton)) {
            fail("Button: btnL3_ChangeLoopSpeed not properly constructed.");
        } else if (!(instance.btnL4_ChangeLoopPoint instanceof ModeButton)) {
            fail("Button: btnL4_ChangeLoopPoint not properly constructed.");
        } else if (!(instance.btnR1_ChangeLayer instanceof ModeButton)) {
            fail("Button: btnR1_ChangeLayer not properly constructed.");
        } else if (!(instance.btnR2_SaveConfig instanceof ModeButton)) {
            fail("Button: btnR2_SaveConfig not properly constructed.");
        } else if (!(instance.btnR3_LoadConfig instanceof ModeButton)) {
            fail("Button: btnR3_LoadConfig not properly constructed.");
        } else if (!(instance.btnR4_MasterSlave instanceof ModeButton)) {
            fail("Button: btnR4_MasterSlave not properly constructed.");
        }
        //check onOff and Ok buttons are of correct type
        if (!(instance.onOffBtn instanceof OnOffButton)) {
            fail("Button: onOffBtn not properly constructed.");
        } else if (!(instance.okBtn instanceof OKButton)) {
            fail("Button: okBtn not properly constructed.");
        }
        //check matrix buttons are of correct type
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                if (!(instance.theMatrix[row][col] instanceof MatrixButton)) {
                    fail("Button: matrixButton not properly constructed.");
                }
            }
        }
    }
    
    /**
     * Test of resetMatrixColour method, of class SimoriGUI.
     * 
     * @author Paul
     */
    @Test
    public void testResetMatrix() {
        //set up
        instance.theMatrix[0][0].changeColour();
        assertEquals(Color.ORANGE, instance.theMatrix[0][0].getBackground());
        instance.theMatrix[15][15].changeColour();
        assertEquals(Color.ORANGE, instance.theMatrix[15][15].getBackground());
        instance.theMatrix[4][12].changeColour();
        assertEquals(Color.ORANGE, instance.theMatrix[4][12].getBackground());
        
        System.out.println("Testing: resetMatrix");
        instance.resetMatrixColour();
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                if (instance.theMatrix[row][col].getBackground() == Color.ORANGE) {
                    fail("Colour not properly reset.");
                }
            }
        }
    }

    /**
     * Test of enableModeButtons method, of class SimoriGUI.
     * Enables buttons.
     * 
     * @author Paul
     */
    @Test
    public void testSetEnabledModes_true() {
        System.out.println("Testing: setEnabledModes(true)");
        instance.enableModeButtons(true);
        if (!instance.btnL1_ChangeVoice.isEnabled()) {
            fail("Button not enabled.");
        } else if (!instance.btnL2_ChangeVelocity.isEnabled()) {
            fail("Button not enabled.");
        } else if (!instance.btnL3_ChangeLoopSpeed.isEnabled()) {
            fail("Button not enabled.");
        } else if (!instance.btnL4_ChangeLoopPoint.isEnabled()) {
            fail("Button not enabled.");
        } else if (!instance.btnR1_ChangeLayer.isEnabled()) {
            fail("Button not enabled.");
        } else if (!instance.btnR2_SaveConfig.isEnabled()) {
            fail("Button not enabled.");
        } else if (!instance.btnR3_LoadConfig.isEnabled()) {
            fail("Button not enabled.");
        } else if (!instance.btnR4_MasterSlave.isEnabled()) {
            fail("Button not enabled.");
        }
    }
    
    /**
     * Test of enableModeButtons method, of class SimoriGUI.
     * Disables buttons
     * 
     * @author Paul
     */
    @Test
    public void testSetEnabledModes_false() {
        System.out.println("Testing: setEnabledModes(false)");
        instance.enableModeButtons(false);
        if (instance.btnL1_ChangeVoice.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnL2_ChangeVelocity.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnL3_ChangeLoopSpeed.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnL4_ChangeLoopPoint.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR1_ChangeLayer.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR2_SaveConfig.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR3_LoadConfig.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR4_MasterSlave.isEnabled()) {
            fail("Button not disabled.");
        }
    }

    /**
     * Test of turnOffModeButtons method, of class SimoriGUI.
     * 
     * @author Paul
     */
    @Test
    public void testTurnOffModeButtons() {
        //set up
        instance.btnL1_ChangeVoice.changeColour();
        instance.btnL4_ChangeLoopPoint.changeColour();
        instance.btnR4_MasterSlave.changeColour();
        
        System.out.println("turnOffModeButtons");
        instance.turnOffModeButtons();
        //check whether buttons are disabled
        if (instance.btnL1_ChangeVoice.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnL2_ChangeVelocity.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnL3_ChangeLoopSpeed.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnL4_ChangeLoopPoint.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR1_ChangeLayer.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR2_SaveConfig.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR3_LoadConfig.isEnabled()) {
            fail("Button not disabled.");
        } else if (instance.btnR4_MasterSlave.isEnabled()) {
            fail("Button not disabled.");
        }
        //check whether colour is reset
        if (instance.btnL1_ChangeVoice.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        } else if (instance.btnL2_ChangeVelocity.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        } else if (instance.btnL3_ChangeLoopSpeed.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        } else if (instance.btnL4_ChangeLoopPoint.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        } else if (instance.btnR1_ChangeLayer.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        } else if (instance.btnR2_SaveConfig.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        } else if (instance.btnR3_LoadConfig.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        } else if (instance.btnR4_MasterSlave.getBackground() == Color.ORANGE) {
            fail("Button colour not reset");
        }
    }
    /**
     * Test of setVelocity method of SimoriGui class
     * @author Vlad
     */
    @Test
    public void setVelocityTest() {
        //sets the velocity and checks that it was set to the correct value
        //by using the getVelocity() method.
        System.out.println("Set Velocity test");
        int testVelocity = 100;
        instance.setVelocity(testVelocity);
        int expVelocity = 100;
        int accVelocity = instance.getVelocity();
        assertEquals(expVelocity, accVelocity);
        System.out.println("The velocity was set to " + accVelocity);
    }
    /**
     * Testing setting an invalid velocity
     * @author Vlad
     */
    @Test
    public void setInvalidVelocity() {
        //sets an invalid (negative) velocity. Checks that the correct message
        //is printed to the LCD using getText().
        System.out.println("Setting an invalid velocity");
        int testVelocity = -5;
        instance.setVelocity(testVelocity);
        String msg = instance.display.getText();
        assertEquals(msg, "Invalid velocity.");
        System.out.println("The message that was printed to the LCD: " + msg);
    }
    
    /**
     * Test of setLoopPoint method of SimoriGui class
     * @author Vlad
     */
    @Test
    public void setLoopPointTest() {
        //sets a loop point and checks that it's been set correctly via
        //getLoopPoint() method.
        System.out.println("Set Loop Point test");
        int testLoopPoint = 10;
        instance.setLoopPoint(testLoopPoint);
        int expLoopPoint = 10;
        int accLoopPoint = instance.getLoopPoint();
        assertEquals(expLoopPoint, accLoopPoint);
        System.out.println("The loop point was set to " + accLoopPoint);
    }
    
    /**
     * Testing setting an invalid loop point
     * @author Vlad
     */
    @Test
    public void setInvalidLoopPoint() {
        //sets an invalid loop point (larger than 15), then checks that the
        //correct message is printed to the LCD via getText() method.
        System.out.println("Setting an invalid loop point test");
        int testLoopPoint = 20;
        instance.setLoopPoint(testLoopPoint);
        String msg = instance.display.getText();
        assertEquals(msg, "Invalid loop point.");
        System.out.println("The message that was printed to the LCD: " + msg);
        
    }
    
    /**
     * Test of setLoopSpeed method of SimoriGui class
     * @author Vlad
     */
    @Test
    public void setLoopSpeedTest() {
        //sets the loop speed to a double (100) and checks it was set correctly
        //via the getLoopSpeed() method
        System.out.println("Set Loop Speed test");
        double testLoopSpeed = 100.00;
        instance.setLoopSpeed(testLoopSpeed);
        double expLoopSpeed = 100.00;
        double accLoopSpeed = instance.getLoopSpeed();
        assertEquals(expLoopSpeed, accLoopSpeed, 0.1);
        System.out.println("The loop speed was set to " + accLoopSpeed);
        
    } 
    
    /**
     * Testing setting an invalid loop speed
     * @author Vlad
     */
    @Test
    public void setInvalidLoopSpeed() {
        //sets an invalid loop speed (larger than 160) and checks the correct
        //message is printed to the LCD via getText() method.
        System.out.println("Setting an invalid loop speed test");
        double testLoopSpeed = 200.00;
        instance.setLoopSpeed(testLoopSpeed);
        String msg = instance.display.getText();
        System.out.println("The message that was printed to the LCD: " + msg);
        assertEquals(msg, "Invalid loop speed.");
        
    }
    
    /**
     * Test of setLayer method of SimoriGui class
     * @author Vlad
     */
    @Test
    public void setLayerTest() {
        //sets the layer to 10 and checks it was set correctly via getLayer().
        System.out.println("Set Layer test");
        int testLayer = 10;
        instance.setLayer(testLayer);
        int expLayer = 10;
        int accLayer = instance.getLayer();
        assertEquals(expLayer, accLayer);
        System.out.println("The layer was set to " + accLayer);
    }
    
    /**
     * Testing setting an invalid loop point
     * @author Vlad
     */
    @Test
    public void setInvalidLayer() {
        //sets an invalid layer (larger than 15) and checks the correct message
        //is printed to the LCD via getText().
        System.out.println("Setting an invalid layer test");
        int testLayer = 20;
        instance.setLayer(testLayer);
        String msg = instance.display.getText();
        assertEquals(msg, "Invalid layer.");
        System.out.println("The message that was printed to the LCD: " + msg);
    }    
}
