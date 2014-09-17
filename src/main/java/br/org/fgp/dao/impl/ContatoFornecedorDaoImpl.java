package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ContatoFornecedorDao;
import br.org.fgp.model.ContatoFornecedor;
import br.org.fgp.model.pk.ContatoFornecedorId;

@Repository
public class ContatoFornecedorDaoImpl extends GenericoDaoImpl<ContatoFornecedor, ContatoFornecedorId> implements ContatoFornecedorDao {

}
