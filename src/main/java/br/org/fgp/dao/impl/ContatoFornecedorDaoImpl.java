package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ContatoFornecedorDao;
import br.org.fgp.model.ContatoFornecedor;

@Repository
public class ContatoFornecedorDaoImpl extends GenericoDaoImpl<ContatoFornecedor, Integer> implements ContatoFornecedorDao {

}
