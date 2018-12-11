package com.springboot.business;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.dao.BookRepository;
import com.springboot.dao.SubjectRepository;
import com.springboot.model.Book;
import com.springboot.model.Subject;

public class LibraryBusinessTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@InjectMocks
	private LibraryBusinessImpl business;

	// mock object
	@Mock
	private SubjectRepository subjectRepo;
	@Mock
	private BookRepository bookRepo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddSubject1() throws Exception {
		// case1 when no books found
		when(bookRepo.findAll()).thenReturn(new ArrayList<Book>());
		thrown.expectMessage(startsWith("No Books Found. Please add book(s) first"));
		business.addSubject(new Subject());
	}

	@Test
	public void testAddSubject2() throws Exception {
		// case2 when no matching bookids found
		when(bookRepo.findAll()).thenReturn(getBookData());
		thrown.expectMessage(startsWith("No valid Book ID found. Please enter valid ones..."));
		Subject subject = new Subject();
		subject.setBookIds("1,2,3,4");
		business.addSubject(subject);
	}

	@Test
	public void testAddSubject3() throws Exception {
		// case3 subject saved.
		when(bookRepo.findAll()).thenReturn(getBookData());
		when(subjectRepo.save(org.mockito.Mockito.any(Subject.class))).thenReturn(new Subject());
		Subject subject = new Subject();
		subject.setSubtitle("India");
		subject.setBookIds("100");
		assertEquals(business.addSubject(subject), "Subject - India is added.");
	}
	
	@Test
	public void testAddBook() throws Exception {
		// book saved.
		Book book = new Book();
		book.setBookId(101L);
		book.setTitle("Kerala");
		when(bookRepo.save(org.mockito.Mockito.any(Book.class))).thenReturn(book);		
		assertEquals(business.addBook(book), "Book - Kerala is added as ID : 101");
	}
	
	@Test
	public void testDeleteSubject() {
		Subject subject = new Subject();
		subject.setSubtitle("India");
		when(subjectRepo.findById(org.mockito.Mockito.anyLong())).thenReturn(Optional.of(subject));	
		doNothing().when(subjectRepo).delete(org.mockito.Mockito.any(Subject.class));
		assertEquals(business.deleteSubject("1001"), "Subject - India is deleted.");
	}
	
	@Test
	public void testDeleteBook() {
		Book book = new Book();
		book.setTitle("Kerala");
		when(bookRepo.findById(org.mockito.Mockito.anyLong())).thenReturn(Optional.of(book));	
		doNothing().when(bookRepo).delete(org.mockito.Mockito.any(Book.class));
		assertEquals(business.deleteBook("102"), "Book - Kerala is deleted.");
	}
	
	@Test
	public void testSearchSubject() {
		// case1 search by title
		List<Subject> subjects =  new ArrayList<Subject>();
		Subject subject1 = new Subject();
		subject1.setSubtitle("India");
		subjects.add(subject1);
		when(subjectRepo.searchBySubtitle(org.mockito.Mockito.anyString())).thenReturn(subjects);	
		assertEquals(business.getSubject("a", "title").size(), 1);
		
		//case2 search by duration
		Subject subject2 = new Subject();
		subject2.setSubtitle("Japan");
		subjects.add(subject1);
		when(subjectRepo.searchByDurationInHours(org.mockito.Mockito.anyInt())).thenReturn(subjects);	
		assertEquals(business.getSubject("14", "duration").size(), 2);
	}

	@Test
	public void testSearchBook() {
		// case1 search by title
		when(bookRepo.searchByTitle(org.mockito.Mockito.anyString())).thenReturn(getBookData());	
		assertEquals(business.getBook("a", "title").size(), 4);
		
		// case2 search by price
		when(bookRepo.searchByPrice(org.mockito.Mockito.anyDouble())).thenReturn(getBookData());	
		assertEquals(business.getBook("20.00", "price").size(), 4);
		
		// case3 search by volume
		when(bookRepo.searchByVolume(org.mockito.Mockito.anyInt())).thenReturn(getBookData());	
		assertEquals(business.getBook("30", "volume").size(), 4);
	}
	
	// test data
	private List<Book> getBookData() {
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

		List<Book> books = new ArrayList<Book>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		books.add(b4);

		return books;
	}
}
