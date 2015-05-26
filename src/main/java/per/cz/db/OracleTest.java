package per.cz.db;

import java.sql.*;  
import java.util.List;
import java.util.Map;


public class OracleTest {  

	public static void main(String[] args) throws Exception {  
//		String tableData = DBUtil.getTableData("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.20.244:1521:SYHIS", "COM_USER_EMR", "his_emr", "wxhis.view_emr_feelist");
//		String tableData = DBUtil.getTableData("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.20.1:1521:HISDB", "COM_USER_EMR", "his_emr", "select * from WXHIS.VIEW_EMR_PATIENTLIST");
		//String login_id="B100003511";
//		String sql="select * from WXHIS.VIEW_EMR_PATIENTLIST where 住院号='"+login_id+"' or 公民身份证='"+login_id+"' or 医疗保险卡号='"+login_id+"'";
//		DBManager dbM=DBUtil.getManager(HISDBConfig.DRIVER, HISDBConfig.URL+HISDBConfig.DBNAME, HISDBConfig.UNAME, HISDBConfig.PWD);
		String sql="select * from r_suttagrecord_view";
//		String sql="select * from r_suttagrecord_patient_view";
		DBManager dbM=DBUtil.getManager(MysqlConfig.DRIVER, MysqlConfig.URL+MysqlConfig.DBNAME, MysqlConfig.UNAME, MysqlConfig.PWD);
//		List<R_sutTagRecord> query = dbM.query(sql, null, R_sutTagRecord.class, 0, 1);
//		List<Map<String,Object>> list =dbM.query4ListMap(sql, null, 0, 1);
//		String tableData = DBUtil.getData4AcResult("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//192.168.20.1:1521/hisdb", "COM_USER_EMR", "his_emr", "select * from WXHIS.VIEW_EMR_PATIENTLIST",0,1);
//		System.out.println(query);
//		String tableData = DBUtil.getTableType("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.20.1:1521:HISDB", "COM_USER_EMR", "his_emr", "WXHIS.VIEW_EMR_PATIENTLIST","");
//		String tableData = DBUtil.getTableData("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@10.41.88.71:1521:orcl", "system", "clu111", "test");
		//System.out.println(tableData);
	}  

} 