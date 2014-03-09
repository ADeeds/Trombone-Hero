/** Note represents a single instance of a pitch being played 
 * 
 * @author Chase
 *
 */
public class Note {

	/** Slide position on trombone */
	enum Position { FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH }
	Position position;
	
	/** The starting beat of the note */
	float startTime;
	
	/** The length of the note in beats */
	float duration;
	
	/** The name of the note pitch (on trombone, same as concert pitch) */
	String name;

	Note(Position pos, float start, float dur) {
		this.position = pos;
		this.startTime = start;
		this.duration = dur;
	}
}
