package per.cz.db;

import java.io.InputStream;
import java.util.Properties;

public class OracleConfig {

	public static String DRIVER="oracle.jdbc.driver.OracleDriver";
	public static String URL="jdbc:sqlserver://192.168.20.244:";
	public static String DBNAME="SYHIS";
	public static String UNAME="COM_USER_EMR";
	public static String PWD="his_emr";
	static{
		InputStream resourceAsStream = MysqlConfig.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties p=new Properties();
		try {
			p.load(resourceAsStream);
			DRIVER=p.getProperty("his.driverClassName","oracle.jdbc.driver.OracleDriver");
			URL=p.getProperty("his.url","jdbc:oracle:thin:@192.168.20.2:1521:");
			DBNAME=p.getProperty("his.dbName","HISDB");
			UNAME=p.getProperty("his.username","COM_USER_EMR");
			PWD=p.getProperty("his.password","his_emr");
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
