/*
 * House (Lesson #11) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson11;

//////////////// БУДИНОК – PRELIMINARY /////////////////////
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class House extends Frame {

	private static final long serialVersionUID = 6484638075431581119L;

	House(String title) {
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
		new House("House");
	}

	int frameWidth = 400, frameHeight = 300;
	int xStart = 50, yStart = 50;
	int shiftRoofWall = 10, roofWidth = 280, roofHeight = 20;
	int wallHeight = 150;
	int shiftRoofDoor = 40;
	////////////////////////////////////////////////////////
	int xWall = xStart + shiftRoofWall;
	int yWall = yStart + roofHeight;
	int wallWidth = roofWidth - shiftRoofWall * 2;
	int allShiftsAndWidth = wallWidth / 5;
	int xDoor = xWall + allShiftsAndWidth;
	int yDoorAndWindow = yWall + shiftRoofDoor;
	int doorHeight = wallHeight - shiftRoofDoor;
	int xWindow = xWall + allShiftsAndWidth * 3;
	int windowHeight = doorHeight / 2;

	GeneralPath house = new GeneralPath();
	Rectangle roof = new Rectangle(xStart, yStart, roofWidth, roofHeight);
	Rectangle walls = new Rectangle(xWall, yWall, wallWidth, wallHeight);
	Rectangle door = new Rectangle(xDoor, yDoorAndWindow, allShiftsAndWidth, doorHeight);
	Rectangle window = new Rectangle(xWindow, yDoorAndWindow, allShiftsAndWidth, windowHeight);

	public void paint(Graphics painter) {
		BufferedImage bi = (BufferedImage) createImage(frameWidth, frameHeight);
		Graphics2D big = bi.createGraphics();

		house.append(roof, false);
		house.append(walls, false);
		house.append(door, false);
		house.append(window, false);

		big.draw(house);

		painter.drawImage(bi, 0, 0, this);

	}
}