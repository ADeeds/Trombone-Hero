package music;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class SongFileReader {
	private String filename;
	
	public SongFileReader(String filename) {
		this.filename = filename;
	}
	
	Note parse_note(String line) {
		System.out.println(line);
		Scanner sc = new Scanner(line);
		String name = sc.next();
		float start_beat = sc.nextFloat();
		float length = sc.nextFloat();
		
		//System.out.println("Note " + name + " starts at " + start_beat + " and lasts for " + length + " beats");
		return new Note(name, Note.noteToPos(name), start_beat, length);
	}
	
	public Song parse() {
		Song s = null;
		ArrayList<Note> notes = new ArrayList<Note>();
		try {
			//BufferedReader reader = new BufferedReader(new FileReader(filename));
			Scanner sc = new Scanner(new File(filename));
			String title = sc.nextLine();
			int tempo = sc.nextInt();
			int length = sc.nextInt();
			int numnotes = sc.nextInt();
			numnotes = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if(line.length() > 0) notes.add(parse_note(line));
				numnotes++;
			}
			s = new Song(title, tempo, length, numnotes, notes);
			sc.close();
		} catch (FileNotFoundException e) {}
		return s;
	}
}
