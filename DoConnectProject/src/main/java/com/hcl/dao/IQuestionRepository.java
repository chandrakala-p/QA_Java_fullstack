package com.hcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.beans.Question;

public interface IQuestionRepository  extends JpaRepository<Question, Integer>{

}
