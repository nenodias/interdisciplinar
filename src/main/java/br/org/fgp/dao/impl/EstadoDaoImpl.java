package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.EstadoDao;
import br.org.fgp.model.Estado;

@Repository
public class EstadoDaoImpl extends GenericoDaoImpl<Estado, Integer> implements EstadoDao {

}
