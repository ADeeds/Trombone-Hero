package music;
import java.util.ArrayList;


public class Song {
	public String title;
	int tempo;
	int length; //in beats
	int num_notes;
	ArrayList<Note> notes;
	
	Song(String title, int tempo, int length, int num_notes) {
		this.title = title;
		this.tempo = tempo;
		this.length = length;
		this.num_notes = num_notes;
	}
}
