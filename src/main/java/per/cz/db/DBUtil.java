package per.cz.db;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import per.cz.db.pool.Pool;
import per.cz.db.pool.PoolListUtil;
import per.cz.util.gson.GsonUtil;

public class DBUtil {

	public static String getTableData(String dvname,String url,String username,String pwd,String dbtname)
	{
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			List list = dbM.query4ListMap("select * from "+dbtname,null,0,0);
			return GsonUtil.objectToJson(list);						
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	public static String getData4QRListMap(String dvname,String url,String username,String pwd,String sql,int start, int pageSize)
	{
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			return GsonUtil.objectToJson(dbM.query4QRListMap(sql,null,start,pageSize));						
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	public static String getData4ListMap(String dvname,String url,String username,String pwd,String sql,int start, int pageSize)
	{
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			return GsonUtil.objectToJson(dbM.query4ListMap(sql,null,start,pageSize));						
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	public static String getData4QRAcResult(String dvname,String url,String username,String pwd,String sql,int start, int length)
	{
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			return GsonUtil.objectToJson(dbM.query4QRAcResult(sql,null,start,length));						
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	public static String getData4Vector(String dvname,String url,String username,String pwd,String sql,int start, int pageSize)
	{
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			return GsonUtil.objectToJson(dbM.query4ListVector(sql,null,start,pageSize));						
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	public static String getData4AcResult(String dvname,String url,String username,String pwd,String sql,int start, int pageSize)
	{
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			return GsonUtil.objectToJson(dbM.query4AcResult(sql,null,start ,pageSize));						
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	
	public static String getTableType(String dvname,String url,String username,String pwd,String typetable,String dbtname) {
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			List list = dbM.query4ListMap("select fieldName,field,type,defaultValue,name from `"+typetable+"` where `table`=?",new Object[]{dbtname},0,0);
			return GsonUtil.objectToJson(list);
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	
	public static String insert(String dvname,String url,String username,String pwd,String dbtname,String json) {
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			Map<String, Object> map=(Map<String, Object>) GsonUtil.jsonToMap(json);
			return String.valueOf(dbM.insert(dbtname,map));			
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	
	public static String update(String dvname,String url,String username,String pwd,String dbtname,String old_data,String new_data) {
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);			
			return String.valueOf(dbM.update(dbtname,(Map<String, Object>)GsonUtil.jsonToMap(old_data),(Map<String, Object>)GsonUtil.jsonToMap(new_data)));
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	
	public static String delete(String dvname,String url,String username,String pwd,String dbtname,String json) {
		try {
			DBManager dbM=DBUtil.getManager(dvname, url, username, pwd);
			Map<String, Object> map=(Map<String, Object>) GsonUtil.jsonToMap(json);
			return String.valueOf(dbM.delete(dbtname, map));
		} catch (Exception e) {
			PrintStream pw=null;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			pw=new PrintStream(byteOut);
			e.printStackTrace(pw);
			return  byteOut.toString();
		}
	}
	
	public static DBManager getManager(String dvname,String url,String username,String pwd) {
		try {
			Pool pool = PoolListUtil.getPool(dvname, url, username, pwd);
			Connection connection = pool.getConnection();
			DBManager dbM=new DBManager(connection);
			return dbM;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
