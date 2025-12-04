package dto;

import java.sql.Timestamp;

public class AppointmentDTO {
	private long appointmentId;
	private Timestamp appointmentDateTime;
//	private String status;
//	private long doctorId;
//	private long patientId;
	private String firstName;
	private String lastName;
	public AppointmentDTO(long appointmentId, Timestamp appointmentDateTime, String firstName, String lastName) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDateTime = appointmentDateTime;
		this.firstName = firstName;
		this.lastName = lastName;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "AppointmentDTO [appointmentId=" + appointmentId + ", appointmentDateTime=" + appointmentDateTime
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
