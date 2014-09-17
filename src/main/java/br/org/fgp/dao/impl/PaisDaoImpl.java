package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.PaisDao;
import br.org.fgp.model.Pais;

@Repository
public class PaisDaoImpl extends GenericoDaoImpl<Pais, Integer> implements PaisDao {

}
