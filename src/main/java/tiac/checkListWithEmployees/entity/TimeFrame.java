package tiac.checkListWithEmployees.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class TimeFrame {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@Enumerated(EnumType.STRING)
	private TimeName name;

	@OneToMany(mappedBy = "timeDropdown", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<CheckListItemTemplate> items;

	public TimeFrame(TimeName name, Set<CheckListItemTemplate> items) {
		super();
		this.name = name;
		this.items = items;
	}

	public TimeFrame() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}


	public TimeName getName() {
		return name;
	}

	public void setName(TimeName name) {
		this.name = name;
	}

	public Set<CheckListItemTemplate> getItems() {
		return items;
	}

	public void setItems(Set<CheckListItemTemplate> items) {
		this.items = items;
	}
}