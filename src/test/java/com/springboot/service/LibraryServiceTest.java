package com.springboot.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.business.LibraryBusiness;
import com.springboot.model.Book;
import com.springboot.model.Subject;

public class LibraryServiceTest {

	// injecting mock object into LibraryService service
	@InjectMocks
	private LibraryServiceImpl service;

	// mock object
	@Mock
	private LibraryBusiness businessMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddSubject() throws Exception {
		when(businessMock.addSubject(org.mockito.Mockito.any(Subject.class)))
		.thenReturn("Subject - India is added.");
		assertEquals(service.addSubject(new Subject()), "Subject - India is added.");
	}
	
	@Test
	public void testAddBook() throws Exception {
		when(businessMock.addBook(org.mockito.Mockito.any(Book.class)))
		.thenReturn("Book - Kerala is added as ID : 101");
		assertEquals(service.addBook(new Book()), "Book - Kerala is added as ID : 101");
	}
	
	@Test
	public void testDeleteSubject() throws Exception {
		when(businessMock.deleteSubject(org.mockito.Mockito.anyString()))
		.thenReturn("Subject - India is deleted.");
		assertEquals(service.deleteSubject("1001"), "Subject - India is deleted.");
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		when(businessMock.deleteBook(org.mockito.Mockito.anyString()))
		.thenReturn("Book - Karnataka is deleted.");
		assertEquals(service.deleteBook("102"), "Book - Karnataka is deleted.");
	}
	
	@Test
	public void testSearchSubject() throws Exception {
		when(businessMock.getSubject(org.mockito.Mockito.anyString(), org.mockito.Mockito.anyString()))
		.thenReturn(getSubjectData());
		assertEquals(service.getSubject("a", "title").size(), 2);
	}
	
	@Test
	public void testSearchBook() throws Exception {
		when(businessMock.getBook(org.mockito.Mockito.anyString(), org.mockito.Mockito.anyString()))
		.thenReturn(new ArrayList<Book>());
		assertEquals(service.getBook("g","title").size(), 0);
	}

	// test data
	private List<Subject> getSubjectData() {
		Subject s1 = new Subject();
		s1.setSubjectId(1001L);
		s1.setSubtitle("India");
		s1.setDurationInHours(10);
		s1.setReferences(getBookData());
		
		Subject s2 = new Subject();
		s2.setSubjectId(1002L);
		s2.setSubtitle("Cricket");
		s2.setDurationInHours(3);
		s2.setReferences(getBookData());
		
		List<Subject> subjects = new ArrayList<Subject>();
		subjects.add(s1);
		subjects.add(s2);
		return subjects;
	}

	private Set<Book> getBookData() {
		Book b1 = new Book();
		b1.setBookId(100L);
		b1.setTitle("Tamil Nadu");
		b1.setPrice(100.00);
		b1.setVolume(25);
		b1.setPublishDate(new Date());
		
		Book b2 = new Book();
		b2.setBookId(101L);
		b2.setTitle("Kerala");
		b2.setPrice(80.00);
		b2.setVolume(20);
		b2.setPublishDate(new Date());
		
		Book b3 = new Book();
		b3.setBookId(102L);
		b3.setTitle("Karnataka");
		b3.setPrice(90.00);
		b3.setVolume(24);
		b3.setPublishDate(new Date());
		
		Book b4 = new Book();
		b4.setBookId(102L);
		b4.setTitle("Andara Pradhesh");
		b4.setPrice(94.00);
		b4.setVolume(24);
		b4.setPublishDate(new Date());
		
		Set<Book> books =  new HashSet<Book>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		books.add(b4);
		
		return books;
	}
}
