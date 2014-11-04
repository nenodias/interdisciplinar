package br.org.fgp.dao;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.EntradaProduto;

public interface EntradaProdutoDao extends GenericoDao<EntradaProduto, Integer> {

	void salvarRegra(EntradaProduto entity) throws Exception;

}
