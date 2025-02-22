/*
 * Germany flag painter (Lesson #6) by Oleksander Kroshka (School 42, 5-A, Dnipro )
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

public class GermanyFlag extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	GermanyFlag(String title) {
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
		new GermanyFlag("Flags");
	}

	int frameLength = 300, frameHeight = 400;
	int flagWidth = 180, flagHeight = 80;
	int xFlag = 40, yFlag = 40;
	int letterHeight = 30;
	// variables for calculation
	int lineHeight = flagHeight / 3;

	Color[] germany = { Color.BLACK, Color.RED, Color.YELLOW };
	
	//Method for shapes painting//
	public void paint(Graphics painter) { // API //
		for (int i = 0; i < germany.length; i++) {
			painter.setColor(germany[i]);
			painter.fillRect(xFlag, yFlag + lineHeight * i, flagWidth, lineHeight);			
		}		
		painter.setColor(Color.BLACK);
		painter.drawRect(xFlag, yFlag, flagWidth, flagHeight);
		painter.setFont(new Font("DIALOG", Font.ITALIC, letterHeight));
		painter.drawString("Germany", xFlag, yFlag+flagHeight+30);
	}
}
