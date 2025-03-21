package lesson14;
/*
 * Doll (Lesson #14) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

import java.awt.Button;
import java.awt.FlowLayout;
////////////////СНІГОВИК – PRELIMINARY /////////////////////
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

public class Doll extends Frame {

	private static final long serialVersionUID = 1071695077789535672L;

	Doll(String title) {
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
		Doll dollToy = new Doll("Doll");
		dollToy.move();
	}

	void move() throws Exception {
		for (;;) {
			Thread.sleep(35);
			if (isMoving) {

				if (isRotateRight) {
					dollRotation = dollRotation + speed * 0.05;
					if (dollRotation > 0.6) {
						isRotateRight = false;
					}
				} else {
					dollRotation = dollRotation - speed * 0.05;
					if ((dollRotation < -0.6)) {
						isRotateRight = true;
					}
				}
			}
			repaint();

		}
	}

	int frameWidth = 800, frameHeight = 550;

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
