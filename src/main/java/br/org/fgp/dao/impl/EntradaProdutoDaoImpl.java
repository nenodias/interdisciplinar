package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.model.EntradaProduto;

@Repository
public class EntradaProdutoDaoImpl extends GenericoDaoImpl<EntradaProduto, Integer> implements EntradaProdutoDao {

}
