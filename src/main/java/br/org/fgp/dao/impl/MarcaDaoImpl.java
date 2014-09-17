package br.org.fgp.dao.impl;


import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.MarcaDao;
import br.org.fgp.model.Marca;

@Repository
public class MarcaDaoImpl extends GenericoDaoImpl<Marca, Integer> implements
		MarcaDao {
}
