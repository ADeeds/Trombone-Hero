package music;

/** Note represents a single instance of a pitch being played */
public class Note {
	
	final byte[] PositionDistances =
		{	00, /* Zeroth - Ignore */
			34, /* First */
			43, /* Second */
			52, /* Third */
			31, /* Fourth */
			74, /* Fifth */
			82, /* Sixth */
			95  /* Seventh */
		};
	
	static int noteToPos(String name) {
		if (name.equals("Bb")) return 1;
		if (name.equals("B")) return 7;
		if (name.equals("C")) return 6;
		if (name.equals("C#") || name.equals("Db")) return 5;
		if (name.equals("D")) return 4;
		if (name.equals("Eb") || name.equals("D#")) return 3;
		if (name.equals("E") || name.equals("Fb")) return 2;
		if (name.equals("F")) return 1;
		if (name.equals("Gb") || name.equals("F#")) return 5;
		if (name.equals("G")) return 4;
		if (name.equals("Ab") || name.equals("G#")) return 3;
		if (name.equals("A")) return 2;
		
		System.err.println("UNKNOWN NOTE:" + name);
		return 1;
	}

	/** The name of the note pitch (on trombone, same as concert pitch) */
	public String name;
	
	/** Slide position on trombone */
	public int position;
	
	/** Octave number. Defaults to 1 */
	public int octave;
	
	/** The starting beat of the note */
	public float startBeat;
	
	/** The length of the note in beats */
	public float duration;

	private void set(String n, float start, float dur) {
		this.name = n;
		this.position = noteToPos(n);
		this.startBeat = start;
		this.duration = dur;
	}
	
	public Note(String n, float start, float dur) {
		set(n, start, dur);
		this.octave = 1;
	}

	public Note(String n, int oct, float start, float dur) {
		set(n, start, dur);
		this.octave = oct;
	}
	
	public int getPositionDistance() {
		return PositionDistances[this.position];
	}
}
