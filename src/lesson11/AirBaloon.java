
/*
 * HotAirBaloon (Lesson #11) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */
package lesson11;

////////////////ПОВІТРЯНА КУЛЯ – PRELIMINARY /////////////////////
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

public class AirBaloon extends Frame {
	private static final long serialVersionUID = -6122534134029441676L;

	AirBaloon(String title) {
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
		new AirBaloon("HotAirBaloon");
	}

	int frameWidth = 400, frameHeight = 600;
	int xStart = 100, yStart = 50;
	int baloonDiameter = 204;
	int baloonCarriegeDistance = baloonDiameter / 2;
	int carriegeWidth = baloonDiameter / 3;
	int carriegeHeight = baloonDiameter / 2;
	int xCarriege = xStart + baloonDiameter / 2 - carriegeWidth / 2;
	int yCarriege = yStart + baloonDiameter + baloonCarriegeDistance;
	int xLeftTopPoint = xStart + baloonDiameter / 13;
	int xRightTopPoint = xStart + baloonDiameter * 12 / 13;
	int yTopPoints = yStart + baloonDiameter * 31 / 40;
	GeneralPath airBaloon = new GeneralPath();
	Ellipse2D.Double baloon = new Ellipse2D.Double(xStart, yStart, baloonDiameter, baloonDiameter);
	Rectangle carriege = new Rectangle(xCarriege, yCarriege, carriegeWidth, carriegeHeight);
	Polygon leftLine = new Polygon();
	Polygon rightLine = new Polygon();

	public void paint(Graphics painter) {
		BufferedImage bi = (BufferedImage) createImage(frameWidth, frameHeight);
		Graphics2D big = bi.createGraphics();

		leftLine.addPoint(xCarriege, yCarriege);
		leftLine.addPoint(xLeftTopPoint, yTopPoints);

		rightLine.addPoint(xCarriege + carriegeWidth, yCarriege);
		rightLine.addPoint(xRightTopPoint, yTopPoints);

		airBaloon.append(leftLine, false);
		airBaloon.append(rightLine, false);
		airBaloon.append(baloon, false);
		airBaloon.append(carriege, false);

		big.draw(airBaloon);

		painter.drawImage(bi, 0, 0, this);

	}
}
