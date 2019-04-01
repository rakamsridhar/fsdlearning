package com.fsd.springboothibernate;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsd.springboothibernate.dao.BookDao;
import com.fsd.springboothibernate.entity.Book;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BookDaoTest {
	
	@MockBean
	private BookDao bookDao;
	
	private List<Book> books;
	
    @Before
    public void setup() throws Exception {
        this.books  = getTestData();
    }
	
	@Test
	public void testFindByTitle() {
		
		Mockito.when(bookDao.findByTitle(any(String.class))).thenReturn(books);
		assertEquals(2, books.size());
		
	}
	
	@Test
	public void testFindByPriceAndVolume() {
		//Mockito.when(bookDao.findByPriceAndVolume(any(BigDecimal.class, Integer.class)));
		
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
