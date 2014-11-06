package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.validation.ValidationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.annotations.Permissao;
import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.CidadeDao;
import br.org.fgp.dao.ContatoDao;
import br.org.fgp.dao.ContatoFornecedorDao;
import br.org.fgp.dao.ContatoTelefoneDao;
import br.org.fgp.dao.EstadoDao;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.dao.TelefoneDao;
import br.org.fgp.model.Cidade;
import br.org.fgp.model.Contato;
import br.org.fgp.model.ContatoFornecedor;
import br.org.fgp.model.ContatoTelefone;
import br.org.fgp.model.Endereco;
import br.org.fgp.model.Estado;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ButtonColumnEditar;
import br.org.fgp.view.core.ButtonColumnExcluir;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JButtonAdicionar;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonSalvar;
import br.org.fgp.view.core.JCabecalhoLabel;
import br.org.fgp.view.core.TableModelGenerico;
import br.org.fgp.view.core.Validador;

@Controller
public class CadastroFornecedor extends JPanel implements Inicializavel {
	
	private static final long serialVersionUID = 2174153481281619633L;

	private static final String CLASS_NAME = "Fornecedor";

	private static final Logger LOGGER = Logger.getLogger(CadastroFornecedor.class);
	
	private Fornecedor fornecedor;
	
	@Autowired
	private FornecedorDao fornecedorDao;
	
	@Autowired
	private CidadeDao cidadeDao;
	
	@Autowired
	private EstadoDao estadoDao;
	
	@Autowired
	private ContatoDao contatoDao;
	
	@Autowired
	private ContatoFornecedorDao contatoFornecedorDao;
	
	@Autowired
	private ContatoTelefoneDao contatoTelefoneDao;
	
	@Autowired
	private TelefoneDao telefoneDao;
	
	public ContatoTelefoneDao getContatoTelefoneDao() {
		return contatoTelefoneDao;
	}

	public void setContatoTelefoneDao(ContatoTelefoneDao contatoTelefoneDao) {
		this.contatoTelefoneDao = contatoTelefoneDao;
	}
	
	public ContatoDao getContatoDao() {
		return contatoDao;
	}
	
	public void setContatoDao(ContatoDao contatoDao) {
		this.contatoDao = contatoDao;
	}

	public ContatoFornecedorDao getContatoFornecedorDao() {
		return contatoFornecedorDao;
	}

	public void setContatoFornecedorDao(ContatoFornecedorDao contatoFornecedorDao) {
		this.contatoFornecedorDao = contatoFornecedorDao;
	}

	public TelefoneDao getTelefoneDao() {
		return telefoneDao;
	}

	public void setTelefoneDao(TelefoneDao telefoneDao) {
		this.telefoneDao = telefoneDao;
	}

	public CidadeDao getCidadeDao() {
		return cidadeDao;
	}

	public void setCidadeDao(CidadeDao cidadeDao) {
		this.cidadeDao = cidadeDao;
	}
	
	public FornecedorDao getFornecedorDao() {
		return fornecedorDao;
	}

	public void setFornecedorDao(FornecedorDao fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}
	
	public EstadoDao getEstadoDao() {
		return estadoDao;
	}

	public void setEstadoDao(EstadoDao estadoDao) {
		this.estadoDao = estadoDao;
	}

	private JComboBox<Estado> cbbEstado;
	private JComboBox<Cidade> cbbCidade;

	private JPanel painel;
	
	@Permissao
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JSplitPane splitPane;

	@Permissao
	private JTextField txtCnpj;
	@Permissao
	private JTextField txtInscricaoEstadual;
	@Permissao
	private JTextField txtNomeFantasia;
	@Permissao
	private JTextField txtRazaoSocial;
	@Permissao
	private JTextField txtEndereco;
	@Permissao
	private JTextField txtNumero;
	@Permissao
	private JTextField txtBairro;
	private JScrollPane painelTabela;

	private JTable tabela;

	private List<ContatoFornecedor> listaContato;

	private TableModelGenerico<ContatoFornecedor> modelGenerico;

	@SuppressWarnings("rawtypes")
	private SwingWorker estadoWorker;

	@SuppressWarnings("rawtypes")
	private SwingWorker cidadeWorker;

	@Permissao
	private JButton btnAdicionarContato;

	private ButtonColumnExcluir buttonColumnExcluir;

	public CadastroFornecedor() {
		painel = this;
		setLayout(null);
		adicionarComponente(new JCabecalhoLabel(CLASS_NAME), 0);
		
		adicionarComponente(new JLabel("Nome Fantasia:"), 2);
		txtNomeFantasia = new JTextField();
		adicionarComponente(txtNomeFantasia, 2);
		
		adicionarComponente(new JLabel("Razão Social:"), 4);
		txtRazaoSocial = new JTextField();
		adicionarComponente(txtRazaoSocial, 4);
		
		adicionarComponente(new JLabel("CNPJ:"), 6);
		txtCnpj = new JTextField();
		adicionarComponente(txtCnpj, 6);
		
		adicionarComponente(new JLabel("Inscrição Estadual:"), 8);
		txtInscricaoEstadual = new JTextField();
		adicionarComponente(txtInscricaoEstadual, 8);
		
		adicionarComponente(new JLabel("Rua:"), 10);
		txtEndereco = new JTextField();
		adicionarComponente(txtEndereco, 10);
		
		adicionarComponente(new JLabel("Número:"), 12);
		txtNumero = new JTextField();
		adicionarComponente(txtNumero, 12);
		
		adicionarComponente(new JLabel("Bairro:"),14 );
		txtBairro = new JTextField();
		adicionarComponente(txtBairro, 14);
		
		adicionarComponente(new JLabel("Estado:"), 16);
		cbbEstado = new JComboBox<Estado>();
		adicionarComponente(cbbEstado, 16);

		adicionarComponente(new JLabel("Cidade:"), 18);
		cbbCidade = new JComboBox<Cidade>();
		adicionarComponente(cbbCidade, 18);
		
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 28);
		
		btnSalvar = new JButtonSalvar();
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnSalvar);
		}
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButtonCancelar();
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(TelasUtils.DEFAULT_LARGURA_COMPONENTE/2);
		splitPane.setEnabled(false);
		
		painelTabela = new JScrollPane();
		tabela = new JTable();
		if(this.fornecedor != null && this.fornecedor.getListaContato() != null ){
			listaContato = this.fornecedor.getListaContato();
		}else{
			listaContato = new ArrayList<ContatoFornecedor>();
		}
		modelGenerico = new TableModelGenerico<ContatoFornecedor>(listaContato, ContatoFornecedor.class);
		btnAdicionarContato = new JButtonAdicionar();
		btnAdicionarContato.setBounds(TelasUtils.DEFAULT_X+ TelasUtils.DEFAULT_ESPACO , TelasUtils.DEFAULT_Y +(20 *TelasUtils.DEFAULT_ESPACO) , TelasUtils.DEFAULT_LARGURA_COMPONENTE /2, TelasUtils.DEFAULT_ALTURA_COMPONENTE);
		add(btnAdicionarContato);
		tabela.setModel(modelGenerico);
		tabela.setEnabled(true);
		painelTabela.setViewportView(tabela);
		painelTabela.setEnabled(true);
		painelTabela.setBounds(TelasUtils.DEFAULT_X+ TelasUtils.DEFAULT_ESPACO , TelasUtils.DEFAULT_Y +(22 *TelasUtils.DEFAULT_ESPACO) , TelasUtils.DEFAULT_LARGURA_COMPONENTE*2, TelasUtils.DEFAULT_ALTURA_COMPONENTE+TelasUtils.DEFAULT_ALTURA_COMPONENTE*2);
		add(painelTabela);
		painelTabela.setVisible(true);
		
		ComponenteControlado<CadastroFornecedor> controleAcesso = new ComponenteControlado<CadastroFornecedor>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		
		carregarEstado();
		carregarCidade();
		
		tabela.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				eventoCliqueTabela(e);
			}
		});
		
		cbbEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				carregarCidade();
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}

		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}

		});
		
		btnAdicionarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}

		});
	}

	private void salvar(){
		String mensagemSave = " atualizado ";
		String mensagemFail = " atualizar ";
		try{
			if(fornecedor == null ){
				fornecedor = new  Fornecedor();
				if(fornecedor.getId() != null){
					mensagemSave = " salvo ";
					mensagemFail = " salvar ";
				}
			}
			fornecedor.setNomeFantasia( txtNomeFantasia.getText() );
			fornecedor.setRazaoSocial( txtRazaoSocial.getText() );
			fornecedor.setCnpj(txtCnpj.getText());
			fornecedor.setInscricaoEstadual(txtInscricaoEstadual.getText());
			Cidade cidade = (Cidade) cbbCidade.getSelectedItem();
			fornecedor.setEnderecoComercial(new Endereco(txtEndereco.getText(), txtNumero.getText(), txtBairro.getText(), cidade ));
			Validador<Fornecedor> validador = new Validador<Fornecedor>();
			validador.validacaoCampos(fornecedor);
			fornecedorDao.salvar(fornecedor);
			if(!listaContato.isEmpty()){
				if(fornecedor.getId() != null){
					contatoFornecedorDao.deletarPorIdUsuario(fornecedor.getId());
				}
				for (ContatoFornecedor contatoFornecedor : listaContato) {
					contatoFornecedor.setId(null);
					contatoFornecedor.setFornecedor(fornecedor);
					contatoFornecedor.getContato().setId(null);
					for (ContatoTelefone contatoTelefone : contatoFornecedor.getContato().getListaTelefone() ) {
						contatoTelefone.setId(null);
						contatoTelefone.setContato(contatoFornecedor.getContato());
						telefoneDao.salvar( contatoTelefone.getTelefone() );
						contatoDao.salvar( contatoTelefone.getContato() );
						contatoTelefoneDao.salvar(contatoTelefone);
					}
					contatoFornecedorDao.salvar(contatoFornecedor);
				}
			}
			JOptionPane.showMessageDialog(null, CLASS_NAME.concat(mensagemFail).concat("com sucesso.") );
			fornecedor = null;
			limparComponentes();
			cancelar();
		}
		catch(ValidationException e){
			LOGGER.error(e);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Falha ao ".concat(mensagemSave).concat(" ").concat(CLASS_NAME).concat(".") );
			LOGGER.error(ex);
		}
	}

	private void limparComponentes() {
		final JTextField[] componentes = {txtNomeFantasia,txtRazaoSocial,txtCnpj, txtInscricaoEstadual,txtEndereco,txtNumero,txtBairro};
		for (JTextField jComponent : componentes) {
			jComponent.setText(StringUtils.EMPTY);
		}
		listaContato.clear();
		atualizaDesenhoTabela();
	}

	@SuppressWarnings({ "rawtypes" })
	private void carregarEstado(){
		estadoWorker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				cbbEstado.removeAllItems();
				for (Estado estado : estadoDao.buscarTodos()) {
					cbbEstado.addItem(estado);
					if(fornecedor != null && fornecedor.getEnderecoComercial() != null && estado.equals(fornecedor.getEnderecoComercial().getCidade().getEstado() ) ){
						cbbEstado.setSelectedItem(estado);
					}
				}
				return null;
			}
		};
		estadoWorker.execute();
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void carregarCidade(){
		cidadeWorker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				Estado item = (Estado) cbbEstado.getSelectedItem();
				cbbCidade.removeAllItems();
				while (!estadoWorker.isDone()) {
					
				}
				for (Cidade cidade : cidadeDao.buscaPorEstado(item.getId() ) ) {
					cbbCidade.addItem(cidade);
					if(fornecedor != null && fornecedor.getEnderecoComercial() != null && cidade.equals(fornecedor.getEnderecoComercial().getCidade() ) ){
						cbbCidade.setSelectedItem(cidade);
					}
				}
				return null;
			}
		};
		cidadeWorker.execute();
	}

	@Override
	public void load(Integer id) {
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			fornecedor = fornecedorDao.buscarPorId(id);
		}
		if(id != null){
			txtNomeFantasia.setText( fornecedor.getNomeFantasia() );
			txtInscricaoEstadual.setText( fornecedor.getInscricaoEstadual() );
			txtRazaoSocial.setText( fornecedor.getRazaoSocial() );
			txtCnpj.setText( fornecedor.getCnpj() );
			txtEndereco.setText( fornecedor.getEnderecoComercial().getRua() );
			txtNumero.setText( fornecedor.getEnderecoComercial().getNumero() );
			txtBairro.setText( fornecedor.getEnderecoComercial().getBairro() );
			
			listaContato = fornecedor.getListaContato();
		}else{
			fornecedor = new Fornecedor();
			listaContato.clear();
		}
		atualizaDesenhoTabela();
	}

	private void init(Usuario usuarioLogado) {
		ComponenteControlado<CadastroFornecedor> controleAcesso = new ComponenteControlado<CadastroFornecedor>(this);
		controleAcesso.pronto(usuarioLogado.getTipo());
		carregarEstado();
		carregarCidade();
		repaint();
		revalidate();
	}
	
	private void cancelar() {
		TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		telaPrincipal.cancelar();
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}
	
	private void adicionarContato() {
		final Contato contato = new Contato();
		abrirModalContato(contato);
		if(StringUtils.isNotBlank( contato.getNome() ) && StringUtils.isNotBlank( contato.getEmail() ) ){
			ContatoFornecedor contatoFornecedo = new ContatoFornecedor();
			contatoFornecedo.setContato(contato);
			contatoFornecedo.setFornecedor(fornecedor);
			listaContato.add(contatoFornecedo);
			atualizaDesenhoTabela();
		}
	}
	
	private void abrirModalContato(final Contato contato) {
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					CadastroContato dialog = new CadastroContato(contato);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(painel);
					dialog.setVisible(true);
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
		};
		runnable.run();
	}

	private void atualizaDesenhoTabela() {
		if(listaContato.size() > 0){
			new ButtonColumnEditar(tabela, null, modelGenerico.getCountadorColunas() );
			buttonColumnExcluir = new ButtonColumnExcluir(tabela, null, modelGenerico.getCountadorColunas() + 1 );
			if( TelasUtils.getUsuarioLogado() != null && ! TelasUtils.isPermision(Fornecedor.class, TelasUtils.getUsuarioLogado().getTipo() )  ){
				buttonColumnExcluir.setEnabled(false);
			}
		}
		if(!modelGenerico.getLista().equals(listaContato)){
			modelGenerico.getLista().clear();
			for (ContatoFornecedor usuarioTelefone : listaContato) {
				modelGenerico.getLista().add(usuarioTelefone);
			}
			listaContato = (List<ContatoFornecedor>) modelGenerico.getLista();
			
		}
		modelGenerico.fireTableDataChanged();
		tabela.updateUI();
	}

	@SuppressWarnings("unchecked")
	protected void eventoCliqueTabela(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TableModelGenerico<ContatoFornecedor> model = (TableModelGenerico<ContatoFornecedor>) tabela.getModel();
			boolean atualizaTabela = false;
			int linha = tabela.getSelectedRow();
			int coluna = tabela.getSelectedColumn();
			ContatoFornecedor contatoFornecedor = listaContato.get(linha);
			
			if(coluna == model.getCountadorColunas() ){
				abrirModalContato(contatoFornecedor.getContato());
				atualizaTabela = true;
			} else if(coluna == model.getCountadorColunas() +1 && ( TelasUtils.getUsuarioLogado() == null &&  TelasUtils.isPermision(Fornecedor.class, TelasUtils.getUsuarioLogado().getTipo() ) )  ){
				int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: "+contatoFornecedor.getContatoNome() + " ?", "Excluir?", JOptionPane.YES_NO_OPTION);
				if(excluir == JOptionPane.YES_OPTION){
					int contador = 0;
					while(true){
						if(listaContato.get(contador).equals(contatoFornecedor) ){
							ContatoFornecedor contatoFor = listaContato.get(contador);
							listaContato.remove( contatoFor );
							break;
						}
						contador++;
					}
				}
				atualizaTabela = true;
			}
			if(atualizaTabela){
				atualizaDesenhoTabela();
			}
		}
	}

	public JComboBox<Estado> getCbbEstado() {
		return cbbEstado;
	}

	public void setCbbEstado(JComboBox<Estado> cbbEstado) {
		this.cbbEstado = cbbEstado;
	}

	public JComboBox<Cidade> getCbbCidade() {
		return cbbCidade;
	}

	public void setCbbCidade(JComboBox<Cidade> cbbCidade) {
		this.cbbCidade = cbbCidade;
	}

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public JTextField getTxtCnpj() {
		return txtCnpj;
	}

	public void setTxtCnpj(JTextField txtCnpj) {
		this.txtCnpj = txtCnpj;
	}

	public JTextField getTxtInscricaoEstadual() {
		return txtInscricaoEstadual;
	}

	public void setTxtInscricaoEstadual(JTextField txtInscricaoEstadual) {
		this.txtInscricaoEstadual = txtInscricaoEstadual;
	}

	public JTextField getTxtNomeFantasia() {
		return txtNomeFantasia;
	}

	public void setTxtNomeFantasia(JTextField txtNomeFantasia) {
		this.txtNomeFantasia = txtNomeFantasia;
	}

	public JTextField getTxtRazaoSocial() {
		return txtRazaoSocial;
	}

	public void setTxtRazaoSocial(JTextField txtRazaoSocial) {
		this.txtRazaoSocial = txtRazaoSocial;
	}

	public JTextField getTxtEndereco() {
		return txtEndereco;
	}

	public void setTxtEndereco(JTextField txtEndereco) {
		this.txtEndereco = txtEndereco;
	}

	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(JTextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public JTextField getTxtBairro() {
		return txtBairro;
	}

	public void setTxtBairro(JTextField txtBairro) {
		this.txtBairro = txtBairro;
	}

	public JButton getBtnAdicionarContato() {
		return btnAdicionarContato;
	}

	public void setBtnAdicionarContato(JButton btnAdicionarContato) {
		this.btnAdicionarContato = btnAdicionarContato;
	}
	
}
