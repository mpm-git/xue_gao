package per.cz.db;

import java.io.InputStream;
import java.util.Properties;

public class SqlServerDBConfig {

	public static String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String URL="jdbc:sqlserver://192.168.2.55";
	public static String DBNAME="mandala";
	public static String UNAME="q";
	public static String PWD="1";
	static{
		InputStream resourceAsStream = MysqlConfig.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties p=new Properties();
		try {
			p.load(resourceAsStream);
			DRIVER=p.getProperty("mandala.driverClassName","com.microsoft.sqlserver.jdbc.SQLServerDriver");
			URL=p.getProperty("mandala.url","jdbc:sqlserver://192.168.2.55");
			DBNAME=p.getProperty("mandala.dbName","mandala");
			UNAME=p.getProperty("mandala.username","q");
			PWD=p.getProperty("mandala.password","1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
			jdbc.driverClassName= com.mysql.jdbc.Driver
			jdbc.dialect=com.st.crm.MySQL5Dialect.MySQL5Dialect_ext
			jdbc.url=jdbc:mysql://localhost:3306/mpm?useUnicode=true&amp;characterEncoding=utf8
			jdbc.username=mpm
			jdbc.password=mpm
		 */
	}
	public static String getDRIVER() {
		return DRIVER;
	}
	public static void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}
	public static String getURL() {
		return URL;
	}
	public static void setURL(String uRL) {
		URL = uRL;
	}
	public static String getDBNAME() {
		return DBNAME;
	}
	public static void setDBNAME(String dBNAME) {
		DBNAME = dBNAME;
	}
	public static String getUNAME() {
		return UNAME;
	}
	public static void setUNAME(String uNAME) {
		UNAME = uNAME;
	}
	public static String getPWD() {
		return PWD;
	}
	public static void setPWD(String pWD) {
		PWD = pWD;
	}
}
