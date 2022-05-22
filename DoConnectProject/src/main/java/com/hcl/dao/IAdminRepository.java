package com.hcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.beans.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
