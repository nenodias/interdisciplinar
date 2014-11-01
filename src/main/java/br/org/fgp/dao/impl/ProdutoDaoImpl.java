package br.org.fgp.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.Produto;

@Repository
public class ProdutoDaoImpl extends GenericoDaoImpl<Produto, Integer> implements ProdutoDao, Filtravel<Produto> {

	@Override
	public List<Produto> filtrarPorDescricao(String descricao) {
		return buscarPorCriteria(Restrictions.like("nome", "%"+descricao+"%"));
	}

	@Override
	public Produto buscarPorId(Integer id) {
		Produto produto = super.buscarPorId(id);
		initialize(produto);
		return produto;
	}

	private void initialize(Produto produto) {
		if(produto.getCategoria() != null ){
			produto.getCategoria().getId();
		}
		if(produto.getMarca() != null ){
			produto.getMarca().getId();
		}
		
	}
}
