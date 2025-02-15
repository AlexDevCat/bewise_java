package lesson5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RailwayCarriage extends Frame {
	private static final long serialVersionUID = 1L;

	RailwayCarriage(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		setSize(frameLength, frameHeight);
		setBackground(new Color(0, 128, 128));
		setVisible(true);
	}

	public static void main(String[] args) {
		new RailwayCarriage("Railway Carriage");
	}

	int frameLength = 1300, frameHeight = 600; // Change frame size if needed
	int numCarriages = 1; // Number of carriages (for outer loop)

	// Carriage
	int yCarriage = 200;
	int carriageWidth = 280, carriageHeight = 80;

	// Roof
	int roofHeight = 20;

	int wheelSpacing = 10;
	
	// Connector
	int xConnector = 10;
	int connectorWidth = 20, connectorHeight = 10;

	int numWindows = 9; // for inner loop

	int windowSize = 25;
	int windowSpacing = 10;
	int carriageSpacing = connectorWidth;

	// Shifts
	int carriageShift = 20;
	int connectorShift = connectorWidth*2 + carriageWidth;

	// Calculated variables
	int yConnector = yCarriage + (carriageHeight / 2) - (connectorHeight / 2);
	int xCarriage =xConnector + connectorWidth;
	int wheelDiameter = carriageWidth / 6;

	public void paint(Graphics grf) {
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(3));
		painter.setColor(Color.WHITE);

		for (int c = 0; c < numCarriages; c++) {
			

			// Carriage body
			painter.drawRect(xConnector, yConnector, connectorWidth, connectorHeight); // Front connector
			
			painter.drawRect(xCarriage, yCarriage - roofHeight, carriageWidth, roofHeight); // Roof
			painter.drawRect(xCarriage, yCarriage, carriageWidth, carriageHeight);		

			
			painter.drawRect(xCarriage + carriageWidth, yConnector, connectorWidth, connectorHeight); // End connector
			
			xCarriage = c * (carriageWidth + carriageSpacing);
			

			// Windows
			int totalWindowSpacing = (carriageWidth - (numWindows * windowSize)) / (numWindows + 1);
			for (int w = 0; w < numWindows; w++) {
				int xWindow = xCarriage + totalWindowSpacing + w * (windowSize + totalWindowSpacing);
				int yWindow = yCarriage + 10;
				painter.drawRect(xWindow, yWindow, windowSize, windowSize);
			}

			// Wheels
			int xWheel1 = xCarriage + wheelSpacing;
			int xWheel2 = xCarriage + carriageWidth / 2 - wheelDiameter - wheelSpacing;
			int xWheel3 = xCarriage + carriageWidth / 2 + wheelSpacing;
			int xWheel4 = xCarriage + carriageWidth - wheelSpacing - wheelDiameter;
			int yWheel = yCarriage + carriageHeight;

			// Wheel1, wheel2 and connector
			painter.drawOval(xWheel1, yWheel, wheelDiameter, wheelDiameter);
			painter.drawOval(xWheel4, yWheel, wheelDiameter, wheelDiameter);
			painter.drawRect(xWheel1 + wheelDiameter, yWheel + wheelDiameter / 3, xWheel2 - xWheel1 - wheelDiameter,
					wheelDiameter / 3);

			// Wheel2, wheel3 and connector
			painter.drawOval(xWheel2, yWheel, wheelDiameter, wheelDiameter);
			painter.drawOval(xWheel3, yWheel, wheelDiameter, wheelDiameter);
			painter.drawRect(xWheel3 + wheelDiameter, yWheel + wheelDiameter / 3, xWheel4 - xWheel3 - wheelDiameter,
					wheelDiameter / 3);
		}
	}
}
