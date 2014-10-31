package br.org.fgp.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.org.fgp.model.Marca;
import br.org.fgp.view.core.JBusca;

public class TelaVenda extends JPanel {
	private JTextField txtQuantidade;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TelaVenda() {
		setBorder(new TitledBorder(null, "Realizar Venda", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 210, 20, 0};
		gridBagLayout.rowHeights = new int[]{35, 35, 35, 0, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);		
		
		JLabel lblCdigoProduto = new JLabel("Produto:");
		GridBagConstraints gbc_lblCdigoProduto = new GridBagConstraints();
		gbc_lblCdigoProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCdigoProduto.anchor = GridBagConstraints.WEST;
		gbc_lblCdigoProduto.gridx = 0;
		gbc_lblCdigoProduto.gridy = 1;
		add(lblCdigoProduto, gbc_lblCdigoProduto);
		
		JBusca<Marca, Integer> buscaProduto = new JBusca<Marca, Integer>();
		GridBagConstraints gbc_buscaProduto = new GridBagConstraints();
		gbc_buscaProduto.anchor = GridBagConstraints.SOUTHWEST;
		gbc_buscaProduto.insets = new Insets(0, 0, 5, 5);
		gbc_buscaProduto.gridx = 1;
		gbc_buscaProduto.gridy = 1;
		add(buscaProduto, gbc_buscaProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.anchor = GridBagConstraints.WEST;
		gbc_lblQuantidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantidade.gridx = 0;
		gbc_lblQuantidade.gridy = 2;
		add(lblQuantidade, gbc_lblQuantidade);
		
		txtQuantidade = new JTextField();
		GridBagConstraints gbc_txtQuantidade = new GridBagConstraints();
		gbc_txtQuantidade.anchor = GridBagConstraints.WEST;
		gbc_txtQuantidade.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantidade.gridx = 1;
		gbc_txtQuantidade.gridy = 2;
		add(txtQuantidade, gbc_txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdicionar.fill = GridBagConstraints.BOTH;
		gbc_btnAdicionar.gridx = 2;
		gbc_btnAdicionar.gridy = 2;
		add(btnAdicionar, gbc_btnAdicionar);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 3;
		add(table, gbc_table);
		
		JLabel newlabel = new JLabel("TOTAL:");
		GridBagConstraints gbc_newlabel = new GridBagConstraints();
		gbc_newlabel.anchor = GridBagConstraints.WEST;
		gbc_newlabel.insets = new Insets(0, 0, 0, 5);
		gbc_newlabel.gridx = 0;
		gbc_newlabel.gridy = 4;
		add(newlabel, gbc_newlabel);
		
		JLabel lblTotal = new JLabel("00,00");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 4;
		add(lblTotal, gbc_lblTotal);
		JButton btnFinalizarVenda = new JButton("Finalizar Venda");
		btnFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pagamento pagamento = new Pagamento();
				pagamento.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnFinalizarVenda = new GridBagConstraints();
		gbc_btnFinalizarVenda.fill = GridBagConstraints.BOTH;
		gbc_btnFinalizarVenda.gridx = 2;
		gbc_btnFinalizarVenda.gridy = 4;
		add(btnFinalizarVenda, gbc_btnFinalizarVenda);

	}
}
