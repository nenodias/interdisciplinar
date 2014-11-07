package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.validation.ValidationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonSalvar;
import br.org.fgp.view.core.JCabecalhoLabel;
import br.org.fgp.view.core.JMoneyField;
import br.org.fgp.view.core.Validador;

@Controller
public class CadastroEntradaProduto extends JPanel  implements Inicializavel{
	
	private static final long serialVersionUID = -7159300943489660623L;

	private static final String CLASS_NAME = "Entrada Produto";

	private static final Logger LOGGER = Logger.getLogger(CadastroEntradaProduto.class);
	
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
		txtPrecoCusto = new JMoneyField();
		adicionarComponente(txtPrecoCusto, 6);
		
		adicionarComponente(new JLabel("Fornecedor:"), 8);
		txtFornecedor = new JBusca<Fornecedor, Integer>();
		adicionarComponente(txtFornecedor, 8);
		
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
		limparComponentes();
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
		txtProduto.setText( StringUtils.EMPTY );
		txtQuantidade.setText( StringUtils.EMPTY );
		txtPrecoCusto.setText( StringUtils.EMPTY );
		txtFornecedor.setText( StringUtils.EMPTY );
	}
	
	private void salvar() {
		String mensagemSave = " atualizada ";
		String mensagemFail = " atualizar ";
		try{
			if(entradaProduto == null ){
				entradaProduto = new  EntradaProduto();
				if(entradaProduto.getId() != null){
					mensagemSave = " salva ";
					mensagemFail = " salvar ";
				}
			}
			if( StringUtils.isNotBlank( txtFornecedor.getText() ) ){
				try{
					Integer idFornecedor = Integer.parseInt( txtFornecedor.getText() );
					Fornecedor fornecedor = fornecedorDao.buscarPorId(idFornecedor);
					entradaProduto.setFornecedor(fornecedor);
				}catch(Exception e){
					LOGGER.error(e);
				}
			}
			if( StringUtils.isNotBlank( txtPrecoCusto.getText() ) ){
				try{
					BigDecimal preco = new BigDecimal(txtPrecoCusto.getText());
					entradaProduto.setPrecoCusto(preco);
				}catch(Exception e){
					LOGGER.error(e);
				}
			}
			if( StringUtils.isNotBlank( txtProduto.getText() ) ){
				try{
					Integer idProduto = Integer.parseInt(txtProduto.getText());
					Produto produto = produtoDao.buscarPorId(idProduto);
					entradaProduto.setProduto(produto);
				}catch(Exception e){
					LOGGER.error(e);
				}
			}
			if( StringUtils.isNotBlank( txtQuantidade.getText() ) ){
				try{
					Integer quantidade = Integer.parseInt(txtQuantidade.getText());
					entradaProduto.setQuantidade(quantidade);
				}catch(Exception e){
					LOGGER.error(e);
				}
			}
			
			Validador<EntradaProduto> validador = new Validador<EntradaProduto>();
			validador.validacaoCampos(entradaProduto);
			
			entradaProduto.setUsuario(TelasUtils.getUsuarioLogado());
			if(entradaProduto.getId() == null){
				entradaProduto.setData(new Date());
			}
			entradaProdutoDao.salvarRegra(entradaProduto);
			JOptionPane.showMessageDialog(null, CLASS_NAME.concat(mensagemSave).concat("com sucesso.") );
			entradaProduto = null;
			limparComponentes();
			cancelar();
		}
		catch(ValidationException e){
			LOGGER.error(e);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Falha ao ".concat(mensagemFail).concat(" ").concat(CLASS_NAME).concat(".") );
			LOGGER.error(ex);
		}
	}

}
