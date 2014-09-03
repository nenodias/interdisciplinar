package br.org.fgp.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class CadastroFornecedor extends JPanel {
	private JTable tbContato;
	private JTextField txtNomeFantasia;
	private JTextField txtRazaoSocial;
	private JTextField txtInscricaoEstadual;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtNumero;

	/**
	 * Create the panel.
	 */
	public CadastroFornecedor() {
		
		tbContato = new JTable();
		
		JLabel lblNomeFantasia = new JLabel("Nome Fantasia:");
		
		JLabel lblRazoSocial = new JLabel("Razão Social:");
		
		JLabel lblInscrioEstadual = new JLabel("Inscrição Estadual:");
		
		JLabel lblEndereo = new JLabel("Endereço:");
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		JLabel lblEstado = new JLabel("Estado:");
		
		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setColumns(10);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setText("");
		txtRazaoSocial.setColumns(10);
		
		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setColumns(10);
		
		txtCnpj = new JTextField();
		txtCnpj.setText("");
		txtCnpj.setColumns(10);
		
		JComboBox cbbEstado = new JComboBox();
		
		JComboBox cbbCidade = new JComboBox();
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		
		JLabel lblNmero = new JLabel("Número:");
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCnpj)
										.addComponent(lblInscrioEstadual)
										.addComponent(lblRazoSocial)
										.addComponent(lblNomeFantasia)
										.addComponent(lblCidade)
										.addComponent(lblEndereo)
										.addComponent(lblEstado)
										.addComponent(lblNmero))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(cbbCidade, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtInscricaoEstadual)
											.addComponent(txtNomeFantasia, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
											.addComponent(txtRazaoSocial)
											.addComponent(txtCnpj)
											.addComponent(txtEndereco)
											.addComponent(cbbEstado, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(145)
									.addComponent(lblFornecedor))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(tbContato, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(172)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnSalvar)
									.addGap(42)
									.addComponent(btnCancelar))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(62)
									.addComponent(lblMsg)))))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFornecedor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeFantasia)
						.addComponent(txtNomeFantasia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRazoSocial)
						.addComponent(txtRazaoSocial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInscrioEstadual)
						.addComponent(txtInscricaoEstadual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(cbbEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(cbbCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereo)
						.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmero)
						.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addComponent(tbContato, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(18)
					.addComponent(lblMsg)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
