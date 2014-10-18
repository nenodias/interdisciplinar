package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.PedidoDao;
import br.org.fgp.model.Venda;

@Repository
public class PedidoDaoImpl extends GenericoDaoImpl<Venda, Integer> implements PedidoDao {

}
