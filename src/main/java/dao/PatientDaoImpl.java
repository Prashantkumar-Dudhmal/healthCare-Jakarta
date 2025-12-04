package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Patient;
import utils.DBUtils;

public class PatientDaoImpl implements PatientDao{
	private Connection cn;
	private PreparedStatement ps1;
	
	public PatientDaoImpl() throws SQLException {
		cn = DBUtils.getConnection();
		ps1 = cn.prepareStatement("select * from patients where user_id=?");
		System.out.println("PatientDao Created!!");
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
		System.out.println("Patient Dao Impl CleanUp Done!!");
	}

	@Override
	public Patient getPatientDetails(long userId) throws SQLException {
		
		ps1.setLong(1, userId);
		try(ResultSet rs = ps1.executeQuery()){
			if(rs.next()) {
				return new Patient(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5));
			}
		}
		return null;
	}

}
