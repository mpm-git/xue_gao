package per.cz.db.pool;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import per.cz.db.pool.impl.C3p0Pool;
import per.cz.util.EncryptionUtil;


public class PoolListUtil {


	//	private static List<Pool> dbPoolList=new ArrayList<Pool>();
	private static Map<String,Pool> dbPoolMap=new HashMap<String, Pool>();
	public static Pool getPool(String driver, String url, String user, String password)
	{
		Set<String> keySet = dbPoolMap.keySet();
		String key = getKey(driver,url,user,password);;
		Pool pool=dbPoolMap.get(key);
		if(pool==null)
		{
			try {
				Pool instance = C3p0Pool.getInstance(driver.trim(), url.trim(), user, password);
				dbPoolMap.put(key,instance);
				return instance;
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		return pool;
	}
	public static List<Pool> getPoolsByUrl(String url )
	{
		List<Pool> pl=new ArrayList<Pool>();
		Set<String> keySet = dbPoolMap.keySet();
		for (String k : keySet) {
			Pool pool =dbPoolMap.get(k);
			if(pool.getUrl().trim().equals(url.trim()))
				pl.add(pool);
		}
		return pl;
	}
	public static Pool getPoolByKey(String key)
	{
		return dbPoolMap.get(key);
	}
	public static String getPoolKey(String driver, String url, String user, String password)
	{
		String key = getKey(driver,url,user,password);;
		Pool pool = getPool(driver,url,user,password);
		if(pool!=null)
			return key;
		return null;
	}
	public static String getKey(String driver, String url, String user, String password)
	{
		String _key=driver.trim()+url.trim()+user+password;
		String key = EncryptionUtil.getMd5(_key);
		return key;
	}
}
