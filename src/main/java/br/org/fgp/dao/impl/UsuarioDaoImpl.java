package br.org.fgp.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;

@Repository
public class UsuarioDaoImpl extends GenericoDaoImpl<Usuario, Integer> implements UsuarioDao{	
	
	private static final Logger LOGGER = Logger.getLogger(UsuarioDaoImpl.class);
	
	@Override
	public Usuario buscarPorId(Integer id) {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setLogin("itl");
		usuario.setSenha("123");
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		usuario.setFuncionario(null);
		LOGGER.info(" USUÀRIO DE DROGAS LOGADO");
		return usuario;
	}

}
