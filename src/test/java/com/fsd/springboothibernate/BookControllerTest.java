package com.fsd.springboothibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;   // ...or...
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.PrintingResultHandler.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.JsonPathResultMatchers.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchers.*;
import static org.springframework.test.web.servlet.result.HandlerResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fsd.springboothibernate.dao.BookDao;
import com.fsd.springboothibernate.entity.Book;





@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

	@Autowired
 	private MockMvc mockMvc;
	
	@MockBean
	private BookDao bookDao;
	
	private final String BOOK_REQUEST = "{\"book_id\" : \"1001\",\"title\" : \"computers\"}";
	
	private List<Book> books;
		
    @Before
    public void setup() throws Exception {
        this.books  = getTestData();
    }
	

	
	@Test
	public void testGetBooks() throws Exception {
		Mockito.when(bookDao.findAll()).thenReturn(books);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/books/all")
				.contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].book_id").value(101))
         .andExpect(jsonPath("$[1].book_id").value(102))
         .andDo(print());			
	}
	
	@Test
	public void testGetSortedBooksByTitle() throws Exception {
		Sort sort = new Sort(Direction.ASC, "title");
		Mockito.when(bookDao.findAll(sort)).thenReturn(books);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/books/all/sortByTitle")
				.contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].book_id").value(101))
         .andExpect(jsonPath("$[1].book_id").value(102))
         .andDo(print());			
	}
	
    @Test
	public void testInsertBook() throws Exception {
    	Mockito.when(bookDao.save(any(Book.class))).thenReturn(new Book(101, "computers", new BigDecimal(100), 3, new java.sql.Date(2016, 10, 02)));
    	
		mockMvc.perform(MockMvcRequestBuilders.post("/bookstore/books/add").content(BOOK_REQUEST)
				.contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.statusCode").value("OK"))
         .andExpect(jsonPath("$.message").value("success"))
         .andDo(print()); 		
   		
	}
    
    @Test
    public void testGetBookById() {
    	Mockito.when(bookDao.save(any(Book.class))).thenReturn(new Book(101, "computers", new BigDecimal(100), 3, new java.sql.Date(2016, 10, 02)));
    	//Mockito.when(bookDao.findById(1)).thenReturn(new Book(101, "computers", new BigDecimal(100), 3, new java.sql.Date(2016, 10, 02)));
    }
	


	private List<Book> getTestData(){
    	List<Book> books = new ArrayList<Book>();
    	Book b1=new Book();
		b1.setBook_id(101);
		b1.setTitle("Documentary");
		b1.setPrice(new BigDecimal(200));
		b1.setVolume(2);
		b1.setPublishedDate(new java.sql.Date(2011, 10, 01));
		
		
    	Book b2=new Book();
		b2.setBook_id(102);
		b2.setTitle("Computers");
		b2.setPrice(new BigDecimal(300));
		b2.setVolume(3);
		b2.setPublishedDate(new java.sql.Date(2012, 10, 02));
		
		books.add(b1);
		books.add(b2);
		
		return books;
		
	}

}
