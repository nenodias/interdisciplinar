package br.org.fgp.core.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.fgp.core.dao.GenericoDao;

@Transactional
@Repository
public abstract class GenericoDaoImpl<T, PK> implements GenericoDao<T, PK> {

    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Class<T> clazz = (Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Class<PK> PKclazz = (Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    public void salvar(T entity) {
        getSessaoAtual().saveOrUpdate(entity);
    }
    
    @Override
    public void execute(String sql){
    	SQLQuery sqlQuery = getSessaoAtual().createSQLQuery(sql);
    	sqlQuery.executeUpdate();
    }

    public void deletar(T entity) {
        getSessaoAtual().delete(entity);
    }

    @SuppressWarnings("unchecked")
	public T buscarPorId(PK id) {
        return (T) getSessaoAtual().createCriteria(this.clazz).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
        Criteria criteria = getSessaoAtual().createCriteria(this.clazz);
        return criteria.list();
    }

    public Long count() {
        Criteria criteria = getSessaoAtual().createCriteria(this.clazz);
        criteria.setProjection(Projections.rowCount());
        return Long.valueOf(criteria.uniqueResult().toString());
    }

    @SuppressWarnings("unchecked")
	public List<T> buscarTodos(Order order) {
        Criteria criteria = getSessaoAtual().createCriteria(this.clazz);
        criteria.addOrder(order);
        return criteria.list();
    }

    public void deletarTodos() {
        Query query = getSessaoAtual().createQuery(new StringBuilder().append("DELETE FROM ").append(this.clazz.getSimpleName()).toString());
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
	protected List<T> buscarPorCriteria(Criterion criterion) {
        Criteria criteria = getSessaoAtual().createCriteria(this.clazz);
        if (criterion == null) {
            return null;
        }
        criteria.add(criterion);

        return criteria.list();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> selectHQL(String hql, Map<String, Object> params) {
        Query query = getSessaoAtual().createQuery(hql);

        Set<String> str = params.keySet();

        for (String key : str) {
            Object value = params.get(key);
            if ((value instanceof Collection)) {
                query.setParameterList(key, (Collection) value);
            } else {
                query.setParameter(key, value);
            }
        }
        return query.list();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected T selectPrimeiroHQL(String hql, Map<String, Object> params) {
        Query query = getSessaoAtual().createQuery(hql);
        Set<String> str = params.keySet();
        for (String string : str) {
            query.setParameter(string, params.get(string));
        }
        Object result = null;
        List resultList = query.list();
        if ((resultList != null) && (!resultList.isEmpty())) {
            result = resultList.get(0);
        }
        return (T) result;
    }

    @SuppressWarnings("unchecked")
	protected List<T> buscarPorListaCriteriaOrder(List<Criterion> criterion, Order order) {
        Criteria criteria = getSessaoAtual().createCriteria(this.clazz);
        if (criterion == null) {
            return null;
        }
        List<Criterion> list = criterion;
        for (Criterion c : list) {
            criteria.add(c);
        }
        if (order != null) {
            criteria.addOrder(order);
        }
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
	protected List<T> buscarPorCriteriaOrder(Criterion criterion, Order order) {
        Criteria criteria = getSessaoAtual().createCriteria(this.clazz);

        if (criterion == null) {
            return null;
        }
        criteria.add(criterion);
        if (order != null) {
            criteria.addOrder(order);
        }

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
	protected T buscarPorCriteriaPrimeiro(Criterion criterion) {
        Criteria criteria = getSessaoAtual().createCriteria(this.clazz);
        if (criterion == null) {
            return null;
        }
        criteria.add(criterion);
        return (T) criteria.uniqueResult();
    }

    protected Session getSessaoAtual() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void flush() {
    	getSessaoAtual().flush();    	
    }
    
    @Override
    public Class<?> getObjectClass(){
    	return clazz;
    }
    
    @Override
    public Class<?> getPKClass(){
    	return PKclazz;
    }
    
}
