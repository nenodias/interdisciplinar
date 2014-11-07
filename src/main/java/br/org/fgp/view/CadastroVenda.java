package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.validation.ValidationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.dao.VendaItemDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.UsuarioTelefone;
import br.org.fgp.model.Venda;
import br.org.fgp.model.VendaItem;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.view.core.ButtonColumnEditar;
import br.org.fgp.view.core.ButtonColumnExcluir;
import br.org.fgp.view.core.ComponenteControlado;
import br.org.fgp.view.core.Inicializavel;
import br.org.fgp.view.core.JButtonAdicionar;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonSalvar;
import br.org.fgp.view.core.JCabecalhoLabel;
import br.org.fgp.view.core.TableModelGenerico;
import br.org.fgp.view.core.Validador;

@Controller
public class CadastroVenda extends JPanel implements Inicializavel {
	
	private static final String VALOR_TOTAL = "Valor Total:";

	private static final long serialVersionUID = -3837350240175943970L;

	private static final Logger LOGGER = Logger.getLogger(CadastroVenda.class);

	private static final String CLASS_NAME = "Venda";
	
	private Venda venda;
	
	@Autowired
	private VendaDao vendaDao;
	
	@Autowired
	private VendaItemDao vendaItemDao;

	private JSplitPane splitPane;

	private JButton btnSalvar;

	private JButton btnCancelar;
	
	private JScrollPane painelTabela;

	private JTable tabela;
	
	private JPanel painel;

	private List<VendaItem> listaItens;

	private TableModelGenerico<VendaItem> modelGenerico;

	private JLabel txtValorTotal;

	private BigDecimal total;

	public CadastroVenda() {
		painel = this;
		setLayout(null);
		adicionarComponente(new JCabecalhoLabel("Vendas"), 0);
		
		painelTabela = new JScrollPane();
		tabela = new JTable();
		if(venda != null && venda.getListaItem() != null ){
			listaItens = venda.getListaItem();
		}else{
			listaItens = new ArrayList<VendaItem>();
		}
		modelGenerico = new TableModelGenerico<VendaItem>(listaItens, VendaItem.class);
		JButton btnAdicionarItem = new JButtonAdicionar();
		btnAdicionarItem.setBounds(TelasUtils.DEFAULT_X+ TelasUtils.DEFAULT_ESPACO , TelasUtils.DEFAULT_Y +(2 *TelasUtils.DEFAULT_ESPACO) , TelasUtils.DEFAULT_LARGURA_COMPONENTE /2, TelasUtils.DEFAULT_ALTURA_COMPONENTE);
		add(btnAdicionarItem);
		tabela.setModel(modelGenerico);
		tabela.setEnabled(true);
		painelTabela.setViewportView(tabela);
		painelTabela.setEnabled(true);
		painelTabela.setBounds(TelasUtils.DEFAULT_X+ TelasUtils.DEFAULT_ESPACO , TelasUtils.DEFAULT_Y +(4 *TelasUtils.DEFAULT_ESPACO) , TelasUtils.DEFAULT_LARGURA_COMPONENTE*2, TelasUtils.DEFAULT_ALTURA_COMPONENTE+TelasUtils.DEFAULT_ALTURA_COMPONENTE*8);
		add(painelTabela);
		painelTabela.setVisible(true);
		
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 28);
		
		btnSalvar = new JButtonSalvar();
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnSalvar);
		}
		splitPane.setLeftComponent(btnSalvar);
		btnCancelar = new JButtonCancelar();
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(TelasUtils.DEFAULT_LARGURA_COMPONENTE/2);
		splitPane.setEnabled(false);
		
		txtValorTotal = new JCabecalhoLabel(VALOR_TOTAL);
		adicionarComponente(txtValorTotal, 20);
		
		ComponenteControlado<CadastroVenda> controleAcesso = new ComponenteControlado<CadastroVenda>(this); 
		controleAcesso.pronto(TipoUsuario.ADMINISTRADOR); 
		
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eventoCliqueTabela(e);
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}

		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}

		});
		
		btnAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarVendaItem();
			}
		});
		
	}

	@Override
	public void load(Integer id) {
		if(id != null){
			venda = vendaDao.buscarPorId(id);
			listaItens = venda.getListaItem();
		}
		init(TelasUtils.getUsuarioLogado());
		if(id != null){
			
		}else{
			venda = new Venda();
			listaItens.clear();
		}
		atualizaDesenhoTabela();
	}

	private void init(Usuario usuarioLogado) {
		ComponenteControlado<CadastroVenda> controleAcesso = new ComponenteControlado<CadastroVenda>(this);
		controleAcesso.pronto(usuarioLogado.getTipo());
		repaint();
		revalidate();
	}

	public VendaItemDao getVendaItemDao() {
		return vendaItemDao;
	}

	public void setVendaItemDao(VendaItemDao vendaItemDao) {
		this.vendaItemDao = vendaItemDao;
	}

	public VendaDao getVendaDao() {
		return vendaDao;
	}

	public void setVendaDao(VendaDao vendaDao) {
		this.vendaDao = vendaDao;
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
		listaItens.clear();
		atualizaDesenhoTabela();
	}
	
	private void salvar() {
		String mensagemSave = " atualizado ";
		String mensagemFail = " atualizar ";
		try{
			if(venda == null ){
				venda = new Venda();
				if(venda.getId() != null){
					mensagemSave = " salvo ";
					mensagemFail = " salvar ";
				}
			}
			
			Validador<Venda> validador = new Validador<Venda>();
			validador.validacaoCampos(venda);
			venda.setUsuario(TelasUtils.getUsuarioLogado());
			if(!listaItens.isEmpty()){
				venda.setData(new Date());
				total = BigDecimal.ZERO;
				somaTotal();
				venda.setValorTotal(total);
				vendaDao.salvar(venda);
			}else{
				throw new Exception("Erro venda sem itens");
			}
			if(!listaItens.isEmpty()){
				if(venda.getId() != null){
					vendaItemDao.deletarPorIdVenda(venda.getId());
				}
				for (VendaItem vendaItem : listaItens) {
					vendaItem.setId(null);
					vendaItem.setVenda(venda);
					vendaItemDao.salvar(vendaItem);
				}
			}
			JOptionPane.showMessageDialog(null, CLASS_NAME.concat(mensagemSave).concat("com sucesso.") );
			venda = null;
			limparComponentes();
			cancelar();
		}
		catch(ValidationException e){
			LOGGER.error(e);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Falha ao ".concat(mensagemFail).concat(" ").concat(CLASS_NAME).concat(".") );
			LOGGER.error(ex);
		}
	}
	
	private void adicionarVendaItem() {
		final VendaItem vendaItem = new VendaItem();
		abrirModalVendaItem(vendaItem);
		if(vendaItem.getProduto() != null && vendaItem.getValorUnitario() != null && vendaItem.getQuantidade() != null ){
			listaItens.add(vendaItem);
			atualizaDesenhoTabela();
			if(listaItens != null){
				total = BigDecimal.ZERO;
				somaTotal();
				txtValorTotal.setText(VALOR_TOTAL+total);
			}
		}
	}

	private void somaTotal() {
		for (VendaItem vendaItemAux : listaItens) {
			total = total.add(  vendaItemAux.getValorUnitario().multiply( new BigDecimal(vendaItemAux.getQuantidade() ) ) );
		}
	}

	private void abrirModalVendaItem(final VendaItem vendaItem) {
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					CadastroVendaItem dialog = new CadastroVendaItem(vendaItem);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(painel);
					dialog.setVisible(true);
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
		};
		runnable.run();
	}
	
	@SuppressWarnings("unchecked")
	private void eventoCliqueTabela(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TableModelGenerico<UsuarioTelefone> model = (TableModelGenerico<UsuarioTelefone>) tabela.getModel();
			boolean atualizaTabela = false;
			int linha = tabela.getSelectedRow();
			int coluna = tabela.getSelectedColumn();
			VendaItem vendaItem = listaItens.get(linha);
			
			if(coluna == model.getCountadorColunas() ){
				abrirModalVendaItem(vendaItem);
				atualizaTabela = true;
			} else if(coluna == model.getCountadorColunas() +1 ){
				int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro: "+vendaItem.getProdutoTexto() + " ?", "Excluir?", JOptionPane.YES_NO_OPTION);
				if(excluir == JOptionPane.YES_OPTION){
					int contador = 0;
					while(true){
						if(listaItens.get(contador).equals(vendaItem) ){
							VendaItem vendItem = listaItens.get(contador);
							listaItens.remove( vendItem );
							break;
						}
						contador++;
					}
				}
				atualizaTabela = true;
			}
			if(atualizaTabela){
				atualizaDesenhoTabela();
			}
		}
	}

	private void atualizaDesenhoTabela() {
		if(listaItens.size() > 0){
			new ButtonColumnEditar(tabela, null, modelGenerico.getCountadorColunas() );
			new ButtonColumnExcluir(tabela, null, modelGenerico.getCountadorColunas() + 1 );
		}
		if(!modelGenerico.getLista().equals(listaItens)){
			modelGenerico.getLista().clear();
			for (VendaItem vendaItem : listaItens) {
				modelGenerico.getLista().add(vendaItem);
			}
			listaItens = (List<VendaItem>) modelGenerico.getLista();
			
		}
		modelGenerico.fireTableDataChanged();
		tabela.updateUI();
	}
}
