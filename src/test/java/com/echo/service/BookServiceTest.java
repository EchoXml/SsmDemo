package com.echo.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.echo.BaseTest;
import com.echo.dto.AppointExcuetion;
import com.echo.entity.Book;
import com.echo.enums.RegisterStateEnum;
import com.google.gson.Gson;

public class BookServiceTest extends BaseTest {
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testBookAppoint(){
		AppointExcuetion result=bookService.appoint(1001L, 1208080808L);
		Logger logger=LoggerFactory.getLogger(BookServiceTest.class);
		System.out.println(result);
		System.out.println("-----------------------------------------");
		Book book=bookService.getBookById(1000L);
		System.out.println(book);
	}
	
	@Test
	public void testEnumJson(){
		Gson gson=new Gson();
		
		System.out.println(gson.toJson(RegisterStateEnum.SUCCESS));
	}
}
