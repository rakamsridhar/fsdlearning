package com.fsd.springboothibernate.dao;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsd.springboothibernate.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer>{
	public List<Book> findByTitle(String bookName);
	public List<Book> findByPriceAndVolume(BigDecimal price, int volume);
	
}
