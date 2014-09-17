package br.org.fgp.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.fgp.dao.MarcaDao;
import br.org.fgp.model.Marca;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.JBusca;

public class CadastroProduto extends JPanel {
	
	@Autowired
	private MarcaDao marcaDao;
	
	private JTextField txtNome;
	private JTextField txtPrecoVenda;
	private JTextField txtEstoqueAtual;
	private JTextField txtEstoqueMinimo;
	private JTextField txtEstoqueMaximo;
	private JTextField txtCategoria;
	private JTextField txtPrecoCusto;
	private JTextField txtLucro;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CadastroProduto(TipoUsuario tipoUsuario) {
		setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(341, 76, 104, 39);
		lblProduto.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		txtNome = new JTextField();
		txtNome.setBounds(175, 158, 501, 20);
		txtNome.setColumns(10);
		
		JTextPane txtDescricao = new JTextPane();
		txtDescricao.setBounds(175, 189, 501, 55);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(129, 288, 43, 14);
		
		JLabel lblPreoUnitrio = new JLabel("Pre\u00E7o de venda:");
		lblPreoUnitrio.setBounds(80, 350, 92, 14);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(101, 319, 71, 14);
		
		JComboBox cbbFornecedor = new JComboBox();
		cbbFornecedor.setBounds(174, 316, 502, 20);
		
		txtPrecoVenda = new JTextField();
		txtPrecoVenda.setBounds(176, 344, 96, 20);
		txtPrecoVenda.setColumns(10);
		
		JLabel lblEstoqueAtual = new JLabel("Estoque atual:");
		lblEstoqueAtual.setBounds(90, 373, 82, 14);
		
		txtEstoqueAtual = new JTextField();
		txtEstoqueAtual.setBounds(175, 370, 96, 20);
		txtEstoqueAtual.setColumns(10);
		
		JLabel lblEstoqueMnimo = new JLabel("Estoque m\u00EDnimo:");
		lblEstoqueMnimo.setBounds(292, 373, 98, 14);
		
		txtEstoqueMinimo = new JTextField();
		txtEstoqueMinimo.setBounds(394, 370, 95, 20);
		txtEstoqueMinimo.setColumns(10);
		
		JLabel lblEstoqueMximo = new JLabel("Estoque m\u00E1ximo:");
		lblEstoqueMximo.setBounds(493, 375, 104, 14);
		
		txtEstoqueMaximo = new JTextField();
		txtEstoqueMaximo.setBounds(594, 373, 82, 20);
		txtEstoqueMaximo.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(284, 409, 96, 45);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(433, 409, 104, 45);
		
		JLabel lblMsg = new JLabel("");
		lblMsg.setBounds(435, 523, 0, 0);
		lblMsg.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMsg.setForeground(Color.RED);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(176, 256, 103, 20);
		txtCategoria.setEnabled(false);
		txtCategoria.setColumns(10);
		
		JButton btnPesquisaCategoria = new JButton("...");
		btnPesquisaCategoria.setBounds(285, 255, 31, 23);
		btnPesquisaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaCategoria pesquisaCategoria = new PesquisaCategoria();
				pesquisaCategoria.setVisible(true);
			}
		});
		
		JLabel lblPreoDeCusto = new JLabel("Preço de custo:");
		lblPreoDeCusto.setBounds(298, 350, 92, 14);
		
		txtPrecoCusto = new JTextField();
		txtPrecoCusto.setBounds(394, 347, 95, 20);
		txtPrecoCusto.setColumns(10);
		
		JLabel lblLucro = new JLabel("Lucro:");
		lblLucro.setBounds(550, 350, 43, 14);
		
		txtLucro = new JTextField();
		txtLucro.setBounds(594, 347, 82, 20);
		txtLucro.setEnabled(false);
		txtLucro.setColumns(10);
		setLayout(null);
		add(lblMsg);
		add(lblProduto);
		add(lblMarca);
		add(txtCategoria);
		add(btnPesquisaCategoria);
		add(lblLucro);
		add(txtLucro);
		add(lblEstoqueAtual);
		add(lblPreoUnitrio);
		add(txtEstoqueAtual);
		add(txtPrecoVenda);
		add(lblEstoqueMnimo);
		add(txtEstoqueMinimo);
		add(lblPreoDeCusto);
		add(txtPrecoCusto);
		add(lblEstoqueMximo);
		add(txtEstoqueMaximo);
		add(txtDescricao);
		add(txtNome);
		add(lblFornecedor);
		add(btnCancelar);
		add(btnSalvar);
		add(cbbFornecedor);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(129, 161, 43, 14);
		add(lblNome);
		
		JLabel lblDescio = new JLabel("Descição:");
		lblDescio.setBounds(113, 189, 59, 14);
		add(lblDescio);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(113, 259, 59, 14);
		add(lblCategoria);
		
		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setBounds(344, 264, 46, 14);
		add(lblCdigo);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(394, 256, 199, 20);
		add(textField);
		textField.setColumns(10);
		
		JBusca busca = new JBusca<Marca, Integer>();
		busca.setBounds(176, 282, 297, 35);
		add(busca);
		
		ComponenteControlado<CadastroProduto> controleAcesso = new ComponenteControlado<CadastroProduto>(this); 
		controleAcesso.pronto(tipoUsuario);
		busca.setDaoGenerico(marcaDao);
	}

	public void setMarcaDao(MarcaDao marcaDao) {
		this.marcaDao = marcaDao;
	}
	
	
}
