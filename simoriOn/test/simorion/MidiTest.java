/*
* @ author Zoe
*/
package simorion;

import javax.sound.midi.Synthesizer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zoe
 */
public class MidiTest {

    
    public MidiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of channels method, of class Midi.
     */
//    @Test
//    public void testChannels() {
//        //testing that the channels match up with the matrix layers correctly
//        System.out.println("channels");
//        int layer = 0;
//        Midi instance = new Midi();
//        instance.channels();
//        System.out.println("layer" + layer + "corresponds with channel" + channel);
//
//    }

    /**
     * Test of getSynthesizer method, of class Midi.
     */
    @Test
    public void testGetSynthesizer() {
        System.out.println("getSynthesizer");
        Midi instance = new Midi();
        Synthesizer expResult = null;
        Synthesizer result = instance.getSynthesizer();
        assertEquals(expResult, result);
        

    }

    /**
     * Test of playInstrument method, of class Midi.
     */
    @Test
    public void testPlayInstrument() {
        System.out.println("playInstrument");
        Synthesizer synthesizer = null;
        int program = 1;
        int channel = 1;
        int pitch  = 1;
        int velocity = 1;
        Midi instance = new Midi();
        instance.playInstrument(synthesizer, channel, program, pitch, velocity);
    }

    /**
     * Test of playPercussion method, of class Midi.
     */
    @Test
    public void testPlayPercussion() {
        System.out.println("playPercussion");
        Synthesizer synthesizer = null;
        int pitch = 1;
        int velocity = 1;
        Midi instance = new Midi();
        instance.playPercussion(synthesizer, pitch, velocity);

    }

  
}
