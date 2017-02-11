package speech.mark;

import guru.ttslib.TTS;

public class Speeker {

	public static void getSpeek(String msg){
		TTS tts;
	       tts = new TTS();
	          
	       tts.setPitch( 200 );
	       tts.setPitchRange( 1 );
	       tts.setPitchShift( 0.5f );
	       tts.speak( msg );
	}
}
