package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Produto;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JBusca;
import br.org.fgp.view.core.JCabecalhoLabel;

public class CadastroEntradaProduto extends JPanel  implements Inicializavel{
	
	private static final long serialVersionUID = -7159300943489660623L;
	
	private EntradaProduto entradaProduto;
	
	@Autowired
	private EntradaProdutoDao entradaProdutoDao;
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private FornecedorDao fornecedorDao;

	private JSplitPane splitPane;

	private JButton btnSalvar;

	private JButton btnCancelar;
	
	private JPanel painel;
	
	private JBusca<Produto, Integer> txtProduto;
	private JFormattedTextField txtQuantidade;
	private JFormattedTextField txtPrecoCusto;
	private JBusca<Fornecedor, Integer> txtFornecedor;

	public CadastroEntradaProduto() {
		
		setLayout(null);
		adicionarComponente(new JCabecalhoLabel("Entrada de Produtos"),0);
		
		adicionarComponente(new JLabel("Produto:"), 2);
		txtProduto = new JBusca<Produto, Integer>();
		adicionarComponente(txtProduto, 2);
		
		adicionarComponente(new JLabel("Quantidade:"), 4);
		txtQuantidade = new JFormattedTextField(TelasUtils.getFormatadorInteiro());
		adicionarComponente(txtQuantidade, 4);
		
		adicionarComponente(new JLabel("Preço Unitário:"), 6);
		txtPrecoCusto = new JFormattedTextField(TelasUtils.getFormatadorDecimal());
		adicionarComponente(txtPrecoCusto, 6);
		
		adicionarComponente(new JLabel("Fornecedor:"), 8);
		txtFornecedor = new JBusca<Fornecedor, Integer>();
		adicionarComponente(txtFornecedor, 8);
		
		painel = this;
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 28);
		
		btnSalvar = new JButton("Salvar");
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnSalvar);
		}
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(TelasUtils.DEFAULT_LARGURA_COMPONENTE/2);
		splitPane.setEnabled(false);
		
		ComponenteControlado<CadastroEntradaProduto> controleAcesso = new ComponenteControlado<CadastroEntradaProduto>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		
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
	}

	@Override
	public void load(Integer id) {
		if(id != null){
			entradaProduto = entradaProdutoDao.buscarPorId(id);
		}
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			txtProduto.setText( entradaProduto.getProduto().getId().toString() );
			txtQuantidade.setText( entradaProduto.getQuantidade().toString() );
			txtPrecoCusto.setText( entradaProduto.getPrecoCusto().toString() );
			txtFornecedor.setText( entradaProduto.getFornecedor().getId().toString() );
		}else{
			entradaProduto = new EntradaProduto();
		}
	}

	private void init(Usuario usuarioLogado) {
		ComponenteControlado<CadastroEntradaProduto> controleAcesso = new ComponenteControlado<CadastroEntradaProduto>(this);
		controleAcesso.pronto(usuarioLogado.getTipo());
		txtProduto.setDaoGenerico(produtoDao);
		txtFornecedor.setDaoGenerico(fornecedorDao);
		repaint();
		revalidate();
	}
	
	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public FornecedorDao getFornecedorDao() {
		return fornecedorDao;
	}

	public void setFornecedorDao(FornecedorDao fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}

	public EntradaProdutoDao getEntradaProdutoDao() {
		return entradaProdutoDao;
	}

	public void setEntradaProdutoDao(EntradaProdutoDao entradaProdutoDao) {
		this.entradaProdutoDao = entradaProdutoDao;
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}
	
	private void cancelar() {
		TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		telaPrincipal.cancelar();
	}
	
	private void limparComponentes() {
		//TODO
	}
	
	private void salvar() {
		//TODO
	}

}
