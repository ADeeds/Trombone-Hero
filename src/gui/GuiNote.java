package gui;

import java.awt.Color;

import music.Note;

public class GuiNote {
	public Note note;
	public double x_center, right_side;
	public Color color = Color.RED;
	public GuiNote(Note n, double x, double right_side, int pos) {
		this.note = n;
		x_center = x;
		this.right_side = right_side;
	}
}
