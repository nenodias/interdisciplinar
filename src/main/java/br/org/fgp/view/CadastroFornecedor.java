package br.org.fgp.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.CidadeDao;
import br.org.fgp.dao.EstadoDao;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.model.Cidade;
import br.org.fgp.model.Estado;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;

@Component
public class CadastroFornecedor extends JPanel implements Inicializavel {
	
	private static final long serialVersionUID = 2174153481281619633L;
	
	private JTable tbContato;
	private JTextField txtNomeFantasia;
	private JTextField txtRazaoSocial;
	private JTextField txtInscricaoEstadual;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	
	private Fornecedor fornecedor;
	
	@Autowired
	private FornecedorDao fornecedorDao;
	
	public FornecedorDao getFornecedorDao() {
		return fornecedorDao;
	}

	public void setFornecedorDao(FornecedorDao fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}

	@Autowired
	private CidadeDao cidadeDao;
	
	public CidadeDao getCidadeDao() {
		return cidadeDao;
	}

	public void setCidadeDao(CidadeDao cidadeDao) {
		this.cidadeDao = cidadeDao;
	}
	
	@Autowired
	private EstadoDao estadoDao;

	public EstadoDao getEstadoDao() {
		return estadoDao;
	}

	public void setEstadoDao(EstadoDao estadoDao) {
		this.estadoDao = estadoDao;
	}

	private JComboBox<Estado> cbbEstado;
	private JComboBox<Cidade> cbbCidade;
	
	/**
	 * Create the panel.
	 */
	public CadastroFornecedor() {
		tbContato = new JTable();
		
		JLabel lblNomeFantasia = new JLabel("Nome Fantasia:");
		
		JLabel lblRazoSocial = new JLabel("Razão Social:");
		
		JLabel lblInscrioEstadual = new JLabel("Inscrição Estadual:");
		
		JLabel lblEndereo = new JLabel("Endereço:");
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		
		JLabel lblCidade = new JLabel("Cidade:");
		
		JLabel lblEstado = new JLabel("Estado:");
		
		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setName("Nome Fantasia");
		txtNomeFantasia.setColumns(10);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setText("");
		txtRazaoSocial.setName("Razão Social");
		txtRazaoSocial.setColumns(10);
		
		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setName("Inscrição Estadual");
		txtInscricaoEstadual.setColumns(10);
		
		txtCnpj = new JTextField();
		txtCnpj.setText("");
		txtCnpj.setName("CNPJ");
		txtCnpj.setColumns(10);
		
		cbbEstado = new JComboBox<Estado>();
	
		
		
		cbbCidade = new JComboBox<Cidade>();
		
		txtEndereco = new JTextField();
		txtEndereco.setName("Endereço");
		txtEndereco.setColumns(10);
		
		JLabel lblNmero = new JLabel("Número:");
		
		txtNumero = new JTextField();
		txtNumero.setName("Número");
		txtNumero.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCnpj)
										.addComponent(lblInscrioEstadual)
										.addComponent(lblRazoSocial)
										.addComponent(lblNomeFantasia)
										.addComponent(lblCidade)
										.addComponent(lblEndereo)
										.addComponent(lblEstado)
										.addComponent(lblNmero))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(cbbCidade, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtInscricaoEstadual)
											.addComponent(txtNomeFantasia, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
											.addComponent(txtRazaoSocial)
											.addComponent(txtCnpj)
											.addComponent(txtEndereco)
											.addComponent(cbbEstado, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(145)
									.addComponent(lblFornecedor))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(tbContato, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(172)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnSalvar)
									.addGap(42)
									.addComponent(btnCancelar))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(62)
									.addComponent(lblMsg)))))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFornecedor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeFantasia)
						.addComponent(txtNomeFantasia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRazoSocial)
						.addComponent(txtRazaoSocial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInscrioEstadual)
						.addComponent(txtInscricaoEstadual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(txtCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(cbbEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(cbbCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndereo)
						.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmero)
						.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addComponent(tbContato, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(18)
					.addComponent(lblMsg)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
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
		try{
			List<JTextField> txts = Arrays.asList(txtNomeFantasia,txtRazaoSocial,txtInscricaoEstadual,txtCnpj,txtEndereco,txtNumero);
			boolean erro = false;
			StringBuilder nomes = new StringBuilder(); 
			for (JTextField txt : txts) {
				if(StringUtils.isBlank(txt.getText()) || StringUtils.isEmpty(txt.getText())){
					nomes.append(txt.getName()).append(", ");
					erro = true;
				}
			}
			if(erro){
				String mensagem = nomes.toString().substring(0,nomes.length()-2);
				JOptionPane.showMessageDialog(null, mensagem + " está vazio.");
			}
			else{
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setCnpj(txtCnpj.getText());
				//TODO Corrigir o cadastro de Endereco e quebrar em mais campos
//				fornecedor.setEnderecoComercial(txtEndereco.getText());
				fornecedor.setInscricaoEstadual(txtInscricaoEstadual
						.getText());
				fornecedor.setNomeFantasia(txtNomeFantasia.getText());
				fornecedor.setRazaoSocial(txtRazaoSocial.getText());
				Cidade item = (Cidade) cbbCidade.getSelectedItem();
				//TODO  A Cidade vai ser setada no Objeto referente ao endereço
//				fornecedor.setCidade(item);
				fornecedorDao.salvar(fornecedor);
				JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
				
				for (JTextField txt : txts) {
						txt.setText("");
					}
			}
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Falha ao salvar fornecedor.");
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
		fornecedorDao.buscarPorId(id);
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
}
