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

import com.hcl.beans.Answer;
import com.hcl.beans.Question;
import com.hcl.beans.ResponseMessage;

import com.hcl.exception.ProjectException;
import com.hcl.service.IAnswerService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AnswerController {

	@Autowired
	IAnswerService answerService;

	// Register Answer
	@RequestMapping(method = RequestMethod.POST, value = "/addAnswer")
	public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer) {

		return new ResponseEntity<Answer>(answerService.addAnswer(answer), HttpStatus.CREATED);
	}

	// Get All Answers
	@RequestMapping(method = RequestMethod.GET, value = "/getAllAnswers")
	public ResponseEntity<List<Answer>> getAllAnswers() {

		return new ResponseEntity<List<Answer>>(answerService.getAllAnswer(), HttpStatus.OK);
	}

	// Get Answer By ID
	@RequestMapping(method = RequestMethod.GET, value = "/getAnswerById/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") Integer id) throws ProjectException {

		return new ResponseEntity<Answer>(answerService.getAnswerByID(id), HttpStatus.OK);
	}

	// Update Answer
	@RequestMapping(method = RequestMethod.PUT, value = "/updateAnswer")
	public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer) throws ProjectException {

		return new ResponseEntity<Answer>(answerService.updateAnswer(answer), HttpStatus.OK);
	}

	// Delete Answer
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteAnswerById/{id}")
	public ResponseEntity<ResponseMessage> deleteAnswerById(@PathVariable("id") int id) throws ProjectException {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(answerService.deleteAnswerById(id));
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getApprovedAnswerByAdminId/{adminId}")
	public ResponseEntity<List<Answer>> getApprovedAnswerByAdminId(@PathVariable int adminId) throws ProjectException {

		return new ResponseEntity<List<Answer>>(answerService.getApprovedAnswerByAdminId(adminId), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeAnswersfromQuestion/{userId}/{orderId}")
	public ResponseEntity<String> removeAnswersfromQuestion(@PathVariable int questionId, @PathVariable int answerId)
			throws ProjectException {
		return new ResponseEntity<>(answerService.removeAnswersfromQuestion(questionId, answerId), HttpStatus.OK);
	}
	
	
	// Get Answer By AnswerId
		@RequestMapping(method = RequestMethod.GET, value = "/getallAnsByuserId/{userId}")
		public ResponseEntity<List<Answer>> getallAnsByuserId(@PathVariable("userId") Integer userId) throws ProjectException {

			return new ResponseEntity<List<Answer>>(answerService.getallAnsByuserId(userId), HttpStatus.OK);
		}

	// Delete Answer By AnswerId
		@RequestMapping(method = RequestMethod.DELETE, value = "/deleteAnswerByAdminId/{adminId}/{answerId}")
		public ResponseEntity<ResponseMessage> deleteAnswerByAdminId(@PathVariable("adminId") int adminId,@PathVariable("answerId") int answerId) throws ProjectException {
			ResponseMessage rm = new ResponseMessage();
			rm.setMessage(answerService.deleteAnswerByAdminId(adminId,answerId));
			return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		}
		
		
		@RequestMapping(method = RequestMethod.GET, value = "/getallAnsByQuestionId/{questionId}")
		public ResponseEntity<List<Answer>> getallAnsByQuestionId(@PathVariable("questionId") Integer questionId) throws ProjectException {

			return new ResponseEntity<List<Answer>>(answerService.getallAnsByQuestionId(questionId), HttpStatus.OK);
		}
		
		
		@RequestMapping(method = RequestMethod.POST, value = "/addAnswersToQuestion/{questionId}/{answerId}")
		public ResponseEntity<String> addAnswersToQuestion(@PathVariable int questionId, @PathVariable int answerId)
				throws ProjectException {
			return new ResponseEntity<String>(answerService.addAnswersToQuestion(questionId, answerId), HttpStatus.OK);
		}
		
		
		@RequestMapping(method = RequestMethod.POST, value = "/addAnswerByUserId/{userId}")
		public ResponseEntity<Answer> addAnswerByUserId(@PathVariable("userId") Integer userId, @RequestBody Answer answer) throws ProjectException {

			return new ResponseEntity<Answer>(answerService.addAnswerByUserId(answer, userId), HttpStatus.OK);
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/getAllApprovedAnswers")
		public ResponseEntity<List<Answer>> getAllApprovedAnswers() {

			return new ResponseEntity<List<Answer>>(answerService.getAllApprovedAnswers(), HttpStatus.OK);
		}
		
		
		
		@RequestMapping(method = RequestMethod.POST, value = "/addAnswerByQuestionId/{questionId}")
		public ResponseEntity<Answer> addAnswerByQuestionId(@PathVariable("questionId") Integer questionId, @RequestBody Answer answer) throws ProjectException {

			return new ResponseEntity<Answer>(answerService.addAnswerByQuestionId(answer, questionId), HttpStatus.OK);
		}
		
		
		@RequestMapping(method = RequestMethod.POST, value = "/addAnswerByUserId/{userId}/{questionId}")
		public ResponseEntity<Answer> addAnswerByUserId(@PathVariable("userId") Integer userId,@PathVariable("questionId") Integer questionId, @RequestBody Answer answer) throws ProjectException {

		 return new ResponseEntity<Answer>(answerService.addAnswerByUserIdquestionId(answer, userId, questionId), HttpStatus.OK);
		}
		

	@ExceptionHandler(ProjectException.class)
	public ResponseEntity<ResponseMessage> handleEmployeeIdException(HttpServletRequest request, Exception ex) {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(ex.getMessage());
		rm.setErrorCode(404);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND);
	}

}
