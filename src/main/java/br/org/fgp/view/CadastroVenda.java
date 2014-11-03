package br.org.fgp.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.springframework.beans.factory.annotation.Autowired;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.Venda;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;

public class CadastroVenda extends JPanel implements Inicializavel {
	
	private Venda venda;
	
	@Autowired
	private VendaDao vendaDao;

	private JSplitPane splitPane;

	private JButton btnSalvar;

	private JButton btnCancelar;
	
	private JPanel painel;

	public CadastroVenda() {
		painel = this;
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 28);
		
		btnSalvar = new JButton("Salvar");
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnSalvar);
		}
		splitPane.setLeftComponent(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(TelasUtils.DEFAULT_LARGURA_COMPONENTE/2);
		splitPane.setEnabled(false);
		
		ComponenteControlado<CadastroVenda> controleAcesso = new ComponenteControlado<CadastroVenda>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR);
		
	}

	@Override
	public void load(Integer id) {
		if(id != null){
			venda = vendaDao.buscarPorId(id);
		}
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			
		}else{
			venda = new Venda();
		}
	}

	private void init(Usuario usuarioLogado) {
		ComponenteControlado<CadastroVenda> controleAcesso = new ComponenteControlado<CadastroVenda>(this);
		controleAcesso.pronto(usuarioLogado.getTipo());
		repaint();
		revalidate();
	}

	public VendaDao getVendaDao() {
		return vendaDao;
	}

	public void setVendaDao(VendaDao vendaDao) {
		vendaDao = vendaDao;
	}

	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}
	
	private void cancelar() {
		TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		telaPrincipal.cancelar();
	}
	
	private void limparComponentes() {
		//TODO
	}
	
	private void salvar() {
		//TODO
	}
}
