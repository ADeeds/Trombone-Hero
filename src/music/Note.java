package music;

/** Note represents a single instance of a pitch being played */
public class Note {
	static Position noteToPos(String name) {
		if (name.equals("Bb")) return Position.FIRST;
		if (name.equals("B")) return Position.SEVENTH;
		if (name.equals("C")) return Position.SIXTH;
		if (name.equals("C#") || name.equals("Db")) return Position.FIFTH;
		if (name.equals("D")) return Position.FOURTH;
		if (name.equals("Eb") || name.equals("D#")) return Position.THIRD;
		if (name.equals("E") || name.equals("Fb")) return Position.SECOND;
		if (name.equals("F")) return Position.FIRST;
		if (name.equals("Gb") || name.equals("F#")) return Position.FIFTH;
		if (name.equals("G")) return Position.FOURTH;
		if (name.equals("Ab") || name.equals("G#")) return Position.THIRD;
		if (name.equals("A")) return Position.SECOND;
		
		System.err.println("UNKNOWN NOTE:" + name);
		return Position.FIRST;
	}

	/** The name of the note pitch (on trombone, same as concert pitch) */
	public String name;
	
	/** Slide position on trombone */
	public enum Position { FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH }
	public Position position;
	
	/** Octave number. Defaults to 1 */
	public int octave;
	
	/** The starting beat of the note */
	public float startBeat;
	
	/** The length of the note in beats */
	public float duration;

	private void set(String n, Position pos, float start, float dur) {
		this.name = n;
		this.position = pos;
		this.startBeat = start;
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
