import javax.sound.midi.*; 

public class MiniMiniMusicApp {
   public static void main(String[] args) {
      public void play() {
      
         try {
            /* Get a sequencer and open it */
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            /* Create a new sequence */
            Sequence seq = new Sequence(Sequence.PPQ, 4);

            /* Ask the sequence for a track */
            Track track = seq.createTrack(); //Remember, tracks live in the sequence and the MIDI data lives in the track

            ShortMessage a = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(b,16);
            track.add(noteOff);

            player.setSequence(seq); //Give the sequence to the sequencer (like putting the CD in the CD player)
            player.start(); //Start the sequencer (like pushing PLAY)
            
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
   }
}
