package dao;

import java.sql.SQLException;

public interface BaseDao {
	public void cleanUp() throws SQLException;
}
