package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Controller;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.dao.MarcaDao;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.dao.SetorDao;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Produto;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Usuario;
import br.org.fgp.view.core.JDialogBusca;

@Controller
public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = -7747890711976699854L;

	private JPanel contentPane;
	private JFrame frmInterdisciplinar;

	public TelaPrincipal() {
		frmInterdisciplinar = new JFrame();
		frmInterdisciplinar.setTitle("Interdisciplinar -");
		frmInterdisciplinar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInterdisciplinar.setBounds(100, 100, 800, 600);
		frmInterdisciplinar.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		frmInterdisciplinar.setJMenuBar(menuBar);

		JMenu mnCadastrar = new JMenu("Consultar");
		menuBar.add(mnCadastrar);

		JMenu mnProdutos = new JMenu("Estoque");
		mnCadastrar.add(mnProdutos);

		JMenuItem mntmEstoque = new JMenuItem("Movimenta\u00E7\u00E3o");
		mnProdutos.add(mntmEstoque);

		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mnCadastrar.add(mntmProdutos);

		JMenuItem mntmFornecedor = new JMenuItem("Fornecedores");
		mnCadastrar.add(mntmFornecedor);

		JMenuItem mntmMarca = new JMenuItem("Marcas");
		mnCadastrar.add(mntmMarca);

		JMenuItem mntmCategorias = new JMenuItem("Categorias");
		mnCadastrar.add(mntmCategorias);

		JMenuItem mntmUsurio = new JMenuItem("Usu\u00E1rios");
		mnCadastrar.add(mntmUsurio);

		JMenuItem mntmSetor = new JMenuItem("Setor");
		mnCadastrar.add(mntmSetor);

		JMenu mnVenda = new JMenu("Venda");
		menuBar.add(mnVenda);

		JMenuItem mntmRealizarVenda = new JMenuItem("Realizar Venda");
		mnVenda.add(mntmRealizarVenda);
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

		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoDao produtoDao = ApplicationContextConfig.getContext().getBean(ProdutoDao.class);
				JDialogBusca<Produto, Integer> dialogo = new JDialogBusca<Produto, Integer>(produtoDao, null, null);
				dialogo.setLocationRelativeTo(frmInterdisciplinar);
				dialogo.setVisible(true);
			}
		});
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FornecedorDao fornecedorDao = ApplicationContextConfig.getContext().getBean(FornecedorDao.class);
				JDialogBusca<Fornecedor, Integer> dialogo = new JDialogBusca<Fornecedor, Integer>(fornecedorDao, null, null);
				dialogo.setLocationRelativeTo(frmInterdisciplinar);
				dialogo.setVisible(true);
			}
		});
		mntmRealizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mntmUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDao usuarioDao = ApplicationContextConfig.getContext().getBean(UsuarioDao.class);
				JDialogBusca<Usuario, Integer> dialogo = new JDialogBusca<Usuario, Integer>(usuarioDao, null, null);
				dialogo.setLocationRelativeTo(frmInterdisciplinar);
				dialogo.setVisible(true);
			}
		});
		mntmCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CategoriaDao categoriaDao = ApplicationContextConfig.getContext().getBean(CategoriaDao.class);
				JDialogBusca<Categoria, Integer> dialogo = new JDialogBusca<Categoria, Integer>(categoriaDao, null, null);
				dialogo.setLocationRelativeTo(frmInterdisciplinar);
				dialogo.setVisible(true);
			}
		});
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MarcaDao marcaDao = ApplicationContextConfig.getContext().getBean(MarcaDao.class);
				JDialogBusca<Marca, Integer> dialogo = new JDialogBusca<Marca, Integer>(marcaDao, null, null);
				dialogo.setLocationRelativeTo(frmInterdisciplinar);
				dialogo.setVisible(true);
			}
		});
		mntmSetor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetorDao setorDao = ApplicationContextConfig.getContext().getBean(SetorDao.class);
				JDialogBusca<Setor, Integer> dialogo = new JDialogBusca<Setor, Integer>(setorDao, null, null);
				dialogo.setLocationRelativeTo(frmInterdisciplinar);
				dialogo.setVisible(true);
			}
		});
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
	
}
