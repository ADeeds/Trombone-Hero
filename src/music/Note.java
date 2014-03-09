package music;
/** Note represents a single instance of a pitch being played 
 * 
 * @author Chase
 *
 */
public class Note {


	/** The name of the note pitch (on trombone, same as concert pitch) */
	public String name;
	
	/** Slide position on trombone */
	public enum Position { FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH }
	public Position position;
	
	/** Octave number. Defaults to 1 */
	public int octave;
	
	/** The starting beat of the note */
	public float startTime;
	
	/** The length of the note in beats */
	public float duration;

	private void set(String n, Position pos, float start, float dur) {
		this.name = n;
		this.position = pos;
		this.startTime = start;
		this.duration = dur;
	}
	
	Note(String n, Position pos, float start, float dur) {
		set(n, pos, start, dur);
		this.octave = 1;
	}

	Note(String n, Position pos, int oct, float start, float dur) {
		set(n, pos, start, dur);
		this.octave = oct;
	}
}
