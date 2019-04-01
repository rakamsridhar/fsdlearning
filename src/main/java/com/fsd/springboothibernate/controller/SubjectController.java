package com.fsd.springboothibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.springboothibernate.dao.SubjectDao;
import com.fsd.springboothibernate.entity.Subject;

@RestController
@RequestMapping("bookstore/subjects")
public class SubjectController {
	
	@Autowired
	private SubjectDao subjectDao;
	
	@GetMapping("/all")	
	public List<Subject> getSubjectDetails() {
		return subjectDao.findAll();
	}
	
	@GetMapping(value="/all/sortByTitle")
	public List<Subject> sortSubjectByTitle(){
		Sort sort = new Sort(Direction.ASC, "subtitle");
		return subjectDao.findAll(sort);
	}

}
