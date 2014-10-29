package br.org.fgp.dao;

import java.util.List;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.UsuarioTelefone;

public interface UsuarioTelefoneDao extends
		GenericoDao<UsuarioTelefone, Integer> {
	List<UsuarioTelefone> buscarPorIdUsuario(Integer id);
	void deletarPorIdUsuario(Integer id);
}
