package br.org.fgp.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Venda extends JPanel {
	private JTextField txtCodigo;
	private JTextField txtQuantidade;
	private JTable tbProduto;

	/**
	 * Create the panel.
	 */
	public Venda() {
		
		JLabel lblRealizarVenda = new JLabel("Realizar Venda");
		lblRealizarVenda.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		JLabel lblCdigoProduto = new JLabel("Código produto:");
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCodigo.setColumns(10);
		
		JButton btnPesquisaProduto = new JButton("...");
		btnPesquisaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesquisaProduto pesquisaProduto = new PesquisaProduto();
				pesquisaProduto.setVisible(true);				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Quantidade:");
		
		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtQuantidade.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		tbProduto = new JTable();
		
		JLabel newlabel = new JLabel("TOTAL:");
		newlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblTotal = new JLabel("00,00");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JButton btnFinalizarVenda = new JButton("Finalizar Venda");
		btnFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pagamento pagamento = new Pagamento();
				pagamento.setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(btnFinalizarVenda)
					.addPreferredGap(ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
					.addComponent(newlabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTotal)
					.addGap(166))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCdigoProduto)
									.addGap(199)
									.addComponent(lblNewLabel))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblRealizarVenda)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnPesquisaProduto, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(tbProduto, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRealizarVenda)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigoProduto)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnPesquisaProduto, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtCodigo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(btnAdicionar, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(txtQuantidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tbProduto, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFinalizarVenda, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(newlabel)
						.addComponent(lblTotal))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
