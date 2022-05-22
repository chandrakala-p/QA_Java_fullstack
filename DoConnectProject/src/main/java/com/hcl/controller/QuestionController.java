package com.hcl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.hcl.beans.Question;
import com.hcl.beans.ResponseMessage;

import com.hcl.exception.ProjectException;
import com.hcl.service.IQuestionService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class QuestionController {

	@Autowired
	IQuestionService questionService;

	// add Question
	@RequestMapping(method = RequestMethod.POST, value = "/addQuestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {

		return new ResponseEntity<Question>(questionService.addQuestion(question), HttpStatus.CREATED);
	}

	// Get All Question
	@RequestMapping(method = RequestMethod.GET, value = "/getAllQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {

		return new ResponseEntity<List<Question>>(questionService.getAllQuestions(), HttpStatus.OK);
	}

	// Get Question By ID
	@RequestMapping(method = RequestMethod.GET, value = "/getQuestionById/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable("id") Integer id) throws ProjectException {

		return new ResponseEntity<Question>(questionService.getQuestionByID(id), HttpStatus.OK);
	}

	// Update Question
	@RequestMapping(method = RequestMethod.PUT, value = "/updateQuestion")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) throws ProjectException {

		return new ResponseEntity<Question>(questionService.updateQuestion(question), HttpStatus.OK);
	}

	// Delete Question
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteQuestionById/{id}")
	public ResponseEntity<ResponseMessage> deleteQuestionById(@PathVariable("id") int id) throws ProjectException {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(questionService.deleteQuestionById(id));
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getApprovedQuestionByAdminId/{adminId}")
	public ResponseEntity<List<Question>> getApprovedQuestionByAdminId(@PathVariable int adminId) throws ProjectException {

		return new ResponseEntity<List<Question>>(questionService.getApprovedQuestionByAdminId(adminId), HttpStatus.OK);
	}
	
	
	// Get Question By UserID
		@RequestMapping(method = RequestMethod.GET, value = "/getallQuestionByUserId/{userId}")
		public ResponseEntity<List<Question>> getallQuestionByUserId(@PathVariable("userId") Integer userId) throws ProjectException {

			return new ResponseEntity<List<Question>>(questionService.getallQuestionByUserId(userId), HttpStatus.OK);
		}
		
		// Delete Question By AdminID
		@RequestMapping(method = RequestMethod.DELETE, value = "/deletequestionByAdminId/{adminId}/{questionId}")
		public ResponseEntity<ResponseMessage> deletequestionByAdminId(@PathVariable("adminId") int adminId,@PathVariable("questionId") int questionId) throws ProjectException {
			ResponseMessage rm = new ResponseMessage();
			rm.setMessage(questionService.deletequestionByAdminId(adminId,questionId));
			return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		}
		
		
		
		@RequestMapping(method = RequestMethod.POST, value = "/addQuestionByUserId/{userId}")
		public ResponseEntity<Question> addQuestionByUserId(@PathVariable("userId") Integer userId, @RequestBody Question question) throws ProjectException {

			return new ResponseEntity<Question>(questionService.addQuestionByUserId(question, userId), HttpStatus.OK);
		}
	
		
		@RequestMapping(method = RequestMethod.GET, value = "/getAllApprovedQuestions")
		public ResponseEntity<List<Question>> getAllApprovedQuestions() {

			return new ResponseEntity<List<Question>>(questionService.getAllApprovedQuestions(), HttpStatus.OK);
		}

	@ExceptionHandler(ProjectException.class)
	public ResponseEntity<ResponseMessage> handleEmployeeIdException(HttpServletRequest request, Exception ex) {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(ex.getMessage());
		rm.setErrorCode(404);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND);
	}

}
