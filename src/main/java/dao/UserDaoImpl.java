package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;
import utils.DBUtils;

public class UserDaoImpl implements UserDao {
	private Connection cn;
	private PreparedStatement ps1;
	
	public UserDaoImpl() throws SQLException {
		cn = DBUtils.getConnection();
		ps1 = cn.prepareStatement("select * from users where email = ? and password=?");
	}

	@Override
	public void cleanUp() throws SQLException {
		// TODO Auto-generated method stub
		if(cn!=null) {
			cn.close();
			cn=null;
		}
		if(ps1!=null) {
			ps1.close();
			ps1=null;
		}
		System.out.println("User Dao Impl CleanUp Done!!");
	}

	@Override
	public User getUser(String email,String password) throws SQLException {
		
		ps1.setString(1, email);
		ps1.setString(2, password);
		try(ResultSet rs = ps1.executeQuery()){
			if(rs.next()) {
				return new User(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getInt(8),rs.getString(9));
			}
		}
		
		return null;
	}

}
