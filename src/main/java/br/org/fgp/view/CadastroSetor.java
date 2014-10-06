package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.fgp.dao.SetorDao;
import br.org.fgp.model.Setor;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;

public class CadastroSetor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSetor;
	JLabel lblCampoObrigatorio = new JLabel("");
	JLabel lblMsg = new JLabel("");
	JButton btnSalvar = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	
	@Autowired
	private SetorDao setorDao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroSetor dialog = new CadastroSetor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroSetor() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNovoSetor = new JLabel("Novo Setor");
			lblNovoSetor.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblNovoSetor.setBounds(136, 51, 160, 31);
			contentPanel.add(lblNovoSetor);
		}
		{
			JLabel lblSetor = new JLabel("Setor:");
			lblSetor.setBounds(102, 96, 46, 14);
			contentPanel.add(lblSetor);
		}
		{
			txtSetor = new JTextField();
			txtSetor.setBounds(143, 93, 153, 20);
			contentPanel.add(txtSetor);
			txtSetor.setColumns(10);
		}
		{
			btnSalvar.setBounds(102, 124, 89, 23);
			contentPanel.add(btnSalvar);
		}
		{			
			btnCancelar.setBounds(207, 124, 89, 23);
			contentPanel.add(btnCancelar);
		}
		{
			
			lblCampoObrigatorio.setBounds(306, 96, 46, 14);
			contentPanel.add(lblCampoObrigatorio);
		}
		{
			
			lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblMsg.setBounds(102, 168, 194, 23);
			contentPanel.add(lblMsg);
		}
		
		ComponenteControlado<CadastroSetor> controleAcesso = new ComponenteControlado<CadastroSetor>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		
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
						lblCampoObrigatorio.setText("*");
					}
				}
				catch(Exception ex){
					lblMsg.setText("Falha ao cadastrada novo setor");
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public SetorDao getSetorDao() {
		return setorDao;
	}

	public void setSetorDao(SetorDao setorDao) {
		this.setorDao = setorDao;
	}

}
