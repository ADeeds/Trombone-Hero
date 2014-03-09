package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import music.Song;
import music.SongFileReader;

public class GameFrame {
	Song song;
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
		    			Board b = new Board(song);
		    			b.setSize(frame.getWidth(), frame.getHeight());
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
		JButton openbutton = new JButton("OPEN A SONG");
		openbutton.addActionListener(opensong());
		frame.add(openbutton);
		//frame.pack();
		frame.setVisible(true);
	}
	
	
	

}