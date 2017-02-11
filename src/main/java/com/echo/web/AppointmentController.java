package com.echo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.echo.dto.Result;
import com.echo.entity.Appointment;
import com.echo.service.AppointmentService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping(value="/ajax/getAppointsById",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Result<List<Appointment>> getAppointmentsByBookId(Long bookId){
		List<Appointment> appointments=appointmentService.queryAppointmentsByBookId(bookId);
		if (appointments!=null&&!appointments.isEmpty()) {
			return new Result<List<Appointment>>(true, appointments);
		}else{
			return new Result<>(false, "未查询到相关数据记录");
		}
	}

	
}
