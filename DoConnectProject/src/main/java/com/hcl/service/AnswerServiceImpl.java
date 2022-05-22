package com.hcl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcl.beans.Admin;
import com.hcl.beans.Answer;
import com.hcl.beans.Question;
import com.hcl.beans.User;
import com.hcl.dao.IAdminRepository;
import com.hcl.dao.IAnswerRepository;
import com.hcl.dao.IQuestionRepository;
import com.hcl.dao.IUserRepository;
import com.hcl.exception.ProjectException;

@Transactional
@Service
public class AnswerServiceImpl implements IAnswerService {
	@Autowired
	IAnswerRepository answerRepository;
	
	@Autowired
	IAdminRepository adminRepository;
	
	@Autowired
	IQuestionRepository questionRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	EntityManager em;

	@Override
	public Answer addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		return answerRepository.save(answer);
	}

	@Override
	public List<Answer> getAllAnswer() {
		// TODO Auto-generated method stub
		return answerRepository.findAll();
	}

	@Override
	public Answer getAnswerByID(Integer answerId) throws ProjectException {
		// TODO Auto-generated method stub
		return answerRepository.findById(answerId).orElseThrow(() -> new ProjectException("answer Id not found"));
	}

	@Override
	public Answer updateAnswer(Answer answer) throws ProjectException {
		// TODO Auto-generated method stub
		answerRepository.findById(answer.getId()).orElseThrow(() -> new ProjectException("answer Id not found"));
		return answerRepository.saveAndFlush(answer);
	}

	@Override
	public String deleteAnswerById(Integer answerId) throws ProjectException {
		// TODO Auto-generated method stub
//		Answer answer = answerRepository.findById(answerId)
//				.orElseThrow(() -> new ProjectException("answer Id not found"));
//		answerRepository.delete(answer);
		
		Query query = em.createNativeQuery("DELETE FROM answer WHERE id=:id").setParameter("id", answerId);
		query.executeUpdate();
		return "answer deleted succesfully";
		
	}
	
	@Override
	public List<Answer> getApprovedAnswerByAdminId(Integer adminId) throws ProjectException {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.findById(adminId).orElseThrow(() -> new ProjectException("admin Id not found"));
		 return admin.getApprovedAnswers();
	}
	
	
	@Override
	public String removeAnswersfromQuestion(Integer questionId, Integer answerId) throws ProjectException {
		// TODO Auto-generated method stub
		Question  question= questionRepository.findById(questionId).orElseThrow(() -> new ProjectException("question Id not found"));
		Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ProjectException("answer Id not found"));
		 List<Answer> questionAnswers=question.getAnswers();
		 questionAnswers.removeIf( data -> (data.getId()==answer.getId()));
		 question.setAnswers(questionAnswers);
		 questionRepository.save(question);
		 return "The order deleted from user";
	}
	
	
	@Override
	public List<Answer> getallAnsByuserId(Integer userId) throws ProjectException {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()-> new ProjectException("User Id not found"));
		List<Answer> ans = user.getUserAnswers();
		return ans;
	}

	@Override
	public String deleteAnswerByAdminId(Integer adminId, Integer answerId) throws ProjectException {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.findById(adminId).orElseThrow(()-> new ProjectException("Admin Id not found"));
		Answer answer=answerRepository.findById(answerId).orElseThrow(()-> new ProjectException("Question Id not found"));
		List<Answer> ansList=admin.getApprovedAnswers();	
		ansList.removeIf(data ->(data.getId()==answer.getId()));
		admin.setApprovedAnswers(ansList);
		adminRepository.save(admin);
		return "Inappropriate answers are deleted"; 	 
	}
	
	@Override
	public List<Answer> getallAnsByQuestionId(Integer questionId) throws ProjectException {
		// TODO Auto-generated method stub
		Question question=questionRepository.findById(questionId).orElseThrow(()-> new ProjectException("question Id not found"));
		List<Answer> ans = question.getAnswers();
		return ans;
	}

	
	@Override
	public String addAnswersToQuestion(Integer questionId, Integer answerId) throws ProjectException {
		// TODO Auto-generated method stub
		Question question=questionRepository.findById(questionId).orElseThrow(()-> new ProjectException("question Id not found"));
		Answer answer=answerRepository.findById(answerId).orElseThrow(()-> new ProjectException("Question Id not found"));
	 List<Answer> quesAnswers=question.getAnswers();
	 quesAnswers.add(answer);
	 question.setAnswers(quesAnswers);
	 questionRepository.save(question);
	 return "The Answer added to Question";
	}
	
	
	@Override
	public Answer addAnswerByUserId(Answer answer, Integer userId)throws ProjectException {
	User user=userRepository.findById(userId).orElseThrow(()-> new ProjectException("User Id not found"));
	Answer answerAdded=answerRepository.save(answer);
	answerAdded.setUser(user);
	userRepository.save(user);
		return answerAdded;
	}
	
	
	@Override
	public Answer addAnswerByUserIdquestionId(Answer answer, Integer userId, Integer questionId)throws ProjectException {
	User user=userRepository.findById(userId).orElseThrow(()-> new ProjectException("User Id not found"));
	Answer answerAdded=answerRepository.save(answer);
	answerAdded.setUser(user);
	Question question = questionRepository.getById(questionId);
	answerAdded.setQuestion(question);
	userRepository.save(user);
	return answerAdded;
	}
	
	
	

	@Override
	public Answer addAnswerByQuestionId(Answer answer, Integer questiobId)throws ProjectException {
	Question question=questionRepository.findById(questiobId).orElseThrow(()-> new ProjectException("question Id not found"));
	Answer answerAdded=answerRepository.save(answer);
	answerAdded.setQuestion(question);
	questionRepository.save(question);
		return answerAdded;
	}
	
	
	@Override
	public List<Answer> getAllApprovedAnswers() {
		Query qry=em.createQuery("from Answer where admin_id IS NOT NULL");
		return (List<Answer>) qry.getResultList();
	}

}
