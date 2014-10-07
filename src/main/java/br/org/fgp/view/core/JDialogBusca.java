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
	}

	private void carregarTabela() {
		List<T> buscarTodos = daoGenerico.buscarTodos();
		tabela.setModel( new TableModelGenerico(buscarTodos, clazz ) );
		tabela.setVisible(true);
	}

	
}
