package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	public AppointmentDaoImpl() throws SQLException {
		cn = DBUtils.getConnection();
		ps1 = cn.prepareStatement("select a.appointment_id,a.appointment_date_time,u.first_name,u.last_name from appointments a join doctors d on a.doctor_id=d.doctor_id join users u on d.user_id=u.user_id where a.patient_id =? and a.status='SCHEDULED' and a.appointment_date_time > now()");
		ps2 = cn.prepareStatement("insert into appointments (appointment_date_time,doctor_id,patient_id,status) values(?,?,?,'SCHEDULED')");
		ps3 = cn.prepareStatement("update appointments set status='CANCELLED' where appointment_id=?;");
		ps4 = cn.prepareStatement("select appointment_date_time from appointments where doctor_id=? and appointment_date_time between ? and ?");
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
	public boolean bookAppointment(Appointment a) throws SQLException {
		
//		ps4.setLong(1, a.getAppointmentId());
		Timestamp adtPlusHalf = a.getAppointmentDateTime();
		int hr = (a.getAppointmentDateTime().getHours()+1)%24;
		adtPlusHalf.setHours(hr);
		ps4.setLong(1, a.getDoctorId());
		ps4.setTimestamp(2, a.getAppointmentDateTime());
		ps4.setTimestamp(3, adtPlusHalf);
		try(ResultSet rs = ps4.executeQuery()){
			if(rs.next()) {
				System.out.println("Appointment already present!!");
				return false;
			}
			else {
				System.out.println("No appointment At that time , Good to Go!!");
			}
		}
//		if(a.getAppointmentDateTime().equals(adt)) {
//			return false;
//		}
//		else if(a.getAppointmentDateTime().before())
		ps2.setTimestamp(1, a.getAppointmentDateTime());
		ps2.setLong(2, a.getDoctorId());
		ps2.setLong(3, a.getPatientId());
		int res = ps2.executeUpdate();
		if(res==1)
		return true;
		else
			return false;
	}

	@Override
	public int cancelAppointment(long aId) throws SQLException {
		ps3.setLong(1, aId);
		return ps3.executeUpdate();
	}

}
