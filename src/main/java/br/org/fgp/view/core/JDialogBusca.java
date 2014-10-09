package br.org.fgp.view.core;

import java.awt.BorderLayout;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.org.fgp.core.dao.GenericoDao;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

public class JDialogBusca<T, PK> extends JDialog {

	private static final long serialVersionUID = 3237053657686708968L;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFiltro;
	private JTable tabela;
	private GenericoDao<T, PK> daoGenerico;
	private Class clazz;

	private JDialogBusca dialogo;

	/**
	 * Create the dialog.
	 * @param descricaoComponente 
	 * @param codigoComponente 
	 */
	public JDialogBusca(GenericoDao<T, PK> daoGenerico, final JTextField codigoComponente, final JTextField descricaoComponente) {
		dialogo = this;
		this.daoGenerico = daoGenerico;
		this.clazz = daoGenerico.getObjectClass(); 
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		setSize(400, 250);
		setTitle(clazz.getSimpleName());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Buscar " + clazz.getSimpleName(), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 145, 0};
		gbl_contentPanel.rowHeights = new int[]{35, 35, 132, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		JLabel label = new JLabel(clazz.getSimpleName().concat(":"));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		contentPanel.add(label, gbc_label);

		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		GridBagConstraints gbc_txtFiltro = new GridBagConstraints();
		gbc_txtFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFiltro.insets = new Insets(0, 0, 5, 0);
		gbc_txtFiltro.gridx = 1;
		gbc_txtFiltro.gridy = 1;
		contentPanel.add(txtFiltro, gbc_txtFiltro);

		JScrollPane scrollPane = new JScrollPane();

		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		
		carregarTabela();
		tabela.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					TableModelGenerico model = (TableModelGenerico) tabela.getModel();
					int linha = tabela.getSelectedRow();
					int coluna = tabela.getSelectedColumn();
					if(coluna == model.getCountadorColunas() ){
						//TODO Editar
					} else if(coluna == model.getCountadorColunas() +1 ){
						//TODO excluir
					}else{
						codigoComponente.setText( (String) model.getValueAt(linha, 0) );
						descricaoComponente.setText( (String) model.getValueAt(linha, 1) ); 
					}
					dialogo.dispose();
				}
			}

		});
		AbstractAction delete = new AbstractAction(){

			public void actionPerformed(ActionEvent e){
				//TODO Método para exclusão dos dados
				int modelRow = Integer.valueOf( e.getActionCommand() );
				((DefaultTableModel)tabela.getModel()).removeRow(modelRow);
			}
		};
		AbstractAction editar = new AbstractAction(){

			public void actionPerformed(ActionEvent e){
				//TODO Método para editar
			}
		};
		TableModelGenerico model = (TableModelGenerico) tabela.getModel();
		int coluna = model.getCountadorColunas();
		ButtonColumn botaoEditar = new ButtonColumn(tabela, editar, coluna);
		ButtonColumn botaoExcluir = new ButtonColumn(tabela, delete, coluna + 1 );
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPanel.add(scrollPane, gbc_scrollPane);
	}

	private void carregarTabela() {
		List<T> buscarTodos = daoGenerico.buscarTodos();
		tabela.setModel( new TableModelGenerico(buscarTodos, clazz ) );
		tabela.setVisible(true);
	}


}
