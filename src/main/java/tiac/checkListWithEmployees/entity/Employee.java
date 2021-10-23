package tiac.checkListWithEmployees.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@DynamicInsert
@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Employee {

	public RoleEntity getRole() {
		return role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	 @Column(columnDefinition = "varchar(256) default 'John'")
	private String name;

	 @Column(columnDefinition = "varchar(256) default 'Snow'")
	private String surname;

	@Column(name = "soc_sec_number", columnDefinition= "varchar(256) default '000000000000'")
	private String socialSecurityNumber;

	@Column(name = "phone_number",columnDefinition= "varchar(256) default '0000000000'")
	private String phoneNumber;

	@Column(columnDefinition= "varchar (256) default 'info@email.com'")
	@Email
	private String email;
	
	@Column(columnDefinition= "varchar(256) default 'user'")
	private String username;
	
	@Column(columnDefinition= "varchar(256) default '12345'")
	private String password;

	@Column(name = "education_level",columnDefinition= "varchar(256) default 'xxx'")
	private String educationLevel;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private RoleEntity role;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<EmployeeCheckList> employeeCheckLists;

	public Employee(Long id, String name, String surname, String socialSecurityNumber, String phoneNumber, String email,
			String username, String password, String educationLevel, RoleEntity role,
			Set<EmployeeCheckList> employeeCheckLists) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.socialSecurityNumber = socialSecurityNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.educationLevel = educationLevel;
		this.role = role;
		this.employeeCheckLists = employeeCheckLists;
	}

	public Employee() {

	}

	public Set<EmployeeCheckList> getEmployeeCheckLists() {
		return employeeCheckLists;
	}

	public void setEmployeeCheckLists(Set<EmployeeCheckList> employeeCheckLists) {
		this.employeeCheckLists = employeeCheckLists;
	}

	public Long getId() {
		return id;
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

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
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

	public void setRole(RoleEntity role) {
		this.role = role;
	}
}
