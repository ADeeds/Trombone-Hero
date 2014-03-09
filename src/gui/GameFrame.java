package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import bone.SlideGetter;

import music.Song;
import music.SongFileReader;

public class GameFrame {
	Song song;
	SlideGetter slide;
	Board b;
	final JFrame frame = new JFrame("TROMBONE HERO");

	private ActionListener opensong() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Trombone Hero songs", "bone");
				final JFileChooser fc = new JFileChooser();
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(frame);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            SongFileReader fr = new SongFileReader(file.getAbsolutePath());
		            song = fr.parse();
		            System.out.println("Opening: " + file.getName() + "." + '\n');
		            if (song != null) {
		    			System.out.println(song.title + " is loaded");
		    			frame.remove((Component) arg0.getSource());
		    			frame.repaint();
		    			b = new Board(song);
		    			b.setSize(frame.getWidth(), frame.getHeight());
		    			b.setPreferredSize(frame.getSize());
		    			b.setMaximumSize(frame.getSize());
		    			b.setSlideGetter(slide);
		    			frame.add(b);

		    		} else {
		    			System.err.println("Song couldn't be opened");
		    		}
		        } else {
		        	System.out.println("Open command cancelled by user." + '\n');
		        }		
			}
		};
	}
	
	public GameFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("res/title.png");
		frame.setIconImage(icon.getImage());

		final JButton title = new JButton(icon);
		title.setSize(frame.getSize());
		title.addActionListener(opensong());
		frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				title.setSize(frame.getSize());
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.add(title);
		//frame.pack();
		frame.setVisible(true);
	}

	public void setSlider(SlideGetter slider) {
		System.out.println(slider);
		slide = slider;
	}
	
	
	

}