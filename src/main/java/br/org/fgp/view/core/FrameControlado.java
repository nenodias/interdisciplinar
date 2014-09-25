package br.org.fgp.view.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import br.org.fgp.annotations.Permissao;
import br.org.fgp.model.enums.TipoUsuario;

/**
 * @author nenodias
 *
 * Para utilizar o nível de acesso nos atributos que herdam dos JComponent
 * Trocar os JFrames por FrameControlado
 * E após o método construtor inicializar todos os componentes, deve se chamar o metodo pronto
 *  passando o TipoUsuario que o Usuario logado possui 
 * Os atributos da classe anotados com @Permissao deverão possuir o metodo getNomeDoCampo para retornar o componente
 */
public abstract class FrameControlado extends JFrame{

	private static final String LITERAL_GET = "get";

	private static final long serialVersionUID = -1660186148473856758L;
	
	private static final Logger LOGGER = Logger.getLogger(FrameControlado.class); 
	
	public void pronto(TipoUsuario tipoUsuario){
		pronto(tipoUsuario,this);
	}
	
	private void pronto(TipoUsuario tipoUsuario, FrameControlado frame){
		Class<? extends FrameControlado> clazz = this.getClass();
		Field[] camposDeclarados = clazz.getDeclaredFields();
		for (Field campo : camposDeclarados) {
			Permissao permissao = campo.getAnnotation(Permissao.class);
			if(permissao != null && !permissao.tipo().equals(tipoUsuario)){
				try {
					String campoUpperFirstWord = upperFirstWord( campo.getName() );
					String nomeMetodo = LITERAL_GET.concat(campoUpperFirstWord);
					Method metodo = clazz.getMethod(nomeMetodo,null);
					if(metodo != null){
						Object objeto = metodo.invoke(frame, null);
						if(objeto instanceof JComponent){
							JComponent componente = (JComponent)objeto;
							componente.setEnabled(false);
						}
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}
	
	private String upperFirstWord(String texto){
		String retorno = null;
		if(texto != null && texto.length() > 1){
			String resto = texto.substring(1);
			String first = texto.substring(0, 1);
			retorno = first.toUpperCase().concat(resto);
		}else if(texto != null && texto.length() == 1){
			retorno = texto.toUpperCase();
		}
		return retorno;
	}
}
