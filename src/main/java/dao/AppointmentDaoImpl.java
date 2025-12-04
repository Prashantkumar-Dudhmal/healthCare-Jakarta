package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AppointmentDTO;
import entities.Appointment;
import utils.DBUtils;

public class AppointmentDaoImpl implements AppointmentDao{
	private Connection cn;
	private PreparedStatement ps1;
	private PreparedStatement ps2;
	private PreparedStatement ps3;
	private PreparedStatement ps4;
	private PreparedStatement ps5;
	private PreparedStatement ps6;
	
	public AppointmentDaoImpl() throws SQLException {
		cn = DBUtils.getConnection();
		ps1 = cn.prepareStatement("select a.appointment_id,a.appointment_date_time,u.first_name,u.last_name from appointments a join doctors d on a.doctor_id=d.doctor_id join users u on d.user_id=u.user_id where a.patient_id =? and a.status='SCHEDULED' and a.appointment_date_time > now() orderby 2");
		ps2 = cn.prepareStatement("insert into appointments (appointment_date_time,doctor_id,patient_id,status) values(?,?,?,'SCHEDULED')");
		ps3 = cn.prepareStatement("update appointments set status='CANCELLED' where appointment_id=?;");
		ps4 = cn.prepareStatement("SELECT appointment_date_time FROM appointments WHERE doctor_id = ? AND patient_id= ? AND appointment_date_time BETWEEN ? AND DATE_ADD(?, INTERVAL 1 HOUR);");
		ps5 = cn.prepareStatement("select a.appointment_id,a.appointment_date_time,u.first_name,u.last_name from appointments a join patients p on a.patient_id=p.patient_id join users u on p.user_id=u.user_id where a.doctor_id =? and a.status='SCHEDULED' and a.appointment_date_time > now() orderby 2");
		ps6 = cn.prepareStatement("update appointments set status='COMPLETED' where appointment_id=?;");
		System.out.println("AppointmentDao Created!!");
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
		if(ps2!=null) {
			ps2.close();
			ps2=null;
		}
		if(ps3!=null) {
			ps3.close();
			ps3=null;
		}
		if(ps4!=null) {
			ps4.close();
			ps4=null;
		}
		if(ps5!=null) {
			ps5.close();
			ps5=null;
		}
		if(ps6!=null) {
			ps6.close();
			ps6=null;
		}
		System.out.println("Appointment Dao Impl CleanUp Done!!");
	}

	@Override
	public List<AppointmentDTO> getAppointments(long patientId) throws SQLException {
		ps1.setLong(1, patientId);
		List<AppointmentDTO> ls = new ArrayList<>();
		try(ResultSet rs = ps1.executeQuery()){
			while(rs.next()) {
				ls.add(new AppointmentDTO(rs.getLong(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4)));
			}
		}
		return ls;
	}
	@Override
	public List<AppointmentDTO> getDocAppointments(long docId) throws SQLException {
		ps5.setLong(1, docId);
		List<AppointmentDTO> ls = new ArrayList<>();
		try(ResultSet rs = ps5.executeQuery()){
			while(rs.next()) {
				ls.add(new AppointmentDTO(rs.getLong(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4)));
			}
		}
		return ls;
	}

	@Override
	public String bookAppointment(Appointment a) throws SQLException {
		ps4.setLong(1, a.getDoctorId());
		ps4.setLong(2, a.getPatientId());
		ps4.setTimestamp(3, a.getAppointmentDateTime());
		ps4.setTimestamp(4, a.getAppointmentDateTime());
		try(ResultSet rs = ps4.executeQuery()){
			if(rs.next()) {
				System.out.println("Appointment already present!!");
				return "Either Doctor is Unavailable or You already have an Appointment!!";
			}
			else {
				System.out.println("No appointment At that time , Good to Go!!");
			}
		}
		ps2.setTimestamp(1, a.getAppointmentDateTime());
		ps2.setLong(2, a.getDoctorId());
		ps2.setLong(3, a.getPatientId());
		int res = ps2.executeUpdate();
		if(res==1)
		return "Booking Done !!";
		else
			return "Booking Error :(";
	}

	@Override
	public int cancelAppointment(long aId) throws SQLException {
		ps3.setLong(1, aId);
		return ps3.executeUpdate();
	}

	@Override
	public int markComplete(long appId) throws SQLException {
		ps6.setLong(1, appId);
		return ps6.executeUpdate();
	}

}
