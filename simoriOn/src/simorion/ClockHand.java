package simorion;

/**
 * A class to implement a clock hand.
 * 
 * @author Paul
 */
class ClockHand extends Midi implements Runnable {
    
    Player player;
    
    /**
     * ClockHand constructor.
     * 
     * @author Paul
     */
    public ClockHand(Player player) {
        this.player = player;
    }
    
    /**
     * A method to convert BPM to millisecond delay.
     * 
     * @param  bpm  the BPM
     * @author Paul
     */
    private double convertBPM(double bpm) {
        //beats per millisecond
        return 60000.00/bpm;
    }
    
    /**
     * Overridden run method.
     */
    @Override
    public synchronized void run() {
        SimoriGUI gui = SimoriGUI.getInstance();
        while ( !Thread.currentThread().isInterrupted() ) {
            int loopPoint = gui.getLoopPoint();
            for (int i = 0; i <= loopPoint; i++) {
                double msDelay = convertBPM(gui.getLoopSpeed());
                try {
                    if ( gui.getModeType() == ModeType.PERFORMANCE ) {
                        gui.theMatrix[0][i].clockHandOrange();
                        gui.theMatrix[5][i].clockHandOrange();
                        gui.theMatrix[10][i].clockHandOrange();
                        gui.theMatrix[15][i].clockHandOrange();
                        this.player.play(i);
                    }
                    this.wait((long)msDelay);
                    if ( gui.getModeType() == ModeType.PERFORMANCE ) {
                        gui.theMatrix[0][i].clockHandWhite();
                        gui.theMatrix[5][i].clockHandWhite();
                        gui.theMatrix[10][i].clockHandWhite();
                        gui.theMatrix[15][i].clockHandWhite();
                    }
                } catch (InterruptedException e) {
                    //do nothing as Simori-On is being turned off
                    return;
                }
            }
        }
    }
};
