package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.CidadeDao;
import br.org.fgp.model.Cidade;

@Repository
public class CidadeDaoImpl extends GenericoDaoImpl<Cidade, Integer> implements CidadeDao {

}
