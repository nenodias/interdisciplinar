package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.org.fgp.core.TelasUtils;
import br.org.fgp.model.Produto;
import br.org.fgp.model.VendaItem;
import br.org.fgp.view.core.JBusca;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonOk;

public class CadastroVendaItem extends JDialog {

	private static final long serialVersionUID = -2894768848965803045L;
	
	private JDialog dialog;

	private VendaItem vendaItem;

	private JBusca<Produto, Integer> txtProduto;

	private JFormattedTextField txtQuantidade;

	private JFormattedTextField txtPrecoCusto;

	public CadastroVendaItem(VendaItem vendaItem) {
		dialog = this;
		this.vendaItem = vendaItem;
		this.setModal(true);
		setAlwaysOnTop(true);
		setBounds(0, 0, 300, 300);
		setSize(353, 301);
		setLocationRelativeTo(null);
		setTitle("Itens");
		JPanel buttonPane = new JPanel();
		getContentPane().setLayout(null);
		getContentPane().add(buttonPane);
		
		adicionarComponente(new JLabel("Produto:"), 2);
		txtProduto = new JBusca<Produto, Integer>();
		adicionarComponente(txtProduto, 2);
		
		adicionarComponente(new JLabel("Quantidade:"), 4);
		txtQuantidade = new JFormattedTextField(TelasUtils.getFormatadorInteiro());
		adicionarComponente(txtQuantidade, 4);
		
		adicionarComponente(new JLabel("Preço Unitário:"), 6);
		txtPrecoCusto = new JFormattedTextField(TelasUtils.getFormatadorDecimal());
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
	}
	
	private void novoVendaItem() {
		fecharDialogo();
	}

	private void fecharDialogo() {
		dialog.dispose();
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put(TelasUtils.PARAM_LARGURA_COMPONENTES, -150);
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}

}
