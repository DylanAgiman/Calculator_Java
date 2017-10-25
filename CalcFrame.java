//Dylan Agiman
//Period 6

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.BevelBorder;

public class CalcFrame extends JFrame
{

	private JButton zeroBut, pointBut, plusBut, cBut, ceBut, equalsBut;
	private JButton[] buttonsTop;
	private String[] names = {"7", "8", "9", "X", "4", "5", "6", "/", "1", "2", "3", "-"};
	private JPanel panelTop, panelBottom;
	private JLabel output;
	private Calc calculator;

	public CalcFrame()
	{
		super("Calculator");
		setSize(290, 371);
		setLocation(20, 20);
		setFocusable(true);

		calculator = new Calc();

		//setting up container
		Container contain = getContentPane();
		contain.setBackground(Color.GRAY);
		contain.setLayout(null);


		setResizable(false);

		GridLayout layout = new GridLayout(3, 4, 10, 10);

		//set up output label, starting with 0
		output = new JLabel("0");
		output.setSize(260, 50);
		output.setFont(new Font("Ariel", Font.BOLD, 20));
		output.setBackground(Color.black);
		output.setForeground(Color.green);
		output.setOpaque(true);
		output.setHorizontalAlignment(SwingConstants.RIGHT);
		output.setLocation(10, 10);
		output.setBorder(new BevelBorder(BevelBorder.LOWERED));
		contain.add(output);

		//setting up top panel for numbers and operators
		panelTop = new JPanel();
		panelTop.setLayout(layout);
		panelTop.setSize(265, 150);
		panelTop.setLocation(10, 70);
		panelTop.setBackground(Color.GRAY);

		buttonsTop = new JButton[12];
		ButtonListener listener = new ButtonListener();

		for(int i = 0; i < buttonsTop.length; i++)
		{
			buttonsTop[i] = new JButton(names[i]);
			buttonsTop[i].addActionListener(listener);
			panelTop.add(buttonsTop[i]);
		}


		contain.add(panelTop);

		zeroBut = new JButton("0");
		zeroBut.setSize(126, 45);
		zeroBut.setLocation(10, 230);
		zeroBut.addActionListener(listener);

		contain.add(zeroBut);

		pointBut = new JButton(".");
		pointBut.setSize(58, 45);
		pointBut.setLocation(146, 230);
		pointBut.addActionListener(listener);

		contain.add(pointBut);

		cBut = new JButton("C");
		cBut.setSize(58, 45);
		cBut.setLocation(10, 285);
		cBut.addActionListener(listener);

		contain.add(cBut);

		ceBut = new JButton("CE");
		ceBut.setSize(58, 45);
		ceBut.setLocation(78, 285);
		ceBut.addActionListener(listener);

		contain.add(ceBut);

		equalsBut = new JButton("=");
		equalsBut.setSize(58, 45);
		equalsBut.setLocation(146, 285);
		equalsBut.addActionListener(listener);

		contain.add(equalsBut);


		plusBut = new JButton("+");
		plusBut.setSize(58, 100);
		plusBut.setLocation(214, 230);
		plusBut.addActionListener(listener);

		contain.add(plusBut);


		addKeyListener(listener);
	}

	public JLabel getOutput()
	{
		return output;
	}

	private class ButtonListener implements ActionListener, KeyListener
	{
		public void actionPerformed(ActionEvent event)
		{
			JButton buttonPressed = (JButton) event.getSource();

			if(buttonPressed.getText().equals("C"))
			{
				output.setText("0");
				calculator.reset();
			}

			else if(buttonPressed.getText().equals("CE"))
			{
				output.setText("");
				calculator.ceClear();
			}

			else if(buttonPressed.getText().equals("+"))
			{
				calculator.resetFunctions();
				calculator.addNum(Double.parseDouble(output.getText()));
				calculator.setAdd();
				output.setText("");
			}
			else if(buttonPressed.getText().equals("-"))
			{
				calculator.resetFunctions();
				calculator.addNum(Double.parseDouble(output.getText()));
				calculator.setSubtract();
				output.setText("");
			}
			else if(buttonPressed.getText().equals("X"))
			{
				calculator.resetFunctions();
				calculator.addNum(Double.parseDouble(output.getText()));
				calculator.setMultiply();
				output.setText("");
			}
			else if(buttonPressed.getText().equals("/"))
			{
				calculator.resetFunctions();
				calculator.addNum(Double.parseDouble(output.getText()));
				calculator.setDivide();
				output.setText("");
			}
			else if(buttonPressed.getText().equals("."))
			{
				if(output.getText().indexOf(".") == -1)
					output.setText(output.getText() + buttonPressed.getText());
			}
			else if(buttonPressed.getText().equals("="))
			{
				calculator.addNum(Double.parseDouble(output.getText()));
				output.setText("" + calculator.calculate());

				calculator.reset();

				if(output.getText().equals("Infinity"))
				{
					output.setText("Error: Cannot divide by 0");
				}

				else
				{
					calculator.addNum(Double.parseDouble(output.getText()));
				}
			}
			else
			{
				if(output.getText().equals("0"))
					output.setText("");

				output.setText(output.getText() + buttonPressed.getText());
			}

			requestFocusInWindow();
		}

		public void keyPressed(KeyEvent event)
		{
			String letter = event.getKeyText(event.getKeyCode());
			char c = letter.charAt(0);

			if(letter.equalsIgnoreCase("c"))
			{
				output.setText("0");
				calculator.reset();
			}


			else if(letter.equalsIgnoreCase("Backspace"))
			{
				output.setText("");
				calculator.ceClear();
			}

			else if(letter.equalsIgnoreCase("Period"))
			{
				if(output.getText().indexOf(".") == -1)
					output.setText(output.getText() + ".");
			}


			else if(letter.equalsIgnoreCase("Enter"))
			{
				calculator.addNum(Double.parseDouble(output.getText()));
				output.setText("" + calculator.calculate());

				calculator.reset();

				if(output.getText().equals("Infinity"))
				{
					output.setText("Error: Cannot divide by 0");
				}

				else
				{
					calculator.addNum(Double.parseDouble(output.getText()));
				}
			}

			else if(Character.isDigit(c))
			{
				if(output.getText().equals("0"))
					output.setText("");
				output.setText(output.getText() + letter);
			}
			else if(letter.length() > 7 && letter.substring(0, 3).equalsIgnoreCase("NUM"))
			{
				if(letter.substring(7).equals("+"))
				{
					calculator.resetFunctions();
					calculator.addNum(Double.parseDouble(output.getText()));
					calculator.setAdd();
					output.setText("");
				}
				else if(letter.substring(7).equals("-"))
				{
					calculator.resetFunctions();
					calculator.addNum(Double.parseDouble(output.getText()));
					calculator.setSubtract();
					output.setText("");
				}

				else if(letter.substring(7).equals("*"))
				{
					calculator.resetFunctions();
					calculator.addNum(Double.parseDouble(output.getText()));
					calculator.setMultiply();
					output.setText("");
				}

				else if(letter.substring(7).equals("/"))
				{
					calculator.resetFunctions();
					calculator.addNum(Double.parseDouble(output.getText()));
					calculator.setDivide();
					output.setText("");
				}

				else if(letter.substring(7).equals("."))
				{
					if(output.getText().indexOf(".") == -1)
						output.setText(output.getText() + letter.substring(7));
				}
				else
				{
					if(output.getText().equals("0"))
						output.setText("");
					output.setText(output.getText() + letter.substring(7));
				}

			}

			else if(letter.equals("Minus"))
			{
				calculator.resetFunctions();
				calculator.addNum(Double.parseDouble(output.getText()));
				calculator.setSubtract();
				output.setText("");
			}

			else if(letter.equals("Slash"))
			{
				calculator.resetFunctions();
				calculator.addNum(Double.parseDouble(output.getText()));
				calculator.setDivide();
				output.setText("");
			}

			else if(letter.equals("Equals"))
			{
				calculator.addNum(Double.parseDouble(output.getText()));
				output.setText("" + calculator.calculate());

				calculator.reset();

				if(output.getText().equals("Infinity"))
				{
					output.setText("Error: Cannot divide by 0");
				}

				else
				{
					calculator.addNum(Double.parseDouble(output.getText()));
				}
			}
		}

		public void keyReleased(KeyEvent event) {}
		public void keyTyped(KeyEvent event) {}
	}


	public static void main(String[] args)
	{
		CalcFrame calculator = new CalcFrame();

		calculator.setVisible(true);
	}

}
