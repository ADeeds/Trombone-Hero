package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import bone.SlideGetter;

import music.Song;

public class Board extends JPanel implements ActionListener{
	int boneage = 50;

	ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
	private Timer timer;

	private final int DELAY = 20;
	private Song song;
	private long start_time = 0;
	float current_beat = -8;
	long frame = 0;
	SlideGetter slide;
	ImageIcon bellcon = new ImageIcon("res/bell.png");
	ImageIcon inslide = new ImageIcon("res/inslide.png");
	ImageIcon outslide = new ImageIcon("res/outslide.png");
	
	public void setSlideGetter(SlideGetter s) {
		slide = s;
	}
	public Board(Song s) {
		initBoard();
		song = s;
	}
	private void initBoard() {

		setBackground(Color.BLACK);

		setDoubleBuffered(true);


		timer = new Timer(DELAY, this);
		start_time = System.currentTimeMillis();

		timer.start();
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		RenderingHints rh =
				new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2.setRenderingHints(rh);
		Dimension size = getSize();
		int w = (int)size.getWidth();
		int h = (int)size.getHeight();
		//System.out.println(w + "\t" + h);
		g2.drawImage(inslide.getImage(), 0,100, 200, h - 100, null);
		g2.drawImage(outslide.getImage(), 0,100, 200, h - 100, null);
		g2.drawImage(bellcon.getImage(), 0,h - 200, 200, 200, null);
		/*
		Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.gray);
		for (double deg = 0; deg < 360; deg += 5) {
			AffineTransform at =
					AffineTransform.getTranslateInstance(w / 2, h / 2);
			at.rotate(Math.toRadians(deg));
			g2.draw(at.createTransformedShape(e));
		}*/
		g2.setColor(Color.YELLOW);
		g2.fillRect(150, 5, 200 * boneage / 100, 17);
		g2.setColor(Color.RED);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 24)); 
		g2.drawString("BONEAGE:", 10, 22);
		g2.drawRect(150, 5, 200, 17);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long cur_time = System.currentTimeMillis();
		current_beat = (song.tempo * (cur_time - start_time) / (60f * 1000)) - 8f;
		System.out.println("Current beat: "  + current_beat);
		if (slide != null) {
			System.out.println("Pos: " + slide.getPosition());
		}
		repaint();  
		frame++;
	}
}
