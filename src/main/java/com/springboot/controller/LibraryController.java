package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.model.Book;
import com.springboot.model.Subject;
import com.springboot.service.LibraryService;

@Controller
public class LibraryController {
	
	@Autowired
	private LibraryService service;

	@GetMapping("/")
	public String index(){
		return "index";		
	}
	
	@GetMapping("/subject")
	public ModelAndView loadSubject(ModelMap model){
		return new ModelAndView("subject","command", new Subject());		
	}
	
	@GetMapping("/book")
	public ModelAndView loadBook(){
		return new ModelAndView("book","command", new Book());		
	}	
	
	@PostMapping("/subject")
	public ModelAndView addSubjectDetails(@ModelAttribute("subject") Subject subject, ModelMap model){
		try {
			model.addAttribute("message",service.addSubject(subject));
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());					
		}
		return new ModelAndView("subject","command",  new Subject());		
	}
	
	@PostMapping("/book")
	public ModelAndView addBookDetails(@ModelAttribute("book") Book book, ModelMap model){
		try {
			model.addAttribute("message",service.addBook(book));
		} catch (Exception e) {
			model.addAttribute("message",e.getMessage());					
		}
		return new ModelAndView("book","command", new Book());				
	}
	
	@GetMapping("/deleteSubject")
	public String deleteSubjectDetails(String subjectId,Model model){
		model.addAttribute("message",service.deleteSubject(subjectId));	
		return "deleteResult";
	}
	
	@GetMapping("/deleteBook")
	public String deleteBookDetails(String bookId, Model model){		
		model.addAttribute("message", service.deleteBook(bookId));
		return "deleteResult";
	}
	
	@GetMapping("/searchSubject")	
	public String getSubjects(@RequestParam String searchParam,@RequestParam String searchType, Model model){
		List<Subject> subjectList = service.getSubject(searchParam, searchType);
		model.addAttribute("subjects",subjectList);
		return "subjectResult";
	}
	
	@GetMapping("/searchBook")	
	public String getBooks(@RequestParam String searchParam,@RequestParam String searchType, Model model){
		List<Book> bookList = service.getBook(searchParam, searchType);
		model.addAttribute("books",bookList);
		return "bookResult";
	}
}
