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
	int  carriageHeight = 180;

	// Roof
	int roofHeight = 20;
	int roofWidth = 380; 
	

	int wheelSpacing = 10;

	// Connector
	int xConnector = 30;
	int connectorWidth = 30, connectorHeight = 20;

	int numWindows = 9; // For inner loop
	
	int carriageWidth = roofWidth - connectorWidth;

	int xWindowSize = carriageWidth / (numWindows + 5);
	int yWindowSize = carriageHeight / 3;
	int xWindowSpacing = (carriageWidth - numWindows * xWindowSize)/(numWindows+1);
	int carriageSpacing = connectorWidth;

	// Shifts
	int carriageShift = 2* connectorWidth + carriageWidth;
	int connectorShift = connectorWidth * 2 + carriageWidth;

	// Calculated variables
	int yConnector = yCarriage + carriageHeight - connectorHeight;
	
	int xCarriage = xConnector + connectorWidth;

	int wheelDiameter = carriageWidth / 8;

	public void paint(Graphics grf) {
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(3));
		painter.setColor(Color.WHITE);

		for (int c = 0; c < numCarriages; c++) {

			// Carriage body
			painter.drawRect(xConnector + c * carriageShift, yConnector, connectorWidth, connectorHeight); // Front connector 
																												

			painter.drawRect(xCarriage - connectorWidth/2, yCarriage - roofHeight, roofWidth, roofHeight); // Roof
			painter.drawRect(xCarriage, yCarriage, carriageWidth, carriageHeight); // Carriage

			painter.drawRect(xCarriage + carriageWidth, yConnector, connectorWidth, connectorHeight); // End connector

//			// Windows
			// int totalWindowSpacing = (carriageWidth - (numWindows * windowSize)) /
			// (numWindows + 1);
			int xWindowStart = xCarriage + xWindowSpacing;
			for (int w = 0; w < numWindows; w++) {

				int xWindow = xWindowStart + w * (xWindowSize + xWindowSpacing);
				int yWindow = yCarriage + 10;
				painter.drawRect(xWindow, yWindow, xWindowSize, yWindowSize);
			}
//
//			// Wheels
			int xWheel1 = xCarriage + wheelSpacing;
			int xWheel2 = xCarriage + carriageWidth / 3 - wheelDiameter - wheelSpacing;
			int xWheel3 = xCarriage + carriageWidth * 2 / 3 + wheelSpacing;
			int xWheel4 = xCarriage + carriageWidth - wheelSpacing - wheelDiameter;
			int yWheel = yCarriage + carriageHeight;
//
//			// Wheel1, wheel2 and connector
			painter.drawOval(xWheel1, yWheel, wheelDiameter, wheelDiameter);
			painter.drawOval(xWheel2, yWheel, wheelDiameter, wheelDiameter);
			painter.drawRect(xWheel1 + wheelDiameter, yWheel + wheelDiameter / 3, xWheel2 - xWheel1 - wheelDiameter,
					wheelDiameter / 3);

			// Wheel3, wheel4 and connector
			painter.drawOval(xWheel3, yWheel, wheelDiameter, wheelDiameter);
			painter.drawOval(xWheel4, yWheel, wheelDiameter, wheelDiameter);
			painter.drawRect(xWheel3 + wheelDiameter, yWheel + wheelDiameter / 3, xWheel4 - xWheel3 - wheelDiameter,
					wheelDiameter / 3);

			xCarriage = xCarriage + carriageWidth + 2 * connectorWidth;
		}
	}
}
