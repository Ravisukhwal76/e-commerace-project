package com.ravi.major.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class TaskAssign {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee employee;

	private String description;
	private String date;
	private boolean status;
	private boolean isComplted;
//	private  long addedBy;

	

}
