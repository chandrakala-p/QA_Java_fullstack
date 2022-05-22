package com.hcl.service;

import java.util.List;

import com.hcl.beans.Question;

import com.hcl.exception.ProjectException;

public interface IQuestionService {

	public Question addQuestion(Question question);

	public List<Question> getAllQuestions();

	public Question getQuestionByID(Integer questionId) throws ProjectException;

	public Question updateQuestion(Question question) throws ProjectException;

	public String deleteQuestionById(Integer questionId) throws ProjectException;

	public List<Question> getApprovedQuestionByAdminId(Integer adminId) throws ProjectException;

	public List<Question> getallQuestionByUserId(Integer userId) throws ProjectException;

	public String deletequestionByAdminId(Integer adminId, Integer questionId) throws ProjectException;

	public Question addQuestionByUserId(Question question, Integer userId) throws ProjectException;

	public List<Question> getAllApprovedQuestions();

}
