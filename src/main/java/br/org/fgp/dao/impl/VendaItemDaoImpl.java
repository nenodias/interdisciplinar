package br.org.fgp.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.VendaItemDao;
import br.org.fgp.model.VendaItem;

@Repository
public class VendaItemDaoImpl extends GenericoDaoImpl<VendaItem, Integer> implements VendaItemDao {

	@Override
	@Transactional
	public void deletarPorIdVenda(Integer id) {
		List<VendaItem> listaBD = buscarPorIdVenda(id);
		int contador = 0;
		while(contador != listaBD.size()){
			VendaItem vendaItem = listaBD.get(contador);
			deletar(vendaItem.getId());
			listaBD.remove(contador);
		}
	}
	
	@Override
	public List<VendaItem> buscarPorIdVenda(Integer id){
		return buscarPorCriteria(Restrictions.eq("venda.id", id));
	}


}
