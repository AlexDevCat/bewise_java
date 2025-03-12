package teacherLessons;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Buttons extends Frame {
	int count = 0;
	String modeText = "+";
	boolean isDecrement = false;
	private static final long serialVersionUID = 1L;

	Buttons(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		setSize(700, 100);

		init();

		setVisible(true);

	}

	public void init() {
		Panel panel = new Panel();
		add(panel, BorderLayout.CENTER);
		Button action = new Button("ACTION");
		Button mode = new Button("MODE");
		TextField res = new TextField("" + count);
		TextField modeField = new TextField(modeText);
		res.setSize(200, 100);
		panel.add(action);
		panel.add(res);
		panel.add(modeField);
		panel.add(mode);
		action.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isDecrement) {
					count--;
				} else {
					count++;

				}
				res.setText("" + count);
			}
		});
		mode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isDecrement = !isDecrement;
				if (isDecrement) {
					modeText = "-";

				} else {
					modeText = "+";
				}

				modeField.setText(modeText);

			}
		});

	}

	public static void main(String[] args) {
		new Buttons("Alex painter super - puper");

	}

}
