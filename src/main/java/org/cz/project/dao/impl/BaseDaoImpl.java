package org.cz.project.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cz.project.dao.BaseDao;
import org.cz.project.dao.PaginationModel;
import org.cz.project.dao.QueryResult;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;





@Repository(value="baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public SimpleJdbcCall createSimpleJdbcCall() {
		return new SimpleJdbcCall(this.jdbcTemplate);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	protected Map executeProcedure(String procedureName) {
		SimpleJdbcCall sjc = this.createSimpleJdbcCall();  
		sjc.getJdbcTemplate().setResultsMapCaseInsensitive(true);  
		sjc.withProcedureName(procedureName);
		return sjc.execute(); 
	}
	
	protected Map executeProcedureWithCondition(String procedureName,Map<String, Object> condition) {
		SimpleJdbcCall sjc = this.createSimpleJdbcCall();  
		sjc.getJdbcTemplate().setResultsMapCaseInsensitive(true);
		sjc.withProcedureName(procedureName);
		Map<String, Object> param = new HashMap<String, Object>();
		param.putAll(condition == null ? new HashMap() : condition);
		return sjc.execute(param); 
	}
	
	@SuppressWarnings("unchecked")
	protected Map queryWithCondition(Class clazz, Map<String, Object> condition,
			String order, PaginationModel pagination, String procedureName) {
		SimpleJdbcCall sjc = this.createSimpleJdbcCall();
		sjc.getJdbcTemplate().setResultsMapCaseInsensitive(true);
		sjc.withProcedureName(procedureName)
		.returningResultSet("Pagination",
				BeanPropertyRowMapper.newInstance(PaginationModel.class))
		.returningResultSet("List",
						BeanPropertyRowMapper.newInstance(clazz));
		Map<String, Object> param = new HashMap<String, Object>();
		param.putAll(condition == null ? new HashMap() : condition);
		param.put("orderby", order == null ? "" : order);
		param.put("pageSize", pagination == null?1:pagination.getPageSize());
		param.put("currPage", pagination == null?10:pagination.getCurrPage());
		Map returnResultSet = sjc.execute(param);
		
		Map<String, Object> newResult = new HashMap<String, Object>();
		newResult.put("List", returnResultSet.get("List"));
		
		List list = (List) returnResultSet.get("Pagination");
		if (list.size() > 0) {
			newResult.put("Pagination", list.get(0));
		}
		return newResult;
	}

	
	@Override
	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			String[] namedParameters = q.getNamedParameters();
			for (String key : namedParameters) {
				if(params.get(key)!=null)
				{
					if(params.get(key)!=null)
					{
						if(params.get(key) instanceof  Collection)
						{
							q.setParameterList(key, (Collection) params.get(key));
						}
						else if(params.get(key) instanceof Object[])
						{
							q.setParameterList(key, (Object[])params.get(key));
						}
						else
						{
							q.setParameter(key, params.get(key));
						}
					}
				}
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
	}
	@Override
	public void update(List<T> o) {
		for (T t : o) {
			this.getCurrentSession().update(t);
		}
	}
	
	@Override
	public void save(List<T> o) {
		for (T t : o) {
			this.getCurrentSession().save(t);
		}
	}


	@Override
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			String[] namedParameters = q.getNamedParameters();
			for (String key : namedParameters) {
				if(params.get(key)!=null)
				{
					if(params.get(key)!=null)
					{
						if(params.get(key) instanceof  Collection)
						{
							q.setParameterList(key, (Collection) params.get(key));
						}
						else if(params.get(key) instanceof Object[])
						{
							q.setParameterList(key, (Object[])params.get(key));
						}
						else
						{
							q.setParameter(key, params.get(key));
						}
					}
				}
			}
		}
		return q.list();
	}

	public QueryResult<List<T>> find2QueryResultByPage(String hql, Map<String, Object> params, int page, int rows) {
		return find2QueryResult(hql,params,(page - 1) * rows,rows);
	}
	@Override
	public QueryResult<List<T>> find2QueryResult(String hql, Map<String, Object> params, int start, int length) {
		String _queryString=hql.trim().toLowerCase();
		hql=hql.trim();
		String _countQueryString=null;
		if(_queryString.startsWith("from"))
			_countQueryString="select count(*) "+hql;
		else if(_queryString.indexOf(" from ")>-1)
		{
			_countQueryString="select count(*)"+hql.substring(_queryString.indexOf(" from "));
		}
		List<T> list = find(hql,params,start,length);
		Long count = this.count(_countQueryString,params);
		QueryResult<List<T>> result=new QueryResult<List<T>>(count, start, length,list);
		return result;
	}
	public List<T> findByPage(String hql, Map<String, Object> params, int page, int rows) {
		return find(hql,params,(page - 1) * rows,rows);
	}
	@Override
	public List<T> find(String hql, Map<String, Object> params, int start, int length) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			String[] namedParameters = q.getNamedParameters();
			for (String key : namedParameters) {
				if(params.get(key)!=null)
				{
					if(params.get(key)!=null)
					{
						if(params.get(key) instanceof  Collection)
						{
							q.setParameterList(key, (Collection) params.get(key));
						}
						else if(params.get(key) instanceof Object[])
						{
							q.setParameterList(key, (Object[])params.get(key));
						}
						else
						{
							q.setParameter(key, params.get(key));
						}
					}
				}
			}
		}
		
		return q.setFirstResult(start).setMaxResults(length).list();
	}

	@Override
	public List<T> find(String hql, int start, int length) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult(start).setMaxResults(length).list();
	}
	@Override
	public List<T> findByPage(String hql, int page, int rows) {
		return find(hql,(page - 1) * rows,rows);
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			String[] namedParameters = q.getNamedParameters();
			for (String key : namedParameters) {
				if(params.get(key)!=null)
				{
					if(params.get(key)!=null)
					{
						if(params.get(key) instanceof  Collection)
						{
							q.setParameterList(key, (Collection) params.get(key));
						}
						else if(params.get(key) instanceof Object[])
						{
							q.setParameterList(key, (Object[])params.get(key));
						}
						else
						{
							q.setParameter(key, params.get(key));
						}
					}
				}
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}
	public int executeSQL(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			String[] namedParameters = q.getNamedParameters();
			for (String key : namedParameters) {
				if(params.get(key)!=null)
				{
					if(params.get(key)!=null)
					{
						if(params.get(key) instanceof  Collection)
						{
							q.setParameterList(key, (Collection) params.get(key));
						}
						else if(params.get(key) instanceof Object[])
						{
							q.setParameterList(key, (Object[])params.get(key));
						}
						else
						{
							q.setParameter(key, params.get(key));
						}
					}
				}
			}
		}
		return q.executeUpdate();
	}

	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public Map query(String procedureName) {
		return this.executeProcedure(procedureName);
	}
	
	@Override
	public Map executeByCondition(Class clazz, Map<String, Object> condition,
			String order, PaginationModel pagination, String procedureName) {
		return this.queryWithCondition(clazz, condition, order ,pagination,procedureName);
	}
	
	@Override
	public Map executeWithCondition(String procedureName,Map<String, Object> condition) {
		return this.executeProcedureWithCondition(procedureName,condition);
	}

}
