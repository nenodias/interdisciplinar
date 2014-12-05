package br.org.fgp.core.dao;

import java.util.List;

import org.hibernate.criterion.Order;

public abstract interface GenericoDao<T, PK> {
	abstract Long count();

	abstract void salvar(T entity);

	abstract void deletar(PK id);

	abstract void flush();

	abstract void deletarTodos();

	abstract T buscarPorId(PK paramPK);

	abstract List<T> buscarTodos();

	abstract List<T> buscarTodos(Order paramOrder);

	@SuppressWarnings("rawtypes")
	Class getPKClass();

	@SuppressWarnings("rawtypes")
	Class getObjectClass();

	void execute(String sql);

	void evict(Object objeto);

}