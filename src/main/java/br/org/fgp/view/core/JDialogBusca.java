package br.org.fgp.view.core;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.org.fgp.core.dao.GenericoDao;
import javax.swing.SwingConstants;

public class JDialogBusca<T, PK> extends JDialog {

	private static final long serialVersionUID = 3237053657686708968L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFiltro;
	private JTable tabela;
	private GenericoDao<T, PK> daoGenerico;
	private Class clazz;

	/**
	 * Create the dialog.
	 */
	public JDialogBusca(GenericoDao<T, PK> daoGenerico) {
		this.daoGenerico = daoGenerico;
		this.clazz = daoGenerico.getObjectClass(); 
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel label = new JLabel(clazz.getSimpleName().concat(":"));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		
		JLabel label_1 = new JLabel(clazz.getSimpleName());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
					.addGap(32))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		
		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		contentPanel.setLayout(gl_contentPanel);
		carregarTabela();
	}

	private void carregarTabela() {
		List<T> buscarTodos = daoGenerico.buscarTodos();
		tabela.setModel( new TableModelGenerico(buscarTodos, clazz ) );
		tabela.setVisible(true);
	}
}
