package br.org.fgp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import org.springframework.stereotype.Component;

import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.MarcaDao;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;

@Component
public class CadastroMarca extends JDialog implements Inicializavel {

	private static final long serialVersionUID = 4699949600267605436L;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMarca;
	JButton btnSalvar = new JButton("Salvar");

	@Autowired
	private MarcaDao marcaDao;

	private final JLabel lblMsg = new JLabel("");

	private Marca marca;

	public CadastroMarca() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		setSize(300, 200);
		setTitle("Marca");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Cadastrar nova Marca",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 60, 210, 0 };
		gbl_contentPanel.rowHeights = new int[] { 35, 35, 35, 35, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
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
				if(marca == null){
					marca = new Marca();
				}
				try {
					if (!txtMarca.getText().isEmpty()) {
						marca.setMarca(txtMarca.getText());
						marcaDao.salvar(marca);
						JOptionPane.showMessageDialog(null,
								"Marca cadastrada com sucesso.");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"O campo Marca é obrigatório.");
						txtMarca.setBorder(new LineBorder(new Color(255, 0, 0),
								1));
					}
				} catch (Exception ex) {
					lblMsg.setText("Falha ao cadastrar nova marca.");
				}
			}
		});

		ComponenteControlado<CadastroMarca> controleAcesso = new ComponenteControlado<CadastroMarca>(
				this);
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
	}

	public MarcaDao getMarcaDao() {
		return marcaDao;
	}

	public void setMarcaDao(MarcaDao marcaDao) {
		this.marcaDao = marcaDao;
	}

	@Override
	public void load(Integer id) {
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			marca = marcaDao.buscarPorId(id);
			txtMarca.setText(marca.getMarca());
		}else{
			marca = new Marca();
		}
	}

	public void init(Usuario usuario) {
		ComponenteControlado<CadastroMarca> controleAcesso = new ComponenteControlado<CadastroMarca>(this);
		controleAcesso.pronto(TelasUtils.getUsuarioLogado().getTipo());
	}
}
