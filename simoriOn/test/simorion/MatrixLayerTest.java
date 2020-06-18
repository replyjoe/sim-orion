package simorion;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @author Vlad
 * @author Paul
 */
public class MatrixLayerTest {
    MatrixLayer instance;
    
    public MatrixLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    @Before
    public void setUp() {
        instance = new MatrixLayer();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of setVoice method, of class MatrixLayer.
     * @author Paul
     */
    @Test
    public void testChangeVoice() {
        System.out.println("Testing Change Voice");
        int testVoice = 10;
        instance.setVoice(testVoice);
        System.out.println("The voice has been changed to " + testVoice);
    }
    /**
     * Test of toggleButton method, of class MatrixLayer
     * @author Vlad
     */
    @Test
    public void testToggleButton() {
        //toggling a specific button and checking whether its state is toggled
        int row = 2;
        int col = 2;
        instance.toggleButton(row, col);
        boolean testState = instance.matrixGrid[row][col];
        assertEquals(testState, true);
        System.out.println("The selected button's state is: " + testState);
    }
}
