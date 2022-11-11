package br.org.fgp.dao;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.Produto;
import br.org.fgp.model.VendaItem;

public interface ProdutoDao extends GenericoDao<Produto, Integer> {

    void adicionaItensEstoque(VendaItem vendaItem);

    void removeItensEstoque(VendaItem vendaItem);

}
