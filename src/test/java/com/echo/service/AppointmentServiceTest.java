package com.echo.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.echo.BaseTest;
import com.echo.entity.Appointment;

public class AppointmentServiceTest extends BaseTest{

	@Autowired
	private AppointmentService appointmentService;
	
	@Test
	public void testQueryAllApp(){
		List<Appointment> appointments=appointmentService.queryAppointmentsByBookId(1000L);
		for (Appointment appointment : appointments) {
			System.out.println(appointment);
		}
	}
}
