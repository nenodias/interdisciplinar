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
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.fgp.dao.MarcaDao;
import br.org.fgp.model.Marca;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

public class CadastroMarca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMarca;
	JButton btnSalvar = new JButton("Salvar");

	@Autowired
	private MarcaDao marcaDao;
	private final JLabel lblMsg = new JLabel("");


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
		setSize(300, 200);
		setTitle("Marca");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Cadastrar nova Marca", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{60, 210, 0};
		gbl_contentPanel.rowHeights = new int[]{35, 35, 35, 35, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblMarca = new JLabel("Marca:");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.WEST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 0;
		gbc_lblMarca.gridy = 1;
		contentPanel.add(lblMarca, gbc_lblMarca);


		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		GridBagConstraints gbc_txtMarca = new GridBagConstraints();
		gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMarca.insets = new Insets(0, 0, 5, 0);
		gbc_txtMarca.gridx = 1;
		gbc_txtMarca.gridy = 1;
		contentPanel.add(txtMarca, gbc_txtMarca);

		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 1;
		gbc_btnSalvar.gridy = 2;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.gridx = 1;
		gbc_lblMsg.gridy = 3;
		contentPanel.add(lblMsg, gbc_lblMsg);

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
						txtMarca.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					}
				}
				catch(Exception ex){					
					lblMsg.setText("Falha ao cadastrar nova marca.");
				}
			}
		});

		ComponenteControlado<CadastroMarca> controleAcesso = new ComponenteControlado<CadastroMarca>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
	}

	public MarcaDao getMarcaDao() {
		return marcaDao;
	}

	public void setMarcaDao(MarcaDao marcaDao) {
		this.marcaDao = marcaDao;
	}

}
