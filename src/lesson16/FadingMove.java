/*
* Затухаючий рух, декомпозиція (математичний маятник, вертикальний маятник, лялька неваляйка) (Lesson #15) by Oleksander Kroshka (School 42, 5-A, Dnipro )
*/

package lesson16;

/*👉
 * 1. Здійснити рефакторинг кодів попереднього завдання наступним чином:
1.1. Коди з трьох класів з’єднати в один клас. Затухаючий рух кожного з трьох елементів - вертикального маятника, математичного маятника, сніговика – оформити окремим методом. В методі main реалізувати виклик одного з трьох методів для руху одного з елементів; виклики двох інших методів для руху двох інших елементів в цей час перетворити в коментарі.

1.2. В трьох методах рух кожного з трьох елементів максимально використати спільні змінні із різними значеннями для кожного з методів.
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
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class FadingMove extends Frame {

	private static final long serialVersionUID = 1071695077789535672L;

	////// CONSTRUCTOR////////////
	FadingMove(String title) {
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

////Draw buttons and set layout
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

//#############################################################MAIN#########################################
	public static void main(String[] args) throws Exception {
		FadingMove fadingMoveInstance = new FadingMove("Fading move");
		fadingMoveInstance.dollMove();
		// fadingMoveInstance.pvMove();
		// fadingMoveInstance.mpMove();
	}

	void dollMove() throws Exception {
		isDoll = true;
		for (;;) {
			Thread.sleep(35);
			if (isMoving) {

				if (isRotateRight) {
					dollRotation = dollRotation + speed * discreteDoll;
					if (dollRotation > deltaDoll) {
						isRotateRight = false;
						deltaDoll = deltaDoll - differenceDoll;
						if (deltaDoll < 0.6) {
							differenceDoll = 0.03;
						}
						if (deltaDoll < 0.3) {
							differenceDoll = 0.01;
						}
						if (deltaDoll < 0.04) {
							discreteDoll = 0;
							dollRotation = 0;
						}

					}
				} else {
					dollRotation = dollRotation - speed * discreteDoll;
					if ((dollRotation < -deltaDoll)) {
						isRotateRight = true;
						deltaDoll = deltaDoll - differenceDoll;
					}
				}
			}
			repaint();

		}
	}

//Mathematical pendulum fading move
	void mpMove() throws Exception {
		for (;;) {
			Thread.sleep(35);
			if (isMoving) {
				if (isRotateRight) {
					pendulumRotation = pendulumRotation + speed * discreteMP;
					if (pendulumRotation > deltaMP) {
						isRotateRight = false;
						deltaMP = deltaMP - differenceMP;
						if (deltaMP < 0.6) {
							differenceMP = 0.03;
						}
						if (deltaMP < 0.3) {
							differenceMP = 0.01;
						}
						if (deltaMP < 0.09) {
							discreteMP = 0;
							pendulumRotation = 0;
						}

					}
				} else {
					pendulumRotation = pendulumRotation - speed * discreteMP;
					if ((pendulumRotation < -deltaMP)) {
						isRotateRight = true;
						deltaMP = deltaMP - differenceMP;
					}
				}
			}
			repaint();

		}
	}

//Vertical pendulum fading move
	void pvMove() throws Exception {
		isPendulumVertical = true;
		for (;;) {
			Thread.sleep(4);
			if (isMoving) {
				if (isMoveUp) {

					moveShift = moveShift + discretePV;
					if (moveShift >= (frameHeight - yPendulum - pendulumHeight - deltaPV)) {
						isMoveUp = false;
						deltaPV = deltaPV + differencePV;
						if (deltaPV > 400) {
							differencePV = 3;
						}
						if (deltaPV > 800) {
							discretePV = 0;
							isMoving = false;
						}
					}
				} else {
					moveShift = moveShift - discretePV;
					if (moveShift < deltaPV - pendulumHeight) {
						isMoveUp = true;
						deltaPV = deltaPV + differencePV;
					}
				}
			}
			repaint();
		}
	}

	// Frame variables
	int frameWidth = 1000, frameHeight = 1000;
	boolean isRotateRight = true;

	/////////////////////// Doll variables//////////////////////

	double deltaDoll = 1.1, differenceDoll = 0.1, discreteDoll = 0.05;
	int diameterUp = 80, diameterMiddle = 120, diameterBottom = 200;
	int xStartDoll = frameWidth / 2 - diameterBottom / 2, yStartDoll = 100;
	int dollCenter = xStartDoll + diameterBottom / 2;
	int xUp = dollCenter - diameterUp / 2;
	int xMiddle = dollCenter - diameterMiddle / 2;
	int yMiddle = yStartDoll + diameterUp;
	int yBottom = yMiddle + diameterMiddle;
	int xCenter = diameterBottom / 2 + xStartDoll;
	int yCenter = yBottom + diameterBottom;

	boolean isDoll = false, isPendulumVertical = false; // Визначаємо об'єкт який треба промалювати в update за
														// допомогою drawShape() - якщо це не лялька і не
														// вертикальний маятник, то малюємо математичний

	double dollRotation = 0;

	////////////////////////////// Mathematical pendulum variables
	int horizontalLineWidth = 300, verticalLineHeight = 150;
	int pendulumWidth = 60, pendulumHeight = 120;

	int xStartPendulum = 350, yStartPendulum = 100;
	int pendulumCenter = xStartPendulum + horizontalLineWidth / 2;
	int xRightPoint = xStartPendulum + horizontalLineWidth;
	int xPendulum = pendulumCenter - pendulumWidth / 2;
	int yPendulum = yStartPendulum + verticalLineHeight;

	double deltaMP = 0.8, differenceMP = 0.1, discreteMP = 0.05;

	double pendulumRotation = 0;
	Line2D.Double lineHor;
	Line2D.Double lineVert;

/////////////////////Pendulum Vertical Variables///////////////////////////////////
	int deltaPV = 100, differencePV = 15, discretePV = 1;
	boolean isMoveUp = true;

////////////////////////////////////////////////////////
	int moveShift = yPendulum;

	// Shared variables
	boolean isMoving = false;
	int speed = 1;

	GeneralPath fadingMoveObj;
	Rectangle weight;
	Ellipse2D.Double bottom;
	Ellipse2D.Double middle;
	Ellipse2D.Double up;
	BufferedImage bi;
	Graphics2D big;

	public void update(Graphics painter) {
		graphInit();
		drawShape();

		painter.drawImage(bi, 0, 0, this);

	}

	// What should be painted?
	void drawShape() {
		if (isDoll) {
			dollDraw();

		} else {
			drawPendulum();
		}
	}

	void dollDraw() {

		bottom = new Ellipse2D.Double(xStartDoll, yBottom, diameterBottom, diameterBottom);
		middle = new Ellipse2D.Double(xMiddle, yMiddle, diameterMiddle, diameterMiddle);
		up = new Ellipse2D.Double(xUp, yStartDoll, diameterUp, diameterUp);

		fadingMoveObj.append(up, false);
		fadingMoveObj.append(middle, false);
		fadingMoveObj.append(bottom, false);
		big.rotate(dollRotation, xCenter, yCenter);
		big.draw(fadingMoveObj);
	}

	void drawPendulum() {
		weight = new Rectangle(xPendulum, yPendulum + moveShift, pendulumWidth, pendulumHeight);

		lineHor = new Line2D.Double(xStartPendulum, yStartPendulum, xRightPoint, yStartPendulum);
		lineVert = new Line2D.Double(pendulumCenter, yStartPendulum, pendulumCenter, yPendulum + moveShift);

		big.draw(lineHor);

		fadingMoveObj.append(lineVert, false);
		fadingMoveObj.append(weight, false);
		if (!isPendulumVertical) {
			big.rotate(pendulumRotation, pendulumCenter, yStartPendulum); // add rotation for mathematical pendulum

		}
		big.draw(fadingMoveObj);

	}

	// Shared method for all shapes
	void graphInit() {
		bi = (BufferedImage) createImage(frameWidth, frameHeight);
		big = bi.createGraphics();

		fadingMoveObj = new GeneralPath();
	}
}