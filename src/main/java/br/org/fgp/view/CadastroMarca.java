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

import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.MarcaDao;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Usuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonSalvar;
import br.org.fgp.view.core.JCabecalhoLabel;
import br.org.fgp.view.core.Validador;

@Controller
public class CadastroMarca extends JDialog implements Inicializavel {

	private static final long serialVersionUID = 4699949600267605436L;

	private static final Logger LOGGER = Logger.getLogger(CadastroMarca.class);

	private static final String CLASS_NAME = "Marca";   

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtMarca;

	@Autowired
	private MarcaDao marcaDao;
	
	private Marca marca;

	private JSplitPane splitPane;

	private JButton btnSalvar;

	private JButton btnCancelar;

	public CadastroMarca() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		setSize(300, 200);
		setTitle(CLASS_NAME);
		setLocationRelativeTo(null);
		contentPanel.setVisible(true);
		setContentPane(contentPanel);
		getContentPane().setLayout(null);
		
		adicionarComponente(new JCabecalhoLabel(CLASS_NAME), 0);
		
		adicionarComponente(new JLabel("Marca:"), 4);
		txtMarca = new JTextField();
		adicionarComponente(txtMarca, 4);
		splitPane = new JSplitPane();
		
		adicionarComponente(splitPane, 8);
		
		btnSalvar = new JButtonSalvar();
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnSalvar);
		}
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButtonCancelar();
		splitPane.setRightComponent(btnCancelar);
		
		splitPane.setDividerLocation( ( ( Double ) ( TelasUtils.DEFAULT_LARGURA_COMPONENTE * 0.30 ) ).intValue()  );
		
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

	public MarcaDao getMarcaDao() {
		return marcaDao;
	}

	public void setMarcaDao(MarcaDao marcaDao) {
		this.marcaDao = marcaDao;
	}

	public void load(Integer id) {
		init( TelasUtils.getUsuarioLogado() );
		if(id != null){
			marca = marcaDao.buscarPorId(id);
			txtMarca.setText(marca.getMarca());
		}else{
			marca = new Marca();
		}
	}
	
	private void salvar(){
		String mensagemSave = " atualizada ";
		String mensagemFail = " atualizar ";
		if(marca == null){
			marca = new Marca();
			mensagemSave = " salva ";
			mensagemFail = " salvar ";
		}
		try{
			marca.setMarca(txtMarca.getText());
			Validador<Marca> validador = new  Validador<Marca>();
			validador.validacaoCampos(marca);
			marcaDao.salvar(marca);
			JOptionPane.showMessageDialog(null, CLASS_NAME.concat(mensagemSave).concat("com sucesso.") );
			marca = null;
			txtMarca.setText(StringUtils.EMPTY);	
			dispose();
		}
		catch(ValidationException e){
			LOGGER.error(e);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Falha ao ".concat(mensagemFail).concat(" ").concat(CLASS_NAME).concat(".") );
			LOGGER.error(ex);
		}
	}

	public void init(Usuario usuario){
		ComponenteControlado<CadastroMarca> controleAcesso = new ComponenteControlado<CadastroMarca>(this); 
		controleAcesso.pronto(TelasUtils.getUsuarioLogado().getTipo());
	}
	
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put(TelasUtils.PARAM_LARGURA_COMPONENTES, -100);
		parametros.put(TelasUtils.PARAM_X, -120);
		parametros.put(TelasUtils.PARAM_ESPACO, -5);
		TelasUtils.adicionarComponente(componente, valor, contentPanel, parametros);
	}
}
