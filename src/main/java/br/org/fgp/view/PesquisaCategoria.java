package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PesquisaCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoria;
	private JTable tbCategoria;
	static JDialog dia = new JDialog(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PesquisaCategoria dialog = new PesquisaCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PesquisaCategoria() {
		this.setModal(true); 
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("Categoria:");
		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		JLabel label_1 = new JLabel("Categoria");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 30));
		tbCategoria = new JTable();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(28)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGap(111)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addGap(40)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
												.addGap(4)
												.addComponent(txtCategoria, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
												.addComponent(tbCategoria, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
												.addContainerGap(31, Short.MAX_VALUE))
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(19)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addGap(11)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGap(3)
										.addComponent(label))
										.addComponent(txtCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(tbCategoria, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(40, Short.MAX_VALUE))
				);
		contentPanel.setLayout(gl_contentPanel);
	}

}
