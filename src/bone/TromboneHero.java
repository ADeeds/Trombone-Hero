package bone;

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

		final JFrame frame = new JFrame("TROMBONE HERO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		JButton openbutton = new JButton("OPEN A SONG");
		openbutton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Trombone Hero songs", "bone");
				final JFileChooser fc = new JFileChooser();
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(frame);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            SongFileReader fr = new SongFileReader(file.getAbsolutePath());
		            song = fr.parse();
		            System.out.println("Opening: " + file.getName() + "." + '\n');
		        } else {
		        	System.out.println("Open command cancelled by user." + '\n');
		        }
			}
		});
		frame.add(openbutton);
		//frame.pack();
		frame.setVisible(true);

	}

}
