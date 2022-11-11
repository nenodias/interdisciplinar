package br.org.fgp.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.SetorDao;
import br.org.fgp.model.Setor;

@Repository
public class SetorDaoImpl extends GenericoDaoImpl<Setor, Integer> implements SetorDao, Filtravel<Setor> {

    @Override
    public List<Setor> filtrarPorDescricao(String descricao) {
        return buscarPorCriteria(Restrictions.like("setor", descricao));
    }

}
