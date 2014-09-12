package br.org.fgp.view.core;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import br.org.fgp.core.InterdisciplinarReflectionUtil;
import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.view.annotations.DescricaoComponente;

public class JBusca <T, PK> extends JPanel {
	
	private static final long serialVersionUID = -314039162074460134L;

	private static final Logger LOGGER = Logger.getLogger(JBusca.class);
	
	private JTextField descricaoComponente;
	
	private JTextField codigoComponente;
	
	private GenericoDao<T,PK> daoGenerico;

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
					try{
						PK codigoConvertido = (PK) daoGenerico.getPKClass().cast(codigoTxt);
						T retorno = daoGenerico.buscarPorId( codigoConvertido );
						if(retorno != null){
							Class classe = daoGenerico.getObjectClass();
							List<Field> campos = InterdisciplinarReflectionUtil.getAtributoComAnotacao(classe, DescricaoComponente.class);
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
			
		});
		add(codigoComponente);
		codigoComponente.setColumns(10);
		
		JButton button = new JButton("...");
		add(button);
	}
	
	public String getText(){
		return codigoComponente.getText();
	}

	public String getLabelText(){
		return descricaoComponente.getText();
	}
}
