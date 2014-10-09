package br.org.fgp.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.CidadeDao;
import br.org.fgp.model.Cidade;
import br.org.fgp.model.Usuario;

@Repository
public class CidadeDaoImpl extends GenericoDaoImpl<Cidade, Integer> implements CidadeDao {

	@Override
	public Cidade buscaPorEstado(Integer idEstado) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("IdEstado", idEstado);
		return (Cidade) selectHQL(" FROM Cidade c where c.estado.id = :idEstado", parametros);
	}	
}
