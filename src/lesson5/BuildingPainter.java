/*
 * Building painter with loops (Lesson #4) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 */
package lesson5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BuildingPainter extends Frame {
	int SCREEN_WIDTH = 1000, SCREEN_HEIGHT = 600;

	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	BuildingPainter(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

		});

		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		//setBackground(new Color(0, 128, 128)); // Background color = teal
		setVisible(true);
	}

	// Basic variables
	int xRoof = 10;
	int yRoof = SCREEN_HEIGHT / 6;
	int widthRoof = 300;
	int heightRoof = 30;

	int heightWalls = 330;
	int shiftWalls = 30;
	
	int yShiftDoor = 50;
	int distance = 20;
	// Calculated variables
	int autoShift = widthRoof + distance;

	// Walls calculation
	int xWalls = xRoof + shiftWalls;
	int yWalls = yRoof + heightRoof;
	int widthWalls = widthRoof - 2 * shiftWalls;
	int xShiftDoor = widthWalls/9;
	// Doors calculation
	int xDoor = xWalls + xShiftDoor;
	int yDoor = yWalls + heightWalls / 5;
	int widthDoorAndSideWindow = widthWalls / 3;
	int heightDoor = heightWalls - heightWalls / 5;

	// Windows calculation
	int yWindow = yDoor;
	int xWindow = xWalls + widthWalls - widthDoorAndSideWindow - shiftWalls;

	// Main method - entry point //
	public static void main(String[] args) {
		new BuildingPainter("Building");
	}
	// Method for shapes painting//

	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.DARK_GRAY);
		for (int i = 0; i < 3; i++) {
			painter.drawRect(xRoof + autoShift * i, yRoof, widthRoof, heightRoof); // roof
			painter.drawRect(xWalls + autoShift * i, yWalls, widthWalls, heightWalls);// walls
			painter.drawRect(xDoor + autoShift * i, yDoor, widthDoorAndSideWindow, heightDoor); // door
			painter.drawRect(xWindow + autoShift * i, yWindow, widthDoorAndSideWindow, widthDoorAndSideWindow); // window

		}

	}
}
