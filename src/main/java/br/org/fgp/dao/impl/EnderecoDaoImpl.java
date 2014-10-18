package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.model.Endereco;

@Repository
public class EnderecoDaoImpl extends GenericoDaoImpl<Endereco, Integer> implements GenericoDao<Endereco, Integer> {

}
