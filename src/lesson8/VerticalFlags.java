/*
 * Vertical flags painter (Lesson #8) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */
package lesson8;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VerticalFlags extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	VerticalFlags(String title) {
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
		new VerticalFlags("Flags");
	}

	int frameLength = 700, frameHeight = 300;
	int flagWidth = 180, flagHeight = 120;
	int xFlag = 40, yFlag = 40;
	int letterHeight = 30;
	// variables for calculation
	int distance = flagWidth + 40;

	Color[] italy = { new Color(33, 125, 30), Color.WHITE, Color.RED };
	Color[] france = { Color.BLUE, Color.WHITE, Color.RED };
	Color[] romania = { Color.BLUE, Color.ORANGE, Color.RED };

	Color[][] states = { italy, france, romania };
	String[] stateNames = { "Italy", "France", "Romania" };

	// Method for shapes painting//
	public void paint(Graphics painter) { // API //
		int countColors = 0;
		int lineWidth = 0;
		painter.setFont(new Font("DIALOG", Font.ITALIC, letterHeight));
		for (int s = 0; s < states.length; s++) {
			countColors = states[s].length; // Обчислюємо довжину поточного масиву з кольорами
			lineWidth = flagWidth / countColors;
			for (int stateColorIndex = 0; stateColorIndex < countColors; stateColorIndex++) {
				painter.setColor(states[s][stateColorIndex]);
				painter.fillRect(xFlag + distance * s + lineWidth * stateColorIndex, yFlag, lineWidth, flagHeight);
			}
			painter.setColor(Color.BLACK);
			painter.drawString(stateNames[s], xFlag + distance * s, yFlag + flagHeight + 40);
			painter.drawRect(xFlag + distance * s, yFlag, flagWidth, flagHeight);
		}
	}
}
