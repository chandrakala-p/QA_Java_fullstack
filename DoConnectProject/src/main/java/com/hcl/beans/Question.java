package com.hcl.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="Question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String question;
	private String status;

	@JsonIgnore
	@ManyToOne( cascade=CascadeType.ALL)
	@JoinColumn(name = "user_id")
    private User user;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
//	@JsonIgnore
//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
//	@JoinTable(name = "question_answers", joinColumns = { @JoinColumn(name = "question_id")}, inverseJoinColumns = {
//			@JoinColumn(name = "answer_id") })
//	private List<Answer> questionAnswers;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "question" ,cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Answer> Answers;
}
