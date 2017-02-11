package com.echo.entity;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long bookId;
	
	private Long studentId;
	
	private Date appointTime;
	
	 // 多对一的复合属性
    private Book book;// 图书实体

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}


	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}


	public Appointment() {
		super();
	}

	public Date getAppointTime() {
		return appointTime;
	}

	public void setAppointtTime(Date appointTime) {
		this.appointTime = appointTime;
	}

	@Override
	public String toString() {
		return "Appointment [bookId=" + bookId + ", studentId=" + studentId + ", appointmentTime=" + appointTime
				+ ", book=" + book + "]";
	}
    
    


}
