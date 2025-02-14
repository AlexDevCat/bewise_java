/*
 * Building painter with Rectangles (Lesson #2) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 */


package lesson2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BuildingPainter extends Frame {
private static final long serialVersionUID = 1L;
	// ************** Constructor ************	
	BuildingPainter(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
			
		});
		
		setSize(500, 320);
		setBackground(new Color(0, 128, 128)); //Background color = teal
		setVisible(true);
	}
	
	
	// main method	//
	public static void main(String[] args) {
		new BuildingPainter("Building");	
	}

	//Method for shapes painting//
	public void paint(Graphics grf) { // API //
		Graphics2D painter = (Graphics2D) grf;
		painter.setStroke(new BasicStroke(5));
		painter.setColor(Color.WHITE);		
		painter.drawRect(100,40,300,30); //roof
		painter.drawRect(130,70,240,230);//walls
		painter.drawRect(160,120,80,180); //door
		painter.drawRect(260,120,80,80); //window
		
		
		
		
	}
}
