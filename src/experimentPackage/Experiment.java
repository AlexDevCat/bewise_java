package experimentPackage;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class Experiment extends Frame {
	private static final long serialVersionUID = 3433569497085314618L;

	Experiment(String title) {
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
		Experiment myCar = new Experiment("Column");
		myCar.go();
	}

	public void go() throws Exception {
		while (true) {
			repaint();
			Thread.sleep(3);
			for (int i = 0; i < columnsCount; i++) {
				if (shifts[i] < frameLength + bottomColumnWidth) {
					shifts[i]++;
				} else {
					shifts[i] = 0;
				}
			}
		}
	}

	// Shared variables
	int frameLength = 1550, frameHeight = 400, yDownStart, yUpStart = 70;
	int columnsCount = 4;
	int cutColumn = 50, columnHeight = 200;
	// top column parts
	int lowerBodyHeight, lowerBodyWidth = 90; // column body
	int bottomColumnHeight = 30, bottomColumnWidth = 120; // column foots

	int xStart = -bottomColumnWidth;
	int distance = (frameLength - bottomColumnWidth * (columnsCount - 1)) / columnsCount;
	int shiftColumns = bottomColumnWidth + distance;
	int shiftBottomColumnLowerBody = (bottomColumnWidth - lowerBodyWidth) / 2;
	int xLowerBody = xStart + shiftBottomColumnLowerBody;
	int yBottomColumn; //
//bottom column parts
	int upperBodyHeight, upperBodyWidth = lowerBodyWidth;
	int topColumnHeight = bottomColumnHeight, topColumnWidth = bottomColumnWidth;

	int yUpperColumn = yUpStart + topColumnHeight;
	int shiftTopColumnUpperBody = (topColumnWidth - upperBodyWidth) / 2;

	////////////////////
	int[] shifts = new int[columnsCount];
	Rectangle[] lowerBodies = new Rectangle[columnsCount];
	Rectangle[] bottomColumns = new Rectangle[columnsCount];
	Rectangle[] upperBodies = new Rectangle[columnsCount];
	Rectangle[] topColumns = new Rectangle[columnsCount];
	GeneralPath[] columns = new GeneralPath[columnsCount];
	BufferedImage bi;
	Graphics2D big;

	int[] upperColumnHeights = new int[columnsCount];

	public void update(Graphics g) {
		bi = (BufferedImage) createImage(frameLength, frameHeight);
		big = bi.createGraphics();
		upperColumnHeights[0] = 150;
		upperColumnHeights[1] = 80;
		upperColumnHeights[2] = 100;
		upperColumnHeights[3] = 30;
/////////////////////////////////////////////////////////////		
		for (int i = 0; i < columnsCount; i++) {
			upperBodyHeight = upperColumnHeights[i];
			yDownStart = yUpStart + upperBodyHeight + cutColumn;
			lowerBodyHeight = columnHeight - upperBodyHeight;
			yBottomColumn = yDownStart + lowerBodyHeight;

			lowerBodies[i] = new Rectangle(xLowerBody + shifts[i], yDownStart, lowerBodyWidth, lowerBodyHeight);
			bottomColumns[i] = new Rectangle(xStart + shifts[i], yBottomColumn, bottomColumnWidth, bottomColumnHeight);

			upperBodies[i] = new Rectangle(xStart + shifts[i], yUpStart, topColumnWidth, topColumnHeight);
			topColumns[i] = new Rectangle(xLowerBody + shifts[i], yUpperColumn, upperBodyWidth, upperBodyHeight);

			columns[i] = new GeneralPath(lowerBodies[i]);
			columns[i].append(bottomColumns[i], false);
			columns[i].append(upperBodies[i], false);
			columns[i].append(topColumns[i], false);
			big.draw(columns[i]);
		}
/////////////////////////////////////////////////////////////		
		g.drawImage(bi, 0, 0, this);
	}
}