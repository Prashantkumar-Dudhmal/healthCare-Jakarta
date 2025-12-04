package entities;

public class Doctor {
	long doctorId;
	int experienceInYears;
	int fees;
	String qualifications;
	String speciality;
	long userId;
	public Doctor(long doctorId, int experienceInYears, int fees, String qualifications, String speciality,
			long userId) {
		super();
		this.doctorId = doctorId;
		this.experienceInYears = experienceInYears;
		this.fees = fees;
		this.qualifications = qualifications;
		this.speciality = speciality;
		this.userId = userId;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public int getExperienceInYears() {
		return experienceInYears;
	}
	public void setExperienceInYears(int experienceInYears) {
		this.experienceInYears = experienceInYears;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public String getQualifications() {
		return qualifications;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", experienceInYears=" + experienceInYears + ", fees=" + fees
				+ ", qualifications=" + qualifications + ", speciality=" + speciality + ", userId=" + userId + "]";
	}
	
}
