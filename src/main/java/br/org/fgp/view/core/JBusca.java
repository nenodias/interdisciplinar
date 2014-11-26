package br.org.fgp.view.core;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EventListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.org.fgp.core.InterdisciplinarReflectionUtil;
import br.org.fgp.core.TelasUtils;
import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.view.annotations.LabelDescricao;

public class JBusca <T, PK> extends JPanel implements EventListener {
	
	private static final long serialVersionUID = -314039162074460134L;

	private static final Logger LOGGER = Logger.getLogger(JBusca.class);
	
	private JTextField descricaoComponente;
	
	private JCodigoComponente codigoComponente;
	
	private GenericoDao<T,PK> daoGenerico;

	private JButton botao;
	
	private JBusca<T, PK> jBusca;

	public JBusca() {
		init();
	}
	private void init() {
		jBusca = this;
		descricaoComponente = new JTextField();
		descricaoComponente.setEnabled(false);
		add(descricaoComponente);
		descricaoComponente.setColumns(10);
		
		codigoComponente = new JCodigoComponente(TelasUtils.getFormatadorInteiro());
		codigoComponente.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				eventoPerdeFoco();
			}

		});
		add(codigoComponente);
		codigoComponente.setColumns(10);
		
		botao = new JButton("...");
		add(botao);
		botao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eventoBotao();
				
			}
		});
	}
	
	@SuppressWarnings({"all"})
	private void eventoPerdeFoco() {
		if(daoGenerico != null){
			String codigoTxt = codigoComponente.getText();
			if(StringUtils.isNotEmpty(codigoTxt)&& StringUtils.isNotBlank(codigoTxt)){
				try{
					T retorno = null;
					if(Integer.class.equals( daoGenerico.getPKClass() ) ) {
					PK codigoConvertido = (PK) ( daoGenerico.getPKClass().cast(Integer.parseInt(codigoTxt)));
					retorno = daoGenerico.buscarPorId( codigoConvertido );
					}
					if(retorno != null){
						Class<?> classe = daoGenerico.getObjectClass();
						List<Field> campos = InterdisciplinarReflectionUtil.getAtributoComAnotacao(classe, LabelDescricao.class);
						if(!campos.isEmpty()){
							Field campo = campos.get(0);
							Method metodoGet = InterdisciplinarReflectionUtil.getMetodoGet(classe, campo);
							Object descricao = metodoGet.invoke(retorno, null);
							if(descricao != null && descricao instanceof String){
								descricaoComponente.setText( (String) descricao );
							}
						}
					}else{
						descricaoComponente.setText("");
					}
				}catch(Exception ex){
					LOGGER.info("Código não encontrado, ou de tipo incompatível", ex);
				}
			}
		}
	}
	public String getText(){
		return codigoComponente.getText();
	}

	public void setText(String text){
		codigoComponente.setText(text);
		descricaoComponente.setText(text);
		eventoPerdeFoco();
	}
	
	public String getLabelText(){
		return descricaoComponente.getText();
	}

	public GenericoDao<T, PK> getDaoGenerico() {
		return daoGenerico;
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		codigoComponente.setEnabled(enabled);
		botao.setEnabled(enabled);
	}
	
	public void setDaoGenerico(GenericoDao<T, PK> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x , y-7, width, height+10);
		int largura = ( (Double) (width * 0.4) ).intValue();
		int larguraBtn = ( (Double) (width * 0.1) ).intValue();
		codigoComponente.setSize(largura, height);
		descricaoComponente.setSize(largura, height);
		botao.setSize(larguraBtn, height);
	}
	private void eventoBotao() {
		if(daoGenerico != null){
			JDialogBusca<T, PK> dialogo = null;
			Component component = jBusca.getParent();
			while(! (component instanceof JFrame || component instanceof JDialog)){
				component = component.getParent();
			}
			if(component instanceof Frame){
				Frame frame = (Frame) component;
				dialogo = new JDialogBusca<T, PK>(daoGenerico, codigoComponente, descricaoComponente, frame);
			}else if(component instanceof Dialog){
				Dialog dialog = (Dialog) component;
				dialogo = new JDialogBusca<T, PK>(daoGenerico, codigoComponente, descricaoComponente, dialog);
			}
			dialogo.setLocationRelativeTo(component);
			dialogo.setVisible(true);
		}
	}
	
	public void limpar(){
		codigoComponente.setText(StringUtils.EMPTY);
		descricaoComponente.setText(StringUtils.EMPTY);
	}
	public JTextField getDescricaoComponente() {
		return descricaoComponente;
	}

	public JCodigoComponente getCodigoComponente() {
		return codigoComponente;
	}
}
