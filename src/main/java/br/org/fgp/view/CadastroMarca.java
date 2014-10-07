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

import br.org.fgp.dao.MarcaDao;
import br.org.fgp.model.Marca;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import java.awt.Color;

public class CadastroMarca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMarca;
	JButton btnSalvar = new JButton("Salvar");
	JButton btnCancelar = new JButton("Cancelar");
	final JLabel lblMsg = new JLabel("");
	final static JLabel lblCampoObrigatrio = new JLabel("");
	
	@Autowired
	private MarcaDao marcaDao;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroMarca dialog = new CadastroMarca();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroMarca() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNovaMarca = new JLabel("Nova Marca");
			lblNovaMarca.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblNovaMarca.setBounds(132, 56, 201, 43);
			contentPanel.add(lblNovaMarca);
		}
		{
			JLabel lblMarca = new JLabel("Marca:");
			lblMarca.setBounds(100, 112, 52, 14);
			contentPanel.add(lblMarca);
		}
		{
			txtMarca = new JTextField();
			txtMarca.setColumns(10);
			txtMarca.setBounds(162, 109, 134, 20);
			contentPanel.add(txtMarca);
		}
		{
			
			btnSalvar.setBounds(94, 137, 89, 23);
			contentPanel.add(btnSalvar);
		}
		{
			btnCancelar.setBounds(193, 137, 89, 23);
			contentPanel.add(btnCancelar);
		}
		{
			
			lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblMsg.setBounds(88, 171, 263, 20);
			contentPanel.add(lblMsg);
		}
		{
			lblCampoObrigatrio.setForeground(Color.RED);
			lblCampoObrigatrio.setBounds(305, 112, 27, 14);
			contentPanel.add(lblCampoObrigatrio);
		}
		
		ComponenteControlado<CadastroMarca> controleAcesso = new ComponenteControlado<CadastroMarca>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marca marca = new Marca();
				try
				{
					if(!txtMarca.getText().isEmpty()){
						marca.setMarca(txtMarca.getText());
						marcaDao.salvar(marca);
						JOptionPane.showMessageDialog(null, "Marca cadastrada com sucesso.");
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "O campo Marca é obrigatório.");
						lblCampoObrigatrio.setText("*");
					}
				}
				catch(Exception ex){
					//throw ex;
					lblMsg.setText("Falha ao cadastrar nova marca.");
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public MarcaDao getMarcaDao() {
		return marcaDao;
	}

	public void setMarcaDao(MarcaDao marcaDao) {
		this.marcaDao = marcaDao;
	}

}
