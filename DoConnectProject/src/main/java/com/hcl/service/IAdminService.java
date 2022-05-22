package com.hcl.service;

import java.util.List;

import com.hcl.beans.Admin;

import com.hcl.exception.ProjectException;

public interface IAdminService {
	
	public Admin addAdmin(Admin admin);

	public List<Admin> getAllAdmins();

	public Admin getAdminById(Integer adminId) throws ProjectException;

	public Admin updateAdmin(Admin admin) throws ProjectException;

	public String deleteAdminById(Integer adminId) throws ProjectException;

	public String approveQuestionByAdmin(Integer adminId, Integer questionId) throws ProjectException;

	public String approveAnswerByAdmin(Integer adminId, Integer answerId) throws ProjectException;

	public Admin getAdminByEmailPass(String email, String password);


}
