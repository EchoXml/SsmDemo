package com.echo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.echo.dao.AppointmentDao;
import com.echo.dao.BookDao;
import com.echo.dto.AppointExcuetion;
import com.echo.entity.Appointment;
import com.echo.entity.Book;
import com.echo.enums.AppointStateEnum;
import com.echo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookDao bookDao;
	@Autowired
	private AppointmentDao appintmentDao;

	@Override
	public Book getBookById(Long id) {
		return bookDao.queryById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.queryAll(0, 999);
	}

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public AppointExcuetion appoint(Long bookId, Long studentId) {
		try {
			// 减少库存
			int update = bookDao.reduceNumber(bookId);
			if (update <= 0) {// 库存不足
				return new AppointExcuetion(bookId, AppointStateEnum.NO_NUMBER);
			} else {// 执行预约操作
				int insert = appintmentDao.insertAppointment(bookId, studentId);

				if (insert <= 0) {// 重复预约
					return new AppointExcuetion(bookId, AppointStateEnum.REPEAT_APPOINT);
				} else {// 预约成功
					Appointment appointment = appintmentDao.queryByKeyWithBook(bookId, studentId);
					return new AppointExcuetion(bookId, appointment, AppointStateEnum.SUCCESS);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new AppointExcuetion(bookId, AppointStateEnum.INNER_ERROR);
		}
	}

	@Override
	public int addNumber() {
		return bookDao.addNumber();
	}

	@Override
	public int addBook(String name, int number) {
		return bookDao.addBook(name, number);
	}

	@Override
	public int delBook(Long bookId) {
		return bookDao.delBook(bookId);
	}


}
