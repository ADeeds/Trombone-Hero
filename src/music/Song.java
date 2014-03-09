package music;
import java.util.ArrayList;


public class Song {
	public String title;
	/** Beats per minute */
	int tempo;
	/** Total number of beats */
	int num_beats;
	/** Total number of Notes */
	int num_notes;
	/** List of Notes */
	ArrayList<Note> notes;
	
	Song(String title, int tempo, int num_beats, int num_notes) {
		this.title = title;
		this.tempo = tempo;
		this.num_beats = num_beats;
		this.num_notes = num_notes;
	}
}
