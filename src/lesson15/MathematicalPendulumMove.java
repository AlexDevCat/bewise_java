// Завдання: 👉Cтворити 3 класи з кодами для реалізації затухаючого руху вертикального маятника, математичного маятника, сніговика(лялька неваляйка).

/*
 * Затихаючий рух (математичний маятник, вертикальний маятник, лялька неваляйка) (Lesson #15) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 */

/////////////////////////Класс математичний маятник/////////////////////// 

package lesson15;

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
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class MathematicalPendulumMove extends Frame {

	private static final long serialVersionUID = 8771788840447724245L;

	MathematicalPendulumMove(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		setSize(frameWidth, frameHeight);
		addButtons();
		setVisible(true);
	}

	private void addButtons() {
		setLayout(new FlowLayout());

		Button startButton = new Button("Start");
		Button stopButton = new Button("Stop");

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isMoving = true;
			}
		});
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isMoving = false;
			}
		});

		add(startButton);
		add(stopButton);
	}

	public static void main(String[] args) throws Exception {
		MathematicalPendulumMove pendulumObj = new MathematicalPendulumMove("Mathematical Pendulum");
		pendulumObj.move();
	}

	void move() throws Exception {
		for (;;) {
			Thread.sleep(35);
			if (isMoving) {
				if (isRotateRight) {
					pendulumRotation = pendulumRotation + speed * discrete;
					if (pendulumRotation > delta) {
						isRotateRight = false;
						delta = delta - difference;
						if (delta < 0.6) {
							difference = 0.03;
						}
						if (delta < 0.3) {
							difference = 0.01;
						}
						if (delta < 0.09) {
							discrete = 0;
							pendulumRotation = 0;
						}

					}
				} else {
					pendulumRotation = pendulumRotation - speed * discrete;
					if ((pendulumRotation < -delta)) {
						isRotateRight = true;
						delta = delta - difference;
					}
				}
			}
			repaint();

		}
	}

	int frameWidth = 1000, frameHeight = 600;
	double delta = 0.8, difference = 0.1, discrete = 0.05;
	int xStart = 350, yStart = 100;
	int horizontalLineWidth = 300, verticalLineHeight = 250;
	int pendulumWidth = 60, pendulumHeight = 120;
	boolean isRotateRight = true;
	int speed = 1;
	private boolean isMoving = false;
	double pendulumRotation = 0;

////////////////////////////////////////////////////////
	int pendulumCenter = xStart + horizontalLineWidth / 2;
	int xRightPoint = xStart + horizontalLineWidth;
	int xPendulum = pendulumCenter - pendulumWidth / 2;
	int yPendulum = yStart + verticalLineHeight;

	Rectangle weight;
	GeneralPath pendulum;
	Polygon lineHor;
	Polygon lineVert;
	BufferedImage bi;
	Graphics2D big;

	public void update(Graphics painter) {

		bi = (BufferedImage) createImage(frameWidth, frameHeight);
		big = bi.createGraphics();

		pendulum = new GeneralPath();
		weight = new Rectangle(xPendulum, yPendulum, pendulumWidth, pendulumHeight);
		lineHor = new Polygon();
		lineVert = new Polygon();

		lineHor.addPoint(xStart, yStart);
		lineHor.addPoint(xRightPoint, yStart);
		big.draw(lineHor);

		big.rotate(pendulumRotation, pendulumCenter, yStart);

		lineVert.addPoint(pendulumCenter, yStart);
		lineVert.addPoint(pendulumCenter, yPendulum);

		pendulum.append(lineVert, false);
		pendulum.append(weight, false);

		big.rotate(pendulumRotation, pendulumCenter, yStart);
		big.draw(pendulum);

		painter.drawImage(bi, 0, 0, this);

	}
}
