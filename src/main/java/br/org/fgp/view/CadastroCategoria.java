package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.Color;
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

import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;

public class CadastroCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtCategoria;
	
	final static JLabel lblCampoObrigatorio = new JLabel("");
	
	@Autowired
	private CategoriaDao categoriaDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroCategoria dialog = new CadastroCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroCategoria() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNovaCategoria = new JLabel("Nova Categoria");
		lblNovaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNovaCategoria.setBounds(126, 42, 201, 43);
		contentPanel.add(lblNovaCategoria);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(99, 98, 68, 14);
		contentPanel.add(lblCategoria);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(177, 95, 134, 20);
		contentPanel.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
	
		btnSalvar.setBounds(109, 123, 89, 23);
		contentPanel.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
	
		btnCancelar.setBounds(208, 123, 89, 23);
		contentPanel.add(btnCancelar);
		
		final JLabel lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMsg.setBounds(103, 157, 263, 20);
		contentPanel.add(lblMsg);
		
		
		lblCampoObrigatorio.setForeground(Color.RED);
		lblCampoObrigatorio.setBounds(321, 98, 46, 14);
		contentPanel.add(lblCampoObrigatorio);
		
		ComponenteControlado<CadastroCategoria> controleAcesso = new ComponenteControlado<CadastroCategoria>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Categoria categoria = new Categoria();
				try{		
					if(!txtCategoria.getText().isEmpty()){
					categoria.setDescricao(txtCategoria.getText());					
					categoriaDao.salvar(categoria);
					JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso.");
					dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "O campo Categoria é obrigatório.");
						lblCampoObrigatorio.setText("*");
					}
				}
				catch(Exception ex){
					lblMsg.setText("Falha ao cadastrar nova categoria.");
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
}
