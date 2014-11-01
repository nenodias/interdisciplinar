package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

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
import br.org.fgp.view.core.JCabecalhoLabel;

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
	private JTextField txtPrecoUnitario;
	private JBusca<Categoria, Integer> txtCategoria;
	private JBusca<Marca, Integer> txtMarca;
	
	private JPanel painel;

	private JSplitPane splitPane;

	private JButton btnSalvar;

	private JButton btnCancelar;
	
	public CadastroProduto() {
		painel = this;
		setLayout(null);
		
		adicionarComponente(new JCabecalhoLabel(CLASS_NAME), 0);
		
		adicionarComponente(new JLabel("Nome"), 2);
		txtNome = new JTextField();
		adicionarComponente(txtNome, 2);
		
		adicionarComponente(new JLabel("Descricao"), 4);
		txtDescricao = new JTextField();
		adicionarComponente(txtDescricao, 4);
		
		adicionarComponente(new JLabel("Preço Venda"), 6);
		txtPrecoUnitario = new JTextField();
		adicionarComponente(txtPrecoUnitario, 6);
		
		adicionarComponente(new JLabel("Categoria"), 8);
		txtCategoria = new JBusca<Categoria, Integer>();
		adicionarComponente(txtCategoria, 8);
		
		adicionarComponente(new JLabel("Marca"), 10);
		txtMarca = new JBusca<Marca, Integer>();
		adicionarComponente(txtMarca, 10);
		
		
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 14);
		
		btnSalvar = new JButton("Salvar");
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
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
