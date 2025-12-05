package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Doctor;
import utils.DBUtils;

public class DoctorDaoImpl implements DoctorDao{
	private Connection cn;
	private PreparedStatement ps1;
	public DoctorDaoImpl(String url,String usr,String pass) throws SQLException {
		cn = DBUtils.getConnection(url,usr,pass);
		ps1 = cn.prepareStatement("select * from doctors where user_id=?");
		System.out.println("Doctor Dao Created!!");
	}
	@Override
	public void cleanUp() throws SQLException {
		if(cn!=null) {
			cn.close();
			cn=null;
		}
		if(ps1!=null) {
			ps1.close();
			ps1=null;
		}
		System.out.println("Doctor Dao CleanUp DONE!!");
	}

	@Override
	public Doctor getDoctorDetails(long uId) throws SQLException {
		ps1.setLong(1, uId);
		try(ResultSet rs = ps1.executeQuery()){
			if(rs.next()) {
				return new Doctor(rs.getLong(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getLong(6));
			}
		}
		return null;
	}

}
