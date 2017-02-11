package com.echo.enums;

/**
 * 注册常量数据字典
 * @author Echo
 *
 */
public enum RegisterStateEnum {
	
	SUCCESS(1,"注册成功"),REPEAT_REGISTER(-1,"重复注册"),ERROR(0,"系统异常");
	
	private int state;

    private String stateInfo;
    
    private RegisterStateEnum(int state,String stateInfo){
    	this.setState(state);
    	this.setStateInfo(stateInfo);
    	
    }

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

    
}
