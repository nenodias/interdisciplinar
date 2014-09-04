package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class Pagamento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Pagamento dialog = new Pagamento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Pagamento() {
		this.setModal(true);
		setBounds(100, 100, 447, 535);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Total:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("New label");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel("Pagamento");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		JButton button = new JButton("Cancelar");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(new Color(255, 69, 0));
		
		JButton button_1 = new JButton("Concluir Pagamento");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.GREEN);
		
		JLabel label_3 = new JLabel("Troco:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_4 = new JLabel("Dinheiro:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_5 = new JLabel("Desconto:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(32)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(32)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(23)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
					.addGap(85))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(196, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
