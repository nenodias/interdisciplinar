package br.org.fgp.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ContatoFornecedorDao;
import br.org.fgp.dao.ContatoTelefoneDao;
import br.org.fgp.model.ContatoFornecedor;
import br.org.fgp.model.ContatoTelefone;

@Repository
public class ContatoFornecedorDaoImpl extends GenericoDaoImpl<ContatoFornecedor, Integer> implements ContatoFornecedorDao {

    private static final Logger LOGGER = Logger.getLogger(ContatoFornecedorDaoImpl.class);

    @Autowired
    private ContatoTelefoneDao contatoTelefoneDao;

    @Transactional
    @Override
    public void deletarPorIdUsuario(Integer id) {
        try {
            List<ContatoFornecedor> listaBD = buscarPorIdFornecedor(id);
            int contador = 0;
            while (contador != listaBD.size()) {
                ContatoFornecedor contatoFornecedor = listaBD.get(contador);
                deletar(contatoFornecedor.getId());
                int contadorTelefone = 0;
                List<ContatoTelefone> listaContatoTelefone = contatoFornecedor.getListaTelefone();
                while (contadorTelefone != listaContatoTelefone.size()) {
                    ContatoTelefone contatoTelefone = listaContatoTelefone.get(contadorTelefone);
                    contatoTelefoneDao.deletar(contatoTelefone.getId());
                    listaContatoTelefone.remove(contadorTelefone);
                }
                listaBD.remove(contador);
            }
        } catch (Exception e) {
            LOGGER.info(e);
        }
    }

    @Override
    public List<ContatoFornecedor> buscarPorIdFornecedor(Integer id) {
        return buscarPorCriteria(Restrictions.eq("fornecedor.id", id));
    }

}
