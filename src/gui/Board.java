package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import bone.SlideGetter;
import bone.SlideStats;

import music.Song;
import music.SongPlayer;

public class Board extends JPanel implements ActionListener{
	final int note_circle_radius = 10;
	final int roundrect_arc_len = 15;
	final int bone_offset = 0;
	ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
	private Timer timer;

	private final int DELAY = 33;
	private Song song;
	private long start_time = 0;
	long frame = 0;
	public SlideGetter slide;
	ImageIcon bellcon = new ImageIcon("res/bell.png");
	ImageIcon inslide = new ImageIcon("res/inslide.png");
	ImageIcon outslide = new ImageIcon("res/outslide.png");
	SongPlayer player;
	public void setSlideGetter(SlideGetter s) {
		slide = s;
	}
	public Board(Song s) {
		song = s;
		initBoard();
	}
	private void initBoard() {
		player = new SongPlayer(this, song);

		setBackground(Color.BLACK);

		setDoubleBuffered(true);

		timer = new Timer(DELAY, this);
		start_time = System.currentTimeMillis();
		player.play();
		timer.start();
	}

	
	public void paint(Graphics g)
	{
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		/*RenderingHints rh =
				new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2.setRenderingHints(rh);*/
		Dimension size = getSize();
		int w = (int)size.getWidth();
		int h = (int)size.getHeight();
		int staff_line = w/5;
		ArrayList<GuiNote> visibleNotes = (ArrayList<GuiNote>) player.getVisibleGuiNotes();
		//System.out.println(visibleNotes.size());
		for (GuiNote n : visibleNotes) {
			//System.out.println("")
			g2.setColor(n.color);
			int x = (int) (n.x_center * (w - staff_line) - note_circle_radius + staff_line);
			int y = map_slidepos_to_screen_pos(n.note.position, h) - note_circle_radius;
			g2.fillOval(x, y, 2*note_circle_radius, 2*note_circle_radius);
			if (n.note.duration > 0.9) g2.drawRoundRect(x, y, (int) (n.right_side*(w - staff_line)), 2*note_circle_radius, roundrect_arc_len, roundrect_arc_len);
			g2.drawString(n.note.name, x + note_circle_radius, y - 5);
		}


		//System.out.println(w + "\t" + h);
		g2.setColor(Color.GRAY);
		for(int i = 1; i < 8; i++) {
			//First pos is 275 pixels tall
			int height = map_slidepos_to_screen_pos(i, h);
			g2.drawLine(0,height, w, height);
		}
		g2.drawImage(inslide.getImage(), 0,bone_offset, w/4, h - bone_offset, null);
		g2.drawImage(outslide.getImage(), 0,(int)
				(bone_offset - h/150 * SlideStats.getFirstOffset(slide.getPosition())), w/4, h - bone_offset, null);
		g2.drawImage(bellcon.getImage(), 0,(int)(bone_offset - (h * 0.2)), w/4, (int)(1.2*h - bone_offset), null);

		g2.setColor(Color.WHITE);
		g2.drawLine(w/5 + 4, h/4, w/5 + 4, h*5/4);
		g2.setColor(Color.YELLOW);
		g2.fillRect(150, 5, (int)((player.judge.boneage / 100.0) * (w/4.0)), 17);
		g2.setColor(Color.RED);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 24)); 
		g2.drawString("BONEAGE:", 10, 22);
		g2.drawString("Score: " + player.judge.score, w*7/10, 22);
		g2.drawRect(150, 5, w/4, 17);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long cur_time = System.currentTimeMillis();
		player.advanceToTime(cur_time - start_time);
		/*if (slide != null) {
			System.out.println("Pos: " + slide.getPosition());
		}*/
		repaint();
		
		//frame++;
	}

	public int map_slidepos_to_screen_pos(int pos, int h) {
		int centimeters = SlideStats.getFirstOffset(SlideStats.PositionDistances[pos]);
		return (int)(h - (275.0/2432) * h - h/150.0 * centimeters);
	}



}
