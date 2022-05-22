package com.hcl.service;

import java.util.List;

import com.hcl.beans.Answer;

import com.hcl.exception.ProjectException;

public interface IAnswerService {
	
	
	public Answer addAnswer(Answer answer);

	public List<Answer> getAllAnswer();

	public Answer getAnswerByID(Integer answerId) throws ProjectException;

	public Answer updateAnswer(Answer answer) throws ProjectException;

	public String deleteAnswerById(Integer answerId) throws ProjectException;

	public List<Answer> getApprovedAnswerByAdminId(Integer adminId) throws ProjectException;

	public String removeAnswersfromQuestion(Integer questionId, Integer answerId) throws ProjectException;
	
	public List<Answer> getallAnsByuserId(Integer userId) throws ProjectException;
	
	public String deleteAnswerByAdminId(Integer adminId,Integer answerId) throws ProjectException;

	public List<Answer> getallAnsByQuestionId(Integer questionId) throws ProjectException;

	public String addAnswersToQuestion(Integer questionId, Integer answerId) throws ProjectException;

	public Answer addAnswerByUserId(Answer answer, Integer userId) throws ProjectException;

	public List<Answer> getAllApprovedAnswers();

	public Answer addAnswerByQuestionId(Answer answer, Integer questiobId) throws ProjectException;

	public Answer addAnswerByUserIdquestionId(Answer answer, Integer userId, Integer questionId) throws ProjectException;
	

}
