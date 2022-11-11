package br.org.fgp.dao;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.Usuario;

public interface UsuarioDao extends GenericoDao<Usuario, Integer> {

    Usuario buscarPorLogin(String login);
}
