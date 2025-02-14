/*
 * Snowman painter with circles (Lesson #2) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 */


package lesson2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnowmanPainter extends Frame {
private static final long serialVersionUID = 1L;
	// ************** Constructor ************	
	SnowmanPainter(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
			
		});
		
		setSize(500, 300);
		setBackground(new Color(0, 128, 128));
		setVisible(true);			
		
	}
	
	
// main method	//
	public static void main(String[] args) {
		new SnowmanPainter("Snowman");
		

	}
//Method for shapes painting//
	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.WHITE);		
		
		painter.drawOval(210, 200, 100, 100); //bottom snowball
		painter.drawOval(230,140, 60, 60); //middle snowball
		painter.drawOval(240,100, 40, 40); //upper snowball
		painter.drawOval(200,150, 30, 30); //left hand
		painter.drawOval(290,150, 30, 30); //right hand
	}
}
