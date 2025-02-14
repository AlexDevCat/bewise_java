package teacherLessons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Painter;

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

		setSize(frameLength, frameHeight);
		setBackground(new Color(0, 128, 128));
		setVisible(true);
	}

// main method	//
	public static void main(String[] args) {
		new AutoPainter("My Painter");
	}
	int frameLength = 1300, frameHeight = 600;
	int yTopLine = 40;
	int cabinHeight = 30;
	int xEngine = 60, engineWidth = 190, engineHeight = 40;
	int diameter = 30;
	int shiftEngineCabin = 20, shiftEngineWheels = 10;
	int distance = 40;
	int laysCount = 3;
	int shiftLineAuto = 20;
	// variables for calculation
	int xCabin = xEngine + shiftEngineCabin;
	int yCabin = yTopLine + shiftLineAuto;
	int cabinWidth = engineWidth - shiftEngineCabin * 2;
	int yEngine = yCabin + cabinHeight;
	int xLeftWheel = xEngine + shiftEngineWheels;
	int yWheels = yEngine + engineHeight;
	int xRightWheel = engineWidth + xEngine - shiftEngineWheels - diameter;
	int autoShift = engineWidth + distance;
	int roadLayHeight = shiftLineAuto * 2 + cabinHeight + engineHeight + distance;
	

//Method for shapes painting//
	public void paint(Graphics grf) { // API //		
		Graphics2D painter = (Graphics2D) grf;		
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.WHITE);
		painter.drawLine(0,yTopLine, frameLength, yTopLine);
		// x y //
		for(int lay = 0;lay < laysCount;lay++) {
			for (int i = 0; i < 5; i++) { // i = i + 1			
				painter.drawRect(xCabin + autoShift * i, yCabin + roadLayHeight * lay, cabinWidth, cabinHeight);// cabin
				painter.drawRect(xEngine + autoShift * i, yEngine + roadLayHeight * lay, engineWidth, engineHeight); // engine
				painter.drawOval(xLeftWheel + autoShift * i, yWheels + roadLayHeight * lay, diameter, diameter); // leftWheel
				painter.drawOval(xRightWheel + autoShift * i, yWheels + roadLayHeight * lay, diameter, diameter); // rightWheel
		}
			painter.drawLine(0,yTopLine + roadLayHeight * (lay + 1), frameLength, yTopLine + roadLayHeight * (lay + 1));
		}
	}
}
