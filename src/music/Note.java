package music;
/** Note represents a single instance of a pitch being played 
 * 
 * @author Chase
 *
 */
public class Note {

	/** Slide position on trombone */
	public enum Position { FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH }
	public Position position;
	
	/** The starting beat of the note */
	public float startTime;
	
	/** The length of the note in beats */
	public float duration;
	
	/** The name of the note pitch (on trombone, same as concert pitch) */
	public String name;

	Note(Position pos, float start, float dur) {
		this.position = pos;
		this.startTime = start;
		this.duration = dur;
	}
}
