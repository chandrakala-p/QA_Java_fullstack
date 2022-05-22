package com.hcl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.beans.Admin;
import com.hcl.beans.Question;
import com.hcl.beans.User;
import com.hcl.dao.IAdminRepository;
import com.hcl.dao.IQuestionRepository;
import com.hcl.dao.IUserRepository;
import com.hcl.exception.ProjectException;
@Transactional
@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	IQuestionRepository questionRepository;
	@Autowired
	IAdminRepository adminRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	EntityManager em;
	
	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionRepository.save(question);
	}

	@Override
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestionByID(Integer questionId) throws ProjectException {
		// TODO Auto-generated method stub
		 return questionRepository.findById(questionId).orElseThrow(() -> new ProjectException("question Id not found"));
	}

	@Override
	public Question updateQuestion(Question question) throws ProjectException {
		// TODO Auto-generated method stub
		 questionRepository.findById(question.getId()).orElseThrow(() -> new ProjectException("question Id not found"));
		return questionRepository.saveAndFlush(question);
	}

	@Override
	public String deleteQuestionById(Integer questionId) throws ProjectException {
		// TODO Auto-generated method stub
//		Question question= questionRepository.findById(questionId).orElseThrow(() -> new ProjectException("question Id not found"));
//		questionRepository.delete(question);
		Query query = em.createNativeQuery("DELETE FROM question WHERE id=:id").setParameter("id", questionId);
		query.executeUpdate();
		return "Question deleted successfully";
	
	}
	
	
	@Override
	public List<Question> getApprovedQuestionByAdminId(Integer adminId) throws ProjectException {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.findById(adminId).orElseThrow(() -> new ProjectException("admin Id not found"));
		 return admin.getApprovedQuestions();
	}
	
	
	@Override
	public List<Question> getallQuestionByUserId(Integer userId) throws ProjectException {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()-> new ProjectException("User Id not found"));
		List<Question> qns = user.getUserQuestion();
		return qns;
	}
	
	
	
	@Override
	public Question addQuestionByUserId(Question question, Integer userId)throws ProjectException {
	User user=userRepository.findById(userId).orElseThrow(()-> new ProjectException("User Id not found"));
	Question questionadded=questionRepository.save(question);
	questionadded.setUser(user);
	userRepository.save(user);
		return questionadded;
	}
	

	@Override
	public String deletequestionByAdminId(Integer adminId,Integer questionId) throws ProjectException {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.findById(adminId).orElseThrow(()-> new ProjectException("Admin Id not found"));
		Question question=questionRepository.findById(questionId).orElseThrow(()-> new ProjectException("Question Id not found"));
		List<Question> qnList=admin.getApprovedQuestions();	
		qnList.removeIf(data ->(data.getId()==question.getId()));
		admin.setApprovedQuestions(qnList);
		adminRepository.save(admin);
		return "Inappropriate Questions are deleted"; 	 	
	}
	
	@Override
	public List<Question> getAllApprovedQuestions() {
		System.out.println("adminlogin");
		Query qry=em.createQuery("from Question where admin_id IS NOT NULL");
		return (List<Question>) qry.getResultList();
	}

}
