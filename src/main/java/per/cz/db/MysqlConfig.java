package per.cz.db;

import java.io.InputStream;
import java.util.Properties;

public class MysqlConfig {
//"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mpm", "mpm"
	public static String DRIVER="";
	public static String URL="";
	public static String DBNAME="";
	public static String UNAME="";
	public static String PWD="";
	static{
		try {
			InputStream resourceAsStream = MysqlConfig.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties p=new Properties();
			p.load(resourceAsStream);
			DRIVER=p.getProperty("jdbc.driverClassName","com.mysql.jdbc.Driver");
			URL=p.getProperty("jdbc.url","jdbc:mysql://localhost:3306/");
			//System.out.println(URL);
			UNAME=p.getProperty("jdbc.username","mpm");
			PWD=p.getProperty("jdbc.password","mpm");
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
	public static void main(String[] args) {
		
	}
}
