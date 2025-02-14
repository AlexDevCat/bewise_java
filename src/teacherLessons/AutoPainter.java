package teacherLessons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AutoPainter extends Frame {
	private static final long serialVersionUID = 1L;

	// ************** Constructor ************
	AutoPainter(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

		});

		setSize(1500, 300);
		setBackground(new Color(0, 128, 128));
		setVisible(true);
	}

// main method	//
	public static void main(String[] args) {
		new AutoPainter("My Painter");
	}

	int yCabin = 100, cabinHeight = 30;
	int xEngine = 170, engineWidth = 190, engineHeight = 40;
	int diameter = 30;
	int shiftEngineCabin = 20, shiftEngineWheels = 10;
	int distance = 40;
	// variables for calculation
	int xCabin = xEngine + shiftEngineCabin;
	int cabinWidth = engineWidth - shiftEngineCabin * 2;
	int yEngine = yCabin + cabinHeight;
	int xLeftWheel = xEngine + shiftEngineWheels;
	int yWheels = yEngine + engineHeight;
	int xRightWheel = engineWidth + xEngine - shiftEngineWheels - diameter;
	int autoShift = engineWidth + distance;
	

//Method for shapes painting//
	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.WHITE);
		// x y //
		for (int i = 0; i < 2; i++) { // i = i + 1			
			painter.drawRect(xCabin + autoShift * i, yCabin, cabinWidth, cabinHeight);// cabin
			painter.drawRect(xEngine + autoShift * i, yEngine, engineWidth, engineHeight); // engine
			painter.drawOval(xLeftWheel + autoShift * i, yWheels, diameter, diameter); // leftWheel
			painter.drawOval(xRightWheel + autoShift * i, yWheels, diameter, diameter); // rightWheel
		}
	}
}
