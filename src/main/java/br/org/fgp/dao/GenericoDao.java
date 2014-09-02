package br.org.fgp.dao;

import java.util.List;

public interface GenericoDao <T, PK> {

	T buscarPorId(PK id);
	List<T> buscarTodos();
	void deletar(PK id);
	void salvar(T entidade);
	
}
