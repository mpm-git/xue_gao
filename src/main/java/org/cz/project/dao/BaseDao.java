package org.cz.project.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;




/**
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseDao<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void update(List<T> o);

	public void saveOrUpdate(T o);

	public T get(Class<T> c, Serializable id);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> findByPage(String hql, int page, int rows);
	public List<T> find(String hql, int start, int length);

	/**
	 * @param hql
	 * @param params
	 * @param page start from 1
	 * @param rows
	 * @return
	 */
	public List<T> findBypage(String hql, Map<String, Object> params, int page, int rows);
	/**
	 * 
	 * @param hql
	 * @param params
	 * @param start start from 0
	 * @param length
	 * @return
	 */
	public List<T> find(String hql, Map<String, Object> params, int start, int length);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public int executeHql(String hql);

	public int executeHql(String hql, Map<String, Object> params);
	public int executeSQL(String sql);

	void save(List<T> o);

	Map query(String functionName);

	Map executeWithCondition(String procedureName, Map<String, Object> condition);

	Map executeByCondition(Class clazz, Map<String, Object> condition,
			String order, PaginationModel pagination, String procedureName);

	QueryResult<List<T>> find2QueryResult(String hql, Map<String, Object> params,
			int page, int rows);

	QueryResult<List<T>> find2QueryResultByPage(String hql,Map<String, Object> params, int page, int rows);


}
