package br.org.fgp.dao.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.dao.VendaItemDao;
import br.org.fgp.model.Venda;
import br.org.fgp.model.VendaItem;

@Repository
public class VendaDaoImpl extends GenericoDaoImpl<Venda, Integer> implements VendaDao, Filtravel<Venda> {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private VendaItemDao vendaItemDao;
	
	@Override
	public List<Venda> buscarPorFaixa(Date dataInicio, Date dataTermino){
		return buscarPorCriteriaOrder(Restrictions.between("data", dataInicio, dataTermino), Order.asc("data"));
	}
	
	@Transactional
	@Override
	public void salvarRegra(Venda entity)throws Exception{
		if(entity.getId() != null){
			vendaItemDao.deletarPorIdVenda(entity.getId());
		}
		for (VendaItem vendaItem : entity.getListaItem()) {
			produtoDao.removeItensEstoque(vendaItem);
		}
		salvar(entity);
		for (VendaItem vendaItem : entity.getListaItem()) {
			vendaItemDao.salvar(vendaItem);
		}
	}

	@Override
	public void deletar(Integer id) {
		Venda venda = buscarPorId(id);
		for (VendaItem vendaItem : venda.getListaItem()) {
			produtoDao.adicionaItensEstoque(vendaItem);
			vendaItemDao.deletar( vendaItem.getId() );
		}
		super.deletar(id);
	}

	@Override
	public List<Venda> filtrarPorDescricao(String descricao) {
		return buscarPorCriteria(Restrictions.like("usuario.nome", "%"+descricao+"%"));
	}
	
	
	
}
