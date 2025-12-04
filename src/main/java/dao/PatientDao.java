package dao;

import java.sql.SQLException;

import entities.Patient;

public interface PatientDao extends BaseDao {
	public Patient getPatientDetails(long userId) throws SQLException;
	
}
