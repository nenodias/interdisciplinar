package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.validation.ValidationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.Usuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JCabecalhoLabel;
import br.org.fgp.view.core.Validador;

@Controller
public class CadastroCategoria extends JDialog implements Inicializavel {

	private static final long serialVersionUID = 7790542144250751854L;

	private static final Logger LOGGER = Logger.getLogger(CadastroCategoria.class);

	private static final String CLASS_NAME = "Categoria";   

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtCategoria;

	@Autowired
	private CategoriaDao categoriaDao;
	
	private Categoria categoria;

	private JSplitPane splitPane;

	private JButton btnSalvar;

	private JButton btnCancelar;

	public void load(Integer id) {
		init( TelasUtils.getUsuarioLogado() );
		if(id != null){
			categoria = categoriaDao.buscarPorId(id);
			txtCategoria.setText(categoria.getDescricao());
		}else{
			categoria = new Categoria();
		}
	}
	
	public void init(Usuario usuario){
		ComponenteControlado<CadastroCategoria> controleAcesso = new ComponenteControlado<CadastroCategoria>(this); 
		controleAcesso.pronto(TelasUtils.getUsuarioLogado().getTipo());
	}
	
	public CadastroCategoria() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		setSize(300, 200);
		setTitle(CLASS_NAME);
		setLocationRelativeTo(null);
		contentPanel.setVisible(true);
		setContentPane(contentPanel);
		getContentPane().setLayout(null);
		
		adicionarComponente(new JCabecalhoLabel(CLASS_NAME), 0);
		
		adicionarComponente(new JLabel("Categoria:"), 4);
		txtCategoria = new JTextField();
		adicionarComponente(txtCategoria, 4);
		splitPane = new JSplitPane();
		
		adicionarComponente(splitPane, 8);
		
		btnSalvar = new JButton("Salvar");
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		splitPane.setRightComponent(btnCancelar);
		
		splitPane.setDividerLocation( ( ( Double ) ( TelasUtils.DEFAULT_LARGURA_COMPONENTE * 0.22 ) ).intValue()  );
		
		splitPane.setEnabled(false);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}

		});
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void salvar() {
		if(categoria == null){
			categoria = new Categoria();
		}
		try{
			categoria.setDescricao(txtCategoria.getText());
			Validador<Categoria> validador = new  Validador<Categoria>();
			validador.validacaoCampos(categoria);
			categoriaDao.salvar(categoria);
			JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso.");
			categoria = null;
			txtCategoria.setText(StringUtils.EMPTY);	
			dispose();
		}
		catch(ValidationException e){
			LOGGER.error(e);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Falha ao salvar "+CLASS_NAME+".");
			LOGGER.error(ex);
		}
	}
	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put(TelasUtils.PARAM_LARGURA_COMPONENTES, -140);
		parametros.put(TelasUtils.PARAM_X, -40);
		parametros.put(TelasUtils.PARAM_ESPACO, -5);
		TelasUtils.adicionarComponente(componente, valor, contentPanel, parametros);
	}

}
