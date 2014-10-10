package br.org.fgp.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CadastroEntradaProduto extends JPanel {
	
	private static final long serialVersionUID = -7159300943489660623L;
	private JTextField txtFornecedor;
	private JTextField txtProduto;
	private JTextField txtQuantidade;
	private JTextField txtPreco;

	public CadastroEntradaProduto() {
		
		JLabel label1 = new JLabel("Data:");
		
		JLabel lblData = new JLabel("DataAtual");
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		
		JLabel lblFuncionario = new JLabel("Funcionario");
		
		txtFornecedor = new JTextField();
		txtFornecedor.setEnabled(false);
		txtFornecedor.setColumns(10);
		
		JButton btnPesquisaFornecedor = new JButton("+");
		
		JLabel lblProduto = new JLabel("Produto:");
		
		txtProduto = new JTextField();
		txtProduto.setEnabled(false);
		txtProduto.setColumns(10);
		
		JButton btnPesquisaProduto = new JButton("+");
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		
		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		JLabel lblEntradaDeProdutos = new JLabel("Entrada de Produtos");
		lblEntradaDeProdutos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPreo)
						.addComponent(lblProduto)
						.addComponent(label1)
						.addComponent(lblQuantidade)
						.addComponent(lblFornecedor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEntradaDeProdutos)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnSalvar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCancelar)
									.addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(lblFuncionario)
															.addPreferredGap(ComponentPlacement.UNRELATED, 216, Short.MAX_VALUE))
														.addComponent(txtProduto, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
														.addComponent(txtFornecedor, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
													.addGap(6)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(btnPesquisaFornecedor, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
														.addComponent(btnPesquisaProduto, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblData)
													.addPreferredGap(ComponentPlacement.RELATED, 246, GroupLayout.PREFERRED_SIZE)))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 208, GroupLayout.PREFERRED_SIZE)))
										.addGap(60))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblEntradaDeProdutos)
					.addGap(18)
					.addComponent(lblFuncionario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label1)
						.addComponent(lblData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(txtProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisaProduto))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPesquisaFornecedor)
						.addComponent(lblFornecedor)
						.addComponent(txtFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidade)
						.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreo)
						.addComponent(txtPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
