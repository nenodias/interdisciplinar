package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.MessagemUtil;
import br.org.fgp.core.SecurityUtils;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.CidadeDao;
import br.org.fgp.dao.EstadoDao;
import br.org.fgp.dao.TelefoneDao;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.dao.UsuarioTelefoneDao;
import br.org.fgp.model.Cidade;
import br.org.fgp.model.Endereco;
import br.org.fgp.model.Estado;
import br.org.fgp.model.Telefone;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.UsuarioTelefone;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ButtonColumn;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.TableModelGenerico;

@Component
public class CadastroUsuario extends JPanel implements Inicializavel {
	
	private static final String VAZIO = "";

	private static final long serialVersionUID = -2627759817945447945L;
	
	private static final String CLASS_NAME = "Usuário";
	
	private static final int X = 0; 
	
	private static final int Y = 0; 
	
	private static final int LARGURA_COMPONENTES = 300;
	
	private static final int ALTURA_COMPONENTES = 18;
	
	private static final Logger LOGGER = Logger.getLogger(CadastroUsuario.class);
	
	private JTextField txtNome;
	private JTextField txtLogin;
	private JTextField txtCPF;
	private JPasswordField txtSenha;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JComboBox<TipoUsuario> cbbTipo;
	private JComboBox<Estado> cbbEstado;
	private JComboBox<Cidade> cbbCidade;
	private JLabel lblEndereo;
	private JLabel lblNmero;
	private JLabel lblBairro;
	private JLabel lblEstado;
	private JLabel lblCidade;
	private JSplitPane splitPane;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private Usuario usuario;
	
	private JPanel painel; 
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private CidadeDao cidadeDao;
	
	@Autowired
	private EstadoDao estadoDao;
	
	@Autowired
	private UsuarioTelefoneDao usuarioTelefoneDao;
	
	@Autowired
	private TelefoneDao telefoneDao;
	
	public UsuarioTelefoneDao getUsuarioTelefoneDao(){
		return usuarioTelefoneDao;
	}
	public void setUsuarioTelefoneDao(UsuarioTelefoneDao telefoneDao){
		this.usuarioTelefoneDao = telefoneDao;
	}
	
	public TelefoneDao getTelefoneDao(){
		return telefoneDao;
	}
	public void setTelefoneDao(TelefoneDao telefoneDao){
		this.telefoneDao = telefoneDao;
	}
	
	public CidadeDao getCidadeDao() {
		return cidadeDao;
	}

	public void setCidadeDao(CidadeDao cidadeDao) {
		this.cidadeDao = cidadeDao;
	}
	
	
	private SwingWorker<Object, String> cidadeWorker;

	private List<UsuarioTelefone> listaTelefones;

	private TableModelGenerico<UsuarioTelefone> modelGenerico;

	private JScrollPane painelTabela;

	private JTable tabela;

	private SwingWorker<Object, String> estadoWorker;

	public EstadoDao getEstadoDao() {
		return estadoDao;
	}

	public void setEstadoDao(EstadoDao estadoDao) {
		this.estadoDao = estadoDao;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public CadastroUsuario() {
		painel = this;
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Nome:");
		adicionarComponente(lblNewLabel, 2);
		
		txtNome = new JTextField();
		adicionarComponente(txtNome, 2);
		
		JLabel lblLogin = new JLabel("Login:");
		adicionarComponente(lblLogin, 4);
		
		txtLogin = new JTextField();
		adicionarComponente(txtLogin, 4);
		
		JLabel lblSenha = new JLabel("Senha:");
		adicionarComponente(lblSenha, 6);
		
		txtSenha = new JPasswordField();
		adicionarComponente(txtSenha, 6);
		
		JLabel lblTipo = new JLabel("Tipo:");
		adicionarComponente(lblTipo,8);
		
		cbbTipo = new JComboBox<TipoUsuario>();
		cbbTipo.addItem(TipoUsuario.ADMINISTRADOR);
		cbbTipo.addItem(TipoUsuario.BALCAO);
		adicionarComponente(cbbTipo,8);
		
		JLabel label = new JLabel("CPF:");
		adicionarComponente(label, 10);
		
		txtCPF = new JTextField();
		adicionarComponente(txtCPF,10);
		
		
		lblEndereo = new JLabel("Rua:");
		adicionarComponente(lblEndereo, 12);
		
		txtEndereco = new JTextField();
		adicionarComponente(txtEndereco, 12);
		
		lblNmero = new JLabel("Número:");
		adicionarComponente(lblNmero, 14);
		
		txtNumero = new JTextField();
		adicionarComponente(txtNumero, 14);
		
		lblBairro = new JLabel("Bairro:");
		adicionarComponente(lblBairro,16 );
		
		txtBairro = new JTextField();
		adicionarComponente(txtBairro, 16);
		
		lblEstado = new JLabel("Estado:");
		adicionarComponente(lblEstado, 18);
		
		cbbEstado = new JComboBox<Estado>();
		adicionarComponente(cbbEstado, 18);
		
		lblCidade = new JLabel("Cidade:");
		adicionarComponente(lblCidade, 20);
		
		cbbCidade = new JComboBox<Cidade>();
		adicionarComponente(cbbCidade, 20);
		
		painelTabela = new JScrollPane();
		tabela = new JTable();
		if(usuario != null && usuario.getListaTelefone() != null ){
			listaTelefones = usuario.getListaTelefone();
		}else{
			listaTelefones = new ArrayList<UsuarioTelefone>();
		}
		modelGenerico = new TableModelGenerico(listaTelefones, UsuarioTelefone.class);
		JButton btnAdicionarTelefone = new JButton("Adicionar");
		JLabel telefones = new JLabel("Telefone:");
		adicionarComponente(telefones, 21);
		telefones.setHorizontalAlignment(JLabel.CENTER);
		btnAdicionarTelefone.setBounds(X+ ALTURA_COMPONENTES , Y +(20 *ALTURA_COMPONENTES) , LARGURA_COMPONENTES /2, ALTURA_COMPONENTES);
		add(btnAdicionarTelefone);
		tabela.setModel(modelGenerico);
		tabela.setEnabled(true);
		painelTabela.setViewportView(tabela);
		painelTabela.setEnabled(true);
		painelTabela.setBounds(X+ ALTURA_COMPONENTES , Y +(22 *ALTURA_COMPONENTES) , LARGURA_COMPONENTES*2, ALTURA_COMPONENTES+ALTURA_COMPONENTES*2);
		add(painelTabela);
		painelTabela.setVisible(true);

		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 26);
		
		btnSalvar = new JButton("Salvar");
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(LARGURA_COMPONENTES/2);
		splitPane.setEnabled(false);
		
		ComponenteControlado<CadastroUsuario> controleAcesso = new ComponenteControlado<CadastroUsuario>(this); 
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
				cbbCidade.removeAllItems();
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
		
		btnAdicionarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarTelefone();
			}
		});
	}
	
	private void cancelar() {
		TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		telaPrincipal.cancelar();
	}
	
	private void salvar() {
		try{
			if(usuario == null ){
				usuario = new  Usuario();
			}
			
			usuario.setNome(txtNome.getText() );
			usuario.setLogin(txtLogin.getText());
			String password = String.valueOf( txtSenha.getPassword() );
			if(StringUtils.isNotBlank(password)){
				usuario.setSenha( SecurityUtils.encrypt( password ) );
			}
			usuario.setCpf( txtCPF.getText());
			TipoUsuario tipoUsuario = (TipoUsuario)cbbTipo.getSelectedItem();
			usuario.setTipo(tipoUsuario);
			Cidade cidade = (Cidade) cbbCidade.getSelectedItem();
			usuario.setEndereco(new Endereco(txtEndereco.getText(), txtNumero.getText(), txtBairro.getText(), cidade ));
			validacaoCampos();
			usuarioDao.salvar(usuario);
			if(!listaTelefones.isEmpty()){
				if(usuario.getId() != null){
					usuarioTelefoneDao.deletarPorIdUsuario(usuario.getId());
				}
				for (UsuarioTelefone usuarioTelefone : listaTelefones) {
					usuarioTelefone.setId(null);
					usuarioTelefone.setUsuario(usuario);
					usuarioTelefone.getTelefone().setId(null);
					telefoneDao.salvar( usuarioTelefone.getTelefone() );
					usuarioTelefoneDao.salvar(usuarioTelefone);
				}
			}
			JOptionPane.showMessageDialog(null, CLASS_NAME+" cadastrado com sucesso.");
			usuario = null;
			limparComponentes();
			cancelar();
		}
		catch(ValidationException e){
			LOGGER.error(e);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Falha ao salvar "+CLASS_NAME+".");
			LOGGER.error(ex);
		}
	}

	private void limparComponentes() {
		final JTextField[] componentes = {txtNome,txtLogin,txtCPF,txtEndereco,txtNumero,txtBairro};
		for (JTextField jComponent : componentes) {
			jComponent.setText(VAZIO);
		}
		listaTelefones.clear();
		atualizaDesenhoTabela();
		txtSenha.setText(VAZIO);
	}
	
	private void validacaoCampos() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator myValidate = factory.getValidator();
		Set<ConstraintViolation<Usuario>> validacao = myValidate.validate(usuario);
		StringBuilder mensagem = new StringBuilder();
		boolean erro = false;
		for (ConstraintViolation<Usuario> constraintViolation : validacao) {
			mensagem.append(constraintViolation.getMessage() ).append(MessagemUtil.BR);
			erro = true;
		}
		if(erro){
			JOptionPane.showMessageDialog(null, mensagem.toString());
			throw new ValidationException( mensagem.toString() );
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarEstado(){
		estadoWorker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				cbbEstado.removeAllItems();
				for (Estado estado : estadoDao.buscarTodos()) {
					cbbEstado.addItem(estado);
					if(usuario != null && estado.equals(usuario.getEndereco().getCidade().getEstado() ) ){
						cbbEstado.setSelectedItem(estado);
					}
				}
				return null;
			}
		};
		estadoWorker.execute();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarCidade(){
		cidadeWorker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				Estado item = (Estado) cbbEstado.getSelectedItem();
				cbbCidade.removeAllItems();
				for (Cidade cidade : cidadeDao.buscaPorEstado(item.getId() ) ) {
					cbbCidade.addItem(cidade);
					if(usuario != null && cidade.equals(usuario.getEndereco().getCidade() ) ){
						cbbCidade.setSelectedItem(cidade);
					}
				}
				return null;
			}
		};
		cidadeWorker.execute();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void load(Integer id) {
		if(id != null){
			usuario = usuarioDao.buscarPorId(id);
		}
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			txtNome.setText( usuario.getNome() );
			txtLogin.setText( usuario.getLogin() );
			txtCPF.setText( usuario.getCpf() );
			txtEndereco.setText( usuario.getEndereco().getRua() );
			txtNumero.setText( usuario.getEndereco().getNumero() );
			txtBairro.setText( usuario.getEndereco().getBairro() );
			
			SwingWorker<Object, String> tipoWorker = new SwingWorker() {
	
				@Override
				protected Object doInBackground() throws Exception {
					carregarTipoWorker();
					return null;
				}
			};
			tipoWorker.execute();
			
			listaTelefones = usuario.getListaTelefone();
			atualizaDesenhoTabela();
		}else{
			usuario = new Usuario();
		}
	}

	public void init(Usuario usuario) {
		ComponenteControlado<CadastroUsuario> controleAcesso = new ComponenteControlado<CadastroUsuario>(this);
		controleAcesso.pronto(usuario.getTipo());
		carregarEstado();
		carregarCidade();
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		if(componente instanceof JLabel){
			componente.setBounds(X  , Y +(valor *ALTURA_COMPONENTES) , LARGURA_COMPONENTES, ALTURA_COMPONENTES);
			JLabel jLabel = (JLabel)componente;
			jLabel.setHorizontalAlignment(JLabel.RIGHT);
		}else{
			componente.setBounds(X  +LARGURA_COMPONENTES , Y+(valor *ALTURA_COMPONENTES), LARGURA_COMPONENTES, ALTURA_COMPONENTES);
		}
		if (componente instanceof JTextField) {
			JTextField textField = (JTextField) componente;
			textField.setColumns(10);
		}else if(componente instanceof JPasswordField){
			JPasswordField jPasswordField = (JPasswordField)componente;
			jPasswordField.setEchoChar('#');
		}
		this.add(componente);
		componente.setVisible(true);
	}

	private void adicionarTelefone() {
		final Telefone telefone = new Telefone();
		abrirModalTelefone(telefone);
		if(StringUtils.isNotBlank( telefone.getTelefone() ) ){
			UsuarioTelefone usuarioTelefone = new UsuarioTelefone();
			usuarioTelefone.setTelefone(telefone);
			usuarioTelefone.setUsuario(usuario);
			listaTelefones.add(usuarioTelefone);
			atualizaDesenhoTabela();
		}
	}

	private void abrirModalTelefone(final Telefone telefone) {
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					CadastroTelefone dialog = new CadastroTelefone(telefone);
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
	
	private void eventoCliqueTabela(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TableModelGenerico<UsuarioTelefone> model = (TableModelGenerico<UsuarioTelefone>) tabela.getModel();
			boolean atualizaTabela = false;
			int linha = tabela.getSelectedRow();
			int coluna = tabela.getSelectedColumn();
			UsuarioTelefone usuarioTelefone = listaTelefones.get(linha);
			
			if(coluna == model.getCountadorColunas() ){
				abrirModalTelefone(usuarioTelefone.getTelefone());
				atualizaTabela = true;
			} else if(coluna == model.getCountadorColunas() +1 ){
				int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: "+usuarioTelefone.getTelefone() + " ?", "Excluir?", JOptionPane.YES_NO_OPTION);
				if(excluir == JOptionPane.YES_OPTION){
					int contador = 0;
					while(true){
						if(listaTelefones.get(contador).equals(usuarioTelefone) ){
							UsuarioTelefone usuTelefone = listaTelefones.get(contador);
							listaTelefones.remove( usuTelefone );
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

	private void atualizaDesenhoTabela() {
		if(listaTelefones.size() > 0){
			new ButtonColumn(tabela, null, modelGenerico.getCountadorColunas() );
			new ButtonColumn(tabela, null, modelGenerico.getCountadorColunas() + 1 );
		}
		if(!modelGenerico.getLista().equals(listaTelefones)){
			modelGenerico.getLista().clear();
			for (UsuarioTelefone usuarioTelefone : listaTelefones) {
				modelGenerico.getLista().add(usuarioTelefone);
			}
			listaTelefones = (List<UsuarioTelefone>) modelGenerico.getLista();
			
		}
		modelGenerico.fireTableDataChanged();
		tabela.updateUI();
	}
	private void carregarTipoWorker() {
		for (int i = 0; i < cbbTipo.getItemCount(); i++) {
			if(cbbTipo.getItemAt(i).equals( usuario.getTipo() )){
				cbbTipo.setSelectedIndex(i);
				break;
			}
		}
	}
}
