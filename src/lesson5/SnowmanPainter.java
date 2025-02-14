/*
 * Snowman painter with loops and variables (Lesson #4) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 */
package lesson5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnowmanPainter extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	SnowmanPainter(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

		});

		setSize(2000, 600);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);

	}

// main method	//
	public static void main(String[] args) {
		new SnowmanPainter("Snowman");
	}

	// Base variables
	int yHead = 100;
	int dHead = 80;

	int xLeftHand = 50;
	int distance = 40;
	// calculated

	int dMiddle = dHead * 3 / 2;
	int dBottom = dHead * 5 / 2;
	int dHands = dMiddle / 2;
	// shifts
	int autoShift = dHands * 2 + dMiddle + distance;
	int shiftHand = (dMiddle - dHead) / 2;
	int shiftBottom = (dBottom - dMiddle) / 2;
	int shiftShoulder = (dMiddle - dHands) / 2;

	int xHead = xLeftHand + dHands + shiftHand;
	int xMiddle = xHead - shiftHand, yMiddle = yHead + dHead;

	int xBottom = xMiddle - shiftBottom, yBottom = yHead + dHead + dMiddle;

	int yHand = yHead + dHead + shiftShoulder; // Same for both hands
	int xRightHand = xMiddle + dMiddle;

//Method for shapes painting//
	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.BLACK);
		for (int i = 0; i < 4; i++) {
			painter.drawOval(xHead + autoShift*i, yHead, dHead, dHead); // upper - snowman (head)
			painter.drawOval(xMiddle + autoShift*i, yMiddle, dMiddle, dMiddle); // middle - snowman (body)
			painter.drawOval(xBottom + autoShift*i, yBottom, dBottom, dBottom); // bottom - snowman (legs)

			painter.drawOval(xLeftHand + autoShift*i, yHand, dHands, dHands); // left hand
			painter.drawOval(xRightHand + autoShift*i, yHand, dHands, dHands); // right hand
		}
	}
}
