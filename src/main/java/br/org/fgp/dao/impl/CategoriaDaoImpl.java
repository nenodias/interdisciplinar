package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.CategoriaDao;
import br.org.fgp.model.Categoria;

@Transactional
@Repository
public class CategoriaDaoImpl extends GenericoDaoImpl<Categoria, Integer>
		implements CategoriaDao {

}
