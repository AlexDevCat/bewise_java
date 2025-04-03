/*
* Постійний  рух однакових колон (Lesson #17) by Oleksander Kroshka (School 42, 5-A, Dnipro )
*/

package lesson17;
/*
 * 👉2. Написати код для нескінченного руху вправо пари однакових вертикальних колон згідно нижченаведеного рисунку
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class ConveyorColumn extends Frame {
	private static final long serialVersionUID = 3433569497085314618L;

	ConveyorColumn(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		for (int i = 0; i < columnsCount; i++) {
			shifts[i] = -shiftColumns * i;
		}
		setSize(frameLength, frameHeight);
		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		ConveyorColumn myCar = new ConveyorColumn("Auto");
		myCar.go();
	}

	public void go() throws Exception {
		while (true) {
			repaint();
			Thread.sleep(3);
			for (int i = 0; i < columnsCount; i++) {
				if (shifts[i] < frameLength + columnWidth) {
					shifts[i]++;
				} else {
					shifts[i] = 0;
				}
			}
		}
	}

	// Shared variables
	int frameLength = 1550, frameHeight = 400, yUpStart = 70;
	int columnsCount = 4;

	// column parts
	int bodyHeight = 100, bodyWidth = 90; // column bodies
	int columnHeight = 30, columnWidth = 120; // column heads and foots

	// Same for both columns
	int xStart = -columnWidth;
	int distance = (frameLength - columnWidth * (columnsCount - 1)) / columnsCount;
	int shiftColumns = columnWidth + distance;
	int shiftBottomColumnLowerBody = (columnWidth - bodyWidth) / 2;
	int xBody = xStart + shiftBottomColumnLowerBody;
	int yDownStart = yUpStart + columnHeight + bodyHeight + shiftBottomColumnLowerBody * 2;
	int yBottomColumn = yDownStart + bodyHeight;

	int yUpperBody = yUpStart + columnHeight;
	int shiftTopColumnUpperBody = (columnWidth - bodyWidth) / 2;

	////////////////////
	int[] shifts = new int[columnsCount];
	Rectangle[] lowerBodies = new Rectangle[columnsCount];
	Rectangle[] bottomColumns = new Rectangle[columnsCount];
	Rectangle[] topColumns = new Rectangle[columnsCount];
	Rectangle[] upperBodies = new Rectangle[columnsCount];
	GeneralPath[] columns = new GeneralPath[columnsCount];
	BufferedImage bi;
	Graphics2D big;

	public void update(Graphics g) {
		bi = (BufferedImage) createImage(frameLength, frameHeight);
		big = bi.createGraphics();
/////////////////////////////////////////////////////////////		
		for (int i = 0; i < columnsCount; i++) {
			topColumns[i] = new Rectangle(xStart + shifts[i], yUpStart, columnWidth, columnHeight);
			upperBodies[i] = new Rectangle(xBody + shifts[i], yUpperBody, bodyWidth, bodyHeight);

			lowerBodies[i] = new Rectangle(xBody + shifts[i], yDownStart, bodyWidth, bodyHeight);
			bottomColumns[i] = new Rectangle(xStart + shifts[i], yBottomColumn, columnWidth, columnHeight);

			columns[i] = new GeneralPath(topColumns[i]);
			columns[i].append(upperBodies[i], false);
			columns[i].append(lowerBodies[i], false);
			columns[i].append(bottomColumns[i], false);

			big.draw(columns[i]);
		}
/////////////////////////////////////////////////////////////		
		g.drawImage(bi, 0, 0, this);
	}
}
