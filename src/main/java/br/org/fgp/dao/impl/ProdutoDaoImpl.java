package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.Produto;

@Repository
public class ProdutoDaoImpl extends GenericoDaoImpl<Produto, Integer> implements ProdutoDao {

}
