/*
 * Pendulum (Lesson #11) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson11;

////////////////МАЯТНИК – PRELIMINARY /////////////////////                    
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class Pendulum extends Frame {

	private static final long serialVersionUID = 8771788840447724245L;

	Pendulum(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		setSize(frameWidth, frameHeight);
		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Pendulum("Pendulum");
	}

	int frameWidth = 200, frameHeight = 400;
	int xStart = 50, yStart = 50;
	int horizontalLineWidth = 100, verticalLineHeight = 200;
	int pendulumWidth = 40, pendulumHeight = 80;
////////////////////////////////////////////////////////
	int pendulumCenter = xStart + horizontalLineWidth / 2;
	int xRightPoint = xStart + horizontalLineWidth;
	int xPendulum = pendulumCenter - pendulumWidth / 2;
	int yPendulum = yStart + verticalLineHeight;

	Rectangle weight = new Rectangle(xPendulum, yPendulum, pendulumWidth, pendulumHeight);
	GeneralPath pendulum = new GeneralPath();
	Polygon lineHor = new Polygon();
	Polygon lineVert = new Polygon();

	public void paint(Graphics painter) {

		BufferedImage bi = (BufferedImage) createImage(frameWidth, frameHeight);
		Graphics2D big = bi.createGraphics();

		lineHor.addPoint(xStart, yStart);
		lineHor.addPoint(xRightPoint, yStart);

		lineVert.addPoint(pendulumCenter, yStart);
		lineVert.addPoint(pendulumCenter, yPendulum);

		pendulum.append(lineHor, false);
		pendulum.append(lineVert, false);
		pendulum.append(weight, false);
		big.draw(pendulum);

		painter.drawImage(bi, 0, 0, this);

	}
}