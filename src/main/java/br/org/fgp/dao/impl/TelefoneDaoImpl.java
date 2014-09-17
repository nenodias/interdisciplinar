package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.TelefoneDao;
import br.org.fgp.model.Telefone;

@Repository
public class TelefoneDaoImpl extends GenericoDaoImpl<Telefone, Integer> implements TelefoneDao {

}
