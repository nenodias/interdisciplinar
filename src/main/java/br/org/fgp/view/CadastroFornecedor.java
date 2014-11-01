package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.validation.ValidationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.CidadeDao;
import br.org.fgp.dao.EstadoDao;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.model.Cidade;
import br.org.fgp.model.Endereco;
import br.org.fgp.model.Estado;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JCabecalhoLabel;
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
	
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JSplitPane splitPane;

	private JTextField txtCnpj;
	private JTextField txtInscricaoEstadual;
	private JTextField txtNomeFantasia;
	private JTextField txtRazaoSocial;

	private JTextField txtEndereco;

	private JTextField txtNumero;

	private JTextField txtBairro;
	
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
		
		btnSalvar = new JButton("Salvar");
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(TelasUtils.DEFAULT_LARGURA_COMPONENTE/2);
		splitPane.setEnabled(false);
		
		ComponenteControlado<CadastroFornecedor> controleAcesso = new ComponenteControlado<CadastroFornecedor>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		
		carregarEstado();
		carregarCidade();
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		
		cbbEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cbbCidade.removeAllItems();
				carregarCidade();
			}
		});
	}

	private void salvar(){
		String mensagemSave = " atualizado ";
		try{
			if(fornecedor == null ){
				fornecedor = new  Fornecedor();
				if(fornecedor.getId() != null){
					mensagemSave = " salvo ";
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
			JOptionPane.showMessageDialog(null, CLASS_NAME.concat(mensagemSave).concat("com sucesso.") );
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
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarEstado(){
		SwingWorker<Object, String> swingWorker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				for (Estado estado : estadoDao.buscarTodos()) {
					cbbEstado.addItem(estado);
					if(fornecedor != null && estado.equals(fornecedor.getEnderecoComercial().getCidade().getEstado() ) ){
						cbbEstado.setSelectedItem(estado);
					}
				}
				return null;
			}
		};
		swingWorker.execute();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarCidade(){
		SwingWorker<Object, String> swingWorker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				Estado item = (Estado) cbbEstado.getSelectedItem();
				for (Cidade cidade : cidadeDao.buscaPorEstado(item.getId() ) ) {
					cbbCidade.addItem(cidade);
					if(fornecedor != null && cidade.equals(fornecedor.getEnderecoComercial().getCidade() ) ){
						cbbCidade.setSelectedItem(cidade);
					}
				}
				return null;
			}
		};
		swingWorker.execute();
	}

	@Override
	public void load(Integer id) {
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			fornecedor = fornecedorDao.buscarPorId(id);
		}
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			txtNomeFantasia.setText( fornecedor.getNomeFantasia() );
			txtInscricaoEstadual.setText( fornecedor.getInscricaoEstadual() );
			txtRazaoSocial.setText( fornecedor.getRazaoSocial() );
			txtCnpj.setText( fornecedor.getCnpj() );
			txtEndereco.setText( fornecedor.getEnderecoComercial().getRua() );
			txtNumero.setText( fornecedor.getEnderecoComercial().getNumero() );
			txtBairro.setText( fornecedor.getEnderecoComercial().getBairro() );
			
		}else{
			fornecedor = new Fornecedor();
		}
	}

	private void init(Usuario usuarioLogado) {
		ComponenteControlado<CadastroFornecedor> controleAcesso = new ComponenteControlado<CadastroFornecedor>(this);
		controleAcesso.pronto(usuarioLogado.getTipo());
		carregarEstado();
		carregarCidade();
	}
	
	private void cancelar() {
		TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		telaPrincipal.cancelar();
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}
}
