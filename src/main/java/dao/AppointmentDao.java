package dao;

import java.sql.SQLException;
import java.util.List;

import dto.AppointmentDTO;
import entities.Appointment;

public interface AppointmentDao extends BaseDao{
	//Patient DashBoard methods:-
	List<AppointmentDTO> getAppointments(long patientId) throws SQLException;
	String bookAppointment(Appointment a) throws SQLException;
	int cancelAppointment(long aId) throws SQLException;
	//Doctor DashBoard Methods:-
	List<AppointmentDTO> getDocAppointments(long docId) throws SQLException;
	int markComplete(long appId)throws SQLException;
}
