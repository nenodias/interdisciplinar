package br.org.fgp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.view.core.JCabecalhoLabel;

@Controller
public class TelaRelatórioGerencial extends JPanel {

	private static final long serialVersionUID = -8316953380406660553L;
	
	private static final ClassLoader LOADER = TelaRelatórioGerencial.class.getClassLoader();
	
	private static final Logger LOGGER = Logger.getLogger(TelaRelatórioGerencial.class);

	@Autowired
	private VendaDao vendaDao;
	
	@Autowired
	private EntradaProdutoDao entradaProdutoDao;
	
	private JSplitPane splitPane;

	private JButton btnExecutar;

	private JButton btnCancelar;
	
	private JFormattedTextField txtDataInicio;
	
	private JFormattedTextField txtDataTermino;

	private JPanel painel;

	public TelaRelatórioGerencial() {
		painel = this;
		
		adicionarComponente(new JCabecalhoLabel("Relatório Gerencial"), 0);
		
		adicionarComponente(new JLabel("Data Inicial"), 2);
		txtDataInicio = new JFormattedTextField(TelasUtils.getMascaraData());
		adicionarComponente(txtDataInicio, 2);
		
		adicionarComponente(new JLabel("Data Término"), 4);
		txtDataTermino = new JFormattedTextField(TelasUtils.getMascaraData());
		adicionarComponente(txtDataTermino, 4);
		
		splitPane = new JSplitPane();
		adicionarComponente(splitPane, 28);
		
		btnExecutar = new JButton("Visualizar");
		if(getRootPane() != null){
			getRootPane().setDefaultButton(btnExecutar);
		}
		splitPane.setLeftComponent(btnExecutar);
		
		btnCancelar = new JButton("Cancelar");
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
		vendaDao = vendaDao;
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
		String xml = "";
		try{
			gerarRelatorio(xml);
		}catch (Exception e){
		}
	}

	private void gerarRelatorio(String xml) throws JRException {
		JRDataSource dataSource = new JRXmlDataSource(xml);
		JasperReport report = (JasperReport) JRLoader.loadObject(LOADER.getResourceAsStream("Relatorio/RelatorioEntradaSaida.jasper"));
		Map<String, Object> parametros = new HashMap<String, Object>();
		Object impressao = JasperFillManager.fillReport(report, parametros, dataSource);
	}
}
