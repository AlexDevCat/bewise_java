/*
 * Calculator (Lesson #9) by Oleksander Kroshka (School 42, 5-A, Dnipro )
 * 
 */
package lesson9;

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

public class Calculator extends Frame {
	int res = 0, x = 0, y = 0;
	float divres = 0; // результуюча змінна для ділення
	private static final long serialVersionUID = 1L;

	Calculator(String title) {
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
		Panel btnPanel = new Panel();
		Panel inputPanel = new Panel();

		add(btnPanel, BorderLayout.NORTH);

		add(inputPanel, BorderLayout.CENTER);
		Button plusBtn = new Button("+");
		plusBtn.setPreferredSize(new Dimension(40, 40));
		Button minusBtn = new Button("-");
		minusBtn.setPreferredSize(new Dimension(40, 40));
		Button multBtn = new Button("*");
		multBtn.setPreferredSize(new Dimension(40, 40));
		Button divBtn = new Button("/");
		divBtn.setPreferredSize(new Dimension(40, 40));

		TextField xField = new TextField("" + x, 10);
		TextField yField = new TextField("" + y, 10);
		TextField resField = new TextField("" + res, 30);

		btnPanel.add(plusBtn);
		btnPanel.add(minusBtn);
		btnPanel.add(multBtn);
		btnPanel.add(divBtn);

		inputPanel.add(xField);
		inputPanel.add(yField);
		inputPanel.add(resField);

		// Add button
		plusBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					x = Integer.parseInt(xField.getText());
					y = Integer.parseInt(yField.getText());
					res = x + y;
					resField.setText("=" + res);

				} catch (NumberFormatException ex) {
					xField.setText("" + 0);
					yField.setText("" + 0);
					resField.setText("Error");
				}
			}
		});

		// Minus button
		minusBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					x = Integer.parseInt(xField.getText());
					y = Integer.parseInt(yField.getText());
					res = x - y;
					resField.setText("=" + res);

				} catch (NumberFormatException ex) {
					xField.setText("" + 0);
					yField.setText("" + 0);
					resField.setText("Error");
				}
			}
		});

		// Multiplication button
		multBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					x = Integer.parseInt(xField.getText());
					y = Integer.parseInt(yField.getText());
					res = x * y;
					resField.setText("=" + res);

				} catch (NumberFormatException ex) {
					xField.setText("" + 0);
					yField.setText("" + 0);
					resField.setText("Error");
				}
			}
		});
		// division button
		divBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					x = Integer.parseInt(xField.getText());
					y = Integer.parseInt(yField.getText());
					// res = x / y; // не працює треба перевести в флоат
					divres = (float) x / y;
					resField.setText("" + divres);

				} catch (NumberFormatException ex) {
					xField.setText("" + 0);
					yField.setText("" + 0);
					resField.setText("Error");

				} catch (ArithmeticException ex) {
					xField.setText("" + 0);
					yField.setText("" + 1);
					resField.setText("Error: / на 0");
				}
			}
		});
	}

	public static void main(String[] args) {
		new Calculator("Calculator");
	}

}
