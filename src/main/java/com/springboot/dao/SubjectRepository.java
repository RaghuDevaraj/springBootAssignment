package com.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

	@Query("SELECT s FROM Subject s WHERE s.subtitle LIKE %:title%")
	List<Subject> searchBySubtitle(@Param("title") String title);
	@Query("SELECT s FROM Subject s WHERE s.durationInHours <= :hours")
	List<Subject> searchByDurationInHours(@Param("hours") Integer hours);
}
