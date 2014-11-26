package br.org.fgp.view.core;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import br.org.fgp.view.TelaPrincipal;
public class JDialogBusca<T, PK> extends JDialog {

	private static final String IMAGENS_ADICIONAR_PNG = "imagens/adicionar.png";

	private static final long serialVersionUID = 3237053657686708968L;
	
	private static final Logger LOGGER = Logger.getLogger(JDialogBusca.class);
	
	private static final ClassLoader LOADER = JDialogBusca.class.getClassLoader();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFiltro;
	private JTable tabela;
	private GenericoDao<T, PK> daoGenerico;
	
	@SuppressWarnings("rawtypes")
	private Class clazz;

	private JDialogBusca<T, PK> dialogo;

	private JScrollPane scrollPane;

	private Inicializavel inicializavel;
	private JButton btnNovo;

	/**
	 * Create the dialog.
	 * @param descricaoComponente 
	 * @param codigoComponente 
	 */
	public JDialogBusca(GenericoDao<T, PK> daoGenerico, final JTextField codigoComponente, final JTextField descricaoComponente, Frame parent){
		super(parent);
		init(daoGenerico, codigoComponente, descricaoComponente);
	}
	
	public JDialogBusca(GenericoDao<T, PK> daoGenerico, final JTextField codigoComponente, final JTextField descricaoComponente, Dialog parent){
		super(parent);
		init(daoGenerico, codigoComponente, descricaoComponente);
	}

	private void init(GenericoDao<T, PK> daoGenerico,
			final JTextField codigoComponente,
			final JTextField descricaoComponente) {
		dialogo = this;
		this.daoGenerico = daoGenerico;
		this.clazz = daoGenerico.getObjectClass(); 
		this.setModal(true);
		setBounds(100, 100, 600, 400);
		setSize(600, 400);
		setTitle(clazz.getSimpleName());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Buscar " + clazz.getSimpleName(), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 145, 0};
		gbl_contentPanel.rowHeights = new int[]{35, 35, 0, 132, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		btnNovo.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_ADICIONAR_PNG)));
		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chamarNovo();
			}
		});
		if(TelasUtils.getUsuarioLogado() != null && !TelasUtils.isPermision(clazz, TelasUtils.getUsuarioLogado().getTipo() ) ){
			btnNovo.setEnabled(false);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void eventoCliqueTabela(final JTextField codigoComponente,
			final JTextField descricaoComponente, MouseEvent e) {
		if (e.getClickCount() == 2) {
			TableModelGenerico model = (TableModelGenerico) tabela.getModel();
			int linha = tabela.getSelectedRow();
			int coluna = tabela.getSelectedColumn();
			boolean sair = false;
			boolean abreOutraTela = false;
			String idString = (String) model.getValueAt(linha, 0);
			
			if(isDialogoBusca(codigoComponente, descricaoComponente)){
				codigoComponente.setText( idString );
				descricaoComponente.setText( (String) model.getValueAt(linha, 1) );
			}
			Integer id = Integer.parseInt( idString );
			
			if(coluna == model.getCountadorColunas()){
				chamarEditar( id );
				sair = abreOutraTela = true;
			} else if(coluna == model.getCountadorColunas() +1  && (TelasUtils.getUsuarioLogado() == null ||  TelasUtils.isPermision(clazz, TelasUtils.getUsuarioLogado().getTipo())  )){
				
				int excluir = JOptionPane.showConfirmDialog(tabela.getParent().getParent(), "Deseja excluir o registro: "+id + " ?", "Excluir?", JOptionPane.YES_NO_OPTION);
				if(excluir == JOptionPane.YES_OPTION){
					chamarExcluir( id );
				}
			}else{
				if(isDialogoBusca(codigoComponente, descricaoComponente)){
					sair = true;
				}
			}
			
			if(sair){
				dialogo.dispose();
			}
			if(abreOutraTela){
				inicializavel.setVisible(true);
			}
		}
	}

	private boolean isDialogoBusca(final JTextField codigoComponente,
			final JTextField descricaoComponente) {
		return codigoComponente != null || descricaoComponente != null;
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
		if(tela instanceof JPanel){
			carregaPainel(tela);
		}
		inicializavel = (Inicializavel) tela; 
		inicializavel.load(id);
	}

	private void carregaPainel(Object tela) {
		JPanel telaPainel = (JPanel) tela;
		TelaPrincipal frmInterdisciplinar = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		frmInterdisciplinar.getFrameCentral().getContentPane().removeAll();
		frmInterdisciplinar.getFrameCentral().getContentPane().setBounds(telaPainel.getBounds());
		frmInterdisciplinar.getFrameCentral().getContentPane().add(telaPainel);
		frmInterdisciplinar.getFrameCentral().getContentPane().revalidate();
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	private void chamarNovo() {
		if(TelasUtils.getUsuarioLogado() != null && TelasUtils.isPermision(clazz, TelasUtils.getUsuarioLogado().getTipo() ) ){ 
			Class classeDoObjeto = daoGenerico.getObjectClass();
			Class classeTela = TelasUtils.getView(classeDoObjeto);
			Object tela = ApplicationContextConfig.getContext().getBean(classeTela);
			if(tela instanceof JPanel){
				carregaPainel(tela);
			}
			inicializavel = (Inicializavel) tela; 
			inicializavel.load(null);
			inicializavel.setVisible(true);
			dialogo.dispose();
		}
	}
	
	private void carregarTodosTabela() {
		List<T> buscarTodos = daoGenerico.buscarTodos();
		carregarTabela(buscarTodos);
	}
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private void carregarTabela(List<T> buscarTodos) {
		
		tabela.setModel( new TableModelGenerico(buscarTodos, clazz ) );
		tabela.setVisible(true);
		
		TableModelGenerico model = (TableModelGenerico) tabela.getModel();
		int coluna = model.getCountadorColunas();
		
		btnNovo = new JButton("Novo");
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.insets = new Insets(0, 0, 5, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 2;
		contentPanel.add(btnNovo, gbc_btnNovo);
		ButtonColumn botaoEditar = new ButtonColumnEditar(tabela, null, coluna);
		ButtonColumn botaoExcluir = new ButtonColumnExcluir(tabela, null, coluna + 1 );
		if( TelasUtils.getUsuarioLogado() != null && ! TelasUtils.isPermision(clazz, TelasUtils.getUsuarioLogado().getTipo() )  ){
			botaoExcluir.setEnabled(false);
		}
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		
		contentPanel.add(scrollPane, gbc_scrollPane);
	}

	@SuppressWarnings("unchecked")
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
