package com.hcl.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="Admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String adminName;
	private String password;
	private String adminEmail;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "admin" ,cascade= CascadeType.ALL)
	@JsonIgnore
	private List<User> users;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "admin" ,cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Question> approvedQuestions;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "admin" ,cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Answer> approvedAnswers;

}
