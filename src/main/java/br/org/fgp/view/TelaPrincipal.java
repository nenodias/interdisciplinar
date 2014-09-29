package br.org.fgp.view;


import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import br.org.fgp.model.Usuario;

public class TelaPrincipal extends JFrame {

	private Usuario usuarioLogado;
	
	private JPanel contentPane;
	private JFrame frmInterdisciplinar;
	
	/**
	 * Launch the application.
	 */
	public static void main(final Usuario usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal tp = new TelaPrincipal(usuario);
					tp.frmInterdisciplinar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal(Usuario usuario) {
		usuarioLogado = usuario;
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
		
		JMenu mnVenda = new JMenu("Venda");
		menuBar.add(mnVenda);
		
		JMenuItem mntmRealizarVenda = new JMenuItem("Realizar Venda");
		mnVenda.add(mntmRealizarVenda);
		frmInterdisciplinar.getContentPane().setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 732, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 484, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		
		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto cp = new CadastroProduto(usuarioLogado.getTipo());
				frmInterdisciplinar.getContentPane().removeAll();
				frmInterdisciplinar.getContentPane().setBounds(cp.getBounds());//, y, width, height);
				frmInterdisciplinar.getContentPane().add(cp);//, BorderLayout.CENTER);
				frmInterdisciplinar.getContentPane().revalidate();
				cp.setVisible(true);
			}
		});
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmInterdisciplinar.getContentPane().removeAll();
				CadastroFornecedor cf = new CadastroFornecedor();
				frmInterdisciplinar.getContentPane().setBounds(cf.getBounds());//, y, width, height);
				frmInterdisciplinar.getContentPane().add(cf);//, BorderLayout.CENTER);
				frmInterdisciplinar.getContentPane().revalidate();
				cf.setVisible(true);
			}
		});
		mntmRealizarVenda.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				frmInterdisciplinar.getContentPane().removeAll();
				Venda venda = new Venda();
				frmInterdisciplinar.getContentPane().setBounds(venda.getBounds());//, y, width, height);
				frmInterdisciplinar.getContentPane().add(venda);//, BorderLayout.CENTER);
				frmInterdisciplinar.getContentPane().revalidate();
				venda.setVisible(true);
			}			
		});
	}
}
