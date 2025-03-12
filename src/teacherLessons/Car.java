package teacherLessons;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class Car extends Frame {
	private static final long serialVersionUID = 1L;

	Car(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		setSize(frameWidth, frameHeight);
		setVisible(true);
	}

	public static void main(String[] args) {
		Car myFirstCar = new Car("Auto");
	}

	// initializated primitive variables
	int frameWidth = 400, frameHeight = 250;
	int xStart = 80, yStart = 50;
	int cabinHeight = 40, cabinWidth = 120;
	int engineHeight = 60, engineWidth = 260;
	int diameter = 40;
	int shiftEngineWheel = 10;
	// calculated primitive variables
	int shiftEngineCabin = (engineWidth - cabinWidth) / 2;
	int xCabin = xStart + shiftEngineCabin;
	int yEngine = yStart + cabinHeight;
	int xLeftWheel = xStart + shiftEngineWheel;
	int xRightWheel = xStart + engineWidth - shiftEngineWheel - diameter;
	int yWheels = yEngine + engineHeight;
	int yLines = yWheels + diameter / 2;
// object variables
	Rectangle cabin = new Rectangle(xCabin, yStart, cabinWidth, cabinHeight);
	Rectangle engine = new Rectangle(xStart, yEngine, engineWidth, engineHeight);
	Ellipse2D.Double leftWheel = new Ellipse2D.Double(xLeftWheel, yWheels, diameter, diameter);
	Ellipse2D.Double rightWheel = new Ellipse2D.Double(xRightWheel, yWheels, diameter, diameter);
	GeneralPath auto = new GeneralPath();
	Polygon lineLeft = new Polygon();
	Polygon lineRight = new Polygon();

	public void paint(Graphics painter) {
		//////////////// START OF PATTERN_1 //////////////////////////
		BufferedImage bi = (BufferedImage) createImage(frameWidth, frameHeight);
		Graphics2D big = bi.createGraphics();
		//////////////// END OF PATTERN_1 //////////////////////////
		auto.append(cabin, false);
		auto.append(engine, false);
		auto.append(leftWheel, false);
		auto.append(rightWheel, false);
		big.draw(auto);
		lineLeft.addPoint(xLeftWheel, yLines);
		lineLeft.addPoint(xLeftWheel + diameter, yLines);
		big.draw(lineLeft);
		lineRight.addPoint(xRightWheel, yLines);
		lineRight.addPoint(xRightWheel + diameter, yLines);
		big.draw(lineRight);
		//////////////// START OF PATTERN_2 //////////
		painter.drawImage(bi, 0, 0, this);
		//////////////// END OF PATTERN_2 /////////////
	}
}