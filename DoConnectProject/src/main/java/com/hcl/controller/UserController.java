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

import com.hcl.beans.ResponseMessage;
import com.hcl.beans.User;
import com.hcl.exception.ProjectException;
import com.hcl.service.IUserService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	IUserService userService;

	// Register User
	@RequestMapping(method = RequestMethod.POST, value = "/registerUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {

		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
	}

	// Get All Users
	@RequestMapping(method = RequestMethod.GET, value = "/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {

		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	// Get User By ID
	@RequestMapping(method = RequestMethod.GET, value = "/getUserById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) throws ProjectException {

		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	// Update User
	@RequestMapping(method = RequestMethod.PUT, value = "/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws ProjectException {

		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}

	// Delete User
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteUserById/{id}")
	public ResponseEntity<ResponseMessage> deleteUserById(@PathVariable("id") int id) throws ProjectException {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(userService.deleteUserById(id));
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
	}
	
	
	//Get User by Email Pass
		@RequestMapping(method = RequestMethod.PUT, value = "/getUserByEmailPass")
		public ResponseEntity<User> getUserByEmailPass(@RequestBody User user) {
			User newuser = userService.getUserByEmailPass(user.getUserEmail(), user.getPassword());
			System.out.println(newuser);
			return new ResponseEntity<User>(newuser, HttpStatus.OK);
		}

	@ExceptionHandler(ProjectException.class)
	public ResponseEntity<ResponseMessage> handleEmployeeIdException(HttpServletRequest request, Exception ex) {
		ResponseMessage rm = new ResponseMessage();
		rm.setMessage(ex.getMessage());
		rm.setErrorCode(404);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND);
	}

}
