package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.VendaItemDao;
import br.org.fgp.model.VendaItem;

@Repository
public class VendaItemDaoImpl extends GenericoDaoImpl<VendaItem, Integer> implements VendaItemDao {

}
