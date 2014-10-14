package br.org.fgp.view.core;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.GenericoDao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JDialogBusca<T, PK> extends JDialog {

	private static final long serialVersionUID = 3237053657686708968L;
	
	private static final Logger LOGGER = Logger.getLogger(JDialogBusca.class);

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFiltro;
	private JTable tabela;
	private GenericoDao<T, PK> daoGenerico;
	
	@SuppressWarnings("rawtypes")
	private Class clazz;

	private JDialogBusca<T, PK> dialogo;

	private JScrollPane scrollPane;

	private Inicializavel inicializavel;

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
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				eventoFiltro();
			}
		});
		
		txtFiltro.setColumns(10);
		GridBagConstraints gbc_txtFiltro = new GridBagConstraints();
		gbc_txtFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFiltro.insets = new Insets(0, 0, 5, 0);
		gbc_txtFiltro.gridx = 1;
		gbc_txtFiltro.gridy = 1;
		contentPanel.add(txtFiltro, gbc_txtFiltro);

		scrollPane = new JScrollPane();

		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		
		carregarTodosTabela();
		
		tabela.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				eventoCliqueTabela(codigoComponente, descricaoComponente, e);
			}

		});
		
	}
	
	private void eventoCliqueTabela(final JTextField codigoComponente,
			final JTextField descricaoComponente, MouseEvent e) {
		if (e.getClickCount() == 2) {
			TableModelGenerico model = (TableModelGenerico) tabela.getModel();
			int linha = tabela.getSelectedRow();
			int coluna = tabela.getSelectedColumn();
			boolean sair = false;
			boolean abreOutraTela = false;
			
			codigoComponente.setText( (String) model.getValueAt(linha, 0) );
			descricaoComponente.setText( (String) model.getValueAt(linha, 1) );
			Integer id = Integer.parseInt( codigoComponente.getText() );
			
			if(coluna == model.getCountadorColunas() ){
				chamarEditar( id );
				sair = abreOutraTela = true;
			} else if(coluna == model.getCountadorColunas() +1 ){
				
				int excluir = JOptionPane.showConfirmDialog(tabela.getParent().getParent(), "Deseja excluir o registro: "+id + " ?", "Excluir?", JOptionPane.YES_NO_OPTION);
				if(excluir == JOptionPane.YES_OPTION){
					chamarExcluir( id );
				}
			}else{
				sair = true;
			}
			
			if(sair){
				dialogo.dispose();
			}
			if(abreOutraTela){
				inicializavel.setVisible(true);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void chamarExcluir(Integer id) {
		LOGGER.info("Iniciando exclusão");
		try{
			daoGenerico.deletar( (PK) id);
		}catch(Exception e){
			LOGGER.error(e);
		}
		carregarTodosTabela();
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	private void chamarEditar(Integer id) {
		Class classeDoObjeto = daoGenerico.getObjectClass();
		Class classeTela = TelasUtils.getView(classeDoObjeto);
		Object tela = ApplicationContextConfig.getContext().getBean(classeTela);
		if(tela instanceof JDialog){
			JDialog telaDialogo = (JDialog) tela;
		}else if(tela instanceof JPanel){
			JPanel telaPainel = (JPanel) tela;
			tabela.getParent().getParent();
		}
		inicializavel = (Inicializavel) tela; 
		inicializavel.load(id);
	}
	
	
	private void carregarTodosTabela() {
		List<T> buscarTodos = daoGenerico.buscarTodos();
		carregarTabela(buscarTodos);
	}
	
	@SuppressWarnings("unused")
	private void carregarTabela(List<T> buscarTodos) {
		
		tabela.setModel( new TableModelGenerico(buscarTodos, clazz ) );
		tabela.setVisible(true);
		
		TableModelGenerico model = (TableModelGenerico) tabela.getModel();
		int coluna = model.getCountadorColunas();
		ButtonColumn botaoEditar = new ButtonColumn(tabela, null, coluna);
		ButtonColumn botaoExcluir = new ButtonColumn(tabela, null, coluna + 1 );
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		
		contentPanel.add(scrollPane, gbc_scrollPane);
	}

	private void eventoFiltro(){
		try{
		
			Filtravel<T> filtravelDao = (Filtravel<T>) daoGenerico;
			List<T> filtrarPorDescricao = filtravelDao.filtrarPorDescricao(txtFiltro.getText());
			carregarTabela(filtrarPorDescricao);
		}
		catch(Exception e){
			LOGGER.error(e);
		}
	}

}
