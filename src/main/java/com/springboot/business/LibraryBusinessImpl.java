package com.springboot.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.dao.BookRepository;
import com.springboot.dao.SubjectRepository;
import com.springboot.model.Book;
import com.springboot.model.Subject;

@Repository
public class LibraryBusinessImpl implements LibraryBusiness{

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	String message = "";

	//method to add subject details
	@SuppressWarnings("unchecked")
	public String addSubject(Subject subject) throws Exception {
		List<Book> books = bookRepository.findAll();
		if(books.isEmpty()){
			throw new Exception("No Books Found. Please add book(s) first");
		}else {
			Set<Book> referrences = new HashSet<Book>();
			for(String bookId : subject.getBookIds().split(",")){
				//find the book from the books list
				Book selBook = books.stream().filter(book -> Long.parseLong(bookId) == book.getBookId()).findAny().orElse(null);
				if(selBook != null){
					referrences.add(selBook);
				}
			}
			if(referrences.isEmpty()){
				throw new Exception("No valid Book ID found. Please enter valid ones...");
			}
			subject.setReferences(referrences);
			subjectRepository.save(subject);
			message = "Subject - "+subject.getSubtitle()+" is added.";
		}
		return message;
	}
	
	//method to add book details
	public String addBook(Book book) throws Exception {			
		Book bbk = bookRepository.save(book);
		message = "Book - "+book.getTitle()+" is added as ID : " + bbk.getBookId();
		return message;
	}
	
	//method to delete the subject
	public String deleteSubject(String subjectId) {		
		Subject subject = subjectRepository.findById(Long.parseLong(subjectId)).orElse(null);
		if(subject != null){
			message = "Subject - "+subject.getSubtitle()+" is deleted.";
			subjectRepository.delete(subject);
		} 
		return message;
	}
	
	//method to delete the book
	public String deleteBook(String bookId) {
		Book book = bookRepository.findById(Long.parseLong(bookId)).orElse(null);
		if(book != null){
			message = "Book - "+book.getTitle()+" is deleted.";
			bookRepository.delete(book);
		} 
		return message;
	}
	
	//method to search the subjects
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Subject> getSubject(String search, String searchType) {
		List<Subject> subjects = new ArrayList<Subject>();
		if("title".equals(searchType)){
			subjects = subjectRepository.searchBySubtitle(search);
		} else {
			subjects = subjectRepository.searchByDurationInHours(Integer.parseInt(search));
		}
		return subjects;
	}
	
	//method to search the books
	@SuppressWarnings("deprecation")
	public List<Book> getBook(String search, String searchType) {
		List<Book> books = new ArrayList<Book>();
		if("title".equals(searchType)) {
			books = bookRepository.searchByTitle(search);
		} else if("price".equals(searchType))	{
			books = bookRepository.searchByPrice(Double.parseDouble(search));
		} else {
			books = bookRepository.searchByVolume(Integer.parseInt(search));
		} 
		return books;
	}
}
