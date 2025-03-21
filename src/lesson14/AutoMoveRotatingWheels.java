package lesson14;

/*
 * AutoMoveRotatingWheels (Lesson #14) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 *👉1. Доповнити код, наведений вище, для реалізації нескінченного руху автомобіля вліво-вправо в межах фрейму.
 * 
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
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class AutoMoveRotatingWheels extends Frame {
	private static final long serialVersionUID = 4898914898866910535L;

	AutoMoveRotatingWheels(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		setSize(800, 250);
		addButtons();
		setVisible(true);
	}

	private void addButtons() {
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
	}

	public static void main(String[] args) throws Exception {
		AutoMoveRotatingWheels driver = new AutoMoveRotatingWheels("AutoDriver");
		driver.move();
	}

	void move() throws Exception {
		for (;;) {
			Thread.sleep(10);
			if (moving) {
				if (movingRight) {
					shiftAuto = shiftAuto + speed;
					wheelRotation = wheelRotation + 0.05 * speed;
					if (shiftAuto + engineWidth >= getSize().width) {
						movingRight = false;
					}
				} else {
					shiftAuto = shiftAuto - speed;
					wheelRotation = wheelRotation - 0.05 * speed;
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
	private boolean moving = false;
	private int speed = 3;
	private boolean movingRight = true;
	private double wheelRotation = 0;

	int xCabin = xEngine + shiftEngineCabin;
	int yEngine = yCabin + cabinHeight;
	int cabinWidth = engineWidth - shiftEngineCabin * 2;
	int yWheels = yEngine + engineHeight;
	int xLeftWheel = xEngine + shiftEngineWheel;
	int xRightWheel = xEngine + engineWidth - shiftEngineWheel - diameter;

	BufferedImage bi;
	Graphics2D painter2D;

	Rectangle cabin;
	Rectangle engine;
	Polygon leftWheelLineHorizontal, rightWheelLineHorizontal, leftWheelLineVertical;
	Line2D.Double rightWheelLineVertical;

	Ellipse2D.Double leftWheel;
	Ellipse2D.Double rightWheel;

	GeneralPath auto = new GeneralPath();

	public void update(Graphics painter) {
		int w = getSize().width, h = getSize().height;
		bi = (BufferedImage) createImage(w, h);
		painter2D = bi.createGraphics();

		cabin = new Rectangle(xCabin + shiftAuto, yCabin, cabinWidth, cabinHeight);
		engine = new Rectangle(xEngine + shiftAuto, yEngine, engineWidth, engineHeight);
		// Polygon leftWheelLineHorizontal, rightWheelLineHorizontal,
		// leftWheelLineVertical;
		// Line2D.Double rightWheelLineVertical;

		leftWheel = new Ellipse2D.Double(xLeftWheel + shiftAuto, yWheels, diameter, diameter);
		rightWheel = new Ellipse2D.Double(xRightWheel + shiftAuto, yWheels, diameter, diameter);

		auto = new GeneralPath();
		auto.append(cabin, false);
		auto.append(engine, false);
		auto.append(leftWheel, false);
		auto.append(rightWheel, false);

		painter2D.draw(auto);

		// Rotate wheels
		int leftWheelCenterX = xLeftWheel + shiftAuto + diameter / 2;
		int rightWheelCenterX = xRightWheel + shiftAuto + diameter / 2;
		int wheelCenterY = yWheels + diameter / 2;

		painter2D.rotate(wheelRotation, leftWheelCenterX, wheelCenterY);

		// Horizontal line left wheel
		leftWheelLineHorizontal = new Polygon();
		leftWheelLineHorizontal.addPoint(leftWheelCenterX - diameter / 2, wheelCenterY);
		leftWheelLineHorizontal.addPoint(leftWheelCenterX + diameter / 2, wheelCenterY);
		painter2D.draw(leftWheelLineHorizontal);

		// Vertical line left wheel
		leftWheelLineVertical = new Polygon();
		leftWheelLineVertical.addPoint(leftWheelCenterX, wheelCenterY - diameter / 2);
		leftWheelLineVertical.addPoint(leftWheelCenterX, wheelCenterY + diameter / 2);
		painter2D.draw(leftWheelLineVertical);

		painter2D.rotate(-wheelRotation, leftWheelCenterX, wheelCenterY); // reset rotation
//Horizontal line right wheel
		painter2D.rotate(wheelRotation, rightWheelCenterX, wheelCenterY);
		rightWheelLineHorizontal = new Polygon();
		rightWheelLineHorizontal.addPoint(rightWheelCenterX - diameter / 2, wheelCenterY);
		rightWheelLineHorizontal.addPoint(rightWheelCenterX + diameter / 2, wheelCenterY);
		painter2D.draw(rightWheelLineHorizontal);

//Vertical line right wheel
		rightWheelLineVertical = new Line2D.Double(rightWheelCenterX, wheelCenterY - diameter / 2, rightWheelCenterX,
				wheelCenterY + diameter / 2); // Приклад малювання лінії без polygon
		painter2D.draw(rightWheelLineVertical);
		painter2D.rotate(-wheelRotation, rightWheelCenterX, wheelCenterY);

		painter.drawImage(bi, 0, 0, this);
	}
}
