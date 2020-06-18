
package simorion;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zoe
 */

public class MatrixButtonTest {
    
    public MatrixButtonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addLayerStateUpdateListener method
     */
    @Test
    public void testAddLayerStateUpdateListener() {
        //new matrix button
        MatrixButton testButton = new MatrixButton("", 1, 1);
        testButton.addLayerStateUpdateListener();
        System.out.println("add layer state update listener");
        boolean currentState = testButton.getState();
        assertEquals (currentState, true);
        System.out.println("the current state of the matrix button is" + currentState);
    }

    /**
     * Test of removeLayerStateUpdateListener method, of class MatrixButton.
     */
    @Test
    public void testRemoveLayerStateUpdateListener() {
        MatrixButton testButton = new MatrixButton("", 1, 1);
        testButton.removeLayerStateUpdateListener();
        boolean currentState = testButton.getState();
        assertEquals (currentState, false);
        System.out.println("the curent state of the matrix buttin is" + currentState);
    }

    /**
     * Test of addRowLightUpListener method, of class MatrixButton.
     */
    @Test
    public void testAddRowLightUpListener() {
        System.out.println("addRowLightUpListener");
        MatrixButton instance = null;
        instance.addRowLightUpListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRowLightUpListener method, of class MatrixButton.
     */
    @Test
    public void testRemoveRowLightUpListener() {
        System.out.println("removeRowLightUpListener");
        MatrixButton instance = null;
        instance.removeRowLightUpListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addColLightUpListener method, of class MatrixButton.
     */
    @Test
    public void testAddColLightUpListener() {
        System.out.println("addColLightUpListener");
        MatrixButton instance = null;
        instance.addColLightUpListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeColLightUpListener method, of class MatrixButton.
     */
    @Test
    public void testRemoveColLightUpListener() {
        System.out.println("removeColLightUpListener");
        MatrixButton instance = null;
        instance.removeColLightUpListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCrossLightUpListener method, of class MatrixButton.
     */
    @Test
    public void testAddCrossLightUpListener() {
        System.out.println("addCrossLightUpListener");
        MatrixButton instance = null;
        instance.addCrossLightUpListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCrossLightUpListener method, of class MatrixButton.
     */
    @Test
    public void testRemoveCrossLightUpListener() {
        System.out.println("removeCrossLightUpListener");
        MatrixButton instance = null;
        instance.removeCrossLightUpListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatrixNumberListener method, of class MatrixButton.
     */
    @Test
    public void testAddMatrixNumberListener() {
        System.out.println("addMatrixNumberListener");
        MatrixButton instance = null;
        instance.addMatrixNumberListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMatrixNumberListener method, of class MatrixButton.
     */
    @Test
    public void testRemoveMatrixNumberListener() {
        System.out.println("removeMatrixNumberListener");
        MatrixButton instance = null;
        instance.removeMatrixNumberListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
