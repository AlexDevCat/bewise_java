
/*
 * HotAirBaloon animated (Lesson #12) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */
package lesson12;
/* ****************ЗАВДАННЯ*************************
 *  👉 2. Створити код для руху догори повітряної кулі.
	👉3. До коду ввести наступні зміни:
   - додати рух догори та вправо (імітація вітру)
   - додати кнопку для збільшення швидкості руху вправо (імітація підсилення вітру)
 */

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class AirBaloonFly extends Frame {
	private static final long serialVersionUID = -6122534134029441676L;
	private int shiftWind = 0;
	private int speed = 1;

	AirBaloonFly(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		setSize(frameWidth, frameHeight);
		setLayout(new FlowLayout());

		Button windButton = new Button("Wind");

		windButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				speed += 1;
			}
		});

		add(windButton);

		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		AirBaloonFly airBaloonFly = new AirBaloonFly("HotAirBaloon");
		airBaloonFly.move();
	}

	void move() throws Exception {
		for (;;) {
			Thread.sleep(20);
			repaint();
			shiftBaloon -= 3;
			shiftWind += speed;

			// якщо куля вийшла за межі повертаємо на початок (низ зліва)
			if (yStart + shiftBaloon + baloonDiameter < 0 || xStart + shiftWind > frameWidth) { // перевірка чи вилитіли
																								// за
				// фрейм
				shiftBaloon = yStart - baloonDiameter; // Почати промальовувати знизу
				shiftWind = 0; // Почати промальовувати зліва
			}
		}
	}

	int frameWidth = 1200, frameHeight = 800;
	int xStart = 10, yStart = 400;
	int shiftBaloon = 0;
	int baloonDiameter = 204;
	int baloonCarriegeDistance = baloonDiameter / 2;
	int carriegeWidth = baloonDiameter / 3;
	int carriegeHeight = baloonDiameter / 2;
	int xCarriege = xStart + baloonDiameter / 2 - carriegeWidth / 2;
	int yCarriege = yStart + baloonDiameter + baloonCarriegeDistance;
	int xLeftTopPoint = xStart + baloonDiameter / 13;
	int xRightTopPoint = xStart + baloonDiameter * 12 / 13;
	int yTopPoints = yStart + baloonDiameter * 31 / 40;
	GeneralPath airBaloon;
	Ellipse2D.Double baloon;
	Rectangle carriege;
	Polygon leftLine;
	Polygon rightLine;

	public void update(Graphics painter) {
		int w = getSize().width, h = getSize().height;
		BufferedImage bi = (BufferedImage) createImage(w, h);
		Graphics2D painter2D = bi.createGraphics();

		baloon = new Ellipse2D.Double(xStart + shiftWind, yStart + shiftBaloon, baloonDiameter, baloonDiameter);
		carriege = new Rectangle(xCarriege + shiftWind, yCarriege + shiftBaloon, carriegeWidth, carriegeHeight);
		leftLine = new Polygon();
		rightLine = new Polygon();
		leftLine.addPoint(xCarriege + shiftWind, yCarriege + shiftBaloon);
		leftLine.addPoint(xLeftTopPoint + shiftWind, yTopPoints + shiftBaloon);

		rightLine.addPoint(xCarriege + carriegeWidth + shiftWind, yCarriege + shiftBaloon);
		rightLine.addPoint(xRightTopPoint + shiftWind, yTopPoints + shiftBaloon);

		airBaloon = new GeneralPath();
		airBaloon.append(leftLine, false);
		airBaloon.append(rightLine, false);
		airBaloon.append(baloon, false);
		airBaloon.append(carriege, false);

		painter2D.draw(airBaloon);
		painter.drawImage(bi, 0, 0, this);
	}
}
