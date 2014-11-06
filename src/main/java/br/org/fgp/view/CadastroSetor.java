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

import br.org.fgp.annotations.Permissao;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.SetorDao;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Usuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonSalvar;
import br.org.fgp.view.core.JCabecalhoLabel;
import br.org.fgp.view.core.Validador;

@Controller
public class CadastroSetor extends JDialog implements Inicializavel {

	private static final long serialVersionUID = -5360024164470109759L;
	
	private static final Logger LOGGER = Logger.getLogger(CadastroCategoria.class);

	private static final String CLASS_NAME = "Setor";   

	private final JPanel contentPanel = new JPanel();
	
	@Permissao
	private JTextField txtSetor;

	@Autowired
	private SetorDao setorDao;
	
	private Setor setor;

	private JSplitPane splitPane;

	@Permissao
	private JButton btnSalvar;

	private JButton btnCancelar;
	
	public CadastroSetor() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		setSize(300, 200);
		setTitle(CLASS_NAME);
		setLocationRelativeTo(null);
		contentPanel.setVisible(true);
		setContentPane(contentPanel);
		getContentPane().setLayout(null);
		
		adicionarComponente(new JCabecalhoLabel(CLASS_NAME), 0);
		
		adicionarComponente(new JLabel("Setor:"), 4);
		txtSetor = new JTextField();
		adicionarComponente(txtSetor, 4);
		splitPane = new JSplitPane();
		
		adicionarComponente(splitPane, 8);
		
		btnSalvar = new JButtonSalvar();
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnSalvar);
		}
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButtonCancelar();
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
		String mensagemSave = " atualizado ";
		String mensagemFail = " atualizar ";
		if(setor == null){
			setor = new Setor();
			if(setor.getId() != null){
				mensagemSave = " salvo ";
			}
		}
		try{
			setor.setSetor(txtSetor.getText());
			Validador<Setor> validador = new  Validador<Setor>();
			validador.validacaoCampos(setor);
			setorDao.salvar(setor);
			JOptionPane.showMessageDialog(null, CLASS_NAME.concat(mensagemSave).concat("com sucesso.") );
			setor = null;
			txtSetor.setText(StringUtils.EMPTY);	
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
	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		parametros.put(TelasUtils.PARAM_LARGURA_COMPONENTES, -140);
		parametros.put(TelasUtils.PARAM_X, -40);
		parametros.put(TelasUtils.PARAM_ESPACO, -5);
		TelasUtils.adicionarComponente(componente, valor, contentPanel, parametros);
	}

	public SetorDao getSetorDao() {
		return setorDao;
	}

	public void setSetorDao(SetorDao setorDao) {
		this.setorDao = setorDao;
	}

	public void load(Integer id) {
		init( TelasUtils.getUsuarioLogado() );
		if(id != null){
			setor = setorDao.buscarPorId(id);
			txtSetor.setText(setor.getSetor());
		}else{
			setor = new Setor();
		}
	}

	public void init(Usuario usuario){
		ComponenteControlado<CadastroSetor> controleAcesso = new ComponenteControlado<CadastroSetor>(this); 
		controleAcesso.pronto(TelasUtils.getUsuarioLogado().getTipo());
	}

	public JTextField getTxtSetor() {
		return txtSetor;
	}

	public void setTxtSetor(JTextField txtSetor) {
		this.txtSetor = txtSetor;
	}

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}
	
	
}
