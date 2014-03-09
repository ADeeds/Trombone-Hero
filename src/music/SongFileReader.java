package music;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class SongFileReader {
	private String filename;
	
	SongFileReader(String filename) {
		this.filename = filename;
	}
	
	Note parse_note(String line) {
		String[] parsed = line.split(" ");
		String name = parsed[0];
		float start_beat = Float.parseFloat(parsed[1]);
		float length = Float.parseFloat(parsed[2]);
		if(parsed.length > 3) {
			System.out.println("Note has too many parameters!");
		}
		System.out.println("Note " + name + " starts at " + start_beat + " and lasts for " + length + " beats");
		
	}
	
	Song parse() {
		Song s = null;
		ArrayList<Note> notes;
		try {
			//BufferedReader reader = new BufferedReader(new FileReader(filename));
			Scanner sc = new Scanner(new File(filename));
			String title = sc.nextLine();
			int tempo = sc.nextInt();
			int length = sc.nextInt();
			int numnotes = sc.nextInt();
			ArrayList<Note> notes = new ArrayList<Note>();
			for(int i = 0; i < numnotes; i++) {
				String line = sc.nextLine();
				notes.add(parse_note(line));
			}
			s = new Song(title, tempo, length, numnotes);
		} catch (FileNotFoundException e) {}
		
	}
}
