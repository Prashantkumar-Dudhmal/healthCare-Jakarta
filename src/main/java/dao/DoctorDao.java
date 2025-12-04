package dao;

import java.sql.SQLException;

import entities.Doctor;

public interface DoctorDao extends BaseDao {
	public Doctor getDoctorDetails(long uId)throws SQLException;
}
