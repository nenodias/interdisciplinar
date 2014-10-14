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
import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;

@Component
public class CadastroCategoria extends JDialog implements Inicializavel {

	private static final String VAZIO = "";

	private static final long serialVersionUID = 7790542144250751854L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtCategoria;

	@Autowired
	private CategoriaDao categoriaDao;
	
	private Categoria categoria;

	public void load(Integer id) {
		init( TelasUtils.getUsuarioLogado() );
		categoria = categoriaDao.buscarPorId(id);
		txtCategoria.setText(categoria.getDescricao());
	}
	
	public void init(Usuario usuario){
		ComponenteControlado<CadastroCategoria> controleAcesso = new ComponenteControlado<CadastroCategoria>(this); 
		controleAcesso.pronto(usuario.getTipo());
	}
	
	public CadastroCategoria() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		setSize(300, 200);
		setTitle("Categoria");
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Criar nova Categoria", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{60, 210, 0};
		gbl_contentPanel.rowHeights = new int[]{35, 35, 35, 35, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		final JLabel lblCategoria = new JLabel("Categoria:");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.anchor = GridBagConstraints.WEST;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 0;
		gbc_lblCategoria.gridy = 1;
		contentPanel.add(lblCategoria, gbc_lblCategoria);

		txtCategoria = new JTextField();
		GridBagConstraints gbc_txtCategoria = new GridBagConstraints();
		gbc_txtCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategoria.insets = new Insets(0, 0, 5, 0);
		gbc_txtCategoria.gridx = 1;
		gbc_txtCategoria.gridy = 1;	
		contentPanel.add(txtCategoria, gbc_txtCategoria);
		txtCategoria.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 1;
		gbc_btnSalvar.gridy = 2;
		contentPanel.add(btnSalvar, gbc_btnSalvar);

		final JLabel lblMsg = new JLabel(VAZIO);
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.anchor = GridBagConstraints.WEST;
		gbc_lblMsg.gridx = 1;
		gbc_lblMsg.gridy = 3;
		contentPanel.add(lblMsg, gbc_lblMsg);
		
		ComponenteControlado<CadastroCategoria> controleAcesso = new ComponenteControlado<CadastroCategoria>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(categoria == null){
					categoria = new Categoria();
				}
				try{		
					if(!txtCategoria.getText().isEmpty()){
						categoria.setDescricao(txtCategoria.getText());					
						categoriaDao.salvar(categoria);
						JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso.");
						categoria = null;
						txtCategoria.setText(VAZIO);	
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "O campo Categoria é obrigatório.");
						txtCategoria.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					}
				}
				catch(Exception ex){
					lblMsg.setText("Falha ao cadastrar nova categoria.");
				}
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
