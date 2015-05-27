package org.cz.project.jpa_base_service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cz.project.jpa_base_service.QueryResult;
import org.cz.project.jpa_base_service.TransactionalService;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public abstract class TransactionalServiceImpl implements
TransactionalService {

	@PersistenceContext
	public EntityManager entityManager;

	/* ------+------+------ transactional operations ------+------+------ */

	@Transactional
	public void flush() {
		entityManager.flush();
	}

	@Transactional
	public <T> T merge(T entity)  {
		try {
			return entityManager.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public void persist(Object entity)  {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void save(Object entity)  {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Transactional
	public <T> T remove(Class<T> clazz, Object id)  {
		T instance;
		try {
			instance = find(clazz, id);
			entityManager.remove(instance);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return instance;
	}

	@Transactional
	public void remove(Object entity)  {

		try {
			entityManager.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean checkExist(Class clazz, String fieldName, Object value) {
		try {
			String sql = "select count(*) from " + clazz.getName() + " where "
					+ fieldName + "=?1";
			Query query = entityManager.createQuery(sql);
			setParameters(query, value);
			return (Long) query.getSingleResult() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public int update(String queryString, Object... params)
	{
		try {
			Query query = entityManager.createQuery(queryString);
			setParameters(query, params);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* ------+------+------ non-transactional operations ------+------+------ */

	public void clear()  {
		try {
			entityManager.clear();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Transactional
	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		setParameters(query, params);
		return (List<T>) query.getResultList();
	}
	public <T> T findFirst(String queryString, Object... params) {
		Query query = entityManager.createQuery(queryString);
		setParameters(query, params);
		List<T> list=(List<T>) query.getResultList();
		return list==null||list.size()==0?null:list.get(0);
	}
	public <T> QueryResult<List<T>> find(String queryString, Map<String, Object> map, int pageIndex, int pageSize) {

		String _queryString=queryString.trim().toLowerCase();
		queryString=queryString.trim();
		String _countQueryString=null;
		if(_queryString.startsWith("from"))
			_countQueryString="select count(*) "+queryString;
		else if(_queryString.indexOf(" from ")>-1)
		{
			_countQueryString="select count(*)"+queryString.substring(_queryString.indexOf(" from "));
		}
		Query q =entityManager.createQuery(queryString);
		Query q4count = entityManager.createQuery(_countQueryString);
		if (map != null && !map.isEmpty()) {
			Set keys = map.keySet();
			for (Object key : keys) {
				q.setParameter(key.toString(), map.get(key));
			}
		}
		pageIndex=Math.abs(pageIndex);
		pageSize=Math.abs(pageSize);
		int total = ((Number)q4count.getSingleResult()).intValue();
		if(pageIndex==0)
		{
			pageIndex=1;
		}
		if(pageSize==0)
		{
			pageSize=total;
		}
		List<T> resultList = q.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).getResultList();
		//		//System.out.println(resultList);
		QueryResult<List<T>> result=new QueryResult<List<T>>(total, pageIndex, pageSize,resultList);
		return result;
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> findMaxRow(String queryString, int maxRow,
			Object... params) {
		Query query = entityManager.createQuery(queryString);
		setParameters(query, params);
		if (maxRow > 0) {
			query.setMaxResults(maxRow);
		}
		return (List<T>) query.getResultList();
	}

	// add by tiandw
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public <T> List<T> findByField(Class<T> entityClass, String fieldName,
			Object value) {
		Query query = entityManager.createQuery("from " + entityClass.getName()
				+ " e where e." + fieldName + "=?1");
		//		//System.out.println("from " + entityClass.getName()
		//				+ " e where e." + fieldName + "="+value);
		query.setParameter(1, value);
		return query.getResultList();

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public <T> T findBy(Class<T> entityClass, String fieldName, Object value) {
		try {
			Query query = entityManager
					.createQuery("from " + entityClass.getName()
							+ " e where e." + fieldName + "=?1");
			query.setParameter(1, value);
			return (T) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> clazz) {
		return (List<T>) find("from " + clazz.getName());
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public <T> List<T> findNamed(String namedQuery, Object... params) {
		Query query = entityManager.createNamedQuery(namedQuery);
		setParameters(query, params);
		return (List<T>) query.getResultList();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public <T> T findNamedSingle(String namedQuery, Object... params) {
		Query query = entityManager.createNamedQuery(namedQuery);
		setParameters(query, params);
		return (T) query.getSingleResult();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public Integer findRowCount(String hsql, Object... params) {
		try {
			Query query = entityManager.createQuery(hsql);
			setParameters(query, params);

			return ((Long) query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public <T> T findSingle(String queryString, Object... params) {
		try {
			Query query = entityManager.createQuery(queryString);
			setParameters(query, params);
			return (T) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}


	/*
	 * A rowsize query
	 */
	@Transactional(readOnly = true)
	public <T> List<T> query(Integer rowSize, String q, Object... params) {
		// executes the query
		Query query = entityManager.createQuery(q);
		if (rowSize > 0) {
			query.setFirstResult(0);
			query.setMaxResults(rowSize);
		}
		setParameters(query, params);
		return (List<T>) query.getResultList();
	}

	/*
	 * A rowsize query
	 */
	@Transactional(readOnly = true)
	public Integer findMaxId(Class clazz) {
		try {
			Query count = null;
			count = entityManager.createQuery("select max(id) from "
					+ clazz.getName());
			return ((Number) count.getSingleResult()).intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * A rowsize query
	 */
	@Transactional(readOnly = true)
	public Integer count(Class clazz) {
		Query count = null;
		count = entityManager.createQuery("select count(*) from "
				+ clazz.getName());
		return ((Number) count.getSingleResult()).intValue();
	}

	/**
	 * Sets all the parameters of a query
	 */
	public void setParameters(Query query, Object... params) {
		if(params==null)
			return ;
		if (params.length == 1) {// MAP
			if (params[0] instanceof Map) {
				Map map = (Map) params[0];
				Set keys = map.keySet();
				for (Object key : keys) {
					query.setParameter(key.toString(), map.get(key));
				}
				return;
			}

		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
	}

	//	public void setSQLParameters(SQLQuery query, Object... params) {
	//		if (params.length == 1) {// MAP
	//			if (params[0] instanceof Map) {
	//				Map map = (Map) params[0];
	//				Set keys = map.keySet();
	//				for (Object key : keys) {
	//					query.setParameter(key.toString(), map.get(key));
	//				}
	//				return;
	//			}
	//
	//		}
	//		for (int i = 0; i < params.length; i++) {
	//			query.setParameter(i, params[i]);
	//		}
	//	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public int findMaxSeq(String entityClass) {
		String queryString = "select count(*) from " + entityClass;
		Query query = entityManager.createQuery(queryString);
		return ((Number) query.getSingleResult()).intValue() + 1;
	}


	public int findMaxSeq(String hsql, Object... params) {
		Query query = entityManager.createQuery(hsql);
		setParameters(query, params);
		return ((Number) query.getSingleResult()).intValue();
	}

	public int findMaxSeq4ParentNull(String hsql) {
		Query query = entityManager.createQuery(hsql);
		return ((Number) query.getSingleResult()).intValue();
	}

	public int findMaxSeq4ParentNull(String hsql, Object... params) {
		Query query = entityManager.createQuery(hsql);
		setParameters(query, params);
		return ((Number) query.getSingleResult()).intValue();
	}

	/**
	 * 检查数据库中是否存在指定的记录 add by yangzy
	 * 
	 * @param hsql
	 * @param code
	 * @param id
	 * @return Boolean类型
	 */
	public Boolean checkExist(String className, String key, Integer id,
			Object value) {
		String hsql = "";
		Integer temp = 0;
		if (null != id) {
			hsql = "select count(*) from " + className + " o where o." + key
					+ " = ?1 and o.id != ?2";
			temp = this.findRowCount(hsql, value, id);
		} else {
			hsql = "select count(*) from " + className + " o where o." + key
					+ " = ?1";
			temp = this.findRowCount(hsql, value);
		}
		if (temp > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Integer distictCount(String sql, Object... objs) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(")
		.append(sql.substring(sql.indexOf("distinct"),
				sql.indexOf("from"))).append(")");
		sb.append(sql.substring(sql.indexOf("from")));
		// sql = "select count(*) from ("+sql +")";

		return findRowCount(sb.toString(), objs);
	}

	/**
	 * 检查数据库中是否存在指定的记录 add by yangzy
	 * 
	 * @param hsql
	 * @param code
	 * @param id
	 * @param pid
	 * @return Boolean类型
	 */
	public Boolean checkExist(String className, String key, Integer id,
			Integer rootId, String value) {
		String hsql = "";
		Integer temp = 0;
		if (null != id) {
			if (null == rootId || 0 == rootId) {
				hsql = "select count(*) from " + className + " o where o."
						+ key + " = ?1 and o.id != ?2 and o.root.id is null";
				temp = this.findRowCount(hsql, value, id);
			} else {
				hsql = "select count(*) from " + className + " o where o."
						+ key + " = ?1 and o.id != ?2 and o.root.id = ?3";
				temp = this.findRowCount(hsql, value, id, rootId);
			}
		} else {
			if (null == rootId || 0 == rootId) {
				hsql = "select count(*) from " + className + " o where o."
						+ key + " = ?1 and o.root.id is null";
				temp = this.findRowCount(hsql, value);
			} else {
				hsql = "select count(*) from " + className + " o where o."
						+ key + " = ?1 and o.root.id = ?2";
				temp = this.findRowCount(hsql, value, rootId);
			}
		}
		if (temp > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List findByIds(Class<?> clazz, List<Integer> ids) {
		StringBuffer sql = new StringBuffer();
		sql.append("select e from ").append(clazz.getName())
		.append(" e where id in(");
		int i = 1;
		for (Integer id : ids) {
			sql.append(id).append(",");
		}
		if (ids.size() > 0) {
			sql.setLength(sql.length() - 1);
			sql.append(")");
			Query query = entityManager.createQuery(sql.toString());
			return query.getResultList();
		} else {
			return null;
//			throw new RuntimeException("Failed to find " + clazz.getName()
//					+ " list,the input params is null or size is 0!");
		}

	}

}
