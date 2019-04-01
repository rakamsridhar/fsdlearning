package com.fsd.springboothibernate.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fsd.springboothibernate.entity.Book;

@Component
public class BookResponse {
	Book book;
	HttpStatus statusCode;
	String error;
	String error_message;
	String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus httpStatus) {
		this.statusCode = httpStatus;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	@Override
	public String toString() {
		return "BookResponse [bookList=" + book + ", statusCode=" + statusCode + ", error=" + error
				+ ", error_message=" + error_message + "]";
	}
	
	

}
