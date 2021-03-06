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
	public double numNotesHit;
	double beatOfLastUpdate;
	
	public Judge(Board board, SongPlayer songplayer) {
		this.board = board;
		boneage = 25;
		beatOfLastUpdate = 0;
		numNotesHit = 0;
	}
	
	/** Given the currently-expected note, updates score and boneage */
	public void assess(GuiNote n, double currentBeat) {
		int miss = SlideStats.getPositionDistance(n.note.position, board.slide.getPosition());
		n.color = assignPointsFor(miss, currentBeat, n.note.startBeat);
		beatOfLastUpdate = currentBeat;
	}
	
	
	private Color assignPointsFor(int miss, double currentBeat, float startBeat) {
		double bc = 0;
		int sc = 0;
		Color newColor = Color.BLUE;
		
		if (miss < 3) {
			bc = 1;
			sc = 69;
			numNotesHit += 1;
			newColor = Color.GREEN;
		}
		else if (miss < 5) {
			bc = .5;
			sc = 34;
			numNotesHit += .5;
			newColor = Color.YELLOW;
		}
		else if (miss < 8) {
			bc = .1;
			sc = 1;
			newColor = Color.RED;
		}
		else if (miss < 11) {
			bc = -1;
			sc = -15; // ...1...1...1...freshman.
		}
		else {
			bc = -2;
			sc = -34; // Jeff
		}

		// Don't increase boneage as much toward the ends of longer notes
		// It's not much of a challenge
		boneage += bc / ((currentBeat - startBeat) + .001);
		if (boneage < 0) boneage = 0;
		else if (boneage > 90) {
			sc *= 5;
			if (boneage > 100) {
				boneage = 100;
			}
		}

		// Weight score by time elapsed since last update
		// If it's been longer, it should be worth more points
		score += (int) sc * (currentBeat - beatOfLastUpdate) * 3.469;
		return newColor;
	}
}
