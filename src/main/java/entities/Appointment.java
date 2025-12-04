package entities;

import java.sql.Timestamp;

public class Appointment {
	private long appointmentId;
	private Timestamp appointmentDateTime;
	private String status;
	private long doctorId;
	private long patientId;
	public Appointment(Timestamp appointmentDateTime, long doctorId,
			long patientId) {
		this.appointmentDateTime = appointmentDateTime;
		this.doctorId = doctorId;
		this.patientId = patientId;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Timestamp getAppointmentDateTime() {
		return appointmentDateTime;
	}
	public void setAppointmentDateTime(Timestamp appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", appointmentDateTime=" + appointmentDateTime
				+ ", status=" + status + ", doctorId=" + doctorId + ", patientId=" + patientId + "]";
	}
	
}
