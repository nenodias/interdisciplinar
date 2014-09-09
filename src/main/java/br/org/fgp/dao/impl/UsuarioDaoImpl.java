package br.org.fgp.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;

@Transactional
@Repository
public class UsuarioDaoImpl extends GenericoDaoImpl<Usuario, Integer> implements UsuarioDao{

	@Override
	public Usuario buscarPorLogin(String login) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("login", login);
		return selectPrimeiroHQL(" FROM Usuario u where u.login = :login ", parametros);
	}	

}
