package com.echo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.AppointmentDao;
import com.echo.entity.Appointment;
import com.echo.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;
	
	@Override
	public List<Appointment> queryAppointmentsByBookId(long bookId) {
		return appointmentDao.queryAppointmentsByBookId(bookId);
	}

}
