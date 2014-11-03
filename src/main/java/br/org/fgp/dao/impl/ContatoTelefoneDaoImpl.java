package br.org.fgp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ContatoTelefoneDao;
import br.org.fgp.dao.TelefoneDao;
import br.org.fgp.model.ContatoTelefone;

@Repository
public class ContatoTelefoneDaoImpl extends GenericoDaoImpl<ContatoTelefone, Integer> implements ContatoTelefoneDao {

	@Autowired
	private TelefoneDao telefoneDao;
	
	@Override
	public void deletar(Integer id) {
		ContatoTelefone contatoTelefone = buscarPorId(id);
		super.deletar(id);
		telefoneDao.deletar( contatoTelefone.getTelefone().getId() );
	}
}
