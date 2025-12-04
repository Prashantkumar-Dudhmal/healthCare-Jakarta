package dao;

import java.sql.SQLException;

import entities.User;

public interface UserDao extends BaseDao{
	public User getUser(String email,String password)throws SQLException;
}
