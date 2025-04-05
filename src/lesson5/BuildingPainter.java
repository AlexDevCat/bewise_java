/*
 * Building painter with loops (Lesson #5) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
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
	int SCREEN_WIDTH = 1500, SCREEN_HEIGHT = 600;

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
	int countWindows = 4;
	int countFloors = 3;
	int countBuildings = 4;

	int heightWalls = 330;
	int shiftWalls = 30;
	
	int distance = 20;
	// Calculated variables
	int autoShift = widthRoof + distance;

	// Walls calculation
	int xWalls = xRoof + shiftWalls;
	int yWalls = yRoof + heightRoof;
	int widthWalls = widthRoof - 2 * shiftWalls;
	
	// Windows calculation
	int widthXSideWindow = widthWalls / (countWindows + 1);
	int xShiftWindow = widthXSideWindow / (countWindows + 1);
	int widthYSideWindow = heightWalls / (countFloors + 1);
	int yShiftWindow = widthYSideWindow / (countFloors + 1);
	int xStart = xWalls + xShiftWindow;
	int yStart = yWalls + yShiftWindow;

	// Main method - entry point //
	public static void main(String[] args) {
		new BuildingPainter("Building");
	}
	// Method for shapes painting//

	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.DARK_GRAY);
		for (int i = 0; i < countBuildings; i++) { 
			// painting buildings
			painter.drawRect(xRoof + autoShift * i, yRoof, widthRoof, heightRoof); // roof
			painter.drawRect(xWalls + autoShift * i, yWalls, widthWalls, heightWalls);// walls
			int yWindow = yStart;
			for (int f = 0; f < countFloors; f++) {
				// painting floors
				int xWindow = xStart + autoShift * i;
		    	for (int j = 0; j < countWindows; j++) {
		    		// painting windows
				    painter.drawRect(xWindow, yWindow, widthXSideWindow, widthYSideWindow); // window
		    		xWindow = xWindow + widthXSideWindow + xShiftWindow;
		    	}
		    	yWindow = yWindow + widthYSideWindow + yShiftWindow;
			}
		}
	}
}
