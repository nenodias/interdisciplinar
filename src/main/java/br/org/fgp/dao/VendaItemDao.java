package br.org.fgp.dao;

import java.util.List;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.VendaItem;

public interface VendaItemDao extends GenericoDao<VendaItem, Integer> {

	void deletarPorIdVenda(Integer id);

	List<VendaItem> buscarPorIdVenda(Integer id);

}
