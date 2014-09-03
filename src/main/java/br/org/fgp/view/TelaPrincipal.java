package br.org.fgp.view;

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

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal tp = new TelaPrincipal();
					tp.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 756, 586);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Consultar");
		menuBar.add(mnCadastrar);
		
		JMenu mnProdutos = new JMenu("Estoque");
		mnCadastrar.add(mnProdutos);
		
		JMenuItem mntmEstoque = new JMenuItem("Movimenta\u00E7\u00E3o");
		mnProdutos.add(mntmEstoque);
		
		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				CadastroProduto cp = new CadastroProduto();
				frame.getContentPane().setBounds(cp.getBounds());//, y, width, height);
				frame.getContentPane().add(cp);//, BorderLayout.CENTER);
				frame.getContentPane().repaint();
				cp.setVisible(true);
			}
		});
		mnCadastrar.add(mntmProdutos);
				
		JMenuItem mntmFornecedor = new JMenuItem("Fornecedores");
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				CadastroFornecedor cf = new CadastroFornecedor();
				frame.getContentPane().setBounds(cf.getBounds());//, y, width, height);
				frame.getContentPane().add(cf);//, BorderLayout.CENTER);
				frame.getContentPane().repaint();
				cf.setVisible(true);
			}
		});
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
