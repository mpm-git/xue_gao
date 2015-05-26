package per.cz.db.pool.impl;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import per.cz.db.pool.Pool;
import per.cz.util.EncryptionUtil;

public class C3p0Pool implements Pool {
	//	private static C3p0Pool dbPool;
	private static Map<String,C3p0Pool> dbPools;

	private ComboPooledDataSource dataSource=new ComboPooledDataSource();
	//	private String driver;
	//	private String url; 
	//	private String user;
	//	private String password;
	private Properties properties;

	//	private C3p0Pool(String driver, String url, String user, String password) throws PropertyVetoException
	//	{
	//		dataSource.setUser( "id ");       
	//        dataSource.setPassword( "pw ");       
	//        dataSource.setJdbcUrl( "jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=GB2312 "); 
	//        dataSource.setDriverClass( "com.mysql.jdbc.Driver "); 
	//        dataSource.setInitialPoolSize(2); 
	//        dataSource.setMinPoolSize(1); 
	//        dataSource.setMaxPoolSize(10); 
	//        dataSource.setMaxStatements(50); 
	//        dataSource.setMaxIdleTime(60);       
	//	}
	//	private C3p0Pool(int lows, int maxs, int maxc, String driver,String url, String user, String password)
	//	{
	//		properties.
	//	}
	private C3p0Pool(Properties properties) throws PropertyVetoException
	{
		this.properties=new Properties();
		dataSource.setUser(properties.getProperty("user"));
		dataSource.setPassword(properties.getProperty("password"));
		dataSource.setJdbcUrl(properties.getProperty("jdbcUrl")); 
		dataSource.setDriverClass(properties.getProperty("driverClass")); 
		dataSource.setMaxIdleTime(60*60*7);
		dataSource.setIdleConnectionTestPeriod(60*60);
//		dataSource.setInitialPoolSize(2); 
//		dataSource.setMinPoolSize(1); 
//		dataSource.setMaxPoolSize(10); 
//		dataSource.setMaxStatements(50); 
//		dataSource.setMaxIdleTime(60);   
	}

	public static Pool getInstance(int minPoolSize, int maxPoolSize, String driver,String url, String user, String password) throws PropertyVetoException
	{
		Properties properties=getDefaultProperties();
		properties.setProperty("minPoolSize", minPoolSize+"");
		properties.setProperty("maxPoolSize", maxPoolSize+"");
		properties.setProperty("jdbcUrl", url);
		properties.setProperty("user", user);
		properties.setProperty("driverClass", driver);
		properties.setProperty("password", password);
		return getInstance(properties);
	}
	public static Pool getInstance(String driver, String url, String user, String password) throws PropertyVetoException
	{
		Properties properties=getDefaultProperties();
		properties.setProperty("jdbcUrl", url);
		properties.setProperty("user", user);
		properties.setProperty("driverClass", driver);
		properties.setProperty("password", password);
		return getInstance(properties);
	}
	public static Pool getInstance(Properties properties) throws PropertyVetoException
	{
		C3p0Pool c3p0Pool = new C3p0Pool(properties);
		return c3p0Pool;
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	private static Properties getDefaultProperties()
	{
		Properties properties=new Properties();
		return properties;
	}
	public boolean freeConnection(Connection con) {
		return false;
	}

	public boolean close() {
		dataSource.close();
		return true;
	}
	public String getDriver() {
		return this.dataSource.getDriverClass();
	}
	public String getUrl() {
		return this.dataSource.getJdbcUrl();
	}
	public String getUser() {
		return this.dataSource.getUser();
	}
	public String getPassword() {
		return this.dataSource.getPassword();
	}
	public Properties getProperties() {
		return this.properties;
	}
	private String _key;
	public String getKey() {
		if(_key==null)
		{
			String _key=this.getDriver().trim()+this.getUrl().trim()+this.getUser()+this.getPassword();
			this._key=EncryptionUtil.getMd5(_key);
		}
		return _key;
	}

}
