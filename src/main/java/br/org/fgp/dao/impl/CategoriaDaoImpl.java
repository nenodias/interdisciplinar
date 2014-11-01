package br.org.fgp.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.model.Categoria;

@Repository
public class CategoriaDaoImpl extends GenericoDaoImpl<Categoria, Integer>
		implements CategoriaDao, Filtravel<Categoria> {
	
	@Override
	public List<Categoria> filtrarPorDescricao(String descricao) {
		return buscarPorCriteria(Restrictions.like("descricao","%"+descricao+"%") );
	}

}
