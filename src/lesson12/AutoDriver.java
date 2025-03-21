/*
 * Automobile animated (Lesson #12) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson12;
/*
 * *****************ЗАВДАННЯ*****************************************
 * 👉1. До попереднього коду ввести 4 кнопки для:
   - запуску руху автомобіля,
   - зупинки руху автомобіля,
   - збільшення швидкості руху,
   - зменшення швидкості руху.
 */

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class AutoDriver extends Frame {
	private static final long serialVersionUID = 4898914898866910535L;
	private boolean moving = false;
	private int speed = 3;
	private boolean movingRight = true;

	AutoDriver(String title) {
		super(title);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		setSize(800, 250);
		setLayout(new FlowLayout());

		Button startButton = new Button("Start");
		Button stopButton = new Button("Stop");
		Button increaseSpeedButton = new Button("V+");
		Button decreaseSpeedButton = new Button("V-");

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moving = true;
			}
		});
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moving = false;
			}
		});
		increaseSpeedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (speed < 10) {
					speed++;
				}

			}
		});

		decreaseSpeedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (speed > 1) {
					speed--;
				}

			}
		});

		add(startButton);
		add(stopButton);
		add(increaseSpeedButton);
		add(decreaseSpeedButton);

		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		AutoDriver driver = new AutoDriver("AutoDriver");
		driver.move();
	}

	void move() throws Exception {
		for (;;) {
			Thread.sleep(10);
			if (moving) {
				if (movingRight) {
					shiftAuto += speed;
					if (shiftAuto + engineWidth >= getSize().width) {
						movingRight = false;
					}
				} else {
					shiftAuto -= speed;
					if (shiftAuto <= 0) {
						movingRight = true;
					}
				}
				repaint();
			}
		}
	}

	int xEngine = 0, yCabin = 80, engineWidth = 180, cabinHeight = 30, engineHeight = 40;
	int diameter = 30;
	int shiftEngineCabin = 30, shiftEngineWheel = 10;
	int shiftAuto = 0;

	int xCabin = xEngine + shiftEngineCabin;
	int yEngine = yCabin + cabinHeight;
	int cabinWidth = engineWidth - shiftEngineCabin * 2;
	int yWheels = yEngine + engineHeight;
	int xLeftWheel = xEngine + shiftEngineWheel;
	int xRightWheel = xEngine + engineWidth - shiftEngineWheel - diameter;

	Rectangle cabin, engine;
	Ellipse2D.Double leftWheel, rightWheel;
	GeneralPath auto;

	public void update(Graphics painter) {
		int w = getSize().width, h = getSize().height;
		BufferedImage bi = (BufferedImage) createImage(w, h);
		Graphics2D painter2D = bi.createGraphics();

		cabin = new Rectangle(xCabin + shiftAuto, yCabin, cabinWidth, cabinHeight);
		engine = new Rectangle(xEngine + shiftAuto, yEngine, engineWidth, engineHeight);
		leftWheel = new Ellipse2D.Double(xLeftWheel + shiftAuto, yWheels, diameter, diameter);
		rightWheel = new Ellipse2D.Double(xRightWheel + shiftAuto, yWheels, diameter, diameter);
		auto = new GeneralPath();
		auto.append(cabin, false);
		auto.append(engine, false);
		auto.append(leftWheel, false);
		auto.append(rightWheel, false);

		painter2D.draw(auto);
		painter.drawImage(bi, 0, 0, this);
	}
}