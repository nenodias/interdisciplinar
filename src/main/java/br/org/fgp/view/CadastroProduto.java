package br.org.fgp.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;

public class CadastroProduto extends JPanel {
	private JTextField txtNome;
	private JTextField txtPrecoCusto;
	private JTextField txtPrecoVenda;
	private JTextField txtPorcentagem;
	private JTextField txtEstoqueAtual;
	private JTextField txtEstoqueMinimo;
	private JTextField txtEstoqueMaximo;

	/**
	 * Create the panel.
	 */
	public CadastroProduto() {
		
		JLabel lblProduto = new JLabel("");
		lblProduto.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JTextPane txtDescricao = new JTextPane();
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		
		JLabel lblCategoria = new JLabel("Categoria:");
		
		JComboBox cbbCategoria = new JComboBox();
		
		JLabel lblMarca = new JLabel("Marca:");
		
		JComboBox cbbMarca = new JComboBox();
		
		JLabel lblPreoUnitrio = new JLabel("Pre\u00E7o de venda:");
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		
		JComboBox cbbFornecedor = new JComboBox();
		
		txtPrecoCusto = new JTextField();
		txtPrecoCusto.setColumns(10);
		
		JLabel lblPreoDeCusto = new JLabel("Pre\u00E7o de custo:");
		
		txtPrecoVenda = new JTextField();
		txtPrecoVenda.setColumns(10);
		
		JLabel lblPorcentagem = new JLabel("Porcentagem:");
		
		txtPorcentagem = new JTextField();
		txtPorcentagem.setColumns(10);
		
		JLabel lblEstoqueAtual = new JLabel("Estoque atual:");
		
		txtEstoqueAtual = new JTextField();
		txtEstoqueAtual.setColumns(10);
		
		JLabel lblEstoqueMnimo = new JLabel("Estoque m\u00EDnimo:");
		
		txtEstoqueMinimo = new JTextField();
		txtEstoqueMinimo.setColumns(10);
		
		JLabel lblEstoqueMximo = new JLabel("Estoque m\u00E1ximo:");
		
		txtEstoqueMaximo = new JTextField();
		txtEstoqueMaximo.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMsg.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(157)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProduto)
							.addGap(282))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDescrio)
								.addComponent(lblNome)
								.addComponent(lblFornecedor)
								.addComponent(lblCategoria)
								.addComponent(lblPreoDeCusto)
								.addComponent(lblEstoqueAtual))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtDescricao, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
													.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
													.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
												.addComponent(cbbFornecedor, 0, 407, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(cbbCategoria, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
													.addComponent(lblMarca)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(cbbMarca, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(txtEstoqueAtual, 0, 0, Short.MAX_VALUE)
														.addComponent(txtPrecoCusto, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(lblPreoUnitrio)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(txtPrecoVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(lblPorcentagem)
															.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(txtPorcentagem, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(lblEstoqueMnimo)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblMsg)
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(txtEstoqueMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(ComponentPlacement.UNRELATED)
																	.addComponent(lblEstoqueMximo)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(txtEstoqueMaximo, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))))))
											.addGap(20)))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(88))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblProduto)
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescrio))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(cbbCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCategoria))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(cbbMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMarca)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbbFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFornecedor))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPreoDeCusto)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPrecoCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPreoUnitrio)
								.addComponent(txtPrecoVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPorcentagem)
								.addComponent(txtPorcentagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEstoqueAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEstoqueMnimo)
								.addComponent(txtEstoqueMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEstoqueMximo)
								.addComponent(txtEstoqueMaximo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEstoqueAtual))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addGap(32)
					.addComponent(lblMsg)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
