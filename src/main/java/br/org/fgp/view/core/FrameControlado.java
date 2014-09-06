package br.org.fgp.view.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.org.fgp.annotations.Permissao;
import br.org.fgp.core.ApplicationContextConfig;
import br.org.fgp.core.InterdisciplinarReflectionUtil;
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

	private static final long serialVersionUID = -1660186148473856758L;
	
	private static final Logger LOGGER = Logger.getLogger(FrameControlado.class); 
	
	public void pronto(TipoUsuario tipoUsuario){
		pronto(tipoUsuario,this);
	}
	
	private void pronto(TipoUsuario tipoUsuario, FrameControlado frame){
		Class<? extends FrameControlado> clazz = this.getClass();
		verificaPermissao(tipoUsuario, frame, clazz);
		verificaInjecao(frame, clazz);
	}

	private void verificaInjecao(FrameControlado frame,
			Class<? extends FrameControlado> clazz) {
		List<Field> atributoComAnotacao = InterdisciplinarReflectionUtil.getAtributoComAnotacao(clazz, Autowired.class);
		for (Field campo : atributoComAnotacao) {
			ApplicationContext context = ApplicationContextConfig.getContext();
			Method metodoSetter = InterdisciplinarReflectionUtil.getMetodoSet(clazz, campo);
			try{
				if(metodoSetter != null){
					metodoSetter.invoke(frame, context.getBean(campo.getType()) );
				}
			}catch(Exception e){
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	private void verificaPermissao(TipoUsuario tipoUsuario,
			FrameControlado frame, Class<? extends FrameControlado> clazz) {
		 List<Field> atributoComAnotacao = InterdisciplinarReflectionUtil.getAtributoComAnotacao(clazz, Permissao.class);
		for (Field campo : atributoComAnotacao) {
			Permissao permissao = campo.getAnnotation(Permissao.class);
			if(permissao != null && !permissao.tipo().equals(tipoUsuario)){
				Method metodoGet = InterdisciplinarReflectionUtil.getMetodoGet(clazz, campo);
				try{
					if(metodoGet != null){
						Object objeto = metodoGet.invoke(frame, null);
						if(objeto != null && objeto instanceof JComponent){
							JComponent componente = (JComponent)objeto;
							componente.setEnabled(false);
						}
					}
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}
	
}
