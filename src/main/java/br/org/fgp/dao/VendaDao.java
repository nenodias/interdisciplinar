package br.org.fgp.dao;

import java.util.Date;
import java.util.List;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.Venda;

public interface VendaDao extends GenericoDao<Venda, Integer> {
	List<Venda> buscarPorFaixa(Date dataInicio, Date dataTermino);
}
