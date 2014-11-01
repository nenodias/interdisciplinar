package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.dao.SetorDao;
import br.org.fgp.model.Setor;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;

@Controller
public class CadastroSetor extends JDialog {

	private static final long serialVersionUID = -5360024164470109759L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSetor;
	JButton btnSalvar = new JButton("Salvar");
	
	@Autowired
	private SetorDao setorDao;
	private final JLabel lblSetor_1 = new JLabel("Setor:");
	private final JLabel lblMsg = new JLabel("");
	
	public CadastroSetor() {
		setBounds(100, 100, 450, 300);
		setSize(300, 200);
		setTitle("Setor");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Cadastrar novo Setor", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 210, 0};
		gbl_contentPanel.rowHeights = new int[]{35, 35, 35, 35, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
			GridBagConstraints gbc_lblSetor_1 = new GridBagConstraints();
			gbc_lblSetor_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblSetor_1.anchor = GridBagConstraints.WEST;
			gbc_lblSetor_1.gridx = 0;
			gbc_lblSetor_1.gridy = 1;
			contentPanel.add(lblSetor_1, gbc_lblSetor_1);
		
			txtSetor = new JTextField();
			GridBagConstraints gbc_txtSetor = new GridBagConstraints();
			gbc_txtSetor.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSetor.insets = new Insets(0, 0, 5, 0);
			gbc_txtSetor.gridx = 1;
			gbc_txtSetor.gridy = 1;
			contentPanel.add(txtSetor, gbc_txtSetor);
			txtSetor.setColumns(10);
		
			JLabel lblSetor = new JLabel("Setor:");
			GridBagConstraints gbc_lblSetor = new GridBagConstraints();
			gbc_lblSetor.anchor = GridBagConstraints.WEST;
			gbc_lblSetor.insets = new Insets(0, 0, 5, 0);
			gbc_lblSetor.gridx = 1;
			gbc_lblSetor.gridy = 1;
			contentPanel.add(lblSetor, gbc_lblSetor);
		
			GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
			gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
			gbc_btnSalvar.gridx = 1;
			gbc_btnSalvar.gridy = 2;
			contentPanel.add(btnSalvar, gbc_btnSalvar);
		
			GridBagConstraints gbc_lblMsg = new GridBagConstraints();
			gbc_lblMsg.anchor = GridBagConstraints.WEST;
			gbc_lblMsg.gridx = 1;
			gbc_lblMsg.gridy = 3;
			contentPanel.add(lblMsg, gbc_lblMsg);
				
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Setor setor = new Setor();
				try{
					if(!txtSetor.getText().isEmpty()){
						setor.setSetor(txtSetor.getText());
						setorDao.salvar(setor);
						JOptionPane.showMessageDialog(null, "Setor cadastrado com sucesso.");
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Campo Setor obrigatório.");
						txtSetor.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					}
				}
				catch(Exception ex){
					lblMsg.setText("Falha ao cadastrada novo setor");
				}
			}
		});
		
		ComponenteControlado<CadastroSetor> controleAcesso = new ComponenteControlado<CadastroSetor>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
	}

	public SetorDao getSetorDao() {
		return setorDao;
	}

	public void setSetorDao(SetorDao setorDao) {
		this.setorDao = setorDao;
	}

}
