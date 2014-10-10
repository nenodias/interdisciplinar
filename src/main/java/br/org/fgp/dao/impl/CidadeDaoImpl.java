package br.org.fgp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.CidadeDao;
import br.org.fgp.model.Cidade;

@Repository
public class CidadeDaoImpl extends GenericoDaoImpl<Cidade, Integer> implements CidadeDao {

	@Override
	public List<Cidade> buscaPorEstado(Integer IdEstado) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("IdEstado", IdEstado);
		return selectHQL(" FROM Cidade c where c.estado.id = :IdEstado ORDER BY c.descricao ", parametros);
	}	
}
