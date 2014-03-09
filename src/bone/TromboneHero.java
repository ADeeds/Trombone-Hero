package bone;

import music.*;

public class TromboneHero {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TromboneHero();
	}
	
	TromboneHero() {
		System.out.println("TROMBONE HERO!!!!!!!!");
		SongFileReader fr = new SongFileReader("tribute.bone");
		Song s = fr.parse();
		
	}

}
