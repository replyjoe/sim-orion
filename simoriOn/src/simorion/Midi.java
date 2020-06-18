package simorion;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * MIDI
 * 
 * @author Joe
 * @author Paul
 */

public class Midi {
  
    public void delay( int ms ) {
        try {
            Thread.sleep( ms );
        } catch( Exception ex ) {
            Thread.currentThread().interrupt();
        }
    }
  
    /*
    * Get synthesizer.
    */
    public Synthesizer getSynthesizer() {
        Synthesizer synthesizer = null;	  
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch ( Exception exception ) {
            System.out.println( exception ); System.exit( 1 );
        }
        return synthesizer;
    }
  
    /*
    * Play normal instrument.
    */
    public void playInstrument (Synthesizer synthesizer, int channel, int program, int pitch, int velocity){
        MidiChannel[] midiChannels = synthesizer.getChannels();
        MidiChannel   midiChannel  = midiChannels[channel];
        Instrument[]  instruments  = synthesizer.getDefaultSoundbank().getInstruments();
        synthesizer.loadInstrument(instruments[program]);
        midiChannel.programChange( program );
        midiChannel.noteOn ( pitch, velocity*10 );
        delay( velocity );
        midiChannel.noteOff( pitch, velocity*10 );
    }
  
    /*
    * Play percussion instrument.
    */
    public void playPercussion( Synthesizer synthesizer, int pitch, int velocity) {
        MidiChannel[] midiChannels = synthesizer.getChannels();
        MidiChannel   midiChannel  = midiChannels[9];
        midiChannel.noteOn ( pitch, velocity*10);
        delay( velocity );
        midiChannel.noteOff( pitch, velocity*10 );
    }
}