package com.importExport.Excel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nama;
	private String alamat;
	private String umur;
	
	@Override
	public String toString() {
		return "Customer [ id=" + id +", nama=" + alamat +", umur=" + umur + "]";
	}
}
