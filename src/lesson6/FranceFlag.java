/*
 * France flag painter (Lesson #6) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FranceFlag extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	FranceFlag(String title) {
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
		new FranceFlag("Flags");
	}

	int frameLength = 300, frameHeight = 400;
	int flagWidth = 180, flagHeight = 120;
	int xFlag = 40, yFlag = 40;
	int letterHeight = 30;
	// variables for calculation
	int lineWidth = flagWidth / 3;

	Color[] france = { Color.BLUE, Color.WHITE, Color.RED };
	
	//Method for shapes painting//
	public void paint(Graphics painter) { // API //
		
		for (int i = 0; i < france.length; i++) {
			painter.setColor(france[i]);
			painter.fillRect(xFlag + lineWidth * i, yFlag, lineWidth, flagHeight);			
		}		
		painter.setColor(Color.BLACK);
		painter.drawRect(xFlag, yFlag, flagWidth, flagHeight);
		painter.setFont(new Font("DIALOG", Font.ITALIC, letterHeight));
		painter.drawString("France", xFlag, yFlag+flagHeight+30);
	}
}
