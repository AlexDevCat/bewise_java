/*
* Постійний  рух автомобіля (Lesson #17) by Oleksander Kroshka (School 42, 5-A, Dnipro )
*/

package lesson17;
/*
 * 👉1. В попередньому коді додати до коліс автомобілів хрестоподібні лінії, що будуть здійснювати обертовий рух.
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class ConveyorCar extends Frame {
	private static final long serialVersionUID = 4933830196246850813L;

	ConveyorCar(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		for (int i = 0; i < autosCount; i++) {
			shifts[i] = -shiftAutos * i;
		}
		setSize(frameLength, frameHeight);
		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		ConveyorCar myCar = new ConveyorCar("Auto");
		myCar.go();
	}

	public void go() throws Exception {
		while (true) {
			repaint();
			Thread.sleep(3);
			wheelRotation += 0.05;
			for (int i = 0; i < autosCount; i++) {
				if (shifts[i] < frameLength + engineWidth) {
					shifts[i]++;
				} else {
					shifts[i] = 0;
				}
			}
		}
	}

	int frameLength = 1550, frameHeight = 300, yStart = 50;
	int cabinHeight = 30, cabinWidth = 120;
	int engineHeight = 40, engineWidth = 200;
	int diameter = 40;
	int shiftEngineWheel = 20;
	int autosCount = 5;
	private double wheelRotation = 0;

	int xStart = -engineWidth;
	int distance = (frameLength - engineWidth * (autosCount - 1)) / autosCount;
	int shiftAutos = engineWidth + distance;
	int shiftEngineCabin = (engineWidth - cabinWidth) / 2;
	int xCabin = xStart + shiftEngineCabin;
	int yEngine = yStart + cabinHeight;
	int xLeftWheel = xStart + shiftEngineWheel;
	int xRightWheel = xStart + engineWidth - shiftEngineWheel - diameter;
	int yWheels = yEngine + engineHeight;

	int[] shifts = new int[autosCount];
	Rectangle[] cabins = new Rectangle[autosCount];
	Rectangle[] engines = new Rectangle[autosCount];
	Ellipse2D.Double[] wheelsLeft = new Ellipse2D.Double[autosCount];
	Ellipse2D.Double[] wheelsRight = new Ellipse2D.Double[autosCount];
	int leftWheelCenterX;
	int rightWheelCenterX;
	int wheelCenterY;
	GeneralPath[] autos = new GeneralPath[autosCount];
	BufferedImage bi;
	Graphics2D big;
	Line2D.Double leftWheelVertical;
	Line2D.Double leftWheelHorizontal;
	Line2D.Double rightWheelVertical;
	Line2D.Double rightWheelHorizontal;

	public void update(Graphics g) {
		bi = (BufferedImage) createImage(frameLength, frameHeight);
		big = bi.createGraphics();
//////////////////////////for///////////////////////////////////		
		for (int i = 0; i < autosCount; i++) {
			leftWheelCenterX = xLeftWheel + shifts[i] + diameter / 2;
			rightWheelCenterX = xRightWheel + shifts[i] + diameter / 2;
			wheelCenterY = yWheels + diameter / 2;

			cabins[i] = new Rectangle(xCabin + shifts[i], yStart, cabinWidth, cabinHeight);
			engines[i] = new Rectangle(xStart + shifts[i], yEngine, engineWidth, engineHeight);
			wheelsLeft[i] = new Ellipse2D.Double(xLeftWheel + shifts[i], yWheels, diameter, diameter);
			wheelsRight[i] = new Ellipse2D.Double(xRightWheel + shifts[i], yWheels, diameter, diameter);

			autos[i] = new GeneralPath(cabins[i]);
			autos[i].append(engines[i], false);
			autos[i].append(wheelsLeft[i], false);
			autos[i].append(wheelsRight[i], false);
			big.draw(autos[i]);

			big.rotate(wheelRotation, leftWheelCenterX, wheelCenterY);

			leftWheelVertical = new Line2D.Double(leftWheelCenterX, wheelCenterY - diameter / 2, leftWheelCenterX,
					wheelCenterY + diameter / 2);
			leftWheelHorizontal = new Line2D.Double(leftWheelCenterX - diameter / 2, wheelCenterY,
					leftWheelCenterX + diameter / 2, wheelCenterY);
			rightWheelHorizontal = new Line2D.Double(rightWheelCenterX - diameter / 2, wheelCenterY,
					rightWheelCenterX + diameter / 2, wheelCenterY);
			rightWheelVertical = new Line2D.Double(rightWheelCenterX, wheelCenterY - diameter / 2, rightWheelCenterX,
					wheelCenterY + diameter / 2);

			big.draw(leftWheelVertical);
			big.draw(leftWheelHorizontal);
			big.rotate(-wheelRotation, leftWheelCenterX, wheelCenterY);

			big.rotate(wheelRotation, rightWheelCenterX, wheelCenterY);
			big.draw(rightWheelHorizontal);
			big.draw(rightWheelVertical);
			big.rotate(-wheelRotation, rightWheelCenterX, wheelCenterY);

		}
/////////////////////////////////////////////////////////////		
		g.drawImage(bi, 0, 0, this);
	}
}