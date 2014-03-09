package music;
import java.util.ArrayList;


public class Song {
	public String title;

	/** Beats per minute */
	public int tempo;
	/** Total number of beats */
	public int num_beats;
	/** Total number of Notes */
	public int num_notes;
	/** List of Notes */
	ArrayList<Note> notes;
	
	Song(String title, int tempo, int num_beats, int num_notes, ArrayList<Note> notes) {
		this.title = title;
		this.tempo = tempo;
		this.num_beats = num_beats;
		this.num_notes = num_notes;
		this.notes = notes;
	}
}
