package br.org.fgp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("descricao", descricao+"%");
		return selectHQL(" FROM Categoria c where c.descricao like :descricao ", parametros);
	}

}
