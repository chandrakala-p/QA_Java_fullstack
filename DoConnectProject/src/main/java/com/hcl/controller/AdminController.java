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

import com.hcl.beans.Admin;
import com.hcl.beans.ResponseMessage;

import com.hcl.exception.ProjectException;
import com.hcl.service.IAdminService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AdminController {

	@Autowired
	IAdminService adminService;

	// Register admin
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {

		return new ResponseEntity<Admin>(adminService.addAdmin(admin), HttpStatus.CREATED);
	}

	// Get All admins
	@RequestMapping(method = RequestMethod.GET, value = "/getAllAdmins")
	public ResponseEntity<List<Admin>> getAllAdmins() {

		return new ResponseEntity<List<Admin>>(adminService.getAllAdmins(), HttpStatus.OK);
	}

	// Get admin By ID
	@RequestMapping(method = RequestMethod.GET, value = "/getAdminById/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") Integer id) throws ProjectException {

		return new ResponseEntity<Admin>(adminService.getAdminById(id), HttpStatus.OK);
	}

	// Update admin
	@RequestMapping(method = RequestMethod.PUT, value = "/updateAdmin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) throws ProjectException {

		return new ResponseEntity<Admin>(adminService.updateAdmin(admin), HttpStatus.OK);
	}

	// Delete admin
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteAdminById/{id}")
	public ResponseEntity<ResponseMessage> deleteAdminById(@PathVariable("id") int id) throws ProjectException {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(adminService.deleteAdminById(id));
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/approveQuestionByAdmin/{adminId}/{questionId}")
	public ResponseEntity<String> approveQuestionByAdmin(@PathVariable int adminId, @PathVariable int questionId)
			throws ProjectException {
		return new ResponseEntity<String>(adminService.approveQuestionByAdmin(adminId, questionId), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/approveAnswerByAdmin/{adminId}/{answerId}")
	public ResponseEntity<String> approveAnswerByAdmin(@PathVariable int adminId, @PathVariable int answerId)
			throws ProjectException {
		return new ResponseEntity<String>(adminService.approveAnswerByAdmin(adminId, answerId), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/getAdminByEmailPass")
	public ResponseEntity<Admin> getAdminByEmailPass(@RequestBody Admin admin) {
		System.out.println("logged");
		System.out.println(admin.getAdminEmail());
		System.out.println(admin.getPassword());
		Admin newadmin = adminService.getAdminByEmailPass(admin.getAdminEmail(), admin.getPassword());
		System.out.println(newadmin);
		return new ResponseEntity<Admin>(newadmin, HttpStatus.OK);
	}

	@ExceptionHandler(ProjectException.class)
	public ResponseEntity<ResponseMessage> handleEmployeeIdException(HttpServletRequest request, Exception ex) {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(ex.getMessage());
		rm.setErrorCode(404);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND);
	}

}
