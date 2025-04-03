/*
* Постійний  рух різних колон (Lesson #18) by Oleksander Kroshka (School 42, 5-A, Dnipro )
*/

package lesson18;
/*
 * 👉В коді попереднього завдання реалізувати нескінченний рух вправо пари різних вертикальних колон згідно нижченаведеного рисунку
 * 
 * Кількість колон на фреймі – довільна (наприклад, 4). Обов’язкова умова: висота зовнішніх прямокутників колон, 
 * а також віддаль між верхньою та нижньою колонами не повинні змінюватись – змінюється тільки висота внутрішніх прямокутників колон.
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class VariousColumns extends Frame {
	private static final long serialVersionUID = 3433569497085314618L;

//Constructor
	VariousColumns(String title) {
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

//////////////////////MAIN/////////////////////////
	public static void main(String[] args) throws Exception {
		VariousColumns myCar = new VariousColumns("Column");
		myCar.go();
	}
//////////////////////////////////////////////////////

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
	int frameLength = 1550, frameHeight = 400, yDownStart, yUpStart = 70;
	int columnsCount = 9;
	int gapColumn = 50, columnBodiesHeight = 200; // columnBodiesHeight довжина без пропуску посередині
	// column parts
	int bodyWidth = 90; // column bodies width (constant)
	int columnTopBottomHeight = 30, columnWidth = 120; // column foots and heads

	// shared column parts
	int yUpperBody = yUpStart + columnTopBottomHeight;
	int shiftTopColumnUpperBody = (columnWidth - bodyWidth) / 2;
	int xStart = -columnWidth;
	int distance = (frameLength - columnWidth * (columnsCount - 1)) / columnsCount;
	int shiftColumns = columnWidth + distance;
	int shiftBottomColumnLowerBody = (columnWidth - bodyWidth) / 2;
	int xBody = xStart + shiftBottomColumnLowerBody;

	////////////////////
	int[] shifts = new int[columnsCount];
	int yBottomColumn, lowerBodyHeight, upperBodyHeight;
	Rectangle[] lowerBodies = new Rectangle[columnsCount];
	Rectangle[] bottomColumns = new Rectangle[columnsCount];
	Rectangle[] topColumns = new Rectangle[columnsCount];
	Rectangle[] upperBodies = new Rectangle[columnsCount];
	GeneralPath[] columns = new GeneralPath[columnsCount];
	BufferedImage bi;
	Graphics2D big;

	int[] upperColumnHeights = new int[columnsCount];

	int[] generateArray(int count) { // заповнення верїньої частини тіла колони (висоти)
		int[] res = new int[count];
		int[] heightArray = { 150, 60, 100, 30 };
		int j = 0;
		for (int i = 0; i < count; i++) {
			j = i % heightArray.length;

			res[i] = heightArray[j];
		}

		return res;

	}

//############################## UPDATE##################################	
	public void update(Graphics g) {
		graphInit();

		for (int i = 0; i < columnsCount; i++) {
			drawColumns(i);
		}
		g.drawImage(bi, 0, 0, this);
	}

	// ############################## UPDATE -END ##################################

	// Декомпозиція з апдейт
	void drawColumns(int i) {
		upperBodyHeight = upperColumnHeights[i];
		yDownStart = yUpStart + upperBodyHeight + gapColumn;
		lowerBodyHeight = columnBodiesHeight - upperBodyHeight;
		yBottomColumn = yDownStart + lowerBodyHeight;

		topColumns[i] = new Rectangle(xStart + shifts[i], yUpStart, columnWidth, columnTopBottomHeight);
		upperBodies[i] = new Rectangle(xBody + shifts[i], yUpperBody, bodyWidth, upperBodyHeight);

		lowerBodies[i] = new Rectangle(xBody + shifts[i], yDownStart, bodyWidth, lowerBodyHeight);
		bottomColumns[i] = new Rectangle(xStart + shifts[i], yBottomColumn, columnWidth, columnTopBottomHeight);

		columns[i] = new GeneralPath(topColumns[i]);
		columns[i].append(upperBodies[i], false);
		columns[i].append(lowerBodies[i], false);
		columns[i].append(bottomColumns[i], false);

		big.draw(columns[i]);
	}

	void graphInit() {
		bi = (BufferedImage) createImage(frameLength, frameHeight);
		big = bi.createGraphics();
		upperColumnHeights = generateArray(columnsCount);
	}
}