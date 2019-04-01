package com.fsd.springboothibernate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsd.springboothibernate.entity.Subject;

public interface SubjectDao  extends JpaRepository<Subject, Integer>{
	
}
