package per.cz.db.dao.map;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetEntityMapping<T>
{
	public  T mapping(ResultSet resultSet)throws Exception ;
}
