package br.org.fgp.dao;

import java.util.List;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.ContatoFornecedor;

public interface ContatoFornecedorDao extends GenericoDao<ContatoFornecedor, Integer> {
    List<ContatoFornecedor> buscarPorIdFornecedor(Integer id);

    void deletarPorIdUsuario(Integer id);

}
