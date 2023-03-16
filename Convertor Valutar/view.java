import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class view extends JFrame {

	private JPanel contentPane;
	private JTextField txtIntroducetiSuma;
	private JTextField textField;
	private String[] items = { "RON", "EUR", "USD", "GBP" };
	private JComboBox comboBox1;
	private JComboBox comboBox2 = new JComboBox(items);
	private JLabel sumLabel;
	private JLabel rezLabel;
	private JPanel panel1;
	private JPanel panel2;
	private JButton convertButton;
	private JFrame frame;
	private JTextField userInput;
	private JTextField value;
	private JPanel upperPanel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public view(ConvModel model) {
		JFrame frame = new JFrame();
		frame.setSize(500, 350);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 1000, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox1 = new JComboBox(items);

		comboBox1.setBounds(38, 34, 88, 22);
		contentPane.add(comboBox1);

		comboBox2 = new JComboBox(items);
		comboBox2.setBounds(272, 34, 97, 22);
		contentPane.add(comboBox2);

		convertButton = new JButton(">>");
		convertButton.setBounds(158, 34, 89, 23);
		contentPane.add(convertButton);

		upperPanel=new JPanel();
		JLabel upperLabel = new JLabel("RON=1 RON");
		upperPanel.add(upperLabel);
		upperPanel.setBounds(179, 11, 110, 30);
		contentPane.add(upperPanel);

		JLabel sumLabel = new JLabel("Suma: ");
		sumLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		sumLabel.setBounds(121, 127, 49, 14);
		contentPane.add(sumLabel);

		userInput = new JTextField();
		userInput.setBounds(179, 124, 124, 20);
		contentPane.add(userInput);
		userInput.setColumns(10);

		JLabel rezLabel = new JLabel("Rezultat:");
		rezLabel.setBounds(111, 162, 59, 14);
		contentPane.add(rezLabel);

		value = new JTextField();
		value.setBounds(179, 159, 96, 20);
		contentPane.add(value);
		value.setColumns(10);

		panel1 = new JPanel();

		JLabel labelCoin1 = new JLabel(this.getCoin1().toString());
		// panel1.setSize(10,10);
		panel1.setBounds(313, 120, 49, 35);
		panel1.add(labelCoin1);
		// panel1.setBackground(new Color(255, 0, 0));
		contentPane.add(panel1);

		panel2 = new JPanel();
		panel2.setSize(0, 0);
		panel2.setBounds(312, 155, 49, 35);
		JLabel labelCoin2 = new JLabel(this.getCoin2().toString());
		panel2.add(labelCoin2);
		contentPane.add(panel2);

		frame.add(contentPane);
		
		frame.setVisible(true);
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public String getCoin1() {
		return comboBox1.getSelectedItem().toString();
	}

	public void addSumComboBoxListener(ActionListener a) {
		comboBox1.addActionListener(a);
	}

	public JPanel getPanel1() {
		return panel1;

	}

	public String getCoin2() {
		return comboBox2.getSelectedItem().toString();
	}

	public void addRezComboBoxListener(ActionListener b) {
		comboBox2.addActionListener(b);
	}
	
	public void addConvButtonListener(ActionListener c) {
		convertButton.addActionListener(c);
	}

	public JPanel getPanel2() {
		return panel2;

	}

	public float getUserInput() {
		return Float.parseFloat(userInput.getText());
	}
	
	public void setValue(float value) {
		
		this.value.setText(String.valueOf(value));
	}

	public JTextField getValue() {
		return value;
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	public JPanel getUpper() {
		return this.upperPanel;
	}



}
