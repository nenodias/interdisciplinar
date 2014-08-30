package br.org.fgp.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;

public class CadastroFuncionario extends JPanel {
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtCpf;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CadastroFuncionario() {
		
		JLabel lblFuncionario = new JLabel("");
		lblFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		
		JComboBox cbbEstado = new JComboBox();
		
		JComboBox cbbCidade = new JComboBox();
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblCpf = new JLabel("CPF:");
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		
		JLabel lblEstado = new JLabel("Estado:");
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMsg.setForeground(Color.RED);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Telefone:");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(102)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFuncionario, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblEndereo)
										.addComponent(lblNome)
										.addComponent(lblNewLabel)
										.addComponent(lblCpf))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCpf, Alignment.TRAILING, 213, 213, Short.MAX_VALUE)
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
										.addComponent(txtEndereco, Alignment.TRAILING, 213, 213, Short.MAX_VALUE)
										.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(122)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEstado)
								.addComponent(lblCidade))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnSalvar)
									.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
									.addComponent(btnCancelar))
								.addComponent(cbbCidade, Alignment.TRAILING, 0, 213, Short.MAX_VALUE)
								.addComponent(cbbEstado, 0, 213, Short.MAX_VALUE))))
					.addGap(68))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(232, Short.MAX_VALUE)
					.addComponent(lblMsg)
					.addGap(164))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblFuncionario)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCpf))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEndereo)))
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbbEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstado))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbbCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCidade))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(18)
					.addComponent(lblMsg)
					.addGap(26))
		);
		setLayout(groupLayout);

	}
}
