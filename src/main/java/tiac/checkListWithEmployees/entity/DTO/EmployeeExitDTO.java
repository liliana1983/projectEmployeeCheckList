package tiac.checkListWithEmployees.entity.DTO;

public class EmployeeExitDTO {
	private String name;
	private String surname;
	private String socialSecurityNumber;
	private String phoneNumber;
	private String email;
	private String username;
	private String educationLevel;
	public EmployeeExitDTO(String name, String surname, String socialSecurityNumber, String phoneNumber, String email,
			String username, String educationLevel) {
		super();
		this.name = name;
		this.surname = surname;
		this.socialSecurityNumber = socialSecurityNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.educationLevel = educationLevel;
	}
	public EmployeeExitDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
}
