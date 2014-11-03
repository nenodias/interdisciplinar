package br.org.fgp.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.TelefoneDao;
import br.org.fgp.dao.UsuarioTelefoneDao;
import br.org.fgp.model.UsuarioTelefone;

@Repository
public class UsuarioTelefoneDaoImpl extends GenericoDaoImpl<UsuarioTelefone, Integer> implements UsuarioTelefoneDao {
	
	@Autowired
	private TelefoneDao telefoneDao;
	
	public List<UsuarioTelefone> buscarPorIdUsuario(Integer id){
		return buscarPorCriteria(Restrictions.eq("usuario.id", id));
	}

	@Transactional
	@Override
	public void deletarPorIdUsuario(Integer id) {
		List<UsuarioTelefone> listaBD = buscarPorIdUsuario(id);
		int contador = 0;
		while(contador != listaBD.size()){
			UsuarioTelefone usuarioTelefone = listaBD.get(contador);
			deletar(usuarioTelefone.getId());
			telefoneDao.deletar(usuarioTelefone.getTelefone().getId());
			listaBD.remove(contador);
		}
	}
}
