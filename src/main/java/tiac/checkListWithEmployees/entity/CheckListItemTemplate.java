package tiac.checkListWithEmployees.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@DynamicInsert
@SQLDelete(sql = "UPDATE check_list_item_template SET active = false WHERE id=?")
@Where(clause = "active=true")
@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class CheckListItemTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition= "varchar(256) default 'one to do'")
	private String description;
	
	private boolean active = Boolean.TRUE;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_id")
	private CheckListTemplate checkList;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "time_dropdown")
	private TimeFrame timeDropdown;
	

	

	public CheckListItemTemplate(String description, boolean active, CheckListTemplate checkList,
			TimeFrame timeDropdown) {
		super();
		this.description = description;
		this.active = active;
		this.checkList = checkList;
		this.timeDropdown = timeDropdown;
	}

}