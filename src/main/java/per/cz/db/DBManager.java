package per.cz.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import per.cz.db.dao.map.IResultSetEntityMapping;

public class DBManager
{
	//	private DataSource dataSource = null;
	private Logger logger = LoggerFactory.getLogger(DBManager.class);
	private Connection connection = null;
	//	private Statement statement = null;
	//	private ResultSet resultSet = null;
	//	private PreparedStatement preparedStatement = null;

	public Connection getConnection()
	{
		return connection;
	}
	public  DBManager(Connection connection) throws SQLException 
	{
		//		dataSource=dataSource;
		//			connection = dataSource.getConnection();
		this.connection = connection;
		//			statement = connection.createStatement();
	}
	/**
	 * insert update delete
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	public int update(String sql, List args) throws SQLException
	{
		return update(sql,args.toArray());
	}

	public int update(String sql, Object[] args) throws SQLException
	{
		logger.debug(sql);
		logger.debug(Arrays.toString(args));
		int row = 0;
		PreparedStatement preparedStatement =null;
		try{
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++)
			{
				preparedStatement.setObject(i + 1, args[i]);
			}
			row = preparedStatement.executeUpdate();
			return row;
		}catch(SQLException e)
		{
			logger.error(e.getMessage(), new Throwable(e));
			throw e;
		}
		finally
		{
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}
	public int insert(String table,Map<String,Object> data) throws SQLException
	{
		logger.trace("insert");
		String fields="";
		String _values="";
		Object values[];
		Set<String> keySet = data.keySet();
		values=new Object[keySet.size()];
		int t=0;
		for (String k : keySet) {
			values[t]=data.get(k);
			fields+="`"+k+"`,";
			_values+="?,";
			t++;
		}
		fields=fields.replaceAll(",$", "");
		_values=_values.replaceAll(",$", "");
		String sql="INSERT INTO "+table+" ("+fields+") VALUES ("+_values+")";
		return this.update(sql, values);
	}
	public int delete(String table,Map<String,Object> data) throws SQLException
	{
		String fields="";
		Object values[];
		Set<String> keySet = data.keySet();
		values=new Object[keySet.size()];
		int t=0;
		for (String k : keySet) {
			values[t]=data.get(k);
			fields+=" `"+k+"`=? AND";
			t++;
		}
		fields=fields.replaceAll("AND$","");
		String sql="DELETE FROM "+table+" WHERE  "+fields;
		return this.update(sql, values);
	}
	public int update(String table,Map<String,Object> old_data,Map<String,Object> new_data) throws SQLException
	{
		String old_fields="";
		String new_fields="";
		Object values[];
		Set<String> new_keySet = new_data.keySet();
		Set<String> old_keySet = old_data.keySet();
		values=new Object[new_keySet.size()+old_keySet.size()];
		int t=0;
		for (String k : new_keySet) {
			values[t]=new_data.get(k);
			new_fields+=" `"+k+"`=? ,";
			t++;
		}
		for (String k : old_keySet) {
			values[t]=old_data.get(k);
			old_fields+=" `"+k+"`= ? AND";
			t++;
		}
		new_fields=new_fields.replaceAll(",$","");
		old_fields=old_fields.replaceAll("AND$","");
		String sql="UPDATE "+table+" SET "+new_fields+" WHERE " +old_fields;
		return this.update(sql, values);
	}
	/**
	 * @param sql 
	 * @param args 
	 * @param pageIndex page index start from 1 ,if pageIndex <=0 then pageIndex=1 
	 * @param pageSize page size ,if pageSize<=0 then return all.
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> query4ListMap(String sql, Object[] args,int first,int size) throws Exception
	{
		return query(sql,args,new ResultSetEntityMapping4Map(),first,size);
	}
	/**
	 * @param sql
	 * @param args
	 * @param mapping
	 * @param first if first<=0 while be from 0
	 * @param size 
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> query(String sql, Object[] args,IResultSetEntityMapping<T> mapping,int first,int size) throws Exception
	{
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			List<T> list = new Vector<T>();
			preparedStatement = connection.prepareStatement(sql);
			if (args != null && args.length != 0)
			{
				for (int i = 0; i < args.length; i++)
				{
					preparedStatement.setObject(i + 1, args[i]);
				}
			}
			resultSet = preparedStatement.executeQuery();
			if(first>0)
				resultSet.absolute(first);
			int tag=1;
			if(!resultSet.next())
			{
				return null;
			}
			do{
				T obj =(T) mapping.mapping(resultSet);
				list.add(obj);
			}while (resultSet.next()&&(tag++<size||size<=0));
			return list;
		}catch (SQLException e) {
			throw e;
			// TODO: handle exception
		}finally{
			if(resultSet!=null)
				resultSet.close();
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}
	public <T> List<T> query(String sql, Object[] args,final Class<T> cls,int start, int length) throws Exception
	{
//		logger.debug("List<Map<String,Object>> query4ListMap:sql:"+sql+"\targs:"+Arrays.toString(args)+"\tstart:"+start+"\tlength:"+length);
		return query(sql,args,new IResultSetEntityMapping<T>() {

			@Override
			public T mapping(ResultSet resultSet) throws Exception {
				T t=null;
				t = cls.newInstance();
				Field[] fs = cls.getFields();
//				System.out.println(cls.getName());
//				for(int x=0;x<fs.length;x++) {
//					System.out.println(fs[x].getName());
//				}
				ResultSetMetaData metaData = resultSet.getMetaData();
				int fieldCount = metaData.getColumnCount();
				for (int i = 0; i < fieldCount; i++) {
					String k= metaData.getColumnLabel(i + 1);
					Field field=null;
					try{
						field = cls.getDeclaredField(k);
						field.setAccessible(true);
						field.set(t, resultSet.getObject(i));
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				return t;
			}
		},start,length);
	}
	/**
	 * @param sql 
	 * @param fields 
	 * @param param 
	 * @return 
	 * @throws SQLException 
	 */
	public Vector query(String sql,int[] fields, Object[] param) throws SQLException
	{
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			Vector objects = new Vector();
			preparedStatement = connection.prepareStatement(sql);
			if (param != null && param.length != 0)
			{
				for (int i = 0; i < param.length; i++)
				{
					preparedStatement.setObject(i + 1, param[i]);
				}
			}

			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next())
			{
				return null;
			}
			do{
				Vector obj = new Vector();
				for(int i=0;i<fields.length;i++)
				{
					Object o=resultSet.getObject(fields[i]);             ////////取锟竭碉拷锟斤拷菥筒锟斤拷锟斤拷锟饺★拷恕锟�
					obj.add(o);
				}
				objects.add(obj);
			}while (resultSet.next());

			return objects;
		}catch (SQLException e) {
			throw e;
		}
		finally{
			if(resultSet!=null)
				resultSet.close();
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}
	//llll
	public long queryRowCount(String sql, Object[] args) throws Exception
	{
		List list = query4ListVector(sql,args,0,0);
		if(list.size()>0)
		{
			Vector v = (Vector)list.get(0);
			if(v.size()>0)
				return Long.parseLong(v.get(0).toString());
		}
		return -1;
	}
	public List<Vector> query(String sql, Object[] args) throws Exception
	{
		return this.query4ListVector(sql, args,0,0);
	}
	public List<Vector> query4ListVector(String sql, Object[] args,int start,int size) throws Exception
	{
		return query(sql,args,new ResultSetEntityMapping4Vector(),start,size);
	}
	public javax.swing.table.TableModel query4DefaultTableModel(String sql, Object[] args) throws Exception
	{
		Vector data = null;
		Vector columnNames = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			//psta=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			preparedStatement = connection.prepareStatement(sql);
			if (args != null && args.length != 0)
			{
				for (int i = 0; i < args.length; i++)
				{
					preparedStatement.setObject(i + 1, args[i]);
				}
			}
			resultSet = preparedStatement.executeQuery();
			columnNames=new Vector();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			for(int i=1;i<=columnCount;i++)
			{
				columnNames.add(metaData.getColumnLabel(i));
			}

			if(!resultSet.next())
			{
				return null;
			}
			data=new Vector();
			do{
				Vector obj = new Vector();
				for(int i=1;i<=columnCount;i++)
				{ 
					Object o=null;
					if (i == 3) {
						o = new Double(resultSet.getDouble(i));
					}
					else if(i==6)
					{
						o =new ImageIcon(resultSet.getString(i));
					}
					else
					{
						o=resultSet.getObject(i);
					}
					obj.add(o);
				}
				data.add(obj);
			}while (resultSet.next());
			return new DefaultTableModel(data,columnNames);
		}
		catch (SQLException e) {
			throw e;
		}
		finally
		{
			if(resultSet!=null)
				resultSet.close();
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}

	/**
	 * 适用于查询数据长度
	 * @param sql   select count(*) from XXX
	 * @return 数据的长度
	 * @throws SQLException 
	 */
	public int queryRowCount(String sql) throws SQLException
	{
		int rowCount = 0;
		ResultSet resultSet = this.connection.createStatement().executeQuery(sql);
		if (resultSet.next())
		{
			rowCount = resultSet.getInt(1);
		}
		return rowCount;
	}

	/**
	 * insert update delete
	 * 
	 * @param sql
	 * @return sql
	 * @throws SQLException 
	 */
	public int update(String sql) throws SQLException
	{
		int row = 0;
		Statement statement = null;
		try{
			statement = this.connection.createStatement();
			row = statement.executeUpdate(sql);
			return row;
		}catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			if(statement!=null)
				statement.close();
		}
	}
	/**
	 * @param sql
	 * @param values
	 * @param first start from 0
	 * @param size if size <0 then all result will return
	 * @return
	 * @throws SQLException
	 */
	public AcResult query4AcResult(String sql, Object[] values,int first,int size) throws SQLException {
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{	
			preparedStatement = this.connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					preparedStatement.setObject(i+1, values[i]);
				}
			}
			AcResult result = new AcResult();
			resultSet= preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int fieldCount = metaData.getColumnCount();
			String[] fieldNames = new String[fieldCount];
			String[] filedClassNames = new String[fieldCount];
			int[] fieldTypes = new int[fieldCount];
			for (int i = 0; i < fieldCount; i++) {
				fieldNames[i] = metaData.getColumnLabel(i + 1);
				fieldTypes[i] = metaData.getColumnType(i + 1);
				filedClassNames[i]=metaData.getColumnClassName(i+1);
			}
			result.setFieldNames(fieldNames);
			result.setFieldTypes(fieldTypes);
			result.setFiledClassNames(filedClassNames);
			if(first>0)
				resultSet.absolute(first);
			int tag=0;
			while (resultSet.next()&&(tag++<size||size<=0)) {

				Object[] row = new Object[fieldCount];
				for (int i = 0; i < fieldNames.length; i++) {
					row[i] = resultSet.getObject(i + 1);
					if (row[i] instanceof java.sql.Timestamp) {
						row[i] = row[i].toString();
						if (((String)row[i]).endsWith(".0")) {
							row[i] = ((String)row[i]).substring(0, ((String)row[i]).length() - 2);
						}
					}
				}
				result.add(row);
			}
			return result;
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally{
			if(preparedStatement!=null)
				preparedStatement.close();
			if(resultSet!=null)
				resultSet.close();
		}
	}
	public QueryResult<List<Map<String,Object>>> query4QRListMap(String queryString,Object[] values , int start, int size) throws Exception {
		return query4QueryResult(queryString,values,new ResultSetEntityMapping4Map(),start,size);
	}
	public <T> QueryResult<List<T>> query4QueryResult(String queryString,Object[] values ,IResultSetEntityMapping<T> mapping, int start, int length) throws Exception {

		String _queryString=queryString.trim().toLowerCase();
		queryString=queryString.trim();
		String _countQueryString=null;
		if(_queryString.startsWith("from"))
			_countQueryString="select count(*) "+queryString;
		else if(_queryString.indexOf(" from ")>-1)
		{
			_countQueryString="select count(*)"+queryString.substring(_queryString.indexOf(" from "));
		}
		_countQueryString =_countQueryString.replaceAll("order\\s+by\\s+\\S*(desc)?|(asc)?]$", " ");
		start=Math.abs(start);
		length=Math.abs(length);
		int total = queryRowCount(_countQueryString);;
		if(start<0)
		{
			start=0;
		}
		if(length==0)
		{
			length=total;
		}
		List<T> list = query(_queryString,values,mapping,start,length);
		QueryResult<List<T>> result=new QueryResult<List<T>>(total, start, length,list);
		return result;
	}
	public QueryResult<AcResult> query4QRAcResult(String queryString,Object[] values , int start, int length) throws SQLException {

		String _queryString=queryString.trim().toLowerCase();
		queryString=queryString.trim();
		String _countQueryString=null;
		if(_queryString.startsWith("from"))
			_countQueryString="select count(*) "+queryString;
		else if(_queryString.indexOf(" from ")>-1)
		{
			_countQueryString="select count(*)"+queryString.substring(_queryString.indexOf(" from "));
		}
		start=Math.abs(start); 
		length=Math.abs(length);
		int total = queryRowCount(_countQueryString);
		if(start<0)
		{
			start=0;
		}
		if(length==0)
		{
			length=total;
		}
		AcResult acResult = query4AcResult(_queryString,values,start,length);
		QueryResult<AcResult> result=new QueryResult<AcResult>(total,start , length,acResult);
		return result;
	}
	/**
	 * select sql
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet query(String sql) throws SQLException
	{
		//sta = con.createStatement();
		ResultSet resultSet=null;
		try{
			resultSet= this.connection.createStatement().executeQuery(sql);
			return resultSet;
		}catch(SQLException e)
		{
			throw e;
		}
		finally{
			if(resultSet!=null)
				resultSet.close();
		}
	}

	/**
	 * @throws SQLException 
	 *
	 */
	public void close() throws SQLException
	{
		if (connection != null)
		{
			connection.close();
			connection = null;
		}
	}
	protected void finalize() throws SQLException
	{
		close();
	}
}
class ResultSetEntityMapping4Map implements IResultSetEntityMapping<Map<String,Object>>
{

	public Map<String,Object> mapping(ResultSet resultSet) throws SQLException {
		Map<String,Object>  m=new HashMap<String, Object>();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int fieldCount = metaData.getColumnCount();
		for (int i = 0; i < fieldCount; i++) {
			Object v;
			String k= metaData.getColumnLabel(i + 1);
			v = resultSet.getObject(i + 1);
			if (v instanceof java.sql.Timestamp) {
				v = v.toString();
				if (((String)v).endsWith(".0")) {
					v = ((String)v).substring(0, ((String)v).length() - 2);
				}
			}
			m.put(k, v);
		}
		return m;
	}
	
}
class ResultSetEntityMapping4Vector implements IResultSetEntityMapping<Vector>
{
	

		public Vector mapping(ResultSet resultSet) throws SQLException {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int fieldCount = metaData.getColumnCount();
			Vector obj = new Vector();
			for (int i = 0; i < fieldCount; i++) {
				Object o=resultSet.getObject(i+1);
				obj.add(o);
			}
			return obj;
		}
	
}
