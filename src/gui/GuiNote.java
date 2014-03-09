package gui;

import music.Note;

public class GuiNote {
	public Note note;
	public double x;
	public int position;
	public GuiNote(Note n, double x, int pos) {
		this.note = n;
		this.x = x;
		position = pos;
	}
}
