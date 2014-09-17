package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.model.Fornecedor;

@Repository
public class FornecedorDaoImpl extends GenericoDaoImpl<Fornecedor, Integer> implements FornecedorDao {

}
