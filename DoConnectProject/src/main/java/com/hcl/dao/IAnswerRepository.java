package com.hcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.beans.Answer;

public interface IAnswerRepository  extends JpaRepository<Answer, Integer>{

}
