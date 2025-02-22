package teacherLessons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.Painter;

public class Flags extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	Flags(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

		});

		setSize(frameLength, frameHeight);
		setVisible(true);
	}

// main method	//
	public static void main(String[] args) {
		new Flags("Flags");
	}

	int frameLength = 300, frameHeight = 400;
	int flagWids = 180, flagHeight = 80;
	int xFlag = 40, yFlag = 40;
	int letterHeight = 30;
	// variables for calculation
	int distance = flagWids + 40;
	int lineHeight = flagHeight / 3;
	// ArrayList<Color> germanyArrayList = new ArrayList<Color>();
	Color[] germany = { Color.BLACK, Color.RED, Color.YELLOW };
	Color[] lithuania = { Color.YELLOW, Color.GREEN, Color.RED };
	Color[] holland = { Color.RED, Color.WHITE, Color.BLUE };


	
	//Method for shapes painting//
	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		for (int i = 0; i < germany.length; i++) {
			painter.setColor(germany[i]);
			painter.fillRect(xFlag, yFlag + lineHeight * i, flagWids, lineHeight);			
		}		
		painter.setColor(Color.BLACK);
		painter.setFont(new Font("DIALOG", Font.BOLD, letterHeight));
		painter.drawString("Germany", xFlag, yFlag+flagHeight+30);
	}
}
