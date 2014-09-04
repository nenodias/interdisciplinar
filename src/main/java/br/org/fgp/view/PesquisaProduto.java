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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTable;

public class PesquisaProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable tbProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesquisaProduto dialog = new PesquisaProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PesquisaProduto() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		JLabel lblProduto_1 = new JLabel("Produto:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		tbProduto = new JTable();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(155)
							.addComponent(lblProduto))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(101)
							.addComponent(lblProduto_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(120, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(28, Short.MAX_VALUE)
					.addComponent(tbProduto, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProduto)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tbProduto, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
