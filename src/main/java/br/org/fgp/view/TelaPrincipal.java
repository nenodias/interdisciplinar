package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 532);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
	}
}
