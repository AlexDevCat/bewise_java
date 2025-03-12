/*
 * Calculator (Lesson #10) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */
package lesson10;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator10 extends Frame {
	int res = 0, x = 0, y = 0;
	String[] operations = { "+", "-", "*", "/" };
	int indexCurOper = 0;
	float divres = 0; // результуюча змінна для ділення
	private static final long serialVersionUID = 1L;

	Calculator10(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		setSize(700, 300);

		init();

		setVisible(true);

	}

	public void init() {
		Panel panel = new Panel();

		add(panel, BorderLayout.CENTER);
		Button actionBtn = new Button("Action");
		actionBtn.setPreferredSize(new Dimension(60, 40));
		Button modeBtn = new Button("Mode");
		modeBtn.setPreferredSize(new Dimension(60, 40));

		TextField xField = new TextField("" + x, 10);
		TextField operField = new TextField(operations[indexCurOper], 10);
		operField.setEditable(false);
		TextField yField = new TextField("" + y, 10);
		TextField resField = new TextField("" + res, 30);
		resField.setEditable(false);

		panel.add(actionBtn);
		panel.add(xField);
		panel.add(operField);
		panel.add(yField);
		panel.add(resField);
		panel.add(modeBtn);

		// Add button
		actionBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// обробимо ситуацію, коли замість цифр ввели букви
					x = Integer.parseInt(xField.getText());
					y = Integer.parseInt(yField.getText());

					String curentOper = operations[indexCurOper];
					if (curentOper.equals("+")) {
						res = x + y;
						resField.setText("=" + res);
					}
					if (curentOper.equals("-")) {
						res = x - y;
						resField.setText("=" + res);
					}
					if (curentOper.equals("*")) {
						res = x * y;
						resField.setText("=" + res);
					}
					if (curentOper.equals("/")) {
						if (y == 0) {
							resField.setText("=Error");
						}

						else {
							// res = x / y; // не працює треба перевести в флоат
							divres = (float) x / y;
							resField.setText("=" + divres);
						}
					}

				} catch (NumberFormatException ex) {
					xField.setText("" + 0);
					yField.setText("" + 0);
					resField.setText("Error");
				}
			}
		});

		// division button
		modeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (indexCurOper == 3) {
					indexCurOper = 0;
				} else {
					indexCurOper++;
				}
				operField.setText(operations[indexCurOper]);
			}
		});
	}

	public static void main(String[] args) {
		new Calculator10("Calculator");
	}

}
