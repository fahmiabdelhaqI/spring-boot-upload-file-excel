package com.importExport.Excel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="tutorial")
public class Tutorial {

	@Id
	private long id;
	
	private String title;
	private String description;
	private boolean published;
	
	
	@Override
	public String toString() {
		return "Tutorial [id=" + id +", title=" + title + ", description=" + description +", published=" + published +"]";
	}
	
}
