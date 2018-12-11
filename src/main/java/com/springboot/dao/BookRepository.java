package com.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
	List<Book> searchByTitle(@Param("title") String title);
	@Query("SELECT b FROM Book b WHERE b.price <= :price")
	List<Book> searchByPrice(@Param("price") Double price);
	@Query("SELECT b FROM Book b WHERE b.volume <= :volume")
	List<Book> searchByVolume(@Param("volume") Integer volume);
}
