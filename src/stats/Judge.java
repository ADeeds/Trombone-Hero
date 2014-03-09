package stats;

import java.awt.Color;

import gui.*;
import bone.*;
import music.*;

/** Compares player's current position to correct position based on currently-playing note.
 *  Handles score and boneage. */
public class Judge {

	Board board;
	public int score;
	public double boneage;
	double beatOfLastUpdate;
	
	public Judge(Board board, SongPlayer songplayer) {
		this.board = board;
		boneage = 25;
		beatOfLastUpdate = 0;
	}
	
	/** Given the currently-expected note, updates score and boneage */
	public void assess(GuiNote n, double currentBeat) {
		int miss = SlideStats.getPositionDistance(n.position, board.slide.getPosition());
		n.color = assignPointsFor(miss, currentBeat);
		beatOfLastUpdate = currentBeat;
	}
	
	
	private Color assignPointsFor(int miss, double currentBeat) {
		double bc = 0;
		int sc = 0;
		Color newColor = Color.BLUE;
		
		if (miss < 3) {
			bc = 3;
			sc = 69;
			newColor = Color.GREEN;
		}
		else if (miss < 4) {
			bc = 2;
			sc = 57;
			newColor = Color.YELLOW;
		}
		else if (miss < 6) {
			bc = 1;
			sc = 34;
			newColor = Color.RED;
		}
		else if (miss < 9) {
			bc = -.5;
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

		// Weight score by time elapsed since last update
		// If it's been longer, it should be worth more points
		score += (int) sc * (currentBeat - beatOfLastUpdate) * 34.69;
		return newColor;
	}
}
