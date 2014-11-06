package br.org.fgp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.VendaDao;
import br.org.fgp.model.Venda;

@Repository
public class VendaDaoImpl extends GenericoDaoImpl<Venda, Integer> implements VendaDao {

	@Override
	public List<Venda> buscarPorFaixa(Date dataInicio, Date dataTermino){
		return buscarPorCriteria(Restrictions.between("data", dataInicio, dataTermino));
	}
	
}
