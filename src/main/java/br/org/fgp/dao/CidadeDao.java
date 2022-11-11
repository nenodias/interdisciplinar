package br.org.fgp.dao;

import java.util.List;

import br.org.fgp.core.dao.GenericoDao;
import br.org.fgp.model.Cidade;

public interface CidadeDao extends GenericoDao<Cidade, Integer> {

    List<Cidade> buscaPorEstado(Integer idEstado);

}
