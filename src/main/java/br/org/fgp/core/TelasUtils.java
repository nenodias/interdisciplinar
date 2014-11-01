package br.org.fgp.core;

import java.awt.Container;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.apache.log4j.Logger;

import br.org.fgp.model.Categoria;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Produto;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Usuario;
import br.org.fgp.view.CadastroCategoria;
import br.org.fgp.view.CadastroEntradaProduto;
import br.org.fgp.view.CadastroFornecedor;
import br.org.fgp.view.CadastroMarca;
import br.org.fgp.view.CadastroProduto;
import br.org.fgp.view.CadastroSetor;
import br.org.fgp.view.CadastroUsuario;

public class TelasUtils {

	private static final Logger LOGGER = Logger.getLogger(TelasUtils.class);
	public static final int DEFAULT_ALTURA_COMPONENTE = 25;
	public static final int DEFAULT_LARGURA_COMPONENTE = 300;
	public static final int DEFAULT_X = 0;
	public static final int DEFAULT_Y = 0;
	public static final int DEFAULT_ESPACO = ((Double)( DEFAULT_ALTURA_COMPONENTE * 0.75 )).intValue();
	public static final String PARAM_X = "X";
	public static final String PARAM_Y = "Y";
	public static final String PARAM_LARGURA_COMPONENTES = "LARGURA_COMPONENTES";
	public static final String PARAM_ALTURA_COMPONENTES = "ALTURA_COMPONENTES";
	public static final String PARAM_ESPACO = "ESPACO";
	private static Usuario usuarioLogado;
	
	public static Class<?> getView(Class<?> clazz){
		Map<Class<?>, Class<?>> mapa = new HashMap<Class<?>, Class<?>>();
		
		mapa.put(Categoria.class, CadastroCategoria.class);
		mapa.put(EntradaProduto.class, CadastroEntradaProduto.class);
		mapa.put(Fornecedor.class, CadastroFornecedor.class);
		mapa.put(Marca.class, CadastroMarca.class);
		mapa.put(Produto.class, CadastroProduto.class);
		mapa.put(Setor.class, CadastroSetor.class);
		mapa.put(Usuario.class, CadastroUsuario.class);
		
		return mapa.get(clazz);
	}

	public synchronized static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		TelasUtils.usuarioLogado = usuarioLogado;
	}
	
	public static void adicionarComponente(JComponent componente, int valor, Container objetoPai, Map<String, Integer> parametros){
		int X = parametros.containsKey(PARAM_X) ? parametros.get(PARAM_X) : DEFAULT_X; 
		int Y = parametros.containsKey(PARAM_Y) ? parametros.get(PARAM_Y) : DEFAULT_Y; 
		int LARGURA_COMPONENTES = parametros.containsKey(PARAM_LARGURA_COMPONENTES) ? parametros.get(PARAM_LARGURA_COMPONENTES) + DEFAULT_LARGURA_COMPONENTE : DEFAULT_LARGURA_COMPONENTE;
		int ALTURA_COMPONENTES = parametros.containsKey(PARAM_ALTURA_COMPONENTES) ? parametros.get(PARAM_ALTURA_COMPONENTES) + DEFAULT_ALTURA_COMPONENTE : DEFAULT_ALTURA_COMPONENTE;
		int ESPACO = parametros.containsKey(PARAM_ESPACO) ? parametros.get(PARAM_ESPACO) + DEFAULT_ESPACO : DEFAULT_ESPACO;
		if(componente instanceof JLabel){
			componente.setBounds(X  , Y +(valor * ESPACO ) , LARGURA_COMPONENTES, ALTURA_COMPONENTES);
			JLabel jLabel = (JLabel)componente;
			jLabel.setHorizontalAlignment(JLabel.RIGHT);
		}else{
			componente.setBounds(X  +LARGURA_COMPONENTES , Y+ (valor * ESPACO ), LARGURA_COMPONENTES, ALTURA_COMPONENTES);
		}
		if (componente instanceof JTextField) {
			JTextField textField = (JTextField) componente;
			textField.setColumns(10);
		}else if(componente instanceof JPasswordField){
			JPasswordField jPasswordField = (JPasswordField)componente;
			jPasswordField.setEchoChar('#');
		}
		objetoPai.add(componente);
		componente.setVisible(true);
	}
	
	public static NumberFormatter getFormatadorInteiro(){
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setCommitsOnValidEdit(true);
	    return formatter;
	}
	
	public static NumberFormatter getFormatadorDecimal(){
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(2);
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(BigDecimal.class);
	    formatter.setMinimum(0);
	    formatter.setCommitsOnValidEdit(true);
	    return formatter;
	}
	
	public static MaskFormatter getMascaraTelefone(){
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("(##) # ####-####");
			mascara.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return mascara;
	}
	
	public static MaskFormatter getMascaraCPF(){
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("###.###.###-##");
			mascara.setValueContainsLiteralCharacters(true);
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return mascara;
	}
}