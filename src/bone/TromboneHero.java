package bone;

import gui.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import music.*;

public class TromboneHero {

	Song song;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TromboneHero();
	}
	
	TromboneHero() {
		System.out.println("TROMBONE HERO!!!!!!!!");
		SlideGetter slider = new SlideGetter();
		Thread slidethread = new Thread(slider);
		slidethread.start();
		GameFrame frame = new GameFrame();
		System.err.println(slider);
		frame.setSlider(slider);

	}

}
