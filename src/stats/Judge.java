package stats;

import gui.Board;
import bone.*;
import music.*;

/** Compares player's current position to correct position based on currently-playing note.
 *  Handles score and boneage. */
public class Judge {

	Board board;
	SongPlayer songplayer;
	public int score;
	public int boneage;
	int beatOfLastUpdate;
	
	public Judge(Board board, SongPlayer songplayer) {
		this.board = board;
		this.songplayer = songplayer;
		boneage = 0;
		beatOfLastUpdate = 0;
	}
	
	/** Given the currently-expected note, updates score and boneage */
	public void assess(Note n) {
		int miss = SlideStats.getPositionDistance(n.position, board.slide.getPosition());
		assignPointsFor(miss);
	}
	
	
	private void assignPointsFor(int miss) {
		int bc = 0;
		int sc = 0;
		
		if (miss < 3) {
			bc = 4;
			sc = 69;
		}
		else if (miss < 4) {
			bc = 3;
			sc = 57;
		}
		else if (miss < 6) {
			bc = -3;
			sc = 34;
		}
		else if (miss < 9) {
			bc = -4;
			sc = 1; // ...1...1...1...freshman.
		}
		else {
			bc = -6;
			sc = -5; // Jeff
		}
		
		boneage += bc;
		if (boneage < 0) boneage = 0;
		else if (boneage > 100) {
			boneage = 100;
			sc *= 5;
		}

		
		score += sc;
	}
}
