package com.hcl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcl.beans.Admin;
import com.hcl.beans.Answer;
import com.hcl.beans.Question;
import com.hcl.dao.IAdminRepository;
import com.hcl.dao.IAnswerRepository;
import com.hcl.dao.IQuestionRepository;
import com.hcl.exception.ProjectException;

@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	IAdminRepository adminRepository;
	
	@Autowired
	IAnswerRepository answerRepository;
	
	@Autowired
	IQuestionRepository questionRepository;

	@Autowired
	EntityManager em;
	
	@Override
	public Admin addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return adminRepository.findAll();
	}

	@Override
	public Admin getAdminById(Integer adminId) throws ProjectException {
		// TODO Auto-generated method stub
		return adminRepository.findById(adminId).orElseThrow(() -> new ProjectException("admin Id not found"));
	}

	@Override
	public Admin updateAdmin(Admin admin) throws ProjectException {
		// TODO Auto-generated method stub
		adminRepository.findById(admin.getId()).orElseThrow(() -> new ProjectException("admin Id not found"));
		return adminRepository.saveAndFlush(admin);
	}

	@Override
	public String deleteAdminById(Integer adminId) throws ProjectException {
		// TODO Auto-generated method stub
	Admin admin=adminRepository.findById(adminId).orElseThrow(() -> new ProjectException("admin Id not found"));
	adminRepository.delete(admin);
		return "admin deleted succesfully";
	}
	
	@Override
	public String approveQuestionByAdmin(Integer adminId, Integer questionId) throws ProjectException {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.findById(adminId).orElseThrow(() -> new ProjectException("admin Id not found"));
		Question question= questionRepository.findById(questionId).orElseThrow(() -> new ProjectException("question Id not found"));
	 List<Question> approveQuestions=admin.getApprovedQuestions();
	 approveQuestions.add(question);
	 admin.setApprovedQuestions(approveQuestions);
	 question.setAdmin(admin);
	 question.setStatus("Approved");
	adminRepository.save(admin);
	 questionRepository.save(question);
	 return "The question added to approved list";
	}
	
	
	@Override
	public String approveAnswerByAdmin(Integer adminId, Integer answerId) throws ProjectException {
		// TODO Auto-generated method stub
	Admin admin=adminRepository.findById(adminId).orElseThrow(() -> new ProjectException("admin Id not found"));
	Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ProjectException("answer Id not found"));		
	 List<Answer> approveAnswers=admin.getApprovedAnswers();
	 approveAnswers.add(answer);
	 admin.setApprovedAnswers(approveAnswers);
	 answer.setAdmin(admin);
	 answer.setStatus("Approved");
	adminRepository.save(admin); 
	answerRepository.save(answer);
	 return "The answer added to approved list";
	}
	
	@Override
	public Admin getAdminByEmailPass(String email,String password) {
		System.out.println("adminlogin");
		System.out.println(email +" "+password);
		Query qry=em.createQuery("from Admin where adminEmail=:email and password=:pass").setParameter("email",email).setParameter("pass", password);
		return (Admin) qry.getSingleResult();
	}
	

	//from Admin where adminId is not Null
	

}
