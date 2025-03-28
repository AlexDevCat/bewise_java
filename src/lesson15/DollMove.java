/////////////////////////////////////Класс для затухаючого руху ляльки неваляйки (сніговика)//////////////////////
package lesson15;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class DollMove extends Frame {

	private static final long serialVersionUID = 1071695077789535672L;

	DollMove(String title) {
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
		DollMove dollToy = new DollMove("Snowman");
		dollToy.move();
	}

	void move() throws Exception {
		for (;;) {
			Thread.sleep(35);
			if (isMoving) {

				if (isRotateRight) {
					dollRotation = dollRotation + speed * discrete;
					if (dollRotation > delta) {
						isRotateRight = false;
						delta = delta - difference;
						if (delta < 0.6) {
							difference = 0.03;
						}
						if (delta < 0.3) {
							difference = 0.01;
						}
						if (delta < 0.04) {
							discrete = 0;
							dollRotation = 0;
						}

					}
				} else {
					dollRotation = dollRotation - speed * discrete;
					if ((dollRotation < -delta)) {
						isRotateRight = true;
						delta = delta - difference;
					}
				}
			}
			repaint();

		}
	}

	int frameWidth = 800, frameHeight = 550;

	double delta = 1.1, difference = 0.1, discrete = 0.05;
	int diameterUp = 80, diameterMiddle = 120, diameterBottom = 200;
	int xStart = frameWidth / 2 - diameterBottom / 2, yStart = 100;
	boolean isMoving = false;
	boolean isRotateRight = true;
	double dollRotation = 0;
	int speed = 1;
//////////////////////////////////
	int dollCenter = xStart + diameterBottom / 2;
	int xUp = dollCenter - diameterUp / 2;
	int xMiddle = dollCenter - diameterMiddle / 2;
	int yMiddle = yStart + diameterUp;
	int yBottom = yMiddle + diameterMiddle;
	int xCenter = diameterBottom / 2 + xStart;
	int yCenter = yBottom + diameterBottom;
	GeneralPath doll;
	Ellipse2D.Double bottom;
	Ellipse2D.Double middle;
	Ellipse2D.Double up;
	BufferedImage bi;
	Graphics2D big;

	public void update(Graphics painter) {
		bi = (BufferedImage) createImage(frameWidth, frameHeight);
		big = bi.createGraphics();

		doll = new GeneralPath();
		bottom = new Ellipse2D.Double(xStart, yBottom, diameterBottom, diameterBottom);
		middle = new Ellipse2D.Double(xMiddle, yMiddle, diameterMiddle, diameterMiddle);
		up = new Ellipse2D.Double(xUp, yStart, diameterUp, diameterUp);

		doll.append(up, false);
		doll.append(middle, false);
		doll.append(bottom, false);
		big.rotate(dollRotation, xCenter, yCenter);
		big.draw(doll);

		painter.drawImage(bi, 0, 0, this);

	}
}