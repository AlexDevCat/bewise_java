/*
 * Pendulum Vertical(Lesson #13) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson13;

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

public class PendulumMove extends Frame {

	private static final long serialVersionUID = 8771788840447724245L;
	private boolean isMoving = false;

	PendulumMove(String title) {
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
		PendulumMove pendulumObj = new PendulumMove("Pendulum Move");
		pendulumObj.move();
	}

	void move() throws Exception {
		for (;;) {
			Thread.sleep(4);
			if (isMoving) {

				if (isMoveUp) {
					moveShift--;
					if (moveShift == (xStart - 150)) {
						isMoveUp = false;
					}
				} else {
					moveShift++;
					if (moveShift == (frameHeight - yPendulum - pendulumHeight)) {
						isMoveUp = true;
					}
				}
			}
			repaint();

		}
	}

	int frameWidth = 200, frameHeight = 1000;
	int xStart = 50, yStart = 100;
	int horizontalLineWidth = 100, verticalLineHeight = 200;
	int pendulumWidth = 40, pendulumHeight = 80;
	boolean isMoveUp = true;
////////////////////////////////////////////////////////
	int pendulumCenter = xStart + horizontalLineWidth / 2;
	int xRightPoint = xStart + horizontalLineWidth;
	int xPendulum = pendulumCenter - pendulumWidth / 2;
	int yPendulum = yStart + verticalLineHeight;
	int moveShift = yPendulum;

	Rectangle weight;
	GeneralPath pendulum;
	Polygon lineHor;
	Polygon lineVert;
	BufferedImage bi;
	Graphics2D big;

	public void update(Graphics painter) {

		bi = (BufferedImage) createImage(frameWidth, frameHeight);
		big = bi.createGraphics();

		weight = new Rectangle(xPendulum, yPendulum + moveShift, pendulumWidth, pendulumHeight);
		pendulum = new GeneralPath();
		lineHor = new Polygon();
		lineVert = new Polygon();

		lineHor.addPoint(xStart, yStart);
		lineHor.addPoint(xRightPoint, yStart);

		lineVert.addPoint(pendulumCenter, yStart);
		lineVert.addPoint(pendulumCenter, yPendulum + moveShift);

		pendulum.append(lineHor, false);
		pendulum.append(lineVert, false);
		pendulum.append(weight, false);
		big.draw(pendulum);

		painter.drawImage(bi, 0, 0, this);

	}
}