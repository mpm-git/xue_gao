package per.cz.db.pool;

import java.sql.*;
import java.util.Properties;

/**
 * @author chongzi
 *	May 15, 20126:53:24 PM
 *
 */
public interface Pool
{
	/*
			<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
			<property name="javax.persistence.jdbc.url" value="jdbc:mysql://23.100.103.123:3306/portaldemo" />
			<property name="javax.persistence.jdbc.user" value="st" />
			<property name="javax.persistence.jdbc.password" value="P@ssw0rd!@#" />
	 */
	String getDriver();
	String getUrl();
	String getUser();
	String getPassword();
	Properties getProperties();
	String getKey();
//	 Properties p
	/** 
	 * 运新一个数据库连接池。<br> 采用默认的最低空闲连接数,最高空闲连接数,最大连接数。
	 * @param db 数据库名
	 * @param u  用户名
	 * @param p  密码
	 *  @return 是否创建成功
	 */
//	public boolean start(String driver,String url, String user, String password); // 启动数据库连接池服务
	// 以下start函数将允许用户设置最低空闲连接数，最高空闲连接数,最大连接数
	/** 
	 * 运新一个数据库连接池。<br>
	 * @param l  最小空闲连接数
	 * @param m  连最大空闲连接数
	 * @param mc 最大的连接数
	 * @param db 数据库名
	 * @param u  用户名
	 * @param p  密码
	 * @return 是否创建成功
	 */
//	public boolean start(int lows, int maxs, int maxc, String driver,String url,String user, String password);

	/**
	 * 获取连接池中的连接
	 * @return 如果连接池中有空闲的连接则返回连接，如果连接池没有空闲的连接则返回null
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException; // 得到连接器

	/**将连接返还到连接池中
	 * @param con　要返还的连接
	 * @return　当没有init或者已经close之后　返回false
	 */
	public boolean freeConnection(Connection con);// 将连接返回到连接池中
	/***
	 *将内容清空<br>
	 *且将本连接池对象置为无用状态
	 *@return 当此连接池已经关闭或是还没有初始化则返回false
	 */
	public boolean close(); // 清除连接池，并且关闭它（使之变得不可用）
}
