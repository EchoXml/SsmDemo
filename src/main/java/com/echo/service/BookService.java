package com.echo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.echo.dto.AppointExcuetion;
import com.echo.entity.Appointment;
import com.echo.entity.Book;

/**
 * 图书业务接口
 * 站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 * @author Echo
 *
 */
public interface BookService {
	
	/**
	 * 根据编号查询一本图书
	 * @param id
	 * @return
	 */
	Book getBookById(Long id);
	
	/**
	 * 查询所有图书
	 * @return
	 */
	List<Book> getAllBooks();
	
	/**
	 * 预约图书
	 * @param bookId
	 * @param studentId
	 * @return 返回预约执行结果dto
	 */
	AppointExcuetion appoint(Long bookId,Long studentId);
	
	/**
	 * 增加馆藏
	 * @return
	 */
	int addNumber();
	
	 /**
     * 新增图书信息
     * @return
     */
    int addBook(String name,int number);
    
    /**
     * 删除图书信息
     * @param bookId 图书ID
     * @return
     */
    int delBook(@Param("bookId") Long bookId);
    
  


}
