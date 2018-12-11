package com.springboot.business;

import java.util.List;

import com.springboot.model.Book;
import com.springboot.model.Subject;

public interface LibraryBusiness {
	
	String addSubject(Subject subject) throws Exception;
	String addBook(Book book) throws Exception;
	String deleteSubject(String subjectId);
	String deleteBook(String bookId);
	List<Subject> getSubject(String search, String searchType);
	List<Book> getBook(String search, String searchType);
}
