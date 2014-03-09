package music;

import gui.Board;
import gui.GuiNote;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;

import stats.Judge;

/** Plays a song
 * 
 * @author Chase
 *
 */
public class SongPlayer {

	/** Number of beats to "look ahead" */
	private static float previewBeats = 8;
	/** Number of beats for which to keep passed notes visible */
	private static float postviewBeats = 4;

	/** Currently visible notes */
	private ArrayList<GuiNote> visibleNotes = new ArrayList<GuiNote>();
	/** Current beat of playback */
	public double currentBeat;
	/** Index of currently iterating note */
	private int currentNoteIndex;
	/** State of SongPlayer */
	private enum State { STOPPED, PLAYING, PAUSED, DONE }
	public State state;

	public Judge judge;
	private Song song;

	public SongPlayer(Board board, Song song) {
		this.song = song;
		this.state = State.STOPPED;
		this.judge = new Judge(board, this);

	}

	/** Used to play from beginning or resume from paused */
	public void play() {
		if (state != State.PAUSED) {
			currentBeat = -previewBeats;
			currentNoteIndex = 0;
		}
		state = State.PLAYING;	
	}

	/** Adds all notes for which startBeat < currentBeat + previewLength
	 *  to visibleNotes
	 */
	private void addToVisibleNotes() {
		while (currentNoteIndex < song.notes.size() &&
				song.notes.get(currentNoteIndex).startBeat < currentBeat + previewBeats) {
			GuiNote guinote = new GuiNote(song.notes.get(currentNoteIndex), 
					1, song.notes.get(currentNoteIndex).duration/previewBeats, 
					Note.noteToPos(song.notes.get(currentNoteIndex).name));
			//System.out.println("X width: " + guinote.right_side);
			visibleNotes.add(guinote);
			currentNoteIndex++;
		}
	}

	/** Moves all notes towards current line */
	private void advanceNotes() {
		for (int i = 0; i < visibleNotes.size(); i++) {
			GuiNote n = visibleNotes.get(i);
			if (currentBeat > n.note.startBeat + n.note.duration + postviewBeats) {
				visibleNotes.remove(n);
			}
			else {
				if (n.note.startBeat <= currentBeat && n.note.startBeat + n.note.duration > currentBeat) {
					judgeMeOn(n);
				}
				double start_minus_cur = n.note.startBeat - currentBeat;
				n.x_center = (start_minus_cur)/previewBeats;
			}

		}
	}

	public  ArrayList<GuiNote> getVisibleGuiNotes() {
		return visibleNotes;
	}

	/** Advances currentBeat to the realtime-determined time elapsed to avoid
	 *  timer drift. Called by gui/Board
	 */
	public void advanceToTime(long tot_elapsed) {
		currentBeat = (song.tempo * tot_elapsed / (60.0 * 1000.0)) - previewBeats;
		//System.out.println("Cur beat: " + currentBeat);
		addToVisibleNotes();
		advanceNotes();
		if (currentBeat > song.num_beats) {
			state = State.DONE;
		}
	}

	/** Causes Judge to recalculate score, boneage, etc. based on current performance */

	private void judgeMeOn(GuiNote n) {
		judge.assess(n, currentBeat);
	}
}
