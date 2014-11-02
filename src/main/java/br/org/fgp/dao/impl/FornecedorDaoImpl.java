package br.org.fgp.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.model.Fornecedor;

@Repository
public class FornecedorDaoImpl extends GenericoDaoImpl<Fornecedor, Integer> implements FornecedorDao, Filtravel<Fornecedor> {

	@Override
	public List<Fornecedor> filtrarPorDescricao(String descricao) {
		return buscarPorCriteria(Restrictions.like("cnpj", descricao));
	}

}
