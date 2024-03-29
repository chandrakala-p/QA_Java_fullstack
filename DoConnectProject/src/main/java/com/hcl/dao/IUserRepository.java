package com.hcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.beans.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

}
