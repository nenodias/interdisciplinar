package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.validation.ValidationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.dao.MarcaDao;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.Marca;
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
public class CadastroProduto extends JPanel implements Inicializavel {

	private static final long serialVersionUID = 234546250563736381L;
	
	private static final String CLASS_NAME = "Produto";

	private static final Logger LOGGER = Logger.getLogger(CadastroProduto.class);
	
	@Autowired
	private MarcaDao marcaDao;
	
	@Autowired
	private CategoriaDao categoriaDao;

	private Produto produto;

	@Autowired
	private ProdutoDao produtoDao;
	
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JFormattedTextField txtEstoqueMinimo;
	private JFormattedTextField txtEstoqueMaximo;
	private JFormattedTextField txtPrecoUnitario;
	private JBusca<Categoria, Integer> txtCategoria;
	private JBusca<Marca, Integer> txtMarca;
	
	private JSplitPane splitPane;

	private JButton btnSalvar;

	private JButton btnCancelar;
	
	public CadastroProduto() {
		setLayout(null);
		
		adicionarComponente(new JCabecalhoLabel(CLASS_NAME), 0);
		
		adicionarComponente(new JLabel("Nome"), 2);
		txtNome = new JTextField();
		adicionarComponente(txtNome, 2);
		
		adicionarComponente(new JLabel("Descricao"), 4);
		txtDescricao = new JTextField();
		adicionarComponente(txtDescricao, 4);
		
		adicionarComponente(new JLabel("Preço Venda"), 6);
		txtPrecoUnitario = new JMoneyField();
		adicionarComponente(txtPrecoUnitario, 6);
		
		adicionarComponente(new JLabel("Categoria"), 8);
		txtCategoria = new JBusca<Categoria, Integer>();
		adicionarComponente(txtCategoria, 8);
		
		adicionarComponente(new JLabel("Marca"), 10);
		txtMarca = new JBusca<Marca, Integer>();
		adicionarComponente(txtMarca, 10);
		
		adicionarComponente(new JLabel("Estoque Mínimo:"), 12);
		txtEstoqueMinimo = new JFormattedTextField(TelasUtils.getFormatadorInteiro());
		adicionarComponente(txtEstoqueMinimo, 12);
		
		adicionarComponente(new JLabel("Estoque Máximo:"), 14);
		txtEstoqueMaximo = new JFormattedTextField(TelasUtils.getFormatadorInteiro());
		adicionarComponente(txtEstoqueMaximo, 14);
		
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 18);
		
		btnSalvar = new JButtonSalvar();
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnSalvar);
		}
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButtonCancelar();
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(TelasUtils.DEFAULT_LARGURA_COMPONENTE/2);
		splitPane.setEnabled(false);
		
		ComponenteControlado<CadastroProduto> componenteControlado = new ComponenteControlado<CadastroProduto>(this);
		componenteControlado.pronto(TipoUsuario.ADMINISTRADOR);
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

	private void salvar() {
		String mensagemSave = " atualizado ";
		String mensagemFail = " atualizar ";
		try{
			if(produto == null ){
				produto = new  Produto();
				mensagemSave = " salvo ";
				mensagemFail = " salvar ";
			}
			
			produto.setNome( txtNome.getText() );
			produto.setDescricao( txtDescricao.getText() );
			if(StringUtils.isNotBlank(txtPrecoUnitario.getText() ) ){
				produto.setPrecoUnitario( new BigDecimal( txtPrecoUnitario.getText() ) );
			}
			if(StringUtils.isNotBlank(txtEstoqueMinimo.getText() ) ){
				produto.setEstoqueMinimo( Integer.parseInt( txtEstoqueMinimo.getText() ) );
			}
			if(StringUtils.isNotBlank(txtEstoqueMaximo.getText() ) ){
				produto.setEstoqueMaximo( Integer.parseInt( txtEstoqueMaximo.getText() ) );
			}
			if(categoriaDao != null && StringUtils.isNotBlank(txtCategoria.getText() ) ){
				try{
					int codigoCategoria = Integer.parseInt( txtCategoria.getText() );
					Categoria categoria = categoriaDao.buscarPorId(codigoCategoria);
					produto.setCategoria(categoria);
				}catch (Exception e){
					
				}
			}
			if(marcaDao != null && StringUtils.isNotBlank(txtMarca.getText() ) ){
				try{
					int codigoMarca = Integer.parseInt( txtMarca.getText() );
					Marca marca = marcaDao.buscarPorId(codigoMarca);
					produto.setMarca(marca);
				}catch(Exception e){
					
				}
			}
			
			Validador<Produto> validador = new Validador<Produto>();
			validador.validacaoCampos(produto);
			produtoDao.salvar(produto);
			JOptionPane.showMessageDialog(null, CLASS_NAME.concat(mensagemSave).concat("com sucesso.") );
			produto = null;
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

	private void limparComponentes() {
		final JTextField[] componentes = {txtNome,txtDescricao,txtPrecoUnitario};
		for (JTextField jComponent : componentes) {
			jComponent.setText(StringUtils.EMPTY);
		}
		txtCategoria.limpar();;
		txtMarca.limpar();
	}

	@Override
	public void load(Integer id) {
		if(id != null){
			produto = produtoDao.buscarPorId(id);
		}
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			txtNome.setText( produto.getNome() );
			txtDescricao.setText( produto.getDescricao() );
			txtPrecoUnitario.setText( produto.getPrecoUnitario().toString() );
			txtCategoria.setText( produto.getCategoria().getId().toString() );
			txtMarca.setText( produto.getMarca().getId().toString() );
			txtEstoqueMinimo.setText( produto.getEstoqueMinimo().toString() );
			txtEstoqueMaximo.setText( produto.getEstoqueMaximo().toString() );
		}
	}
	
	public void init(Usuario getUsuarioLogado){
		ComponenteControlado<CadastroProduto> controleAcesso = new ComponenteControlado<CadastroProduto>(this);
		controleAcesso.pronto(TelasUtils.getUsuarioLogado().getTipo() );
		txtCategoria.setDaoGenerico(categoriaDao);
		txtMarca.setDaoGenerico(marcaDao);
	}
	
	private void cancelar() {
		TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		telaPrincipal.cancelar();
	}

	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}

	public MarcaDao getMarcaDao() {
		return marcaDao;
	}

	public void setMarcaDao(MarcaDao marcaDao) {
		this.marcaDao = marcaDao;
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
	
	
}
