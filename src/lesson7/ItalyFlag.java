/*
 * Italy flag painter (Lesson #7) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson7;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ItalyFlag extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	ItalyFlag(String title) {
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
		new ItalyFlag("Flags");
	}

	int frameLength = 300, frameHeight = 400;
	int flagWidth = 180, flagHeight = 120;
	int xFlag = 40, yFlag = 40;
	int letterHeight = 30;
	// variables for calculation
	int lineWidth = flagWidth / 3;

	Color[] france = { new Color( 33, 125, 30), Color.WHITE, Color.RED };
	
	//Method for shapes painting//
	public void paint(Graphics painter) { // API //
		
		for (int i = 0; i < france.length; i++) {
			painter.setColor(france[i]);
			painter.fillRect(xFlag + lineWidth * i, yFlag, lineWidth, flagHeight);			
		}		
		painter.setColor(Color.BLACK);
		painter.drawRect(xFlag, yFlag, flagWidth, flagHeight);
		painter.setFont(new Font("DIALOG", Font.ITALIC, letterHeight));
		painter.drawString("Italy", xFlag, yFlag+flagHeight+30);
	}
}
