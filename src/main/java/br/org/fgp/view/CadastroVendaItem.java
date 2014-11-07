package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.Produto;
import br.org.fgp.model.VendaItem;
import br.org.fgp.view.core.JBusca;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonOk;
import br.org.fgp.view.core.JMoneyField;
import br.org.fgp.view.core.Validador;

public class CadastroVendaItem extends JDialog {

	private static final long serialVersionUID = -2894768848965803045L;
	
	private static final Logger LOGGER = Logger.getLogger(CadastroVendaItem.class);
	
	private JDialog dialog;

	private VendaItem vendaItem;

	private JBusca<Produto, Integer> txtProduto;

	private JFormattedTextField txtQuantidade;

	private JFormattedTextField txtPrecoCusto;

	private ProdutoDao produtoDao;
	
	public CadastroVendaItem(VendaItem vendaItem) {
		dialog = this;
		this.vendaItem = vendaItem;
		this.setModal(true);
		setAlwaysOnTop(true);
		setBounds(0, 0, 640, 300);
		setSize(640, 300);
		setLocationRelativeTo(null);
		setTitle("Itens");
		JPanel buttonPane = new JPanel();
		getContentPane().setLayout(null);
		getContentPane().add(buttonPane);
		
		adicionarComponente(new JLabel("Produto:"), 2);
		txtProduto = new JBusca<Produto, Integer>();
		produtoDao = ApplicationContextConfig.getContext().getBean(ProdutoDao.class);
		txtProduto.setDaoGenerico(produtoDao);
		adicionarComponente(txtProduto, 2);
		
		adicionarComponente(new JLabel("Quantidade:"), 4);
		txtQuantidade = new JFormattedTextField(TelasUtils.getFormatadorInteiro());
		adicionarComponente(txtQuantidade, 4);
		
		adicionarComponente(new JLabel("Preço Unitário:"), 6);
		txtPrecoCusto = new JMoneyField();
		txtPrecoCusto.setEditable(false);
		adicionarComponente(txtPrecoCusto, 6);
		
		buttonPane.setBounds(0 , 220 , 300, TelasUtils.DEFAULT_ALTURA_COMPONENTE * 2);
		JButton okButton = new JButtonOk();
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButtonCancelar();
		buttonPane.add(cancelButton);
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharDialogo();
			}
		});
		okButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				novoVendaItem();
			}

		});
		txtProduto.getCodigoComponente().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				atualizaPrecoCusto();
			}

			@Override
			public void focusLost(FocusEvent e) {
				atualizaPrecoCusto();
			}
		});
		txtQuantidade.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				validaEstoqueProduto();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				validaEstoqueProduto();
			}

		});
	}
	
	private void validaEstoqueProduto() {
		try{
			Integer idProduto = Integer.parseInt(txtProduto.getText());
			Produto produto = produtoDao.buscarPorId(idProduto);
			validaEstoque(produto);
		}catch(Exception t){
			LOGGER.warn(t.getMessage(), t);
			txtPrecoCusto.setText( StringUtils.EMPTY );
		}
	}
	
	private void atualizaPrecoCusto(){
		try{
			Integer idProduto = Integer.parseInt(txtProduto.getText());
			Produto produto = produtoDao.buscarPorId(idProduto);
			txtPrecoCusto.setText( produto.getPrecoUnitario().toString() );
			validaEstoque(produto);
		}catch(Exception t){
			LOGGER.warn(t.getMessage(), t);
			txtPrecoCusto.setText( StringUtils.EMPTY );
		}
	}

	private void validaEstoque(Produto produto) {
		if(StringUtils.isNotBlank( txtQuantidade.getText() ) ){
			Integer quantidade = Integer.parseInt( txtQuantidade.getText() );
			Integer estoque = produto.getEstoqueAtual() - quantidade;
			if(estoque < 0 ){
				txtQuantidade.setText( produto.getEstoqueAtual().toString() );
			}
		}
	}
	
	private void novoVendaItem() {
		if( StringUtils.isNotBlank( txtPrecoCusto.getText() ) ){
			try{
				BigDecimal preco = new BigDecimal(txtPrecoCusto.getText());
				vendaItem.setValorUnitario(preco);
			}catch(Exception e){
				LOGGER.error(e);
			}
		}
		if( StringUtils.isNotBlank( txtProduto.getText() ) ){
			try{
				Integer idProduto = Integer.parseInt(txtProduto.getText());
				Produto produto = produtoDao.buscarPorId(idProduto);
				vendaItem.setProduto(produto);
			}catch(Exception e){
				LOGGER.error(e);
			}
		}
		if( StringUtils.isNotBlank( txtQuantidade.getText() ) ){
			try{
				Integer quantidade = Integer.parseInt(txtQuantidade.getText());
				vendaItem.setQuantidade(quantidade);
			}catch(Exception e){
				LOGGER.error(e);
			}
		}
		Validador<VendaItem> validador = new Validador<VendaItem>();
		validador.validacaoCampos(vendaItem);
		fecharDialogo();
	}

	private void fecharDialogo() {
		dialog.dispose();
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}

}
