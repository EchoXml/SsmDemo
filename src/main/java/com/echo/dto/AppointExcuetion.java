package com.echo.dto;

import com.echo.entity.Appointment;
import com.echo.enums.AppointStateEnum;

/**
 * 封装预约执行后状态
 * @author Echo
 *
 */
public class AppointExcuetion {
	
	//图书ID
	private Long bookId;

	//秒杀预约结果状态
	private Integer state;
	
	//状态标识
	private String stateInfo;
	
	//预约成功对象
	private Appointment appointment;

	public AppointExcuetion() {
		super();
	}

	//预约失败的构造器
	public AppointExcuetion(Long bookId, AppointStateEnum appointStateEnum) {
		super();
		this.bookId = bookId;
		this.state=appointStateEnum.getState();
		this.stateInfo=appointStateEnum.getStateInfo();
	}

	//预约成功的构造器
	public AppointExcuetion(Long bookId, Appointment appointment, AppointStateEnum appointStateEnum) {
		super();
		this.bookId = bookId;
		this.state=appointStateEnum.getState();
		this.stateInfo=appointStateEnum.getStateInfo();
		this.appointment = appointment;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "AppointExcuetion [bookId=" + bookId + ", state=" + state + ", stateInfo=" + stateInfo + ", appointment="
				+ appointment + "]";
	}
	
	
	
	
	
	
	
}
