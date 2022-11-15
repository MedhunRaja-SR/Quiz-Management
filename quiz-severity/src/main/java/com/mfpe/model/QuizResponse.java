package com.mfpe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity
@Table(name="quiz_response")
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponse {
	
	@Id
	@GeneratedValue
	private int Id;
	private int score;
	private String userName;
	private String courseType;
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
	
	private String status;
	private String suggestion;
	
	public void setCreationDateTime(Date creationDateTime2) {
		this.creationDateTime = creationDateTime2;
	}
	
}
