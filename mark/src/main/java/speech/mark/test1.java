package speech.mark;

import guru.ttslib.TTS;

public class test1 {
    public static void main(String[] argv) {
        String voiceName = "kevin16"; // the only usable general purpose voice
       TTS tts;
       tts = new TTS();
          
       tts.setPitch( 200 );
       tts.setPitchRange( 1 );
       tts.setPitchShift( 0.5f );
       String str = "I am in the center REETESH.";
       tts.speak( str );
       tts.speakLeft( "I am on the left" );
       tts.speakRight( "I am on the right" ); 
    }
}