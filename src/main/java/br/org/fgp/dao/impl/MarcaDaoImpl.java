package br.org.fgp.dao.impl;


import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.MarcaDao;
import br.org.fgp.model.Marca;

@Repository
public class MarcaDaoImpl extends GenericoDaoImpl<Marca, Integer> implements
        MarcaDao, Filtravel<Marca> {

    @Override
    public List<Marca> filtrarPorDescricao(String descricao) {
        return buscarPorCriteria(Restrictions.like("t.marca", "%" + descricao + "%"));
    }
}
