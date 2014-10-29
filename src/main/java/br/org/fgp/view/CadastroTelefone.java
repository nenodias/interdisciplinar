package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import br.org.fgp.model.Telefone;
import br.org.fgp.model.enums.TipoTelefone;

public class CadastroTelefone extends JDialog {

	private static final String VAZIO = "";
	
	private static final long serialVersionUID = 6594227432444740974L;

	private static final int X = 0; 
	private static final int Y = 0; 
	private static final int LARGURA_COMPONENTES = 150;
	private static final int ALTURA_COMPONENTES = 18;
	
	private Telefone telefone;

	private CadastroTelefone dialog;

	private JTextField txtTelefone;

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
		txtTelefone = new JTextField();
		adicionarComponente(new JLabel("Telefone:"), 2);
		adicionarComponente(txtTelefone, 2);
		cbbTipo = new JComboBox<TipoTelefone>();
		cbbTipo.addItem(TipoTelefone.CELULAR);
		cbbTipo.addItem(TipoTelefone.COMERCIAL);
		cbbTipo.addItem(TipoTelefone.RESIDENCIAL);
		adicionarComponente(new JLabel("Tipo:"), 4);
		adicionarComponente(cbbTipo,4);
		
		buttonPane.setBounds(0 , 220 , 300, ALTURA_COMPONENTES * 2);
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
		txtTelefone.setText(VAZIO);
		fecharDialogo();
	}

	private void fecharDialogo() {
		dialog.dispose();
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		if(componente instanceof JLabel){
			componente.setBounds(X  , Y +(valor *ALTURA_COMPONENTES) , LARGURA_COMPONENTES, ALTURA_COMPONENTES);
			JLabel jLabel = (JLabel)componente;
			jLabel.setHorizontalAlignment(JLabel.RIGHT);
		}else{
			componente.setBounds(X  +LARGURA_COMPONENTES , Y+(valor *ALTURA_COMPONENTES), LARGURA_COMPONENTES, ALTURA_COMPONENTES);
		}
		if (componente instanceof JTextField) {
			JTextField textField = (JTextField) componente;
			textField.setColumns(10);
		}else if(componente instanceof JPasswordField){
			JPasswordField jPasswordField = (JPasswordField)componente;
			jPasswordField.setEchoChar('#');
		}
		getContentPane().add(componente);
	}
	
}
