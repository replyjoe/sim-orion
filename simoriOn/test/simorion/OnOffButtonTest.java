
package simorion;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zoe
 */

public class OnOffButtonTest {
    
    public OnOffButtonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of startClock method, of class OnOffButton.
     */
    @Test
    public void testStartClock() {
        System.out.println("startClock");
        OnOffButton testOnOff = new OnOffButton("");
        testOnOff.startClock();
        
    }

    /**
     * Test of stopClock method, of class OnOffButton.
     */
    @Test
    public void testStopClock() {
        System.out.println("stopClock");
        OnOffButton testOnOff = new OnOffButton("");
        testOnOff.stopClock();
        

    }
    
}
