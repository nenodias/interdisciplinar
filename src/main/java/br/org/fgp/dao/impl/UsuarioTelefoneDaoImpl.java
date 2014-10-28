package br.org.fgp.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.UsuarioTelefoneDao;
import br.org.fgp.model.UsuarioTelefone;

@Repository
public class UsuarioTelefoneDaoImpl extends GenericoDaoImpl<UsuarioTelefone, Integer> implements UsuarioTelefoneDao {
	
	public List<UsuarioTelefone> buscarPorIdUsuario(Integer id){
		return buscarPorCriteria(Restrictions.eq("usuario.id", id));
	}
}
