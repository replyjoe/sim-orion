package simorion;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing OKButton
 * 
 * 
 * @author Joe
 */
public class OKButtonTest {
    
    SimoriGUI gui = SimoriGUI.getInstance();
    
    public OKButtonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Testing pressing OK Button in PERFORMANCE MODE.
     * 
     * @author Joe
     */
    
    @Test
    public void testOKButton() {
        //turn on simoriOn
        gui.onOffBtn.doClick();
        System.out.println("Current Mode is: " + gui.getModeType());
        //press OK Button
        gui.okBtn.doClick();
        if(!(gui.getModeType() == ModeType.PERFORMANCE)){
            fail("Something has changed");
            }
        System.out.println("Mode After clicking OK Button:" + gui.getModeType());
        //off
        gui.onOffBtn.doClick();
    } 
    
    
    /**
     * Testing pressing OK Button in L2 MODE.
     * 
     * @author Joe
     */
    
    @Test
    public void testOKButtonL2() {
        //turn on simoriOn
        gui.onOffBtn.doClick();
        //move to L2 MODE
        gui.btnL2_ChangeVelocity.doClick();
        System.out.println("Current Mode is: " + gui.getModeType());
        //press OK Button
        gui.okBtn.doClick();
        if(!(gui.getModeType() == ModeType.PERFORMANCE)){
            fail("Pressing OK Button does not return to PERFORMANCE MODE");
        }
        System.out.println("Mode After clicking OK Button:" + gui.getModeType());
        //off
        gui.onOffBtn.doClick();
       
    }
    
    /**
     * Testing pressing OK Button in L3 MODE.
     * 
     * All other modes are similar
     * 
     * @author Joe
     */
    
    @Test
    public void testOKButtonL3() {
        //turn on simoriOn
        gui.onOffBtn.doClick();
        //move to L2 MODE
        gui.btnL3_ChangeLoopSpeed.doClick();
        System.out.println("Current Mode is: " + gui.getModeType());
        //press OK Button
        gui.okBtn.doClick();
        if(!(gui.getModeType() == ModeType.PERFORMANCE)){
            fail("Pressing OK Button does not return to PERFORMANCE MODE");
        }
        System.out.println("Mode After clicking OK Button:" + gui.getModeType());
        //off
        gui.onOffBtn.doClick();
       
    }

}