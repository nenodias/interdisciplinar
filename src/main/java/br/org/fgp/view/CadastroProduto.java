package br.org.fgp.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.UIManager;

public class CadastroProduto extends JPanel {
	private JTextField txtNome;
	private JTextField txtPrecoVenda;
	private JTextField txtEstoqueAtual;
	private JTextField txtEstoqueMinimo;
	private JTextField txtEstoqueMaximo;
	private JTextField txtCategoria;
	private JTextField txtMarca;

	/**
	 * Create the panel.
	 */
	public CadastroProduto() {
		setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JTextPane txtDescricao = new JTextPane();
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		
		JLabel lblCategoria = new JLabel("Categoria:");
		
		JLabel lblMarca = new JLabel("Marca:");
		
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
		
		txtCategoria = new JTextField();
		txtCategoria.setEnabled(false);
		txtCategoria.setColumns(10);
		
		JButton btnPesquisaCategoria = new JButton("...");
		
		txtMarca = new JTextField();
		txtMarca.setEnabled(false);
		txtMarca.setColumns(10);
		
		JButton btnPesquisaMarca = new JButton("...");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDescrio)
								.addComponent(lblNome)
								.addComponent(lblFornecedor)
								.addComponent(lblCategoria)
								.addComponent(lblEstoqueAtual)
								.addComponent(lblPreoUnitrio))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrecoVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtEstoqueAtual, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblEstoqueMnimo))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(35)
											.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEstoqueMinimo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(14)
											.addComponent(lblEstoqueMximo)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtEstoqueMaximo, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(cbbFornecedor, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtDescricao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtCategoria, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisaCategoria, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGap(74)
									.addComponent(lblMarca)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisaMarca, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(259)
							.addComponent(lblProduto))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(273)
							.addComponent(lblMsg)))
					.addContainerGap(46, Short.MAX_VALUE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategoria)
						.addComponent(txtCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisaCategoria)
						.addComponent(lblMarca)
						.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisaMarca))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFornecedor)
						.addComponent(cbbFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPrecoVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPreoUnitrio))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEstoqueAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstoqueAtual)
						.addComponent(lblEstoqueMnimo)
						.addComponent(txtEstoqueMinimo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEstoqueMaximo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEstoqueMximo))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMsg)
					.addContainerGap(100, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
