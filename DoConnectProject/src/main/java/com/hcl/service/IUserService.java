package com.hcl.service;

import java.util.List;

import com.hcl.beans.User;
import com.hcl.exception.ProjectException;

public interface IUserService {

	public User addUser(User user);

	public List<User> getAllUsers();

	public User getUserById(Integer userId) throws ProjectException;

	public User updateUser(User user) throws ProjectException;

	public String deleteUserById(Integer userId) throws ProjectException;

	public User getUserByEmailPass(String email, String password);

}
