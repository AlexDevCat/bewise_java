/*
 * Horizontal flags painter (Lesson #8) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */
package lesson8;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HorizontalFlags extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	HorizontalFlags(String title) {
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
		new HorizontalFlags("Flags");
	}

	int frameLength = 1600, frameHeight = 300;
	int flagWidth = 180, flagHeight = 120;
	int xFlag = 40, yFlag = 40;
	int letterHeight = 30;
	// variables for calculation
	int distance = flagWidth + 40;

	Color[] germany = { Color.BLACK, Color.RED, Color.YELLOW };
	Color[] lithuania = { Color.YELLOW, Color.GREEN, Color.RED };
	Color[] holland = { Color.RED, Color.WHITE, Color.BLUE };
	Color[] bulgaria = { Color.WHITE, new Color(33, 125, 30), Color.RED };
	Color[] austria = { Color.RED, Color.WHITE, Color.RED };
	Color[] ukraine = { Color.BLUE, Color.YELLOW };
	Color[] poland = { Color.WHITE, Color.RED };

	Color[][] states = { germany, lithuania, holland, bulgaria, austria, ukraine, poland };
	String[] stateNames = { "Germany", "Lithuania", "Holland", "Bulgaria", "Austria", "Ukraine", "Poland" };

	// Method for shapes painting//
	public void paint(Graphics painter) { // API //
		int countColors = 0;
		int lineHeight = 0;
		painter.setFont(new Font("DIALOG", Font.ITALIC, letterHeight));
		for (int s = 0; s < states.length; s++) {
			countColors = states[s].length; // Обчислюємо довжину поточного масиву з кольорами
			lineHeight = flagHeight / countColors;
			for (int stateColorIndex = 0; stateColorIndex < countColors; stateColorIndex++) {
				painter.setColor(states[s][stateColorIndex]);
				painter.fillRect(xFlag + distance * s, yFlag + lineHeight * stateColorIndex, flagWidth, lineHeight);
			}
			painter.setColor(Color.BLACK);
			painter.drawString(stateNames[s], xFlag + distance * s, yFlag + flagHeight + 40);
			painter.drawRect(xFlag + distance * s, yFlag, flagWidth, flagHeight);
		}
	}
}
