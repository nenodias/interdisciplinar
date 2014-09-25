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
	private JTextField txtPrecoVenda;
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
		
		txtPrecoVenda = new JTextField();
		txtPrecoVenda.setColumns(10);
		
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
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDescrio)
								.addComponent(lblNome)
								.addComponent(lblFornecedor)
								.addComponent(lblCategoria)
								.addComponent(lblPreoUnitrio))
							.addGap(4))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEstoqueAtual)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtDescricao, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(txtEstoqueAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtPrecoVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(lblEstoqueMnimo))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(35)
													.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(46)
													.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txtEstoqueMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(lblEstoqueMximo)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txtEstoqueMaximo, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(cbbCategoria, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
											.addComponent(lblMarca)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbbMarca, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
										.addComponent(cbbFornecedor, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE))))
							.addGap(8)))
					.addGap(75))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(256)
					.addComponent(lblMsg)
					.addContainerGap(281, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(259)
					.addComponent(lblProduto)
					.addContainerGap(270, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
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
							.addComponent(lblMarca)
							.addComponent(cbbMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbbFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFornecedor))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreoUnitrio)
						.addComponent(txtPrecoVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEstoqueAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstoqueAtual)
						.addComponent(lblEstoqueMnimo)
						.addComponent(txtEstoqueMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstoqueMximo)
						.addComponent(txtEstoqueMaximo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addGap(18)
					.addComponent(lblMsg)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
