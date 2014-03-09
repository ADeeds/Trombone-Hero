package music;

import gui.Board;
import gui.GuiNote;

import java.util.ArrayList;

import stats.Judge;

/** Plays a song
 * 
 * @author Chase
 *
 */
public class SongPlayer {
	
	private Board board;

	/** Number of beats to "look ahead" */
	private static float previewBeats = 16;
	/** Number of beats for which to keep passed notes visible */
	private static float postviewBeats = 2;

	/** Currently visible notes */
	private ArrayList<GuiNote> visibleNotes = new ArrayList<GuiNote>();
	/** Current beat of playback */
	public double currentBeat;
	/** Index of currently iterating note */
	private int currentNoteIndex;
	/** State of SongPlayer */
	private enum State { STOPPED, PLAYING, PAUSED, DONE }
	public State state;
	
	private Judge judge;
	

	private Song song;
	
	public SongPlayer(Board board, Song song) {
		this.board = board;
		this.song = song;
		this.state = State.STOPPED;
		this.judge = new Judge(board);
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
				currentBeat + previewBeats <= song.notes.get(currentNoteIndex).startBeat) {
			GuiNote guinote = new GuiNote(song.notes.get(currentNoteIndex), 
					1, Note.noteToPos(song.notes.get(currentNoteIndex).name));
			visibleNotes.add(guinote);
			
			currentNoteIndex++;
		}
	}
	
	/** Moves all notes towards current line */
	private void advanceNotes() {
		for (GuiNote n : visibleNotes) {
			// TODO note.advance or advanceGui
			if (n.note.startBeat <= currentBeat) {
				judgeMeOn(n.note);
			}
			if (currentBeat > n.note.startBeat + n.note.duration + postviewBeats) {
				visibleNotes.remove(n);
			}
			else {
				n.x = (n.note.startBeat - currentBeat)/previewBeats;
			}
		}
	}
	
	public ArrayList<GuiNote> getVisibleGuiNotes() {
		return visibleNotes;
	}
	
	/** Advances currentBeat to the realtime-determined time elapsed to avoid
	 *  timer drift. Called by gui/Board
	 */
	public void advanceToTime(long tot_elapsed) {
		currentBeat = (song.tempo * tot_elapsed / (60.0 * 1000.0)) - previewBeats;
		addToVisibleNotes();
		advanceNotes();
	}
	
	/** Causes Judge to recalculate score, boneage, etc. based on current performance */
	private void judgeMeOn(Note n) {
		
	}
}
