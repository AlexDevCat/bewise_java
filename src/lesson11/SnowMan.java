/*
 * SnowMan (Lesson #11) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson11;

////////////////СНІГОВИК – PRELIMINARY /////////////////////
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class SnowMan extends Frame {

	private static final long serialVersionUID = 1071695077789535672L;

	SnowMan(String title) {
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
		new SnowMan("SnowMan");
	}

	int frameWidth = 400, frameHeight = 600;
	int xStart = 100, yStart = 100;
	int diameterUp = 80, diameterMiddle = 120, diameterBottom = 200;
//////////////////////////////////
	int snowManCenter = xStart + diameterBottom / 2;
	int xUp = snowManCenter - diameterUp / 2;
	int xMiddle = snowManCenter - diameterMiddle / 2;
	int yMiddle = yStart + diameterUp;
	int yBottom = yMiddle + diameterMiddle;
	GeneralPath snowMan = new GeneralPath();
	Ellipse2D.Double bottom = new Ellipse2D.Double(xStart, yBottom, diameterBottom, diameterBottom);
	Ellipse2D.Double middle = new Ellipse2D.Double(xMiddle, yMiddle, diameterMiddle, diameterMiddle);
	Ellipse2D.Double up = new Ellipse2D.Double(xUp, yStart, diameterUp, diameterUp);

	public void paint(Graphics painter) {
		BufferedImage bi = (BufferedImage) createImage(frameWidth, frameHeight);
		Graphics2D big = bi.createGraphics();

		snowMan.append(up, false);
		snowMan.append(middle, false);
		snowMan.append(bottom, false);

		big.draw(snowMan);
		painter.drawImage(bi, 0, 0, this);

	}
}
