package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import br.org.fgp.annotations.Permissao;
import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.dao.MarcaDao;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.dao.SetorDao;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Produto;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.Venda;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JDialogBusca;

@Controller
public class TelaPrincipal extends JFrame implements Inicializavel {

	private static final String IMAGENS_RELATORIO_PNG = "imagens/relatorio.png";

	private static final String IMAGENS_SETOR_PNG = "imagens/setor.png";

	private static final String IMAGENS_MARCA_PNG = "imagens/marca.png";

	private static final String IMAGENS_CATEGORIA_PNG = "imagens/categoria.png";

	private static final String IMAGENS_USUARIO_PNG = "imagens/usuario.png";

	private static final String IMAGENS_VENDA_PNG = "imagens/venda.png";

	private static final String IMAGENS_FORNECEDOR_PNG = "imagens/fornecedor.png";

	private static final String IMAGENS_ESTOQUE_PNG = "imagens/entradaprodutos.png";

	private static final String IMAGENS_PRODUTO_PNG = "imagens/produto.png";

	private static final ClassLoader LOADER = TelaPrincipal.class.getClassLoader();
	
	private static final Logger LOGGER = Logger.getLogger(TelaPrincipal.class);
	
	private static final long serialVersionUID = -7747890711976699854L;

	private JPanel contentPane;
	private JFrame frmInterdisciplinar;

	private JMenu mntmEntradaProduto;

	private JMenu mntmProdutos;

	@Permissao
	private JMenu mntmFornecedor;

	private JMenu mntmMarca;

	private JMenu mntmCategorias;

	@Permissao
	private JMenu mntmUsurio;

	@Permissao
	private JMenu mntmSetor;

	private JMenu mntmVenda;

	@Permissao
	private JMenu mnRelatorio;

	public TelaPrincipal() {
		frmInterdisciplinar = new JFrame();
		frmInterdisciplinar.setTitle("ERP - Autoposto Pederbras");
		frmInterdisciplinar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInterdisciplinar.setBounds(100, 100, 800, 600);
		frmInterdisciplinar.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		frmInterdisciplinar.setJMenuBar(menuBar);

		mntmEntradaProduto = new JMenu("Compras");
		menuBar.add(mntmEntradaProduto);

		mntmProdutos = new JMenu("Produtos");
		menuBar.add(mntmProdutos);

		mntmFornecedor = new JMenu("Fornecedores");
		menuBar.add(mntmFornecedor);

		mntmMarca = new JMenu("Marcas");
		menuBar.add(mntmMarca);

		mntmCategorias = new JMenu("Categorias");
		menuBar.add(mntmCategorias);

		mntmUsurio = new JMenu("Usu\u00E1rios");
		menuBar.add(mntmUsurio);

		mntmSetor = new JMenu("Setor");
		menuBar.add(mntmSetor);

		mntmVenda = new JMenu("Realizar Venda");
		menuBar.add(mntmVenda);
		
		mnRelatorio = new JMenu("Relatório");
		menuBar.add(mnRelatorio);
		
		frmInterdisciplinar.getContentPane().setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGap(0, 732, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGap(0, 484, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);

		mntmProdutos.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_PRODUTO_PNG)));
		mntmProdutos.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaProduto();
			}
		});
		mntmEntradaProduto.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_ESTOQUE_PNG)));
		mntmEntradaProduto.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaEntradaProduto();
			}
		});
		mntmFornecedor.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_FORNECEDOR_PNG)));
		mntmFornecedor.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaFornecedor();
			}
		});
		mntmVenda.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_VENDA_PNG)));
		mntmVenda.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaVenda();
			}
		});
		mntmUsurio.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_USUARIO_PNG)));
		mntmUsurio.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaUsuario();
			}
		});
		mntmCategorias.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_CATEGORIA_PNG)));
		mntmCategorias.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaCategoria();
			}
		});
		mntmMarca.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_MARCA_PNG)));
		mntmMarca.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaMarca();
			}
		});
		mntmSetor.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_SETOR_PNG)));
		mntmSetor.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaSetor();
			}
		});
		mnRelatorio.setIcon(new ImageIcon(LOADER.getResource(IMAGENS_RELATORIO_PNG)));
		mnRelatorio.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				telaRelatorio();
			}
		});
		
		ComponenteControlado<TelaPrincipal> componenteControlado = new ComponenteControlado<TelaPrincipal>(this);
		componenteControlado.pronto(TipoUsuario.ADMINISTRADOR);
	}
	
	protected void telaRelatorio() {
		if(mnRelatorio.isEnabled()){
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						TelaRelatorioGerencial dialog = new TelaRelatorioGerencial();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setLocationRelativeTo(frmInterdisciplinar);
						dialog.setVisible(true);
					} catch (Exception e) {
						LOGGER.error(e);
					}
				}
			};
			runnable.run();
		}
	}

	public void show() {
		frmInterdisciplinar.setVisible(true);
	}
	
	public JFrame getFrameCentral(){
		return frmInterdisciplinar;
	}
	
	public void cancelar() {
		frmInterdisciplinar.getContentPane().removeAll();
		JPanel panel = new JPanel();
		frmInterdisciplinar.getContentPane().add(panel, BorderLayout.CENTER);
		frmInterdisciplinar.getContentPane().revalidate();
	}

	private void telaEntradaProduto() {
		if(mntmEntradaProduto.isEnabled()){
			EntradaProdutoDao entradaProdutoDaoDao = ApplicationContextConfig.getContext().getBean(EntradaProdutoDao.class);
			JDialogBusca<EntradaProduto, Integer> dialogo = new JDialogBusca<EntradaProduto, Integer>(entradaProdutoDaoDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	private void telaFornecedor() {
		if(mntmFornecedor.isEnabled()){
			FornecedorDao fornecedorDao = ApplicationContextConfig.getContext().getBean(FornecedorDao.class);
			JDialogBusca<Fornecedor, Integer> dialogo = new JDialogBusca<Fornecedor, Integer>(fornecedorDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	private void telaVenda() {
		if(mntmVenda.isEnabled()){
			VendaDao vendaDao = ApplicationContextConfig.getContext().getBean(VendaDao.class);
			JDialogBusca<Venda, Integer> dialogo = new JDialogBusca<Venda, Integer>(vendaDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	private void telaUsuario() {
		if(mntmUsurio.isEnabled()){
			UsuarioDao usuarioDao = ApplicationContextConfig.getContext().getBean(UsuarioDao.class);
			JDialogBusca<Usuario, Integer> dialogo = new JDialogBusca<Usuario, Integer>(usuarioDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	private void telaCategoria() {
		if(mntmCategorias.isEnabled()){
			CategoriaDao categoriaDao = ApplicationContextConfig.getContext().getBean(CategoriaDao.class);
			JDialogBusca<Categoria, Integer> dialogo = new JDialogBusca<Categoria, Integer>(categoriaDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	private void telaMarca() {
		if(mntmMarca.isEnabled()){
			MarcaDao marcaDao = ApplicationContextConfig.getContext().getBean(MarcaDao.class);
			JDialogBusca<Marca, Integer> dialogo = new JDialogBusca<Marca, Integer>(marcaDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	private void telaSetor() {
		if(mntmSetor.isEnabled()){
			SetorDao setorDao = ApplicationContextConfig.getContext().getBean(SetorDao.class);
			JDialogBusca<Setor, Integer> dialogo = new JDialogBusca<Setor, Integer>(setorDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	private void telaProduto() {
		if(mntmProdutos.isEnabled()){
			ProdutoDao produtoDao = ApplicationContextConfig.getContext().getBean(ProdutoDao.class);
			JDialogBusca<Produto, Integer> dialogo = new JDialogBusca<Produto, Integer>(produtoDao, null, null);
			dialogo.setLocationRelativeTo(frmInterdisciplinar);
			dialogo.setVisible(true);
		}
	}

	public JMenu getMntmEstoque() {
		return mntmEntradaProduto;
	}

	public void setMntmEstoque(JMenu mntmEstoque) {
		this.mntmEntradaProduto = mntmEstoque;
	}

	public JMenu getMntmProdutos() {
		return mntmProdutos;
	}

	public void setMntmProdutos(JMenu mntmProdutos) {
		this.mntmProdutos = mntmProdutos;
	}

	public JMenu getMntmFornecedor() {
		return mntmFornecedor;
	}

	public void setMntmFornecedor(JMenu mntmFornecedor) {
		this.mntmFornecedor = mntmFornecedor;
	}

	public JMenu getMntmMarca() {
		return mntmMarca;
	}

	public void setMntmMarca(JMenu mntmMarca) {
		this.mntmMarca = mntmMarca;
	}

	public JMenu getMntmCategorias() {
		return mntmCategorias;
	}

	public void setMntmCategorias(JMenu mntmCategorias) {
		this.mntmCategorias = mntmCategorias;
	}

	public JMenu getMntmUsurio() {
		return mntmUsurio;
	}

	public void setMntmUsurio(JMenu mntmUsurio) {
		this.mntmUsurio = mntmUsurio;
	}

	public JMenu getMntmSetor() {
		return mntmSetor;
	}

	public void setMntmSetor(JMenu mntmSetor) {
		this.mntmSetor = mntmSetor;
	}

	public JMenu getMntmRealizarVenda() {
		return mntmVenda;
	}

	public void setMntmRealizarVenda(JMenu mntmRealizarVenda) {
		this.mntmVenda = mntmRealizarVenda;
	}

	public JMenu getMnRelatorio() {
		return mnRelatorio;
	}

	public void setMnRelatorio(JMenu mnRelatorio) {
		this.mnRelatorio = mnRelatorio;
	}

	@Override
	public void load(Integer id) {
		ComponenteControlado<TelaPrincipal> componenteControlado = new ComponenteControlado<TelaPrincipal>(this);
		componenteControlado.pronto( TelasUtils.getUsuarioLogado().getTipo() );
	}
	
}
