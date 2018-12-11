package com.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.business.LibraryBusiness;
import com.springboot.model.Book;
import com.springboot.model.Subject;

@Service
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	private LibraryBusiness business;

	@Transactional
	public String addSubject(Subject subject) throws Exception {
		return business.addSubject(subject);
	}

	@Transactional
	public String addBook(Book book) throws Exception {		
		return business.addBook(book);
	}

	@Transactional
	public String deleteSubject(String subjectId) {
		return business.deleteSubject(subjectId);
	}

	@Transactional
	public String deleteBook(String bookId) {
		return business.deleteBook(bookId);
	}

	@Transactional
	public List<Subject> getSubject(String search, String searchType) {
		return business.getSubject(search, searchType);
	}

	@Transactional
	public List<Book> getBook(String search, String searchType) {
		return business.getBook(search, searchType);
	}

	

}
