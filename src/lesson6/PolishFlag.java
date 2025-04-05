/*
 * Polish flag painter (Lesson #6) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */

package lesson6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PolishFlag extends Frame {
	private static final long serialVersionUID = 1L;

	PolishFlag(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		setSize(300, 350);
		setVisible(true);
	}

	public static void main(String[] args) {
		new PolishFlag("Flags");
	}

	int xFlag = 50, yFlag = 100;
	int flagWidth = 150, flagHeight = 100;
	int letterHeight = 35;
	/////////////////////////

	int flagStringHeight = flagHeight / 2;

	public void paint(Graphics painter) {

		painter.setColor(Color.WHITE);
		painter.fillRect(xFlag, yFlag, flagWidth, flagStringHeight);
		painter.setColor(Color.RED);
		painter.fillRect(xFlag, yFlag + flagStringHeight, flagWidth, flagStringHeight);
		painter.setColor(Color.BLACK);
		painter.drawRect(xFlag, yFlag, flagWidth, flagHeight);
		painter.setFont(new Font("DIALOG", Font.ITALIC, letterHeight));
		painter.drawString("Polish", xFlag, yFlag + flagHeight + letterHeight);
	}
}
