package br.org.fgp.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.FornecedorDao;
import br.org.fgp.model.ContatoFornecedor;
import br.org.fgp.model.ContatoTelefone;
import br.org.fgp.model.Fornecedor;

@Repository
public class FornecedorDaoImpl extends GenericoDaoImpl<Fornecedor, Integer> implements FornecedorDao, Filtravel<Fornecedor> {

	@Override
	public List<Fornecedor> filtrarPorDescricao(String descricao) {
		return buscarPorCriteria(Restrictions.like("cnpj", descricao));
	}
	
	@Override
	public Fornecedor buscarPorId(Integer id) {
		Fornecedor fornecedor = super.buscarPorId(id);
		initialize(fornecedor);
		return fornecedor;
	}

	private void initialize(Fornecedor fornecedor) {
		List<ContatoFornecedor> listaContato = fornecedor.getListaContato();
		if(listaContato != null && !listaContato.isEmpty()){
			for (ContatoFornecedor contatoFornecedor : listaContato) {
				contatoFornecedor.getId();
				if(contatoFornecedor.getContato() != null){
					contatoFornecedor.getContato().getId();
					List<ContatoTelefone> listaTelefone = contatoFornecedor.getContato().getListaTelefone();
					if(listaTelefone != null && !listaTelefone.isEmpty()){
						for (ContatoTelefone contatoTelefone : listaTelefone) {
							contatoTelefone.getId();
							contatoTelefone.getTelefone().getId();
						}
					}
				}
			}
		}
	}

}
