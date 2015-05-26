package org.cz.project.service;

import java.util.List;
import java.util.Map;

public interface TransactionalService {

	void flush();

	<T> T merge(T entity);

	void persist(Object entity);

	void save(Object object);

	<T> T remove(Class<T> clazz, Object id);

	void remove(Object entity);

	int update(String queryString, Object... params);

	void clear();

	<T> T find(Class<T> entityClass, Object primaryKey);

	<T> List<T> find(String queryString, Object... params);

	<T> List<T> findMaxRow(String queryString, int maxRow,
			Object... params);

	<T> T findBy(Class<T> clazz, String fieldName, Object value);

	/**
	 * 检测是否存在
	 * 
	 * @param clazz
	 * @param fieldName
	 * @param value
	 * @return
	 */
	boolean checkExist(Class clazz, String fieldName, Object value);

	/**
	 * add by tiandw ,find by the fieldName and value,return List
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param fieldName
	 * @param value
	 * @return
	 */
	<T> List<T> findByField(Class<T> entityClass, String fieldName,
			Object value);

	<T> List<T> findAll(Class<T> clazz);

	<T> List<T> findNamed(String namedQuery, Object... params);

	<T> T findNamedSingle(String namedQuery, Object... params);

	<T> T findSingle(String queryString, Object... params);

	<T> List<T> query(Integer rowSize, String q, Object... params);

	Integer findRowCount(String namedQuery, Object... params);

	Integer count(Class entityClass);

	// 查询一个表中最大的Id
	Integer findMaxId(Class entityClass);

	// 查询一个表中最大的Seq //add by yangzy
	int findMaxSeq(String entityClass);

	// 根据一个ID，查询此ID对应的实体类中的Seq //add by yangzy
	int findMaxSeq(String hsql, Object... params);

	// 根据一个ID，当Parent查询此ID对应的实体类中的Seq add by yangzy
	int findMaxSeq4ParentNull(String hsql);

	// 根据一个ID，当Parent查询此ID对应的实体类中的Seq add by yangzy
	int findMaxSeq4ParentNull(String hsql, Object... params);

	// 检查数据库中是否存在指定的记录 add by yangzy
	Boolean checkExist(String className, String code, Integer id,
			Object value);

	// 检查数据库中是否存在指定的记录 add by yangzy
	Boolean checkExist(String className, String key, Integer id,
			Integer rootId, String value);

	/**
	 * 查找包含distinct的行数
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	Integer distictCount(String sql, Object... objs) throws Exception;

	/**
	 * 
	 * @param clazz
	 *            Class<? extends IdEntity>
	 * @param ids
	 *            List<Integer>
	 * @return List<? extends IdEntity>
	 */
	List findByIds(Class<?> clazz, List<Integer> ids);

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	// <T> List<T> find(String queryString, Map<String, Object> map, int page,
	// int rows);
	<T> QueryResult<List<T>> find(String queryString, Map<String, Object> map,
			int page, int rows);

	<T> T findFirst(String queryString, Object[] params);

}
