package tiac.checkListWithEmployees.entity.DTO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import tiac.checkListWithEmployees.Util.UniqueUsername;
import tiac.checkListWithEmployees.entity.EmployeeCheckList;
import tiac.checkListWithEmployees.entity.RoleEntity;

public class EmployeeDTO {

	@NotBlank(message = "Name must not be blank or null")
	@Size(min = 2, max = 30, message = "Last name length must be between {min} and {max}")
	private String name;

	private String user;

	private String token;

	@NotBlank(message = "Last name must not be blank or null")
	@Size(min = 2, max = 30, message = "First name length must be between {min} and {max}")
	private String surname;

	@Size(min = 12, max = 12, message = " social security number is a {min} digit number")
	private String socialSecurityNumber;

	@NotBlank(message = "phone number cannot be blank")
	private String phoneNumber;

	@Email
	@NotEmpty(message = "email must be provided")
	private String email;

	@UniqueUsername
	@NotEmpty(message = "Username must not be blank or null and should be unique")
	@Size(min = 5, max = 15, message = "Username length must be between {min} and {max}")
	private String username;



	@NotBlank(message = "Password must not be blank or null")
	@Size(min = 5, max = 15, message = "Password length must be between {min} and {max}")
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "Password is not valid.")
	private String password;

	
	@NotBlank(message = "Password must not be blank or null")
	@Size(min = 5, max = 15, message = "Password length must be between {min} and {max}")
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "Password is not valid.")
	private String confirmPassword;

	@NotBlank
	private String educationLevel;

	private RoleEntity role;

	private Set<EmployeeCheckList> employeeCheckLists;

	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDTO(
			@NotBlank(message = "Name must not be blank or null") @Size(min = 2, max = 30, message = "Last name length must be between {min} and {max}") String name,
			String token,
			@NotBlank(message = "Last name must not be blank or null") @Size(min = 2, max = 30, message = "First name length must be between {min} and {max}") String surname,
			String socialSecurityNumber, String phoneNumber, String email,
			@NotBlank(message = "Useername must not be blank or null and should be unique") @Size(min = 5, max = 15, message = "Username length must be between {min} and {max}") String username,
			@NotBlank(message = "Password must not be blank or null") @Size(min = 5, max = 15, message = "Password length must be between {min} and {max}") @Pattern(regexp = "[a-zA-Z0-9]*", message = "Password is not valid.") String password,
			@NotBlank(message = "Password must not be blank or null") @Size(min = 5, max = 15, message = "Password length must be between {min} and {max}") @Pattern(regexp = "[a-zA-Z0-9]*", message = "Password is not valid.") String confirmPassword,
			String educationLevel, RoleEntity role, Set<EmployeeCheckList> employeeCheckLists) {
		super();
		this.name = name;
		this.token = token;
		this.surname = surname;
		this.socialSecurityNumber = socialSecurityNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.educationLevel = educationLevel;
		this.role = role;
		this.employeeCheckLists = employeeCheckLists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public Set<EmployeeCheckList> getEmployeeCheckLists() {
		return employeeCheckLists;
	}

	public void setEmployeeCheckLists(Set<EmployeeCheckList> employeeCheckLists) {
		this.employeeCheckLists = employeeCheckLists;
	}

}
