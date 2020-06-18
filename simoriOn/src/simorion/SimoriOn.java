package simorion;

import javax.swing.JFrame;

/**
 * NEW TO DO:
 * 
 *   -IT PLAYS SOUNDS NOW !
 *   -make reference mapping of each voice value to the type of instrument
 *   -percussions still needs to be done
 *
 * 
 *   ~ clock hand plays sounds!
 *   ~ maybe display current layer settings in display
 *   ~ GUI needs own thread?
 *   ~ Sound player needs its own thread? Built into clock hand?
 *   ~ MIDI, first play simple sound on click
 */

/**
 * @author Group N.
 */
public class SimoriOn extends JFrame {
    
    /**
     * Main method for building and starting the Simori-On GUI interface.
     * 
     * @author Joe
     */
    public static void main(String[] args) {
        JFrame frame = SimoriGUI.getInstance();
        frame.setVisible( true );
        frame.setLocationRelativeTo( null );
        frame.setResizable( false );
        frame.setDefaultCloseOperation( SimoriOn.EXIT_ON_CLOSE ); // exits run
    }
}
