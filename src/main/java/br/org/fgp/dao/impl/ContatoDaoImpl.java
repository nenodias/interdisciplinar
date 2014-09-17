package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ContatoDao;
import br.org.fgp.model.Contato;

@Repository
public class ContatoDaoImpl extends GenericoDaoImpl<Contato, Integer> implements ContatoDao {

}
