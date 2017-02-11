package com.echo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.echo.service.BookService;
//@Component
public class TestJob {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BookService bookService;

	public TestJob() {
		super();

	}
	@Scheduled(cron = "* * * * * ?")
	public void printInfo() {
		logger.info("定时任务开始......");
	/*	long begin = System.currentTimeMillis();
		// 执行数据库操作了哦...
		bookService.addNumber();
		logger.info("库存+1");
		long end = System.currentTimeMillis();
		logger.info("定时任务结束，共耗时：[" + (end - begin) / 1000 + "]秒");*/
	}

}
