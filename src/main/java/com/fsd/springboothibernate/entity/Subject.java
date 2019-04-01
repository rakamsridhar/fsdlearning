package com.fsd.springboothibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class Subject {
	@Id
	private int subject_id;
	private String subtitle;
	private int duration_in_hours;
	private int book_id;
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getDuration_in_hours() {
		return duration_in_hours;
	}
	public void setDuration_in_hours(int duration_in_hours) {
		this.duration_in_hours = duration_in_hours;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	@Override
	public String toString() {
		return "Subject [subject_id=" + subject_id + ", subtitle=" + subtitle + ", duration_in_hours="
				+ duration_in_hours + ", book_id=" + book_id + "]";
	}
	
	

}
