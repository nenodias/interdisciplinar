package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Venda;
import br.org.fgp.model.xml.DadosXML;
import br.org.fgp.model.xml.MovimentacaoXML;
import br.org.fgp.view.core.JButtonCancelar;
import br.org.fgp.view.core.JButtonOk;
import br.org.fgp.view.core.JCabecalhoLabel;

@Controller
public class TelaRelatorioGerencial extends JPanel {

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

	public TelaRelatorioGerencial() {
		
		adicionarComponente(new JCabecalhoLabel("Relatório Gerencial"), 0);
		
		adicionarComponente(new JLabel("Data Inicial"), 2);
		txtDataInicio = new JFormattedTextField(TelasUtils.getMascaraData());
		adicionarComponente(txtDataInicio, 2);
		
		adicionarComponente(new JLabel("Data Término"), 4);
		txtDataTermino = new JFormattedTextField(TelasUtils.getMascaraData());
		adicionarComponente(txtDataTermino, 4);
		
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 28);
		
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
		TelaPrincipal telaPrincipal = ApplicationContextConfig.getContext().getBean(TelaPrincipal.class);
		telaPrincipal.cancelar();
	}
	
	private void executar() {
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
		try{
			List<Venda> listaVenda = vendaDao.buscarPorFaixa(dataInicio, dataTermino);
			List<EntradaProduto> listaEntrada = entradaProdutoDao.buscarPorFaixa(dataInicio, dataTermino);
			String xml = gerarXml(listaVenda, listaEntrada);
			gerarRelatorio(xml);
		}catch (Exception e){
		}
	}

	private String gerarXml(List<Venda> listaVenda,List<EntradaProduto> listaEntrada) {
		DadosXML dados = new DadosXML();
		dados.setLucroBruto(new BigDecimal(ZERO));
		dados.setLucroLiquido(new BigDecimal(ZERO));
		for (EntradaProduto entradaProduto : listaEntrada) {
			MovimentacaoXML movimentacao = new MovimentacaoXML();
		}
		return null;
	}

	private void gerarRelatorio(String xml) throws JRException {
		JRDataSource dataSource = new JRXmlDataSource(xml);
		JasperReport report = (JasperReport) JRLoader.loadObject(LOADER.getResourceAsStream("Relatorio/RelatorioEntradaSaida.jasper"));
		Map<String, Object> parametros = new HashMap<String, Object>();
		Object impressao = JasperFillManager.fillReport(report, parametros, dataSource);
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
