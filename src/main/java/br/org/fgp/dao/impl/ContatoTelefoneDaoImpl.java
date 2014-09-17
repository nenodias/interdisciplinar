package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ContatoTelefoneDao;
import br.org.fgp.model.ContatoTelefone;
import br.org.fgp.model.pk.ContatoTelefoneId;

@Repository
public class ContatoTelefoneDaoImpl extends GenericoDaoImpl<ContatoTelefone, ContatoTelefoneId> implements ContatoTelefoneDao {

}
