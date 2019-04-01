package com.fsd.springboothibernate.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.springboothibernate.dao.BookDao;
import com.fsd.springboothibernate.entity.Book;
import com.fsd.springboothibernate.response.BookResponse;



@RestController
@RequestMapping("/bookstore/books")
@CrossOrigin
public class BookController {
	
	@Autowired
	private BookDao bookDao;
	
	@GetMapping(value="/all", produces= {"application/json", "application/xml"})	
	public List<Book> getAllBooks() {
		List<Book> books = bookDao.findAll();
		//List<Book> books = bookDao.findByTitle("Java programming");
		//List<Book> books = bookDao.findByPriceAndVolume(new BigDecimal(100), 1);
		return books;
		 
	}
	
	@GetMapping(value="/all/sortByTitle", produces= {"application/json", "application/xml"})	
	public List<Book> getSortedBooksByTitle() {
		Sort sort = new Sort(Direction.ASC, "title");
		List<Book> books = bookDao.findAll(sort);
		return books;
		 
	}
	
	@GetMapping(value="/all/sortByDate", produces= {"application/xml","application/json"})	
	public List<Book> getSortedBooksByPublishDate() {
		Sort sort = new Sort(Direction.ASC, "publishedDate");
		List<Book> books = bookDao.findAll(sort);
		return books;		 
	}
	
	@GetMapping(value="/{book_id}", produces= {"application/xml", "application/json"})
	public Book getBookById(@PathVariable int book_id) {
		Optional<Book> optional = bookDao.findById(book_id);		
		return optional.get();
	}
	
	@PostMapping(value="/add", produces= {"application/json", "application/xml"}, consumes= {"application/json"})
	public ResponseEntity<BookResponse> insertBook(@RequestBody Book book) {
		bookDao.save(book);	
		BookResponse response = new BookResponse();
		response.setStatusCode(HttpStatus.OK);
		response.setMessage("success");
		return ResponseEntity.ok(response); 
	}
	
	@DeleteMapping(value="/delete/{book_id}", produces= {"application/xml", "application/json"})
	public String deleteBook(@PathVariable int book_id) {
		bookDao.deleteById(book_id);
		return "book deleted successfully"; 
	}
	
	@PutMapping(value="/update", produces= {"application/xml", "application/json"}, consumes= {"application/json"})
	public Book updateBook(@RequestBody Book book) {
		return bookDao.save(book);		
	}

}
