package entities;

public class Patient {
	long patientID;
	String bloodGroup;
	String familyHistory;
	String gender;
	long userId;
	public Patient(long patientID, String bloodGroup, String familyHistory, String gender, long userId) {
		super();
		this.patientID = patientID;
		this.bloodGroup = bloodGroup;
		this.familyHistory = familyHistory;
		this.gender = gender;
		this.userId = userId;
	}
	public long getPatientID() {
		return patientID;
	}
	public void setPatientID(long patientID) {
		this.patientID = patientID;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", bloodGroup=" + bloodGroup + ", familyHistory=" + familyHistory
				+ ", gender=" + gender + ", userId=" + userId + "]";
	}
	
}
