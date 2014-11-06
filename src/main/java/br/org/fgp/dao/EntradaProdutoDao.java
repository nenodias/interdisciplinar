package br.org.fgp.dao;

import java.util.Date;
import java.util.List;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.EntradaProduto;

public interface EntradaProdutoDao extends GenericoDao<EntradaProduto, Integer> {
	void salvarRegra(EntradaProduto entity) throws Exception;
	List<EntradaProduto> buscarPorFaixa(Date dataInicio, Date dataTermino);
}
