package br.org.fgp.view.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.org.fgp.core.InterdisciplinarReflectionUtil;
import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.view.annotations.LabelDescricao;

public class JBusca <T, PK> extends JPanel {
	
	private static final long serialVersionUID = -314039162074460134L;

	private static final Logger LOGGER = Logger.getLogger(JBusca.class);
	
	private JTextField descricaoComponente;
	
	private JTextField codigoComponente;
	
	private GenericoDao<T,PK> daoGenerico;

	private JButton botao;

	/**
	 * Create the panel.
	 */
	public JBusca() {
		descricaoComponente = new JTextField();
		descricaoComponente.setEditable(false);
		descricaoComponente.setEnabled(false);
		add(descricaoComponente);
		descricaoComponente.setColumns(10);
		
		codigoComponente = new JTextField();
		codigoComponente.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(daoGenerico != null){
					String codigoTxt = codigoComponente.getText();
					if(StringUtils.isNotEmpty(codigoTxt)&& StringUtils.isNotBlank(codigoTxt)){
						try{
							PK codigoConvertido = (PK) daoGenerico.getPKClass().cast(codigoTxt);
							T retorno = daoGenerico.buscarPorId( codigoConvertido );
							if(retorno != null){
								Class classe = daoGenerico.getObjectClass();
								List<Field> campos = InterdisciplinarReflectionUtil.getAtributoComAnotacao(classe, LabelDescricao.class);
								if(!campos.isEmpty()){
									Field campo = campos.get(0);
									Method metodoGet = InterdisciplinarReflectionUtil.getMetodoGet(classe, campo);
									Object descricao = metodoGet.invoke(retorno, null);
									if(descricao != null && descricao instanceof String){
										descricaoComponente.setText( (String) descricao );
									}
								}
							}
						}catch(Exception ex){
							LOGGER.info("Código não encontrado, ou de tipo incompatível", ex);
						}
					}
				}
			}
			
		});
		add(codigoComponente);
		codigoComponente.setColumns(10);
		
		botao = new JButton("...");
		add(botao);
		botao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialogBusca<T, PK> dialogo = new JDialogBusca<T, PK>(daoGenerico);
				dialogo.setVisible(true);
			}
		});
	}
	
	public String getText(){
		return codigoComponente.getText();
	}

	public String getLabelText(){
		return descricaoComponente.getText();
	}

	public GenericoDao<T, PK> getDaoGenerico() {
		return daoGenerico;
	}

	public void setDaoGenerico(GenericoDao<T, PK> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}
	
	
}
