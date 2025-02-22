/*
 * Greece flag painter (Lesson #6) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson6;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.Painter;

public class GreeceFlag extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	GreeceFlag(String title) {
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
		new GreeceFlag("Flags");
	}

	int frameLength = 300, frameHeight = 400;
	int flagWidth = 180, flagHeight = 90;
	int xFlag = 40, yFlag = 40;
	int letterHeight = 30;
	// variables for calculation
	int lineHeight = flagHeight / 9;
	int flagSmoleSide = 5 * lineHeight;

	//Method for shapes painting//
	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.BLUE);
		painter.fillRect(xFlag, yFlag, flagWidth, flagHeight);
		int yLineShift = yFlag + lineHeight;
		for (int i = 0; i < 4; i++) {
			painter.setColor(Color.WHITE);
			painter.fillRect(xFlag, yLineShift + 2 * lineHeight * i, flagWidth, lineHeight);			
		}
		painter.setColor(Color.BLUE);
		painter.fillRect(xFlag, yFlag, flagSmoleSide, flagSmoleSide);
		painter.setColor(Color.WHITE);
		painter.fillRect(xFlag + lineHeight * 2, yFlag, lineHeight, flagSmoleSide);
		painter.fillRect(xFlag, yFlag + lineHeight * 2, flagSmoleSide, lineHeight);	
		painter.setColor(Color.BLACK);
		painter.setFont(new Font("DIALOG", Font.ITALIC, letterHeight));
		painter.drawString("Greece", xFlag, yFlag+flagHeight+30);
	}
}
