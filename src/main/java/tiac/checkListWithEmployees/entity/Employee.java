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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Employee {


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

}
