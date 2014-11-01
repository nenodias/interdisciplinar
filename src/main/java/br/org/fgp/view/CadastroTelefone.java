package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import br.org.fgp.core.TelasUtils;
import br.org.fgp.model.Telefone;
import br.org.fgp.model.enums.TipoTelefone;

public class CadastroTelefone extends JDialog {

	private static final long serialVersionUID = 6594227432444740974L;
	
	private Telefone telefone;

	private CadastroTelefone dialog;

	private JFormattedTextField txtTelefone;

	private JComboBox<TipoTelefone> cbbTipo;
	

	public CadastroTelefone(Telefone telefone) {
		dialog = this;
		this.telefone = telefone;
		this.setModal(true);
		setAlwaysOnTop(true);
		setBounds(0, 0, 300, 300);
		setSize(353, 301);
		setLocationRelativeTo(null);
		setTitle("Telefone");
		JPanel buttonPane = new JPanel();
		getContentPane().setLayout(null);
		getContentPane().add(buttonPane);
		txtTelefone = new JFormattedTextField( TelasUtils.getMascaraTelefone() );
		adicionarComponente(new JLabel("Telefone:"), 2);
		adicionarComponente(txtTelefone, 2);
		cbbTipo = new JComboBox<TipoTelefone>();
		cbbTipo.addItem(TipoTelefone.CELULAR);
		cbbTipo.addItem(TipoTelefone.COMERCIAL);
		cbbTipo.addItem(TipoTelefone.RESIDENCIAL);
		adicionarComponente(new JLabel("Tipo:"), 4);
		adicionarComponente(cbbTipo,4);
		
		buttonPane.setBounds(0 , 220 , 300, TelasUtils.DEFAULT_ALTURA_COMPONENTE * 2);
		JButton okButton = new JButton("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		buttonPane.add(cancelButton);

		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharDialogo();
			}
		});
		okButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				novoTelefone();
			}

		});
		if(StringUtils.isNotBlank( telefone.getTelefone() ) ){
			txtTelefone.setText( telefone.getTelefone() );
		}
		if(telefone.getTipo() != null){
			for (int i = 0; i < cbbTipo.getItemCount(); i++) {
				if( cbbTipo.getItemAt(i).equals( telefone.getTipo() ) ){
					cbbTipo.setSelectedIndex(i);
					break;
				}
			}
		}
	}

	private void novoTelefone() {
		dialog.telefone.setTelefone( txtTelefone.getText() );
		TipoTelefone tipo = (TipoTelefone) cbbTipo.getSelectedItem();
		dialog.telefone.setTipo( tipo );
		txtTelefone.setText(StringUtils.EMPTY);
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
