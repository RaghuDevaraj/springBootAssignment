package com.springboot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Subject {

	@Id
	@GeneratedValue
	private long subjectId;
	private String subtitle;
	private int durationInHours;
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER,targetEntity = Book.class)
	@JoinTable(name = "subject_book", 
		joinColumns = { @JoinColumn(name = "subject_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "book_id") 
	})
	private Set<Book> references = new HashSet<Book>();
	@Transient
	private String bookIds;
	/**
	 * @return the subjectId
	 */
	public long getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return the durationInHours
	 */
	public int getDurationInHours() {
		return durationInHours;
	}
	/**
	 * @param durationInHours the durationInHours to set
	 */
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	/**
	 * @return the references
	 */
	public Set<Book> getReferences() {
		return references;
	}
	/**
	 * @param references the references to set
	 */
	public void setReferences(Set<Book> references) {
		this.references = references;
	}
	/**
	 * @return the bookIds
	 */
	public String getBookIds() {
		return bookIds;
	}
	/**
	 * @param bookIds the bookIds to set
	 */
	public void setBookIds(String bookIds) {
		this.bookIds = bookIds;
	}
}
