package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ItensPedidoDao;
import br.org.fgp.model.ItensPedido;

@Repository
public class ItensPedidoDaoImpl extends GenericoDaoImpl<ItensPedido, Integer> implements ItensPedidoDao {

}
