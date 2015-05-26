package per.cz.db.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import per.cz.db.QueryResult;

 public interface BaseDao {

	 <T> Serializable save(T o);

	  <T>  void delete(T o);

	  <T>  void update(T o);

	  <T>  void update(List<T> o);

	  <T>  void saveOrUpdate(T o);

	  <T>  T get(Class<T> c, Serializable id);

	  <T>  T get(String hql);

	   <T> T get(String hql, Map<String, Object> params);

	  <T>  List<T> find(String hql);

	  <T>  List<T> find(String hql, Map<String, Object> params);

	  <T>  List<T> find(String hql, int page, int rows);

	 <T> List<T> find(String hql, Map<String, Object> params, int page, int rows);
	<T> QueryResult<List<T>> find2(String queryString, Map<String, Object> map, int satrt, int rows);

	   <T> Long count(String hql);

	  <T> Long count(String hql, Map<String, Object> params);

	  <T> int executeHql(String hql);

	  <T> int executeHql(String hql, Map<String, Object> params);


}
