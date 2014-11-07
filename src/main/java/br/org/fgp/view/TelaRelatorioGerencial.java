package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Venda;
import br.org.fgp.model.VendaItem;
import br.org.fgp.model.xml.DadosXML;
import br.org.fgp.model.xml.MovimentacaoXML;
import br.org.fgp.model.xml.ProdutoXML;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonOk;
import br.org.fgp.view.core.JCabecalhoLabel;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller
public class TelaRelatorioGerencial extends JDialog {

	private static final String PARAM_SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String RELATORIO_ENTRADA_SAIDA_JASPER = "Relatorio/RelatorioEntradaSaida.jasper";
	private static final String SUBREPORT_DIR = "Relatorio/";

	private static final String VENDA = "VENDA";

	private static final String CLASS_NAME = "Relatório Gerencial";

	private static final String ZERO = "0";

	private static final String DATA_INICIO_PADRAO = "01/01/1800";

	private static final long serialVersionUID = -8316953380406660553L;
	
	private static final ClassLoader LOADER = TelaRelatorioGerencial.class.getClassLoader();
	
	private static final Logger LOGGER = Logger.getLogger(TelaRelatorioGerencial.class);

	@Autowired
	private VendaDao vendaDao;
	
	@Autowired
	private EntradaProdutoDao entradaProdutoDao;
	
	private JSplitPane splitPane;

	private JButton btnExecutar;

	private JButton btnCancelar;
	
	private JFormattedTextField txtDataInicio;
	
	private JFormattedTextField txtDataTermino;

	private JDialog dialog;

	private JPanel buttonPane;

	public TelaRelatorioGerencial() {
		dialog = this;
		this.setModal(true);
		setAlwaysOnTop(true);
		setBounds(0, 0, 640, 300);
		setSize(640, 300);
		setLocationRelativeTo(null);
		setTitle(CLASS_NAME);
		JPanel buttonPane = new JPanel();
		getContentPane().setLayout(null);
		getContentPane().add(buttonPane);
		
		adicionarComponente(new JCabecalhoLabel(CLASS_NAME), 0);
		
		adicionarComponente(new JLabel("Data Inicial"), 2);
		txtDataInicio = new JFormattedTextField(TelasUtils.getMascaraData());
		adicionarComponente(txtDataInicio, 2);
		
		adicionarComponente(new JLabel("Data Término"), 4);
		txtDataTermino = new JFormattedTextField(TelasUtils.getMascaraData());
		adicionarComponente(txtDataTermino, 4);
		
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 10);
		
		btnExecutar = new JButtonOk();
		btnExecutar.setText("Visualizar");
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnExecutar);
		}
		splitPane.setLeftComponent(btnExecutar);
		
		btnCancelar = new JButtonCancelar();
		splitPane.setRightComponent(btnCancelar);
		splitPane.setDividerLocation(TelasUtils.DEFAULT_LARGURA_COMPONENTE/2);
		splitPane.setEnabled(false);
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnExecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executar();
			}
		});
		
	}

	public VendaDao getVendaDao() {
		return vendaDao;
	}

	public void setVendaDao(VendaDao vendaDao) {
		this.vendaDao = vendaDao;
	}

	public EntradaProdutoDao getEntradaProdutoDao() {
		return entradaProdutoDao;
	}

	public void setEntradaProdutoDao(EntradaProdutoDao entradaProdutoDao) {
		this.entradaProdutoDao = entradaProdutoDao;
	}

	private void adicionarComponente(JComponent componente, int valor){
		Map<String, Integer> parametros = new HashMap<String, Integer>();
		TelasUtils.adicionarComponente(componente, valor, this, parametros);
	}
	
	private void cancelar() {
		this.dispose();
	}
	
	private void executar() {
		try{
			Date dataInicio = null;
			Date dataTermino = null;
			if(StringUtils.isNotBlank( txtDataInicio.getText() ) ){
				dataInicio = converteData(txtDataInicio.getText() );
				if(dataInicio == null){
					dataInicio = converteData(DATA_INICIO_PADRAO);
				}
			}
			if(StringUtils.isNotBlank( txtDataTermino.getText() ) ){
				dataTermino = converteData(txtDataTermino.getText() );
				if(dataTermino == null){
					dataTermino = new Date();
				}
			}
			List<Venda> listaVenda = vendaDao.buscarPorFaixa(dataInicio, dataTermino);
			List<EntradaProduto> listaEntrada = entradaProdutoDao.buscarPorFaixa(dataInicio, dataTermino);
			String xml = gerarXml(listaVenda, listaEntrada);
			gerarRelatorio(xml);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Falha ao gerar relatório" );
		}
	}

	private String gerarXml(List<Venda> listaVenda,List<EntradaProduto> listaEntrada) {
		DadosXML dados = new DadosXML();
		BigDecimal lucroBruto = new BigDecimal(ZERO);
		BigDecimal lucroLiquido = new BigDecimal(ZERO);
		dados.setMovimentacao(new ArrayList<MovimentacaoXML>());
		String mes = StringUtils.EMPTY;
		MovimentacaoXML movimentacao = new MovimentacaoXML();
		for (EntradaProduto entradaProduto : listaEntrada) {
			Date data = entradaProduto.getData();
			Calendar dataCalendar = Calendar.getInstance();
			dataCalendar.setTime(data);
			String mes2 = TelasUtils.formataMes(dataCalendar.get(Calendar.MONTH) );
			if(! mes.equals(mes2) ){
				mes = mes2;
				dados.getMovimentacao().add(movimentacao);
				movimentacao = new MovimentacaoXML();
				movimentacao.setProdutos( new ArrayList<ProdutoXML>() );
			}
			movimentacao.setMes( mes );
			movimentacao.setProdutos(new ArrayList<ProdutoXML>());
			movimentacao.setData( TelasUtils.formataData(data) );
			movimentacao.setTipo(VENDA);
			ProdutoXML produtoXml = new ProdutoXML();
			produtoXml.setDescricao( entradaProduto.getProdutoTexto() );
			produtoXml.setPreco( entradaProduto.getProduto().getPrecoUnitario() );
			produtoXml.setQuantidade( entradaProduto.getQuantidade() );
			movimentacao.getProdutos().add( produtoXml );
			lucroLiquido = lucroBruto.subtract(  produtoXml.getPreco().multiply( new BigDecimal(produtoXml.getQuantidade() ) ) );
		}
		mes = StringUtils.EMPTY;
		movimentacao = new MovimentacaoXML();
		for (Venda venda : listaVenda) {
			Date data = venda.getData();
			Calendar dataCalendar = Calendar.getInstance();
			dataCalendar.setTime(data);
			String mes2 = TelasUtils.formataMes(dataCalendar.get(Calendar.MONTH) );
			if(! mes.equals(mes2) ){
				mes = mes2;
				dados.getMovimentacao().add(movimentacao);
				movimentacao = new MovimentacaoXML();
				movimentacao.setProdutos( new ArrayList<ProdutoXML>() );
			}
			movimentacao.setMes( mes );
			movimentacao.setProdutos(new ArrayList<ProdutoXML>());
			movimentacao.setData( TelasUtils.formataData(data) );
			movimentacao.setTipo(VENDA);
			for (VendaItem vendaItem : venda.getListaItem()) {
				ProdutoXML produtoXml = new ProdutoXML();
				produtoXml.setDescricao( vendaItem.getProdutoTexto() );
				produtoXml.setPreco( vendaItem.getProduto().getPrecoUnitario() );
				produtoXml.setQuantidade( vendaItem.getQuantidade() );
				movimentacao.getProdutos().add( produtoXml );
				
				lucroBruto = lucroBruto.add(  produtoXml.getPreco().multiply( new BigDecimal(produtoXml.getQuantidade() ) ) );
				lucroLiquido = lucroBruto.add(  produtoXml.getPreco().multiply( new BigDecimal(produtoXml.getQuantidade() ) ) );
			}
		}
		dados.setLucroBruto(lucroBruto);
		dados.setLucroLiquido(lucroLiquido);
		
		XStream xStream = new XStream(new DomDriver());
		xStream.autodetectAnnotations(true);
		return xStream.toXML(dados);
	}

	private void gerarRelatorio(String xml) throws JRException {
		JRDataSource dataSource = new JRXmlDataSource(xml);
		JasperReport report = (JasperReport) JRLoader.loadObject(LOADER.getResourceAsStream(RELATORIO_ENTRADA_SAIDA_JASPER));
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(PARAM_SUBREPORT_DIR, LOADER.getResourceAsStream(SUBREPORT_DIR));
		JasperPrint impressao = JasperFillManager.fillReport(report, parametros, dataSource);
	}
	
	private Date converteData(String dataTexto){
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try{
			data = formatador.parse(dataTexto);
		}catch (Exception e){
			LOGGER.warn(e);
		}
		return data;
	}
	
}
