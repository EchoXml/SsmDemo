package com.echo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.echo.entity.Appointment;

public interface AppointmentService {
	
	/**
     * 获取对应图书编号的借阅记录
     */
    List<Appointment> queryAppointmentsByBookId(@Param("bookId") long bookId);

}
