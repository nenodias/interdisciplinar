package br.org.fgp.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CadastroUsuario extends JPanel {
	private JTextField txtLogin;
	private JTextField txtSenha;

	/**
	 * Create the panel.
	 */
	public CadastroUsuario() {
		
		JLabel lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUsuario.setForeground(Color.BLACK);
		
		JComboBox cbbFuncionario = new JComboBox();
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setText("");
		txtSenha.setColumns(10);
		
		JComboBox cbbTipo = new JComboBox();
		cbbTipo.setToolTipText("");
		cbbTipo.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Funcion\u00E1rio"}));
		
		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio:");
		
		JLabel lblLogin = new JLabel("Login:");
		
		JLabel lblSenha = new JLabel("Senha:");
		
		JLabel lblTipo = new JLabel("Tipo:");
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(130)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblLogin)
										.addComponent(lblFuncionrio)
										.addComponent(lblSenha)
										.addComponent(lblTipo))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cbbFuncionario, 0, 161, Short.MAX_VALUE)
										.addComponent(txtSenha, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
										.addComponent(txtLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
										.addComponent(cbbTipo, 0, 163, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnSalvar)
											.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
											.addComponent(btnCancelar))))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 102, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel)
									.addGap(46))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(131, Short.MAX_VALUE)
							.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
					.addGap(126))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(lblUsuario)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFuncionrio)
						.addComponent(cbbFuncionario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogin)
						.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipo)
								.addComponent(cbbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSenha)
							.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
