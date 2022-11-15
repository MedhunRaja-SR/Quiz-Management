package com.mfpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * this contains the data for the project manager
 * we are using the table project_manager the table is a H2 table and runs in runtime
 * id
 * name
 * username
 * password
 * projectname
 * we are using lombok for the boilerplate code
 */

@Data
@Entity
@Table(name="project_manager")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManager{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
}