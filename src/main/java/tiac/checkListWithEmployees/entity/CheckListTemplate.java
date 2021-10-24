package tiac.checkListWithEmployees.entity;

import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class CheckListTemplate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition= "boolean default false")
	private boolean type;
	
	@Column(columnDefinition= "varchar(256) default 'check list description to dos'")
	private String description;
	@JsonIgnore
	@OneToMany(mappedBy = "checkList", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<CheckListItemTemplate> items;

	@OneToMany(mappedBy = "checkListTemplate", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<EmployeeCheckList> employeeCheckLists;
	
	public CheckListTemplate(boolean type, String description) {
		this.type = type;
		this.description = description;
	}


}